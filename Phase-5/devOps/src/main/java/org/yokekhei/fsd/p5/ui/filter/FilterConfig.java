package org.yokekhei.fsd.p5.ui.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yokekhei.fsd.p5.ui.View;

@Configuration
public class FilterConfig {

	@Bean
	public FilterRegistrationBean<UserAuthenticationFilter> userAuthenticationFilter() {
		FilterRegistrationBean<UserAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new UserAuthenticationFilter());
		registrationBean.addUrlPatterns("/" + View.C_USER_COURSES + "/*");

		return registrationBean;
	}

	@Bean
	public FilterRegistrationBean<AdminAuthenticationFilter> adminAuthenticationFilter() {
		FilterRegistrationBean<AdminAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new AdminAuthenticationFilter());
		registrationBean.addUrlPatterns("/" + View.C_ADMIN_COURSES + "/*",
				"/" + View.C_ADMIN_LOGOUT + "/*");

		return registrationBean;
	}

}
