package br.com.tkn.gestao_vagas.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.tkn.gestao_vagas.providers.JWTProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	private JWTProvider jwtProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		SecurityContextHolder.getContext().setAuthentication(null);
		String header = request.getHeader("Authorization");

		if (header != null) {
			var subjectToken = this.jwtProvider.validateToken(header);

			if (subjectToken == null) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return;
			}

			request.setAttribute("company_id", subjectToken);
			// Cria uma autenticação somente com o Subject e sem roles
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(subjectToken, null,
					Collections.emptyList());

			// Injeta no contexto de segurança
			SecurityContextHolder.getContext().setAuthentication(auth);
		}

		filterChain.doFilter(request, response);
	}

}
