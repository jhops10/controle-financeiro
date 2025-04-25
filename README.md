# 💰 Sistema de Gerenciamento Financeiro

API REST desenvolvida em Java com Spring Boot para controle financeiro pessoal. Permite o cadastro de usuários, autenticação via JWT e o gerenciamento de receitas, despesas e resumos financeiros mensais.

---

## 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Security
- JWT (JSON Web Token)
- MySQL
- JUnit
- Mockito

---

## ⚙️ Funcionalidades

- ✅ Cadastro de usuários com senha criptografada (BCrypt)
- ✅ Autenticação com JWT (login via `/auth/login`)
- ✅ Registro de transações de **receita** e **despesa**
- ✅ Resumo financeiro mensal (income - expenses)
- ✅ Proteção de rotas com token JWT

---

## 🔐 Autenticação

A autenticação utiliza JWT. Após fazer login, o token deve ser usado no header das requisições protegidas:

Authorization: Bearer SEU_TOKEN_AQUI


---

## 📚 Endpoints Principais

### 🧑‍💼 Usuário

| Método | Rota        | Descrição               |
|--------|-------------|--------------------------|
| POST   | `/api/users`    | Cadastrar novo usuário   |
| POST   | `/auth/login` | Login e geração do token |

### 💸 Receitas (Incomes) (`/transactions/income`)

| Método | Rota                   | Descrição              |
|--------|------------------------|-------------------------|
| GET    | `/api/income/user/{userId}` | Listar receitas do usuário |
| POST   | `/api/incomes` | Cadastrar nova receita  |

### 🧾 Despesas (Expenses) (`/transactions/expense`)

| Método | Rota                    | Descrição              |
|--------|-------------------------|-------------------------|
| GET    | `/api/expense/user/{userId}` | Listar despesas do usuário |
| POST   | `/api/expense` | Cadastrar nova despesa  |

### 📊 Resumo (Summary) (`/transactions/summary`)

| Método | Rota                            | Descrição                           |
|--------|----------------------------------|--------------------------------------|
| GET    | `/api/summary/user/{userId}?month=4&year=2025` | Retorna o resumo do mês para o usuário logado |

---

## 🔧 Como Rodar o Projeto Localmente

1. Clone o repositório:
   ```bash
   git clone https://github.com/jhops10/personal-finance.git
   cd personal-finance

2. Configure o banco de dados MySQL:
   - Crie um banco no MySQL com o nome desejado (adicione o nome escolhido em application.yml).
   - Certifique-se de que o usuário e a senha do banco estão corretos e com as permissões necessárias.
   
4. Execute a aplicação com Maven:
   ```bash
   ./mvnw spring-boot:run

5. Testando a API:
   - A API estará disponível em: http://localhost:8080
   - Use o Postman, Insomnia ou qualquer ferramenta de testes HTTP para realizar requisições.
