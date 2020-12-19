package org.yokekhei.fsd.p3.ui.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yokekhei.fsd.p3.ui.View;

@Configuration
public class FilterConfig {

	@Bean
	public FilterRegistrationBean<AdminAuthenticationFilter> adminAuthenticationFilter() {
        FilterRegistrationBean<AdminAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AdminAuthenticationFilter());
        registrationBean.addUrlPatterns("/" + View.C_ADMIN_CATEGORY + "/*",
        		"/" + View.C_ADMIN_PASSWORD + "/*",
        		"/" + View.C_ADMIN_PRODUCT + "/*",
        		"/" + View.C_ADMIN_USER_REPORT + "/*",
        		"/" + View.C_ADMIN_PURCHASE_REPORT + "/*",
        		"/" + View.C_ADMIN_PURCHASE_REPORT_VIEW + "/*",
        		"/" + View.C_ADMIN_LOGOUT + "/*");

        return registrationBean;
    }
	
	@Bean
	public FilterRegistrationBean<UserAuthenticationFilter> userAuthenticationFilter() {
        FilterRegistrationBean<UserAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new UserAuthenticationFilter());
        registrationBean.addUrlPatterns("/" + View.C_USER_CATALOG + "/*",
        		"/" + View.C_USER_CATALOG_PRODUCT + "/*",
        		"/" + View.C_USER_BAG + "/*",
        		"/" + View.C_USER_CHECKOUT + "/*",
        		"/" + View.C_USER_PAYMENT + "/*",
        		"/" + View.C_USER_LOGOUT + "/*");

        return registrationBean;
    }
	
}
