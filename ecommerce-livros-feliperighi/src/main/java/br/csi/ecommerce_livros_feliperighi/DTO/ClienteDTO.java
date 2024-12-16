package br.csi.ecommerce_livros_feliperighi.DTO;

import java.util.Date;
import java.util.UUID;

public interface ClienteDTO {
    Long getIdcliente();
    UUID getUuidcliente();
    String getNome();
    String getEmail();
    String getSenha();
    Date getDataCadastro();
    Boolean getAtivo();
}
