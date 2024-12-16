package br.csi.ecommerce_livros_feliperighi.model.Cliente;

public record DadosCliente(Long id, String email, String permissao) {
    public DadosCliente(Cliente cliente){
        this(cliente.getIdcliente(), cliente.getEmail(), cliente.getPermissao());
    }
}
