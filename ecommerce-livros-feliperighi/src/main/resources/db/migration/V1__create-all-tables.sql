create table cliente(
        idcliente serial not null primary key,
        uuid UUID DEFAULT gen_random_uuid(),
        nome varchar(255) not null ,
        email varchar(255) not null unique,
        senha varchar(255) not null,
        datacadastro date not null,
        ativo boolean
);

create table livro(
                      idlivro serial not null primary key,
                      uuid UUID DEFAULT gen_random_uuid(),
                      titulo varchar(255) not null,
                      autor varchar(255) not null,
                      descricao varchar(255) not null,
                      preco decimal(10,2) not null,
                      esta_a_venda boolean not null,
                      id_cliente_dono int references cliente(idcliente),
                      url varchar(255),
                      qntd_livros int not null
);

create table carrinho_compra(
                                idcarrinhocompra serial not null primary key,
                                uuid UUID DEFAULT gen_random_uuid(),
                                id_cliente_car int references cliente(idcliente),
                                id_livro_car int references livro(idlivro),
                                quantidade int not null,
                                UNIQUE(id_cliente_car, id_livro_car)
);

create table compra(
                       idcompra serial not null primary key,
                       uuid UUID DEFAULT gen_random_uuid(),
                       id_cliente_comp int references cliente(idcliente),
                       data_compra date not null,
                       valor_total decimal(10,2) not null
);

create table itens_compra(
                             iditenscompra serial not null primary key,
                             uuid UUID DEFAULT gen_random_uuid(),
                             id_compra_it int references compra(idcompra),
                             id_livro_it int references livro(idlivro),
                             quantidade int not null,
                             preco decimal(10,2) not null
);
