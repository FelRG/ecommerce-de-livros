package br.csi.ecommerce_livros_feliperighi.DTO;

import java.util.UUID;

public interface CarrinhoCompraDTO {
    Long getIdCarrinhoCompra();
    UUID getUuidCarrinhoCompra();
    int getQuantidade();
    ClienteDTO getCliente();
    LivroDTO getLivro();
}
