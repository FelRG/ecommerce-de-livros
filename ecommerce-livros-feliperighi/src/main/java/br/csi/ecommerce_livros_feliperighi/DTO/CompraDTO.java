package br.csi.ecommerce_livros_feliperighi.DTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public interface CompraDTO {
    Long getIdcompra();
    UUID getUuidcompra();
    Date getDataCompra();
    BigDecimal getValorTotal();
    Long getClienteId();
    Long getItensId();
}
