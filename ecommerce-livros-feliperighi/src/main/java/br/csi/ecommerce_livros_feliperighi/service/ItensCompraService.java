package br.csi.ecommerce_livros_feliperighi.service;

import br.csi.ecommerce_livros_feliperighi.model.ItensCompra.ItensCompra;
import br.csi.ecommerce_livros_feliperighi.model.ItensCompra.ItensCompraRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ItensCompraService {

    private final ItensCompraRepository repository;

    public ItensCompraService(ItensCompraRepository repository) {
        this.repository = repository;
    }

    /**
     * Método que cria ou atualiza um item de compra, dependendo se o ID é novo ou já existe.
     *
     * @param itensCompra Objeto ItensCompra a ser salvo ou atualizado.
     */
    public void salvar(ItensCompra itensCompra) {
        this.repository.save(itensCompra);
    }

    /**
     * Método que retorna todos os itens de compra cadastrados no banco de dados.
     *
     * @return Lista de ItensCompra.
     */
    public List<ItensCompra> listar() {
        return this.repository.findAll();
    }

    /**
     * Método que busca um item de compra pelo ID.
     *
     * @param id ID do item de compra.
     * @return ItensCompra correspondente ao ID, ou null se não encontrado.
     */
    public ItensCompra getItensCompra(Long id) {
        return this.repository.findById(id).orElse(null);
    }

    /**
     * Método que exclui um item de compra pelo ID.
     *
     * @param id ID do item de compra a ser excluído.
     */
    public boolean excluir(Long id) {
        this.repository.deleteById(id);
        return true;
    }

    /**
     * Método que atualiza os dados de um item de compra com base no ID.
     *
     * @param itensCompra Objeto ItensCompra com os dados atualizados.
     */
    public ItensCompra atualizar(ItensCompra itensCompra) {
        ItensCompra existente = this.repository.getReferenceById(itensCompra.getIditenscompra());
        existente.setQuantidade(itensCompra.getQuantidade());
        existente.setPreco(itensCompra.getPreco());
        existente.setLivro(itensCompra.getLivro());
        this.repository.save(existente);
        return existente;
    }

    /**
     * Método que busca um item de compra pelo UUID.
     *
     * @param uuid UUID do item de compra.
     * @return ItensCompra correspondente ao UUID, ou null se não encontrado.
     */
    public ItensCompra getItensCompraByUUID(String uuid) {
        UUID uuidFormatado = UUID.fromString(uuid);
        return this.repository.findByUuid(uuidFormatado);
    }

    /**
     * Método que exclui um item de compra pelo UUID.
     *
     * @param uuid UUID do item de compra a ser excluído.
     */
    public boolean excluirUUID(String uuid) {
        ItensCompra item = this.repository.findByUuid(UUID.fromString(uuid));
        if (item != null) {
            this.repository.deleteByUuid(UUID.fromString(uuid));
            return true;
        } else {
            throw new RuntimeException("Item de compra não encontrado");
        }
    }
}
