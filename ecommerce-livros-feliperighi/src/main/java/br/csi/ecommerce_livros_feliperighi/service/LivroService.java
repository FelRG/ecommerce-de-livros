package br.csi.ecommerce_livros_feliperighi.service;

import br.csi.ecommerce_livros_feliperighi.model.Livro.Livro;
import br.csi.ecommerce_livros_feliperighi.model.Livro.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LivroService {
    private final LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    // CRIAÇÃO/ATUALIZAÇÃO (Long)
    // Método que cria ou atualiza um livro, dependendo se o ID é novo ou já existe.
    public void salvar(Livro livro){
        this.repository.save(livro);
    }

    // LISTAGEM - (Long)
    // Método que retorna todos os livros cadastrados no banco de dados.
    public List<Livro> listar(){
        return this.repository.findAll();
    }

    // MÉTODOS COM ID

    // CONSULTA - ID (Long)
    // Método que busca um livro pelo ID (Long). Retorna o livro ou lança uma exceção se não encontrar.
    public Livro getLivro(Long id){
        return this.repository.findById(id).orElseThrow(() -> new RuntimeException("Livro não encontrado"));
    }

    // EXCLUSÃO - ID (Long)
    // Método que deleta um livro baseado no ID (Long). O ID é passado para localizar o livro e excluí-lo.
    public void excluir(Long id){
        this.repository.deleteById(id);
    }

    // ATUALIZAÇÃO - ID (Long)
    // Método que atualiza os dados de um livro com base no ID (Long). Ele pega o livro existente e atualiza seus dados.
    public void atualizar(Livro livro){
        Livro l = this.repository.getReferenceById(livro.getIdlivro());
        l.setTitulo(livro.getTitulo());
        l.setAutor(livro.getAutor());
        l.setDescricao(livro.getDescricao());
        l.setPreco(livro.getPreco());
        l.setEsta_a_venda(livro.getEsta_a_venda());
        l.setUrl(livro.getUrl());
        l.setQntd_livros(livro.getQntd_livros());
        this.repository.save(l);
    }

    // MÉTODOS COM UUID

    // CONSULTA - UUID (UUID)
    // Método que busca um livro pelo UUID. O UUID é passado como string, então convertemos para o formato UUID.
    public Livro getLivroUUID(String uuid){
        UUID uuidFormatado = UUID.fromString(uuid);
        return this.repository.findLivroByUuid(uuidFormatado);
    }

    // EXCLUSÃO - UUID (UUID)
    // Método que deleta um livro com base no UUID. O UUID é convertido de String para o formato UUID para realizar a exclusão.
    public void excluirUUID(String uuid){
        Livro livro = this.repository.findLivroByUuid(UUID.fromString(uuid));
        if (livro != null) {
            this.repository.deleteLivroByUuid(UUID.fromString(uuid));
        } else {
            throw new RuntimeException("Livro não encontrado"); // Lança uma exceção caso o livro não exista
        }
    }

    // CRIAÇÃO/ATUALIZAÇÃO - UUID (UUID)
    // Método que atualiza os dados de um livro baseado no UUID. O UUID é único e é usado para identificar o livro de forma global.
    public void atualizarUUID(Livro livro){
        Livro l = this.repository.findLivroByUuid(livro.getUuid());
        l.setTitulo(livro.getTitulo());
        l.setAutor(livro.getAutor());
        l.setDescricao(livro.getDescricao());
        l.setPreco(livro.getPreco());
        l.setEsta_a_venda(livro.getEsta_a_venda());
        l.setUrl(livro.getUrl());
        l.setQntd_livros(livro.getQntd_livros());
        this.repository.save(l);
    }
}
