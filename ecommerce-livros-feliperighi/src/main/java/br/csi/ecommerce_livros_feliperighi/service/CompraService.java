package br.csi.ecommerce_livros_feliperighi.service;

import br.csi.ecommerce_livros_feliperighi.model.Compra.Compra;
import br.csi.ecommerce_livros_feliperighi.model.Compra.CompraRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CompraService {
    private final CompraRepository repository;

    public CompraService(CompraRepository repository) {
        this.repository = repository;
    }

    // CRIAÇÃO/ATUALIZAÇÃO
    public void salvar(Compra compra) {
        this.repository.save(compra);
    }

    // LISTAGEM
    public List<Compra> listar() {
        return this.repository.findAll();
    }

    // CONSULTA - ID
    public Compra getCompra(Long id) {
        return this.repository.findById(id).orElseThrow(() -> new RuntimeException("Compra não encontrada"));
    }

    // EXCLUSÃO - ID
    public void excluir(Long id) {
        this.repository.deleteById(id);
    }

    // ATUALIZAÇÃO - ID
    public void atualizar(Compra compra) {
        Compra c = this.repository.findById(compra.getIdcompra())
                .orElseThrow(() -> new RuntimeException("Compra não encontrada"));
        c.setDataCompra(compra.getDataCompra());
        c.setValorTotal(compra.getValorTotal());
        c.setCliente(compra.getCliente());
        c.setItensCompra(compra.getItensCompra());
        this.repository.save(c);
    }

    // CONSULTA - UUID
    public Compra getCompraUUID(String uuid) {
        UUID uuidFormatado = UUID.fromString(uuid);
        return this.repository.findCompraByUuid(uuidFormatado)
                .orElseThrow(() -> new RuntimeException("Compra não encontrada"));
    }

    // EXCLUSÃO - UUID
    public void excluirUUID(String uuid) {
        UUID uuidFormatado = UUID.fromString(uuid);
        Compra compra = this.repository.findCompraByUuid(uuidFormatado)
                .orElseThrow(() -> new RuntimeException("Compra não encontrada"));
        this.repository.delete(compra);
    }
}
