package ru.itmo.hrbotbackend.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.itmo.hrbotbackend.security.filter.JwtFilter;

import java.util.Map;

/**
 * Конфиг безопасности.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final Map<String, String[]> patternMap = Map.of(
			"USER_NDA", new String[]{
					"/content/get",
					"/faq/create",
					"/faq/getAllMy"
			},
			"USER_NOT_NDA", new String[]{
					"/content/get",
					"/faq/create",
					"/faq/getAllMy"
			},
			"ADMIN", new String[]{
					"/content/get",
					"/content/addInstructionToTheme",
					"/position/getAll",
					"/position/create",
					"/position/update",
					"/position/delete",
					"/theme/create",
					"/theme/update",
					"/theme/deleteById",
					"/theme/deleteByParentId",
					"/user/createOrUpdate",
					"/user/block",
					"/faq/create",
					"/faq/getAllMy",
					"/faq/getForAnswer",
					"/faq/answer"
			}
	);

	private final JwtFilter jwtFilter;


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.authorizeRequests()
				.requestMatchers(patternMap.get("ADMIN")).hasRole("ADMIN")
				.requestMatchers(patternMap.get("USER_NDA")).hasRole("USER_NDA")
				.requestMatchers(patternMap.get("USER_NOT_NDA")).hasRole("USER_NOT_NDA")
				.requestMatchers("/login").permitAll()
				.and()
				.csrf(AbstractHttpConfigurer::disable)
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
}
