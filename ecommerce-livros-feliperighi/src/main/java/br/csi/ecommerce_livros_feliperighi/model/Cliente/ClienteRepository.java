package br.csi.ecommerce_livros_feliperighi.model.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    public Cliente findClienteByUuid(UUID uuid);
    public void deleteClienteByUuid(UUID uuid);
    public Cliente findByEmail(String email); //parecido com findByLogin

    /*@Query(value = "SELECT a.id as id, a.nome as nome, a.email as email, a.senha as senha" +
            "a.datacadastro as datacadastro, a.ativo as ativo from cliente a where a.idalgumacoisa =:id", nativeQuery = true)
    List<ClienteDTO> findClienteByAtivo(@Param("id") int id);*/

}
