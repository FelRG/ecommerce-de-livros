package br.csi.ecommerce_livros_feliperighi.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AutenticaoFilter extends OncePerRequestFilter {
    @Autowired
    private TokenServiceJWT tokenServiceJWT;
    @Autowired
    private UserDetailsService autenticacaoService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws
            ServletException, IOException {
        System.out.println("Filtro para autenticação e autorização");

        String tokenJWT = recuperarToken(request);
        System.out.println("tokenJWT: "+tokenJWT);
        //Se não existir token na requisição o SecurityConfig deverá bloquear
        if(tokenJWT != null){
            String subject = this.tokenServiceJWT.getSubject(tokenJWT);
            System.out.println("Login da req. "+subject);

            UserDetails userDetails = this.autenticacaoService.loadUserByUsername(subject);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if(token != null){
            return token.replace("Bearer", "").trim();
        }
        return null;
    }
}
