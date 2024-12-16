package br.csi.ecommerce_livros_feliperighi.model.Compra;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompraRepository extends JpaRepository<Compra, Long> {
    Optional<Compra> findCompraByUuid(UUID uuid);
}
