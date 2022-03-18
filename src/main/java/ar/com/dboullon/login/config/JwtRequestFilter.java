package ar.com.dboullon.login.config;

import ar.com.dboullon.login.model.Usuario;
import ar.com.dboullon.login.service.impl.UserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsServiceImpl jwtUserDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader("Authorization");

		String username = null;
		String jwtToken = null;

		// Obtenemos el token
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				logger.error("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				logger.warn("JWT Token has expired");
			}
		} else {
			logger.warn("JWT Token does not begin with Bearer String");
		}

		// Validamos el token

		System.out.println("0");

		System.out.println("username: "  + username);
		System.out.println("getAuthentication: "  + SecurityContextHolder.getContext().getAuthentication());
		Usuario userDetails;
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			System.out.println("1");

			try {

				userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

				System.out.println("2");
				if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

					System.out.println("3");
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());

					System.out.println("4");
					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));


					System.out.println("5");
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					if(usernamePasswordAuthenticationToken.isAuthenticated()) {
						String token = jwtTokenUtil.generateToken(userDetails);
						response.addHeader("Refresh-Token", token);
						System.out.println("6");
					}

				}
				System.out.println("7");
			} catch (UsernameNotFoundException e) {
				logger.warn("Unable to get username from Token");
			}
		}

		chain.doFilter(request, response);
	}

}