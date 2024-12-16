package br.csi.ecommerce_livros_feliperighi.controller;

import br.csi.ecommerce_livros_feliperighi.model.Compra.Compra;
import br.csi.ecommerce_livros_feliperighi.service.CompraService;
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
@RequestMapping("/compra")
@Tag(name = "Compras", description = "Path relacionado a operações de compras")
public class CompraController {

    private final CompraService service;

    public CompraController(CompraService service) {
        this.service = service;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar todas as compras", description = "Retorna uma lista com todas as compras realizadas.")
    public List<Compra> listar() {
        return this.service.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar compra por ID", description = "Busca uma compra pelo seu ID.")
    public Compra compra(@PathVariable Long id) {
        return this.service.getCompra(id);
    }

    @GetMapping("/uuid/{uuid}")
    @Operation(summary = "Buscar compra por UUID", description = "Busca uma compra pelo seu UUID.")
    public Compra compra(@PathVariable String uuid) {
        return this.service.getCompraUUID(uuid);
    }

    @PostMapping
    @Transactional
    @Operation(summary = "Criar uma nova compra", description = "Cria uma nova compra e a adiciona ao sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Compra criada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Compra.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", content = @Content)
    })
    public ResponseEntity<Compra> salvar(@RequestBody @Valid Compra compra, UriComponentsBuilder uriBuilder) {
        this.service.salvar(compra);
        URI uri = uriBuilder.path("/compra/{uuid}").buildAndExpand(compra.getUuid()).toUri();
        return ResponseEntity.created(uri).body(compra);
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Atualizar compra", description = "Atualiza as informações de uma compra.")
    public ResponseEntity<Compra> atualizar(@RequestBody Compra compra) {
        this.service.atualizar(compra);
        return ResponseEntity.ok(compra);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Deletar compra", description = "Deleta uma compra pelo seu ID.")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        this.service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/uuid/{uuid}")
    @Transactional
    @Operation(summary = "Deletar compra por UUID", description = "Deleta uma compra pelo seu UUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Compra deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Compra não encontrada", content = @Content)
    })
    public ResponseEntity<Void> deletarUuid(@PathVariable String uuid) {
        try {
            this.service.excluirUUID(uuid);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
