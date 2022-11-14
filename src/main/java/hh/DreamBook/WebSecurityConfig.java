package hh.DreamBook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
// This Config class does not extend ...Adapter class anymore.
public class WebSecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		// these endpoints don't need authentication
		// Enable css files for styles
		http.authorizeRequests().antMatchers("/css/**", "/index", "/typelist", "/keywordlist", "/dreams/**").permitAll()
				.anyRequest().authenticated()

				.and()
				// when login is successful direct to /index endpoint
				// when logout is successful direct to /login endpoint
				.formLogin().loginPage("/login").defaultSuccessUrl("/index", true).permitAll()

				.and().logout().logoutSuccessUrl("/login?logout").permitAll().and().httpBasic();
		return http.build();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
}
