package com.nestor.springboot.app;

//import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nestor.springboot.app.auth.handler.LoginSuccessHandler;
import com.nestor.springboot.app.models.service.JpaUserDetailsService;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

  
    @Autowired
    private LoginSuccessHandler successHandler;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
//    @Autowired 
//    private DataSource dataSource;
//    
    @Autowired
    private JpaUserDetailsService userDetailsService;
    
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/css/**", "/js/**", "/images/**", "/listar", "/locale").permitAll()
                // .antMatchers("/ver/**").hasAnyRole("USER")
                // .antMatchers("/uploads/**").hasAnyRole("USER")
                // .antMatchers("/form/**").hasAnyRole("ADMIN")
                // .antMatchers("/eliminar/**").hasAnyRole("ADMIN")
                // .antMatchers("/factura/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
            .and()
                .formLogin()
                    .successHandler(successHandler)
                    .loginPage("/login")
                    .permitAll()
            .and()
                .logout()
                    .logoutUrl("/logout") // URL de logout
                    .logoutSuccessUrl("/") // Redirige al inicio después de cerrar sesión
                    .permitAll() // Permitir a todos acceder a la URL de logout
            .and()
                .exceptionHandling().accessDeniedPage("/error_403");
    }


	@Autowired
    public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception {
		
//		builder.jdbcAuthentication()
//		.dataSource(dataSource)
//		.passwordEncoder(passwordEncoder)
//		.usersByUsernameQuery("select username, password, enabled from users where username=?")
//		.authoritiesByUsernameQuery("select u.username, a.authority from authorities a inner join users u on (a.user_id=u.id) where u.username=?");
		
		/*
        PasswordEncoder encoder = this.passwordEncoder;
        UserBuilder users = User.builder().passwordEncoder(encoder::encode);

        builder.inMemoryAuthentication()
                .withUser(users.username("admin").password("12345").roles("ADMIN", "USER"))
                .withUser(users.username("nestor").password("12345").roles("USER"));
                */
		
		builder.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder);
	}	
    @Autowired
    public void userDetailsService(AuthenticationManagerBuilder build) throws Exception {
       build.userDetailsService(userDetailsService)
       .passwordEncoder(passwordEncoder);
    
		}
	}
  /*
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/css/**", "/js/**", "/images/**", "/listar").permitAll()
                // .antMatchers("/ver/**").hasAnyRole("USER")
                // .antMatchers("/uploads/**").hasAnyRole("USER")
                // .antMatchers("/form/**").hasAnyRole("ADMIN")
                // .antMatchers("/eliminar/**").hasAnyRole("ADMIN")
                // .antMatchers("/factura/**").hasAnyRole("ADMIN")
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

        return http.build();
    }
}

*/