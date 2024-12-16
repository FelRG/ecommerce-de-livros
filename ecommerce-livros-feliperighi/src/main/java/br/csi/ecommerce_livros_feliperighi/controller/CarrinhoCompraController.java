package br.csi.ecommerce_livros_feliperighi.controller;

import br.csi.ecommerce_livros_feliperighi.model.Cliente.Cliente;
import br.csi.ecommerce_livros_feliperighi.model.CarrinhoCompra.CarrinhoCompra;
import br.csi.ecommerce_livros_feliperighi.service.CarrinhoCompraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/carrinho")
@Tag(name = "Carrinho de Compras", description = "Operações relacionadas ao carrinho de compras")
public class CarrinhoCompraController {

    private final CarrinhoCompraService carrinhoService;

    public CarrinhoCompraController(CarrinhoCompraService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    @PostMapping("/adicionar")
    @Operation(summary = "Adicionar item ao carrinho", description = "Adiciona um item ou atualiza a quantidade de um item existente no carrinho.")
    public ResponseEntity<Void> adicionarOuAtualizarItem(
            @RequestBody Cliente cliente,
            @RequestParam UUID livroUuid,
            @RequestParam int quantidade) {
        try {
            carrinhoService.adicionarOuAtualizarItem(cliente, livroUuid, quantidade);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar itens do carrinho", description = "Lista todos os itens do carrinho de um cliente.")
    public ResponseEntity<List<CarrinhoCompra>> listarItensCarrinho(@RequestBody Cliente cliente) {
        List<CarrinhoCompra> itens = carrinhoService.listarItensCarrinho(cliente);
        return ResponseEntity.ok(itens);
    }

    @DeleteMapping("/remover")
    @Operation(summary = "Remover item do carrinho", description = "Remove um item específico do carrinho pelo UUID do livro.")
    public ResponseEntity<Void> removerItemPorLivroUUID(
            @RequestBody Cliente cliente,
            @RequestParam UUID livroUuid) {
        try {
            carrinhoService.removerItemPorLivroUUID(cliente, livroUuid);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/esvaziar")
    @Operation(summary = "Esvaziar o carrinho", description = "Remove todos os itens do carrinho de um cliente.")
    public ResponseEntity<Void> esvaziarCarrinho(@RequestBody Cliente cliente) {
        carrinhoService.esvaziarCarrinho(cliente);
        return ResponseEntity.noContent().build();
    }
}