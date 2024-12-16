package br.csi.ecommerce_livros_feliperighi.DTO;

import java.math.BigDecimal;
import java.util.UUID;

public interface LivroDTO {
    Long getIdlivro();
    UUID getUuidlivro();
    String getTitulo();
    String getAutor();
    String getDescricao();
    BigDecimal getPreco();
    Boolean getEstaAVenda();
    String getUrl();
    Integer getQntdLivros();
}
