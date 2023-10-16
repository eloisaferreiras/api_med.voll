package med.voll.api.infra.security;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component //classe/componente generico, apenas para o string rodar o projeto
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJMT = recuperarToken(request);
        var subject = tokenService.getSubject(tokenJMT);


        filterChain.doFilter(request, response); // para chamar o proximo filtro
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader ==  null) {
            throw new RuntimeException("Token JMT não enviado no cabeçalho Authorization ");

        }

        return  authorizationHeader.replace("Bearer ", "");
    }
}
