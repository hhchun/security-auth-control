package com.cmzn.authcontrol.config;

import com.cmzn.authcontrol.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.UrlAuthorizationConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
public class AuthControlWebSecurityConfigurer {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   @Qualifier("authenticationFilter") Filter authenticationFilter,
                                                   FilterInvocationSecurityMetadataSource ms) throws Exception {
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler()).authenticationEntryPoint(authenticationEntryPoint());
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.formLogin().and().csrf().disable();

        ApplicationContext applicationContext = http.getSharedObject(ApplicationContext.class);
        http.apply(new UrlAuthorizationConfigurer<>(applicationContext))
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setSecurityMetadataSource(ms);
                        object.setRejectPublicInvocations(true);
                        return object;
                    }
                });
        http.addFilter(authenticationFilter);
        return http.build();
    }

    @Bean("authenticationFilter")
    public Filter authenticationFilter(AuthenticationConfiguration configuration, AuthenticationFactory authenticationFactory) throws Exception {
        return new AuthControlAuthenticationFilter(configuration.getAuthenticationManager(), authenticationFactory);
    }


    @Bean
    public AuthenticationFactory authenticationFactory(AbstractVisitorAuthProvider visitorAuthProvider) {
        return new DefaultAuthenticationFactory(visitorAuthProvider);
    }

    @Bean
    public FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource(AbstractTargetAuthProvider targetAuthProvider) {
        return new AuthControlFilterInvocationSecurityMetadataSource(targetAuthProvider);
    }

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("ROLE_");
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthControlAuthenticationEntryPoint();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new AuthControlAccessDeniedHandler();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) {
        builder.authenticationProvider(new AuthControlAuthenticationProvider());
    }
}
