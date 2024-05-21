package com.nestor.springboot.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nestor.springboot.app.auth.handler.LoginSuccessHandler;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private LoginSuccessHandler successHandler;
    
    @Override
	protected void configure(HttpSecurity http) throws Exception {
    	
	http.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/images/**", "/listar").permitAll()
//	.antMatchers("/ver/**").hasAnyRole("USER")
//	.antMatchers("/uploads/**").hasAnyRole("USER")
//	.antMatchers("/form/**").hasAnyRole("ADMIN")
//	.antMatchers("/eliminar/**").hasAnyRole("ADMIN")
//	.antMatchers("/factura/**").hasAnyRole("ADMIN")
	.anyRequest().authenticated()
	.and()
		.formLogin()
			.successHandler(successHandler)
			.loginPage("/login")
		.permitAll()
	.and()
	.logout().permitAll()
	.and()
	.exceptionHandling().accessDeniedPage("/error_403");
	}

	@Autowired
    public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception {
        PasswordEncoder encoder = passwordEncoder();
        UserBuilder users = User.builder().passwordEncoder(encoder::encode);

        builder.inMemoryAuthentication()
                .withUser(users.username("admin").password("12345").roles("ADMIN", "USER"))
                .withUser(users.username("nestor").password("12345").roles("USER"));
    }


}