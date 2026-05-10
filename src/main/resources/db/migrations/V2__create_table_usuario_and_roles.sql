-- V2: Migration criando tabela role (populada) e usuario no banco de dados

CREATE TABLE role (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    user_password VARCHAR(255) NOT NULL
);

CREATE TABLE usuarios_roles (
    usuario_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (usuario_id, role_id),
    CONSTRAINT fk_usuarios_roles_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    CONSTRAINT fk_usuarios_roles_role FOREIGN KEY (role_id) REFERENCES role(id)
);

INSERT INTO role (id, name) VALUES (1, "OPERADOR");
INSERT INTO role (id, name) VALUES (2, "ADMIN");