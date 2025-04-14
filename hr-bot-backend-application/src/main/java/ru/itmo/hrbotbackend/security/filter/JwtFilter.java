package ru.itmo.hrbotbackend.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import ru.itmo.hrbotbackend.security.util.JwtTokenUtil;
import ru.itmo.hrbotbackend.service.user.UserService;

import java.io.IOException;

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
		String token = getTokenFromRequest((HttpServletRequest) servletRequest);
		if (token != null && jwtTokenUtil.validateToken(token)) {
			String userLogin = jwtTokenUtil.getLoginFromToken(token);
			UserDetails account = userService.loadUserByUsername(userLogin);
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(account, null, account.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}

	private String getTokenFromRequest(HttpServletRequest request) {
		return request.getHeader(AUTHORIZATION);
	}
}
