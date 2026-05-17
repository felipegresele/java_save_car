-- V3: Adicionando usuario id na tabela de carro

ALTER TABLE carro
ADD COLUMN usuario_id BIGINT;

ALTER TABLE carro
ADD CONSTRAINT fk_carro_usuario
FOREIGN KEY (usuario_id)
REFERENCES usuario(id)