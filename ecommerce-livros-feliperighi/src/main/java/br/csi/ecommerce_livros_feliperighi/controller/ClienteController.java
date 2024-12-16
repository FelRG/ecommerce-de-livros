package br.csi.ecommerce_livros_feliperighi.controller;

import br.csi.ecommerce_livros_feliperighi.model.Cliente.Cliente;
import br.csi.ecommerce_livros_feliperighi.model.Cliente.DadosCliente;
import br.csi.ecommerce_livros_feliperighi.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cliente")
@Tag(name = "Clientes", description = "Path relacionado a operações de clientes")
public class ClienteController {

    private final ClienteService service;

    // Construtor que injeta o serviço de cliente para ser usado nos métodos
    public ClienteController(ClienteService service){
        this.service = service;
    }

    /**
     * Método que lista todos os clientes cadastrados no sistema.
     *
     * @return Lista de clientes
     */
    // http://localhost:8080/ecommerce-livros-feliperighi/cliente/listar
    @GetMapping("/listar")
    @Operation(summary = "Listar todos os clientes", description = "Retorna uma lista com todos os clientes cadastrados.")
    public List<Cliente> listar(){
        return this.service.listar();
    }

    /**
     * Método que busca um cliente específico pelo ID.
     *
     * @param id ID do cliente a ser recuperado
     * @return Cliente com o ID especificado
     */
    // http://localhost:8080/ecommerce-livros-feliperighi/cliente/detalhes/1
    @GetMapping("/detalhes/{id}") // mudado para não interferência
    @Operation(summary = "Buscar cliente por ID", description = "Busca um cliente pelo seu ID.")
    public Cliente cliente(@PathVariable Long id){
        return this.service.getCliente(id);
    }

    //UUID

    /**
     * Método que busca um cliente específico pelo UUID.
     *
     * @param uuid UUID do cliente a ser recuperado
     * @return Cliente com o UUID especificado
     */
    // http://localhost:8080/ecommerce-livros-feliperighi/cliente/uuid/...
    @GetMapping("/uuid/{uuid}")
    @Operation(summary = "Buscar cliente por UUID", description = "Busca um cliente pelo seu UUID.")
    public Cliente cliente(@PathVariable String uuid){
        return this.service.getClienteUUID(uuid);
    }

    /**
     * Método que atualiza um cliente utilizando seu UUID.
     *
     * @param cliente Objeto Cliente com os dados a serem atualizados
     */
    // http://localhost:8080/ecommerce-livros-feliperighi/cliente/uuid/
    @PutMapping("/uuid")
    @Operation(summary = "Atualizar cliente por UUID", description = "Atualiza as informações de um cliente com base no seu UUID.")
    public void atualizarUUID(@RequestBody Cliente cliente){
        this.service.atualizarUUID(cliente);
    }


    //fecha UUID


    /**
     * Método de exemplo que imprime o JSON enviado no corpo da requisição no console.
     *
     * @param json O conteúdo JSON a ser impresso
     */
    // http://localhost:8080/ecommerce-livros-feliperighi/cliente/print-json
    @PostMapping("/print-json")
    @Operation(summary = "Imprimir JSON no console", description = "Recebe um JSON e o imprime no console.")
    public void printJson(@RequestBody String json){
        System.out.println(json);
    }

    /**
     * Método que cria um novo cliente.
     *
     * @param cliente O cliente a ser criado
     * @param uriBuilder O construtor de URI para gerar a URI do novo cliente criado
     * @return ResponseEntity com o cliente criado e a URI gerada
     */
    // http://localhost:8080/ecommerce-livros-feliperighi/cliente/salvar
    @PostMapping("/salvar")
    @Transactional
    @Operation(summary = "Criar um novo cliente", description = "Cria um novo cliente e o adiciona à lista.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", content = @Content)
    })
    public ResponseEntity salvar(@RequestBody @Valid Cliente cliente, UriComponentsBuilder uriBuilder){
        this.service.salvar(cliente);
        // Monta a URI da aplicação dinamicamente, incluindo o UUID do cliente criado
        URI uri = uriBuilder.path("/cliente/{uuid}").buildAndExpand(cliente.getUuid()).toUri();

        // Retorna a resposta com o status 201 (Created) e o corpo contendo o cliente
        return ResponseEntity.created(uri).body(cliente);
    }

    /**
     * Método que atualiza um cliente.
     *
     * @param cliente O cliente com as informações a serem atualizadas
     * @return ResponseEntity com o cliente atualizado
     */
    // http://localhost:8080/ecommerce-livros-feliperighi/cliente/
    @PutMapping()
    @Transactional
    @Operation(summary = "Atualizar cliente", description = "Atualiza as informações de um cliente.")
    public ResponseEntity atualizar(@RequestBody Cliente cliente){
        this.service.atualizar(cliente);
        return ResponseEntity.ok(cliente);  // Retorna o cliente atualizado com status 200
    }

    /**
     * Método que deleta um cliente utilizando seu ID.
     *
     * @param id ID do cliente a ser deletado
     * @return ResponseEntity com status de no content (sem corpo)
     */
    // http://localhost:8080/ecommerce-livros-feliperighi/cliente/1
    @DeleteMapping("/{id}")
    @Transactional  // Transação que é revertida em caso de erro
    @Operation(summary = "Deletar cliente", description = "Deleta um cliente pelo seu ID.")
    public ResponseEntity deletar(@PathVariable Long id){
        this.service.excluir(id);
        return ResponseEntity.noContent().build();  // Retorna 204 (No Content) após exclusão bem-sucedida
    }

    /**
     * Método que deleta um cliente utilizando seu UUID.
     *
     * @param uuid UUID do cliente a ser deletado
     * @return ResponseEntity com status de no content (sem corpo)
     */
    // http://localhost:8080/ecommerce-livros-feliperighi/cliente/uuid/{uuid}
    @DeleteMapping("/uuid/{uuid}")
    @Transactional  // Transação que é revertida em caso de erro
    @Operation(summary = "Deletar cliente", description = "Deleta um cliente pelo seu UUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content)
    })
    public ResponseEntity<Void> deletarUuid(@PathVariable String uuid) {
        try {
            this.service.excluirUUID(uuid);  // Chama o método de exclusão pelo UUID no serviço
            return ResponseEntity.noContent().build();  // Retorna 204 (No Content) após exclusão bem-sucedida
        } catch (Exception e) {
            return ResponseEntity.notFound().build();  // Retorna 404 (Not Found) caso o cliente não seja encontrado ou ocorra algum erro
        }
    }

    //Spring Security
    //
    @PostMapping
    @Transactional
    public ResponseEntity criar(@RequestBody @Valid Cliente cliente, UriComponentsBuilder uriBuilder){
        this.service.cadastrar(cliente);
        URI uri = uriBuilder.path("/cliente/{id}").buildAndExpand(cliente.getIdcliente()).toUri();
        return ResponseEntity.created(uri).body(cliente);
    }

    @GetMapping("/{id}")
    public DadosCliente findById(@PathVariable Long id) { return this.service.findCliente(id); }

    @GetMapping
    public List<DadosCliente> findAll(){
        return this.service.findAllClientes();
    }

    /*@PutMapping
    public void atualizar(@RequestBody Cliente cliente){
        this.service.atualizar(cliente);
    }*/

    /*@DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        this.service.excluir(id);
    }*/

}
