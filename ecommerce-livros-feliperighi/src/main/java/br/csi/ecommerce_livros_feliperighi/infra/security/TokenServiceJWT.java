package br.csi.ecommerce_livros_feliperighi.infra.security;

import br.csi.ecommerce_livros_feliperighi.model.Cliente.Cliente;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenServiceJWT {

    public String gerarToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256("POO2");
            return JWT.create()
                    .withIssuer("API Ecommerce de Livros")
                    .withSubject(user.getUsername())
                    .withClaim("ROLE", user.getAuthorities().stream().toList().get(0).toString())
                    .withExpiresAt(dataExpiracao())
                    .sign(algorithm);
        } catch (JWTCreationException e){
            throw new RuntimeException("Erro ao gerar o token JWT", e);
        }
    }

    private Instant dataExpiracao(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubject(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256("POO2");
            return JWT.require(algorithm)
                    .withIssuer("API Ecommerce de Livros")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e){
            throw new RuntimeException("Token inválido ou expirado");
        }
    }
}
