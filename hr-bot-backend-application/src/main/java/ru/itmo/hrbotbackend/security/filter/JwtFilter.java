package ru.itmo.hrbotbackend.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import ru.itmo.hrbotbackend.security.util.JwtTokenUtil;
import ru.itmo.hrbotbackend.service.user.UserService;

import java.io.IOException;
import java.util.Locale;

/**
 * Фильтр JWT токенов.
 */
@Component
@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {
	private static final String AUTHORIZATION = "Authorization";

	private final UserService userService;
	private final JwtTokenUtil jwtTokenUtil;

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
						 FilterChain filterChain) throws IOException, ServletException {
		var token = getTokenFromRequest((HttpServletRequest) servletRequest);
		try {
			if (token != null && jwtTokenUtil.validateToken(token)) {
				var userLogin = jwtTokenUtil.getLoginFromToken(token);
				var account = userService.loadUserByUsername(userLogin);
				var auth = new UsernamePasswordAuthenticationToken(account, null, account.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
			filterChain.doFilter(servletRequest, servletResponse);
		} catch (Exception e) {
			var httpResponse = (HttpServletResponse) servletResponse;
			httpResponse.setStatus(HttpStatus.FORBIDDEN.value());
			httpResponse.getWriter().write("{ \"error\": \"" + e.getMessage() + "\" }");
			httpResponse.setContentType("application/json");
		}

	}

	private String getTokenFromRequest(HttpServletRequest request) {
		return request.getHeader(AUTHORIZATION);
	}
}
