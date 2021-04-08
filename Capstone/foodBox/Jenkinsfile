pipeline {
    agent none
    
    environment {
        baseImageName = "foodbox"
        apiImageName = "yokekhei/${baseImageName}-api"
        appImageName = "yokekhei/${baseImageName}-app"
        registryCredential = 'dockerhub_id'
        apiDockerImage = ''
        appDockerImage = ''
    }
    
    stages {
        stage('Setup') {
            agent any
            steps {
                sh 'cd Capstone/foodBox/bin/docker/db && ./run.sh'
                sh 'cp Capstone/foodBox/db/startup.sql Capstone/foodBox/aws_ec2/docker'
                sh 'cp $HOME/env/api/.env Capstone/foodBox/aws_ec2/docker/env'
                sh 'cp $HOME/env/api/.env Capstone/foodBox/api'
                sh 'cp $HOME/env/app/.env Capstone/foodBox/app'
                sh 'cd Capstone/foodBox/app && export $(cat ./.env | xargs) && envsubst < src/assets/env.template.js > src/assets/env.js'
            }
        }
        
        stage('Build & Test API') {
            agent {
                docker {
                    image 'maven:3.6.3-openjdk-8'
                    args '-v $HOME/.m2:/root/.m2 --net=host'
                }
            }
            steps {
                sh 'cd Capstone/foodBox/api && export $(cat ./.env | xargs) && mvn -Dmaven.test.failure.ignore=true clean compile package'
            }
            post {
                success {
                    junit 'Capstone/foodBox/api/target/surefire-reports/TEST-*.xml'
                }
            }
        }
        
        stage('Build APP') {
            agent {
                docker {
                    image 'node:14.15.5-alpine'
                }
            }
            environment {
                // override default cache directory (~/.npm)
                NPM_CONFIG_CACHE = "${WORKSPACE}/.npm"
            }
            steps {
                sh 'cd Capstone/foodBox/app && npm install && npm run build-prod'
            }
        }
        
        stage('Build Docker Image') {
            agent any
            steps {
                sh 'cd Capstone/foodBox/bin/docker/db && ./stop.sh'
                script {
                    apiDockerImage = docker.build("${apiImageName}:${BUILD_NUMBER}", "-f Capstone/foodBox/api/JenkinsDockerfile .")
                    appDockerImage = docker.build("${appImageName}:${BUILD_NUMBER}", "-f Capstone/foodBox/app/JenkinsDockerfile .")
                    apiDockerImage.tag('latest')
                    appDockerImage.tag('latest')
                }
            }
        }
        
        stage('Selenium Test') {
            agent any
            steps {
                sh 'cd Capstone/foodBox/selenium && export $(cat $HOME/env/api/.env | xargs) && docker-compose up -d'
                sh 'chmod +x Capstone/foodBox/selenium/bin/chromedriver'
                sh 'chmod +x Capstone/foodBox/selenium/bin/geckodriver'
                sh 'sleep 15'
                sh 'cd Capstone/foodBox/selenium && mvn clean test'
            }
            post {
                success {
                    step([$class: 'Publisher', reportFilenamePattern: 'Capstone/foodBox/selenium/target/surefire-reports/testng-results.xml'])
                }
            }
        }
		
        stage('Publish Docker Image') {
            agent any
            steps {
                script {
                    docker.withRegistry('', registryCredential) {
                        apiDockerImage.push("${BUILD_NUMBER}")
                        apiDockerImage.push('latest')
                        appDockerImage.push("${BUILD_NUMBER}")
                        appDockerImage.push('latest')
                    }
                }
            }
        }

        stage('Deploy to AWS EC2 Instance') {
            agent any
            steps([$class: 'BapSshPromotionPublisherPlugin']) {
                sshPublisher(
                    continueOnError: false, failOnError: true,
                    publishers: [
                        sshPublisherDesc(
                            configName: "web-app-server",
                            verbose: true,
                            transfers: [
                                sshTransfer(sourceFiles: "Capstone/foodBox/aws_ec2/docker/*"),
                                sshTransfer(execCommand: "cd Capstone/foodBox/aws_ec2/docker/;chmod 755 start-*.sh;./start-db.sh;./start-api.sh ${BUILD_NUMBER};./start-app.sh ${BUILD_NUMBER};")
                            ]
                        )
                    ]
                )
            }
        }
        
        stage('Clean Up') {
            agent any
            steps {
                sh 'cd Capstone/foodBox && docker-compose down'
                sh "docker rmi ${appImageName}:${BUILD_NUMBER}"
                sh "docker rmi ${appImageName}:latest"
                sh "docker rmi ${apiImageName}:${BUILD_NUMBER}"
                sh "docker rmi ${apiImageName}:latest"
                sh 'cd Capstone/foodBox/bin/docker/db && ./stop.sh'
            }
        }
    }
}
