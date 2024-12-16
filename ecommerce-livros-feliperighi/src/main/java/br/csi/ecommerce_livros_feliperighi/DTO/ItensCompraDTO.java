package br.csi.ecommerce_livros_feliperighi.DTO;

import br.csi.ecommerce_livros_feliperighi.model.Livro.Livro;
import java.math.BigDecimal;
import java.util.UUID;

public interface ItensCompraDTO {

    Long getIditenscompra();
    UUID getUuiditenscompra();
    int getQuantidade();
    BigDecimal getPreco();
    Long getIdLivro();
    Long getIdCompra(); // para associar o item com uma compra específica

}
