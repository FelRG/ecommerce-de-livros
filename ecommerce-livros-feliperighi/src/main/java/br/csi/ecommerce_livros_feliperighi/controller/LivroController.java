package br.csi.ecommerce_livros_feliperighi.controller;

import br.csi.ecommerce_livros_feliperighi.model.Livro.Livro;
import br.csi.ecommerce_livros_feliperighi.service.LivroService;
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
@RequestMapping("/livro")
@Tag(name = "Livros", description = "Path relacionado a operações de livros")
public class LivroController {

    private final LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    /**
     * Endpoint para listar todos os livros cadastrados.
     * Exemplo de URL: http://localhost:8080/ecommerce-livros-feliperighi/livro/listar
     *
     * @return Lista de livros
     */
    @GetMapping("/listar")
    @Operation(summary = "Listar todos os livros", description = "Retorna uma lista com todos os livros cadastrados.")
    public List<Livro> listar() {
        return this.service.listar();
    }

    /**
     * Endpoint para buscar um livro pelo seu ID.
     * Exemplo de URL: http://localhost:8080/ecommerce-livros-feliperighi/livro/1
     *
     * @param id ID do livro a ser recuperado
     * @return Livro com o ID especificado
     */
    @GetMapping("/{id}")
    @Operation(summary = "Buscar livro por ID", description = "Busca um livro pelo seu ID.")
    public Livro getLivro(@PathVariable Long id) {
        return this.service.getLivro(id);
    }

    /**
     * Endpoint para buscar um livro pelo seu UUID.
     * Exemplo de URL: http://localhost:8080/ecommerce-livros-feliperighi/livro/uuid/{uuid}
     *
     * @param uuid UUID do livro a ser recuperado
     * @return Livro com o UUID especificado
     */
    @GetMapping("/uuid/{uuid}")
    @Operation(summary = "Buscar livro por UUID", description = "Busca um livro pelo seu UUID.")
    public Livro getLivroUUID(@PathVariable String uuid) {
        return this.service.getLivroUUID(uuid);
    }

    /**
     * Endpoint para atualizar um livro utilizando seu UUID.
     * Exemplo de URL: http://localhost:8080/ecommerce-livros-feliperighi/livro/uuid
     *
     * @param livro Objeto Livro com os dados a serem atualizados
     */
    @PutMapping("/uuid")
    @Operation(summary = "Atualizar livro por UUID", description = "Atualiza as informações de um livro com base no seu UUID.")
    public void atualizarUUID(@RequestBody Livro livro) {
        this.service.atualizarUUID(livro);
    }

    /**
     * Endpoint para criar um novo livro.
     * Exemplo de URL: http://localhost:8080/ecommerce-livros-feliperighi/livro
     *
     * @param livro Livro a ser criado
     * @param uriBuilder Construtor de URI para gerar a URI do novo livro criado
     * @return ResponseEntity com o livro criado e a URI gerada
     */
    @PostMapping
    @Transactional
    @Operation(summary = "Criar um novo livro", description = "Cria um novo livro e o adiciona à lista.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Livro criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Livro.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", content = @Content)
    })
    public ResponseEntity<Livro> salvar(@RequestBody @Valid Livro livro, UriComponentsBuilder uriBuilder) {
        this.service.salvar(livro);
        URI uri = uriBuilder.path("/livro/{uuid}").buildAndExpand(livro.getUuid()).toUri();
        return ResponseEntity.created(uri).body(livro);
    }

    /**
     * Endpoint para atualizar as informações de um livro.
     * Exemplo de URL: http://localhost:8080/ecommerce-livros-feliperighi/livro
     *
     * @param livro Livro com as informações a serem atualizadas
     * @return ResponseEntity com o livro atualizado
     */
    @PutMapping
    @Transactional
    @Operation(summary = "Atualizar livro", description = "Atualiza as informações de um livro.")
    public ResponseEntity<Livro> atualizar(@RequestBody Livro livro) {
        this.service.atualizar(livro);
        return ResponseEntity.ok(livro);
    }

    /**
     * Endpoint para deletar um livro pelo seu ID.
     * Exemplo de URL: http://localhost:8080/ecommerce-livros-feliperighi/livro/1
     *
     * @param id ID do livro a ser deletado
     * @return ResponseEntity com status de no content (sem corpo)
     */
    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Deletar livro", description = "Deleta um livro pelo seu ID.")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        this.service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint para deletar um livro pelo seu UUID.
     * Exemplo de URL: http://localhost:8080/ecommerce-livros-feliperighi/livro/uuid/{uuid}
     *
     * @param uuid UUID do livro a ser deletado
     * @return ResponseEntity com status de no content (sem corpo)
     */
    @DeleteMapping("/uuid/{uuid}")
    @Transactional
    @Operation(summary = "Deletar livro", description = "Deleta um livro pelo seu UUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Livro deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado", content = @Content)
    })
    public ResponseEntity<Void> deletarUUID(@PathVariable String uuid) {
        try {
            this.service.excluirUUID(uuid);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
