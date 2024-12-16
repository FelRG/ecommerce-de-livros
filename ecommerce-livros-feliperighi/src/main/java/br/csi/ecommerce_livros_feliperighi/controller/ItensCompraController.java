package br.csi.ecommerce_livros_feliperighi.controller;

import br.csi.ecommerce_livros_feliperighi.model.ItensCompra.ItensCompra;
import br.csi.ecommerce_livros_feliperighi.service.ItensCompraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/itens-compra")
@Tag(name = "Itens de Compra", description = "Path relacionado a operações de itens de compra")
public class ItensCompraController {

    private final ItensCompraService service;

    public ItensCompraController(ItensCompraService service) {
        this.service = service;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar todos os itens de compra", description = "Retorna uma lista com todos os itens de compra cadastrados.")
    public List<ItensCompra> listar() {
        return this.service.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar item de compra por ID", description = "Busca um item de compra pelo seu ID.")
    public ResponseEntity<ItensCompra> getItemCompra(@PathVariable Long id) {
        ItensCompra item = this.service.getItensCompra(id);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.notFound().build();
    }

    @GetMapping("/uuid/{uuid}")
    @Operation(summary = "Buscar item de compra por UUID", description = "Busca um item de compra pelo seu UUID.")
    public ResponseEntity<ItensCompra> getItemCompraByUUID(@PathVariable String uuid) {
        ItensCompra item = this.service.getItensCompraByUUID(uuid);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Criar um novo item de compra", description = "Cria um novo item de compra.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item de compra criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<ItensCompra> criar(@RequestBody ItensCompra item, UriComponentsBuilder uriBuilder) {
        this.service.salvar(item);
        URI uri = uriBuilder.path("/itens-compra/{id}").buildAndExpand(item.getIditenscompra()).toUri();
        return ResponseEntity.created(uri).body(item);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar item de compra", description = "Atualiza as informações de um item de compra.")
    public ResponseEntity<ItensCompra> atualizar(@PathVariable Long id, @RequestBody ItensCompra itemAtualizado) {
        ItensCompra item = this.service.atualizar(itemAtualizado);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar item de compra", description = "Deleta um item de compra pelo seu ID.")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (this.service.excluir(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/uuid/{uuid}")
    @Operation(summary = "Deletar item de compra por UUID", description = "Deleta um item de compra pelo seu UUID.")
    public ResponseEntity<Void> deletarByUUID(@PathVariable String uuid) {
        if (this.service.excluirUUID(uuid)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
