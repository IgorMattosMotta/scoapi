SET session_replication_role = 'replica';

INSERT INTO usuario (id, login, email, senha, telefone, admin, dtype) VALUES
(0, 'admin', 'admin@scoapi.com', '$2b$10$4YJHesFqGGu3GRba0J69PONYYlxYfmDcOu.Zzg3LrfVtn1T0qBkoK', '(11) 99999-0000', true, 'Usuario');

INSERT INTO usuario (id, login, email, senha, telefone, admin, dtype, cnpj, nome) VALUES
(1, 'ong_patas_felizes', 'contato@pataspelizes.org', '$2b$10$p.gRtDlWMO84Kafo67.nD.pQNg0yGymjhPFNSEyogO1ylAVbxiimm', '(11) 3333-1111', false, 'ONG', '12.345.678/0001-90', 'Patas Felizes'),
(2, 'ong_abrigo_amigo', 'info@abrigoamigo.org', '$2b$10$Iutc8NwnRo3i0Zm7SFzwWuUcWGwUDT/jZknLbnwQfp4seZp/zvDfG', '(21) 3333-2222', false, 'ONG', '98.765.432/0001-10', 'Abrigo Amigo');

INSERT INTO usuario (id, login, email, senha, telefone, admin, dtype, cpf, nome_completo, data_nascimento, endereco) VALUES
(3, 'joao_silva', 'joao@email.com', '$2b$10$peSpnDzHH.yE3eTBiVJoIuJfEFCJ/NVj.ciNq0IxtYISQoqS2j1Iq', '(11) 98888-3333', false, 'Adotante', '123.456.789-00', 'João Silva', '1990-05-15', 'Rua A, 123 - São Paulo, SP'),
(4, 'maria_santos', 'maria@email.com', '$2b$10$uUNQo1yxe050UK8UEnL6GeRGmEvXXKbpy1tYVLrgxcbaq6WZWp85i', '(21) 98888-4444', false, 'Adotante', '987.654.321-00', 'Maria Santos', '1985-08-22', 'Avenida B, 456 - Rio de Janeiro, RJ'),
(5, 'pedro_oliveira', 'pedro@email.com', '$2b$10$Tj0I8E0ecfvDTMSG8ZmV0.fPJJG2AnZ0EJqtwFhJM0n7k/VA5XA4S', '(31) 98888-5555', false, 'Adotante', '456.789.123-00', 'Pedro Oliveira', '1995-03-10', 'Rua C, 789 - Belo Horizonte, MG'),
(6, 'ana_costa', 'ana@email.com', '$2b$10$r.NGAbBVyNjs9kSTj17xh.9285xxnOXB18nEIcnpy6cneMpWnQ2fi', '(61) 98888-6666', false, 'Adotante', '789.123.456-00', 'Ana Costa', '1988-12-30', 'Avenida D, 321 - Brasília, DF');

INSERT INTO status_animal (id, estado) VALUES
(1, 'Disponível'),
(2, 'Adotado'),
(3, 'Em tratamento'),
(4, 'Sob adoção');

INSERT INTO animal (id, nome, especie, raca, data_nascimento, foto, tamanho, peso, sexo, castrado, vermifugado, status_id, observacoes, ong_id) VALUES
(1, 'Rex', 'Cão', 'Labrador', '2020-01-15', 'rex.jpg', 'Grande', 30.5, 'Macho', true, true, 1, 'Cão dócil e amigável', 1),
(2, 'Mimi', 'Gato', 'Persa', '2019-06-20', 'mimi.jpg', 'Pequeno', 4.2, 'Fêmea', true, true, 1, 'Gata carinhosa', 1),
(3, 'Max', 'Cão', 'Pastor Alemão', '2021-03-10', 'max.jpg', 'Grande', 28.0, 'Macho', false, true, 1, 'Jovem e energético', 2),
(4, 'Luna', 'Gato', 'Siamês', '2020-09-05', 'luna.jpg', 'Pequeno', 3.8, 'Fêmea', true, true, 2, 'Já adotada', 2),
(5, 'Bella', 'Cão', 'Poodle', '2019-11-12', 'bella.jpg', 'Pequeno', 5.5, 'Fêmea', true, true, 3, 'Em tratamento de dermatite', 1),
(6, 'Thor', 'Cão', 'Bulldog', '2021-07-22', 'thor.jpg', 'Médio', 25.0, 'Macho', false, false, 1, 'Cão tranquilo e calmo', 2);

INSERT INTO status_solicitacao (id, status) VALUES
(1, 'Pendente'),
(2, 'Aprovada'),
(3, 'Recusada'),
(4, 'Cancelada');

INSERT INTO solicitacao_adocao (id, data_solicitacao, status_id, data_decisao, motivo_recusa, adotante_id, animal_id) VALUES
(1, '2024-01-10', 2, '2024-01-15', NULL, 3, 1),
(2, '2024-02-05', 2, '2024-02-10', NULL, 4, 2),
(3, '2024-03-20', 1, NULL, NULL, 5, 3),
(4, '2024-04-12', 3, '2024-04-18', 'Não atende aos critérios de espaço', 6, 5),
(5, '2024-05-08', 2, '2024-05-15', NULL, 3, 6);

INSERT INTO protocolo_vacina (id, nome_vacina, quantidade_doses, intervalo_interdoses, descricao) VALUES
(1, 'Tríplice Felina', 3, 21, 'Vacina contra Panleucopenia, Calicivírus e Rinotraqueíte'),
(2, 'Múltipla Canina', 3, 21, 'Vacina contra Cinomose, Parvovirose e Leptospirose'),
(3, 'Raiva', 1, 0, 'Vacina contra Raiva'),
(4, 'Bordetella', 2, 21, 'Vacina contra Traqueobronquite Infecciosa');

INSERT INTO registro_vacina (id, data_aplicacao, dose, protocolo_id, animal_id) VALUES
(1, '2024-01-15', 1, 2, 1),
(2, '2024-02-05', 2, 2, 1),
(3, '2024-02-26', 3, 2, 1),
(4, '2024-01-20', 1, 1, 2),
(5, '2024-02-10', 2, 1, 2),
(6, '2024-01-22', 1, 3, 1),
(7, '2024-03-10', 1, 4, 3),
(8, '2024-03-31', 2, 4, 3);

INSERT INTO questionario (id, ong_id) VALUES
(1, 1),
(2, 2);

INSERT INTO pergunta (id, texto, eh_aberta, questionario_id) VALUES
(1, 'Qual é o seu tipo de moradia?', false, 1),
(2, 'Quantas pessoas moram na sua casa?', false, 1),
(3, 'Você tem experiência anterior com animais de estimação?', false, 1),
(4, 'Qual é a sua renda mensal aproximada?', false, 1),
(5, 'Descreva como você cuidaria do animal', true, 1),
(6, 'Você possui espaço externo?', false, 2),
(7, 'Qual é seu compromisso com o bem-estar animal?', true, 2),
(8, 'Alguém na família tem alergia a animais?', false, 2);

INSERT INTO resposta_questionario (id, conteudo, solicitacao_id, pergunta_id) VALUES
(1, 'Casa própria', 1, 1),
(2, '3 pessoas', 1, 2),
(3, 'Sim, tenho experiência', 1, 3),
(4, 'Entre 3.000 e 5.000', 1, 4),
(5, 'Cuidaria com muito carinho e dedicação', 1, 5),
(6, 'Apartamento', 2, 1),
(7, '2 pessoas', 2, 2),
(8, 'Primeira vez', 2, 3),
(9, 'Mais de 5.000', 2, 4),
(10, 'Ofereceria amor e respeito total', 2, 5),
(11, 'Casa com quintal', 3, 1),
(12, '5 pessoas', 3, 2),
(13, 'Sim, tenho gatos', 3, 3),
(14, 'Acima de 8.000', 3, 4),
(15, 'Proporcionaria veterinário regular', 3, 5);

SET session_replication_role = 'origin';

SELECT setval('hibernate_sequence', (SELECT MAX(id) FROM usuario) + 1);
SELECT setval('status_animal_id_seq', (SELECT MAX(id) FROM status_animal) + 1);
SELECT setval('animal_id_seq', (SELECT MAX(id) FROM animal) + 1);
SELECT setval('status_solicitacao_id_seq', (SELECT MAX(id) FROM status_solicitacao) + 1);
SELECT setval('solicitacao_adocao_id_seq', (SELECT MAX(id) FROM solicitacao_adocao) + 1);
SELECT setval('protocolo_vacina_id_seq', (SELECT MAX(id) FROM protocolo_vacina) + 1);
SELECT setval('registro_vacina_id_seq', (SELECT MAX(id) FROM registro_vacina) + 1);
SELECT setval('questionario_id_seq', (SELECT MAX(id) FROM questionario) + 1);
SELECT setval('pergunta_id_seq', (SELECT MAX(id) FROM pergunta) + 1);
SELECT setval('resposta_questionario_id_seq', (SELECT MAX(id) FROM resposta_questionario) + 1);
