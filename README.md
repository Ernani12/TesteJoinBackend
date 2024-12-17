# 🚀 Aplicação Backend com Spring Boot, Angular e PostgreSQL no Docker

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Angular**
- **PostgreSQL**
- **Docker**
- **Apache Maven**
- **Node.js** / **npm**

---

## Passos para Configuração

### 1. Instalação do Java 17

Certifique-se de que o Java 17 esteja instalado em seu sistema. Para verificar a instalação, utilize:

```bash
java -version


2. Configuração do PostgreSQL com Docker
Execute o comando abaixo para rodar o PostgreSQL no Docker:

bash
Copiar código
docker run --name postgres-crud -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin -e POSTGRES_DB=crud_db -p 5432:5432 -d postgres
POSTGRES_USER: Nome do usuário administrador.
POSTGRES_PASSWORD: Senha do banco.
POSTGRES_DB: Nome do banco de dados.
Para verificar o container em execução:

bash
Copiar código
docker ps
3. Compilar e Executar o Backend Spring Boot
Navegue até a pasta backend do projeto e execute os comandos:

bash
Copiar código
cd backend
mvn clean install
mvn spring-boot:run
A API será iniciada em:

http://localhost:8080

4. Configurar e Rodar o Frontend Angular
4.1 Instalar o Angular CLI
Se ainda não tiver o Angular CLI instalado, execute:

bash
Copiar código
npm install -g @angular/cli
4.2 Instalar Dependências
Na pasta do frontend, instale as dependências do projeto:

bash
Copiar código
cd frontend
npm install
4.3 Executar o Frontend
Inicie o servidor de desenvolvimento com:

bash
Copiar código
ng serve
A aplicação Angular estará disponível em:

http://localhost:4200
