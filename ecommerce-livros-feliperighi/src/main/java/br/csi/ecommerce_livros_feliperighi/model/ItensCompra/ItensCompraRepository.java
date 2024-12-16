package br.csi.ecommerce_livros_feliperighi.model.ItensCompra;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItensCompraRepository extends JpaRepository<ItensCompra, Long> {

    /**
     * Busca um item de compra pelo UUID.
     *
     * @param uuid UUID do item de compra.
     * @return ItensCompra correspondente ao UUID.
     */
    ItensCompra findByUuid(UUID uuid);

    /**
     * Exclui um item de compra pelo UUID.
     *
     * @param uuid UUID do item de compra a ser excluído.
     */
    void deleteByUuid(UUID uuid);
}
