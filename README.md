# Aplicação Backend com Spring Boot/ AngularFront e PostgreSQL no Docker, de Produto na Join

Este projeto é uma aplicação backend desenvolvida com **Spring Boot** e **PostgreSQL** como banco de dados, com o **PostgreSQL** rodando em um container Docker. A aplicação oferece uma API RESTful para gerenciar **categorias** e **produtos** e possui autenticação básica. O frontend é desenvolvido separadamente utilizando **Angular**.

## Arquitetura do Projeto

A aplicação segue uma arquitetura **backend Java** (Spring Boot) com **frontend Angular**, onde o **backend** se comunica com o banco de dados **PostgreSQL** que está rodando dentro de um container Docker.

### Componentes

1. **Spring Boot (Backend)**
   - Fornece a API REST para operações de gerenciamento de categorias e produtos.
   - Exige o PostgreSQL para persistir dados, que é executado em um container Docker.
   
2. **PostgreSQL (Banco de Dados)**
   - Contém as tabelas `categoria` e `produto`, onde os dados são armazenados.
   - Está configurado para ser inicializado automaticamente com um script SQL, que cria as tabelas necessárias.

3. **Angular (Frontend)**
   - A aplicação frontend consome a API fornecida pelo Spring Boot.
   - A comunicação é feita via requisições HTTP para os endpoints configurados.

## Endpoints da API

A aplicação oferece os seguintes endpoints para interagir com o banco de dados:

### **GET /api/categorias**
- Retorna uma lista de todas as categorias no sistema.

### **POST /api/categorias**
- Cria uma nova categoria no sistema.

### **GET /api/produtos**
- Retorna uma lista de todos os produtos no sistema.

### **POST /api/produtos**
- Cria um novo produto no sistema, associando-o a uma categoria.

### **GET /api/categorias/{id}/produtos**
- Retorna todos os produtos de uma categoria específica.

## Estrutura do Banco de Dados

O banco de dados **PostgreSQL** é usado para armazenar as informações de categorias e produtos. Ele é inicializado com o seguinte script SQL, que cria as tabelas no banco de dados.

### Tabelas

1. **Categoria**
   - **id**: Identificador único da categoria (chave primária).
   - **nome**: Nome da categoria.
   - **descricao**: Descrição da categoria.
   - **data_criacao**: Data de criação da categoria.

2. **Produto**
   - **id**: Identificador único do produto (chave primária).
   - **nome**: Nome do produto.
   - **descricao**: Descrição do produto.
   - **preco**: Preço do produto.
   - **categoria_id**: Relacionamento com a categoria (chave estrangeira).

### Script SQL (`init.sql`)

Este é o script SQL que será executado quando o PostgreSQL for iniciado:

```sql
-- Criar tabela de categorias
CREATE TABLE categoria (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Criar tabela de produtos
CREATE TABLE produto (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    preco DECIMAL(10, 2) NOT NULL,
    categoria_id INT REFERENCES categoria(id)
);
```

## Requisitos para Rodar o Projeto

Antes de rodar o projeto, verifique se você possui os seguintes pré-requisitos instalados:

| Requisito                    | Descrição                                                                                     |
|------------------------------|------------------------------------------------------------------------------------------------|
| **Docker**                    | Para rodar o banco de dados PostgreSQL em um container.                                         |
| **Java 11 ou superior** usei 17 | Para rodar a aplicação Spring Boot.                                                           |
| **Maven**                     | Para compilar e executar a aplicação Spring Boot.                                              |
| **PostgreSQL**                | Se não for usar o Docker, é necessário ter o PostgreSQL instalado na máquina.                  |

## Como Funciona

1. **Banco de Dados PostgreSQL no Docker**
   - O PostgreSQL é executado dentro de um container Docker. O banco é inicializado automaticamente com as tabelas `categoria` e `produto` utilizando o script `init.sql`, que está localizado no diretório `resources` do projeto.
   
2. **Spring Boot Local**
   - O **Spring Boot** roda localmente na sua máquina e se conecta ao PostgreSQL no Docker através do endereço de rede do Docker (`localhost` ou o IP do container, dependendo da configuração).
   - O Spring Boot possui endpoints RESTful para gerenciar categorias e produtos.

3. **Frontend Angular**
   - A aplicação Angular (não descrita aqui) consome os endpoints RESTful do Spring Boot para exibir e gerenciar categorias e produtos na interface do usuário.

## Comandos para Rodar a Aplicação

1. **Rodar o PostgreSQL com Docker**

   Para iniciar o banco de dados PostgreSQL dentro de um container Docker, execute o seguinte comando:

   ```bash
   docker run --name postgres-crud -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin -e POSTGRES_DB=crud_db -p 5432:5432 -d postgres
