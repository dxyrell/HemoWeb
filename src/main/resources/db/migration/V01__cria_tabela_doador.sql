CREATE TABLE public.doador (
    cpf VARCHAR(11) NOT NULL, -- CPF como chave primária, formato de 11 caracteres
    nome TEXT NOT NULL,       -- Nome do doador
    tipo_sanguineo TEXT NOT NULL, -- Tipo sanguíneo (exemplo: A+, O-)
    telefone VARCHAR(15),     -- Telefone do doador
    email TEXT,               -- Email do doador
    data_nascimento DATE,     -- Data de nascimento do doador
    PRIMARY KEY (cpf)         -- Definição de chave primária
);
