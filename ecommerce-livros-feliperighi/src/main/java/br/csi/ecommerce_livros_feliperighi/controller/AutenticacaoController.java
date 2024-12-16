package br.csi.ecommerce_livros_feliperighi.controller;

import br.csi.ecommerce_livros_feliperighi.infra.security.TokenServiceJWT;
import br.csi.ecommerce_livros_feliperighi.model.Cliente.Cliente;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    private final AuthenticationManager manager;
    private final TokenServiceJWT tokenServiceJWT;

    public AutenticacaoController(AuthenticationManager manager, TokenServiceJWT tokenServiceJWT){
        this.manager = manager;
        this.tokenServiceJWT = tokenServiceJWT;
    }

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados){
        try {
            Authentication autenticado = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
            Authentication at = manager.authenticate(autenticado);

            User user = (User) at.getPrincipal();
            String token = this.tokenServiceJWT.gerarToken(user);

            return ResponseEntity.ok().body(new DadosTokenJWT(token));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private record DadosTokenJWT(String token) {}
    private record DadosAutenticacao(String email, String senha){}
}
