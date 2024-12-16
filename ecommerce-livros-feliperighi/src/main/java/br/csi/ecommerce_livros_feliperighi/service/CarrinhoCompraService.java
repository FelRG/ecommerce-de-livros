package br.csi.ecommerce_livros_feliperighi.service;

import br.csi.ecommerce_livros_feliperighi.model.Cliente.Cliente;
import br.csi.ecommerce_livros_feliperighi.model.CarrinhoCompra.CarrinhoCompra;
import br.csi.ecommerce_livros_feliperighi.model.CarrinhoCompra.CarrinhoCompraRepository;
import br.csi.ecommerce_livros_feliperighi.model.Livro.Livro;
import br.csi.ecommerce_livros_feliperighi.model.Livro.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CarrinhoCompraService {

    private final CarrinhoCompraRepository carrinhoRepository;
    private final LivroRepository livroRepository;

    public CarrinhoCompraService(CarrinhoCompraRepository carrinhoRepository, LivroRepository livroRepository) {
        this.carrinhoRepository = carrinhoRepository;
        this.livroRepository = livroRepository;
    }

    // Adicionar ou atualizar a quantidade de um livro no carrinho
    public void adicionarOuAtualizarItem(Cliente cliente, UUID livroUuid, int quantidade) {
        Livro livro = livroRepository.findLivroByUuid(livroUuid);
        if (livro == null) {
            throw new RuntimeException("Livro não encontrado");
        }

        CarrinhoCompra itemExistente = this.carrinhoRepository.findByClienteAndLivroUuid(cliente, livroUuid);

        if (itemExistente != null) {
            itemExistente.setQuantidade(quantidade);
            this.carrinhoRepository.save(itemExistente);
        } else {
            CarrinhoCompra novoItem = new CarrinhoCompra();
            novoItem.setCliente(cliente);
            novoItem.setLivro(livro);
            novoItem.setQuantidade(quantidade);
            this.carrinhoRepository.save(novoItem);
        }
    }

    // Listar todos os itens do carrinho para um cliente específico
    public List<CarrinhoCompra> listarItensCarrinho(Cliente cliente) {
        return this.carrinhoRepository.findByCliente(cliente);
    }

    // Remover um item específico do carrinho pelo UUID do livro
    public void removerItemPorLivroUUID(Cliente cliente, UUID livroUuid) {
        CarrinhoCompra item = this.carrinhoRepository.findByClienteAndLivroUuid(cliente, livroUuid);
        if (item != null) {
            //this.carrinhoRepository.delete(item);
            this.carrinhoRepository.deleteByClienteAndLivroUuid(cliente, livroUuid);
        } else {
            throw new RuntimeException("Item do carrinho não encontrado");
        }
    }

    // Esvaziar o carrinho do cliente
    public void esvaziarCarrinho(Cliente cliente) {
        this.carrinhoRepository.deleteByCliente(cliente);
    }
}