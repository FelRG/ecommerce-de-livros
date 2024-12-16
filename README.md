# E-Commerce de Livros  

## Descrição do Sistema Web Java  
O sistema de **E-Commerce de Livros** oferece uma plataforma prática e segura para a compra e venda de livros.  

A aplicação foi desenvolvida em **Spring Boot** com banco de dados **PostgreSQL** e inclui:
- Uma imagem do modelo ER para ilustrar sua estrutura.
- Arquivo contendo todas as coleções de rotas de acesso aos recursos da API.

### Tecnologias e Boas Práticas Utilizadas  
- **Flyway** para controle de migrações.  
- **Spring Data JPA** para mapeamentos NxN.  
- API RESTful documentada com **Spring Doc Swagger**, facilitando a integração e os testes.  
- Validação com **Spring Validation** e tratamento de respostas com mensagens claras para erros.  
- Coleção de endpoints para **Insomnia** ou **Postman** disponível para simplificar os testes.  

### Funcionalidades Principais  
- **Cadastro, edição e exclusão de livros** com a possibilidade de definir disponibilidade para venda.  
- Compra de livros de outros usuários, com suporte para múltiplos itens por compra.  
- Visualização de pedidos e livros cadastrados.  
- Cadastro de clientes com **nome**, **e-mail** e **senha**, além de suporte para edição e exclusão de contas.  

### Segurança  
- Implementação de **Spring Security** com autenticação e autorização.  
- Endpoints protegidos com acesso somente via **token** gerado pela API.  

### Links para Documentação  
- [Swagger UI](http://localhost:8080/ecommerce-livros-feliperighi/swagger-ui.html)  
- [API Docs](http://localhost:8080/ecommerce-livros-feliperighi/api-docs)  
