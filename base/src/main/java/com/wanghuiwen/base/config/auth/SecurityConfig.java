package com.wanghuiwen.base.config.auth;

import com.wanghuiwen.base.config.auth.service.DetailsService;
import com.wanghuiwen.base.config.auth.handler.*;
import com.wanghuiwen.base.model.SysWhitelist;
import com.wanghuiwen.base.service.ApiService;
import com.wanghuiwen.base.service.SysWhitelistService;
import com.wanghuiwen.base.service.UserService;
import com.wanghuiwen.core.response.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.Resource;
import java.util.List;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private ApiService apiService;
    @Resource
    private SysWhitelistService sysWhitelistService;
    @Resource
    private UserService userService;
    @Resource
    private ResultGenerator resultGenerator;
    @Resource
    private   GoAuthenticationFailureHandler goAuthenticationFailureHandler;
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOriginPattern("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;


    @Autowired
    private DetailsService detailsServic;


    /**
     * 认证
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.detailsServic).passwordEncoder(passwordEncoder());
    }


    // 装载BCrypt密码编码器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        List<SysWhitelist> whitelists=sysWhitelistService.findAll();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS,
                        whitelists.stream().map(SysWhitelist::getUrl).toArray(String[]::new)
                        ).permitAll()
                .withObjectPostProcessor(new MyObjectPostProcessor())
                .anyRequest().authenticated()
                .and().exceptionHandling()
                .authenticationEntryPoint(new GoAuthenticationEntryPoint(resultGenerator))
                .accessDeniedHandler(new GoAccessDeniedHandler(resultGenerator))
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(new GoAuthenticationSuccessHandler(userService))
                .failureHandler(goAuthenticationFailureHandler)
                .and().logout().logoutUrl("/logout")
                .logoutSuccessHandler(new GoLogoutSuccessHandler(resultGenerator))
                .and().cors().and().csrf().disable();
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    private class MyObjectPostProcessor implements ObjectPostProcessor<FilterSecurityInterceptor> {
        @Override
        public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
            fsi.setSecurityMetadataSource(new PowerSource(apiService,sysWhitelistService));
            fsi.setAccessDecisionManager(new AccessManager());
            return fsi;
        }

    }

    @Override
    public void configure(WebSecurity web) throws Exception {

        super.configure(web);
    }

    public static void main(String[] args) {
        System.out.println( new BCryptPasswordEncoder().encode("123456"));
    }
}
