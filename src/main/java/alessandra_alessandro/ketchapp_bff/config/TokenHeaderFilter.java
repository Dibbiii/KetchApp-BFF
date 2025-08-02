package alessandra_alessandro.ketchapp_bff.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class TokenHeaderFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {
        String path = request.getRequestURI();
        if (!path.startsWith("/api/auth/login")) {
            String token = null;
            String authorizationHeader = request.getHeader("Authorization");
            if (
                authorizationHeader != null &&
                authorizationHeader.startsWith("Bearer ")
            ) {
                token = authorizationHeader.substring("Bearer ".length());
            }

            if (token != null) {
                TokenHolder.setToken(token);
            } else {
                TokenHolder.clear();
            }
        } else {
            TokenHolder.clear();
        }
        try {
            filterChain.doFilter(request, response);
        } finally {
            TokenHolder.clear();
        }
    }
}
