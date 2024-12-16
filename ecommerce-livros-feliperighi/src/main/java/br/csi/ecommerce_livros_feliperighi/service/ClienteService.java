package br.csi.ecommerce_livros_feliperighi.service;

import br.csi.ecommerce_livros_feliperighi.model.Cliente.Cliente;
import br.csi.ecommerce_livros_feliperighi.model.Cliente.ClienteRepository;
import br.csi.ecommerce_livros_feliperighi.model.Cliente.DadosCliente;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {
    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    // CRIAÇÃO/ATUALIZAÇÃO (Long)
    // Método que cria ou atualiza um cliente, dependendo se o ID é novo ou já existe.
    public void salvar(Cliente cliente){
        this.repository.save(cliente);
    }

    // LISTAGEM - (Long)
    // Método que retorna todos os clientes cadastrados no banco de dados.
    // TESTE PARA SPRING SECURITY
    public List<Cliente> listar(){
        return this.repository.findAll();
    }

    // MÉTODOS COM ID

    // CONSULTA - ID (Long)
    // Método que busca um cliente pelo ID (Long). Retorna o cliente ou lança uma exceção se não encontrar.
    public Cliente getCliente(Long id){
        return this.repository.findById(id).get();
    }

    // EXCLUSÃO - ID (Long)
    // Método que deleta um cliente baseado no ID (Long). O ID é passado para localizar o cliente e excluí-lo.
    public void excluir(Long id){
        this.repository.deleteById(id);
    }

    // ATUALIZAÇÃO - ID (Long)
    // Método que atualiza os dados de um cliente com base no ID (Long). Ele pega o cliente existente e atualiza seus dados.
    public void atualizar(Cliente cliente){
        Cliente c = this.repository.getReferenceById(cliente.getIdcliente());
        c.setNome(cliente.getNome());
        c.setEmail(cliente.getEmail());
        c.setSenha(cliente.getSenha());
        this.repository.save(c);
    }

    // MÉTODOS COM UUID

    // CONSULTA - UUID (UUID)
    // Método que busca um cliente pelo UUID. O UUID é passado como string, então convertemos para o formato UUID.
    public Cliente getClienteUUID(String uuid){
        UUID uuidformatado = UUID.fromString(uuid);
        return this.repository.findClienteByUuid(uuidformatado);
    }

    // EXCLUSÃO - UUID (UUID)
    // Método que deleta um cliente com base no UUID. O UUID é convertido de String para o formato UUID para realizar a exclusão.
    /*public void excluirUUID(String uuid){
        this.repository.deleteClienteByUuid(UUID.fromString(uuid));
    }*/

    public void excluirUUID(String uuid){
    Cliente cliente = this.repository.findClienteByUuid(UUID.fromString(uuid));
    if (cliente != null) {
        this.repository.deleteClienteByUuid(UUID.fromString(uuid));
    } else {
        throw new RuntimeException("Cliente não encontrado");  // Lança uma exceção caso o cliente não exista
    }
}


    // CRIAÇÃO/ATUALIZAÇÃO - UUID (UUID)
    // Método que atualiza os dados de um cliente baseado no UUID. O UUID é único e é usado para identificar o cliente de forma global.
    public void atualizarUUID(Cliente cliente){
        Cliente c = this.repository.findClienteByUuid(cliente.getUuid());
        c.setNome(cliente.getNome());
        c.setEmail(cliente.getEmail());
        c.setSenha(cliente.getSenha());
        this.repository.save(c);
    }

    //Spring Security
    public void cadastrar(Cliente cliente){
        //Criptografia a senha do usuário ANTES desse ser salvo
        cliente.setSenha(new BCryptPasswordEncoder().encode(cliente.getSenha()));
        this.repository.save(cliente);
    }

    public DadosCliente findCliente(Long id){
        Cliente cliente = this.repository.getReferenceById(id);
        return new DadosCliente(cliente);
    }

    public List<DadosCliente> findAllClientes(){
        return this.repository.findAll().stream().map(DadosCliente::new).toList();
    }
}
