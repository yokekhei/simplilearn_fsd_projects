package org.yokekhei.fsd.p3.ui.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yokekhei.fsd.p3.ui.View;

@Configuration
public class FilterConfig {

	@Bean
	public FilterRegistrationBean<AuthenticationFilter> authenticationFilter() {
        FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthenticationFilter());
        registrationBean.addUrlPatterns("/" + View.C_ADMIN_CATEGORY + "/*");

        return registrationBean;

    }
	
}
