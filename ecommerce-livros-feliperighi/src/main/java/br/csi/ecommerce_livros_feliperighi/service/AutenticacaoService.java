package br.csi.ecommerce_livros_feliperighi.service;

import br.csi.ecommerce_livros_feliperighi.model.Cliente.Cliente;
import br.csi.ecommerce_livros_feliperighi.model.Cliente.ClienteRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    private final ClienteRepository repository;

    public AutenticacaoService(ClienteRepository repository){
        this.repository = repository;
    }

    //Método destinado a criação de um UserDetail que será inserido no contexto do Spring

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        Cliente cliente = this.repository.findByEmail(email);
        if(cliente == null){
            throw new UsernameNotFoundException("Email ou senha incorretos");
        } else {
            UserDetails client = User.withUsername(cliente.getEmail()).password(cliente.getSenha()).authorities(cliente.getPermissao()).build();
            return client;
        }
    }

}
