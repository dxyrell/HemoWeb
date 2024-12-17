-- Criação da tabela de usuários
CREATE TABLE usuarios (
    cpf INTEGER PRIMARY KEY, -- CPF como chave primária
    nome TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    senha TEXT NOT NULL,
    cargo TEXT NOT NULL
);

-- Criação da tabela de doadores
CREATE TABLE doadores (
    cpf INTEGER PRIMARY KEY, -- CPF como chave primária
    nome TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    telefone TEXT,
    endereco TEXT,
    nascimento DATE,
    tipo_sanguineo TEXT
);

-- Criação da tabela de bolsas de sangue
CREATE TABLE bolsas (
    id SERIAL PRIMARY KEY,
    status BOOLEAN NOT NULL,
    data TEXT NOT NULL,
    volume FLOAT NOT NULL,
    local TEXT NOT NULL,
    cpf_doador INTEGER NOT NULL,
    FOREIGN KEY (cpf_doador) REFERENCES doadores (cpf) ON DELETE CASCADE
);

-- Inserção de registros na tabela de usuários
INSERT INTO usuarios (cpf, nome, email, senha, cargo) VALUES 
(11111111111, 'João Silva', 'joao.silva@example.com', 'senha123', 'ADMIN'),
(22222222222, 'Maria Oliveira', 'maria.oliveira@example.com', 'senha123', 'USUARIO'),
(33333333333, 'Carlos Pereira', 'carlos.pereira@example.com', 'senha123', 'USUARIO'),
(44444444444, 'Ana Souza', 'ana.souza@example.com', 'senha123', 'ADMIN'),
(55555555555, 'Pedro Gomes', 'pedro.gomes@example.com', 'senha123', 'USUARIO');

-- Inserção de registros na tabela de doadores
INSERT INTO doadores (cpf, nome, email, telefone, endereco, nascimento, tipo_sanguineo) VALUES 
(11111111111, 'João Silva', 'joao.silva@example.com', '11999999999', 'Rua A, 123', '1985-05-10', 'O+'),
(22222222222, 'Maria Oliveira', 'maria.oliveira@example.com', '21999999999', 'Rua B, 456', '1990-08-15', 'A-'),
(33333333333, 'Carlos Pereira', 'carlos.pereira@example.com', '31999999999', 'Rua C, 789', '1975-12-20', 'B+'),
(44444444444, 'Ana Souza', 'ana.souza@example.com', '41999999999', 'Rua D, 101', '1980-07-25', 'AB+'),
(55555555555, 'Pedro Gomes', 'pedro.gomes@example.com', '51999999999', 'Rua E, 202', '1995-03-30', 'O-');

-- Inserção de registros na tabela de bolsas de sangue
INSERT INTO bolsas (status, data, volume, local, cpf_doador) VALUES 
(true, '2024-01-10', 450.0, 'Hospital Central', 11111111111),
(false, '2024-02-15', 350.0, 'Posto de Saúde', 22222222222),
(true, '2024-03-20', 500.0, 'Banco de Sangue', 33333333333),
(false, '2024-04-25', 400.0, 'Hospital Regional', 44444444444),
(true, '2024-05-30', 450.0, 'Clínica Médica', 55555555555);
