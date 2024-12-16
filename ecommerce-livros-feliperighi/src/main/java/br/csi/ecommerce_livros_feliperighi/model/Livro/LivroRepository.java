package br.csi.ecommerce_livros_feliperighi.model.Livro;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    // Método para encontrar um livro pelo UUID.
    Livro findLivroByUuid(UUID uuid);

    // Método para excluir um livro pelo UUID.
    void deleteLivroByUuid(UUID uuid);

    /*
    Exemplo de uma consulta personalizada com Query Annotation:

    @Query(value = "SELECT l.id as id, l.titulo as titulo, l.autor as autor, l.preco as preco, " +
                   "l.anoPublicacao as anoPublicacao FROM livro l WHERE l.preco > :preco", nativeQuery = true)
    List<LivroDTO> findLivrosPorPrecoMinimo(@Param("preco") double preco);
    */
}
