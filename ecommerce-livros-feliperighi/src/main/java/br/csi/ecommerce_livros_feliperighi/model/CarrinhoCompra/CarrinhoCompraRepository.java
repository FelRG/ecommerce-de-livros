package br.csi.ecommerce_livros_feliperighi.model.CarrinhoCompra;

import br.csi.ecommerce_livros_feliperighi.model.Cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CarrinhoCompraRepository extends JpaRepository<CarrinhoCompra, Long> {

    CarrinhoCompra findByClienteAndLivroUuid(Cliente cliente, UUID livroUuid);

    List<CarrinhoCompra> findByCliente(Cliente cliente);

    void deleteByClienteAndLivroUuid(Cliente cliente, UUID livroUuid);

    void deleteByCliente(Cliente cliente);
}