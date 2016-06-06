package fr.pizzeria.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SpringSecurityInit extends AbstractSecurityWebApplicationInitializer {
	
	public SpringSecurityInit() {
        super(SpringSecurityConfig.class);
    }
}
