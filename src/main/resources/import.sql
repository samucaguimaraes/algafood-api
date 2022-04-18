insert into cozinha (nome) value ('Brasileira');
insert into cozinha (nome) value ('Baiana');

insert into restaurante (nome, taxa_frete, cozinha_id) values ("Encheno e Derramando", 4.50,1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Alforria', 6.00, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Moenda', 7.00, 2);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Inacio', 8.00, 2);

insert into estado (id, nome) values (1, 'Bahia');
insert into estado (id, nome) values (2, 'Alagoas');
insert into estado (id, nome) values (3, 'Sergipe');

insert into cidade (id, nome, estado_id) values (1, 'Salvador', 1);
insert into cidade (id, nome, estado_id) values (2, 'Feira de Santana', 1);
insert into cidade (id, nome, estado_id) values (3, 'Aracajú', 3);
insert into cidade (id, nome, estado_id) values (4, 'Maceió', 2);
insert into cidade (id, nome, estado_id) values (5, 'Senhor do Bonfim', 1);

insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito');
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');

insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

