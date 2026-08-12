# DSCatalog

Projeto de estudo em **Spring Boot**, com o objetivo de praticar arquitetura em camadas, persistência de dados, segurança e boas práticas de desenvolvimento back-end.

A aplicação simula um catálogo de produtos, permitindo o gerenciamento de **categorias** e **produtos**, com relacionamento entre eles, autenticação, paginação, tratamento de erros e testes automatizados.

---

## Tecnologias utilizadas

- **Java 21**
- **Spring Boot**
- **Spring Data JPA / Hibernate**
- **Spring Security** (Basic Auth)
- **PostgreSQL** (banco de dados de produção/desenvolvimento)
- **H2 Database** (banco em memória para testes)
- **Maven**
- **JUnit 5** (testes automatizados)
- **Lombok**

---

## Arquitetura do projeto

O projeto segue a arquitetura em camadas, padrão amplamente utilizado em aplicações Spring Boot:

```
Resource (Controller)  →  recebe requisições HTTP e devolve respostas
        ↓
Service                →  regras de negócio e orquestração
        ↓
Repository             →  acesso e manipulação de dados no banco
        ↓
Entity                 →  representação das tabelas do banco de dados
```

Além disso, o projeto utiliza **DTOs (Data Transfer Objects)** para desacoplar a camada de exposição da API das entidades do banco, evitando expor dados sensíveis ou desnecessários e prevenindo problemas de serialização em relacionamentos.

---

## Funcionalidades implementadas

### Categorias (`/categories`)
- `GET /categories` — lista todas as categorias
- `GET /categories/{id}` — busca uma categoria por id
- `POST /categories` — cadastra uma nova categoria
- `PUT /categories/{id}` — atualiza uma categoria existente
- `DELETE /categories/{id}` — remove uma categoria

### Produtos (`/products`)
- `GET /products` — lista produtos de forma **paginada e ordenável**
- `GET /products/{id}` — busca um produto por id, incluindo suas categorias associadas
- `POST /products` — cadastra um novo produto, com vínculo a categorias (`@ManyToMany`)
- `PUT /products/{id}` — atualiza um produto existente
- `DELETE /products/{id}` — remove um produto

### Segurança
- Autenticação via **Basic Auth** (Spring Security)
- Configuração de política de CSRF adequada para API stateless

### Tratamento de exceções
- `ResourceNotFoundException` — retornada quando um recurso não é encontrado (404)
- `DatabaseException` — retornada em casos de violação de integridade referencial (400)

### Persistência
- Timestamps automáticos de criação e atualização (`@PrePersist` / `@PreUpdate`)
- Uso de **H2** para ambiente de desenvolvimento/testes rápidos
- Uso de **PostgreSQL** como banco relacional principal

### Testes
- Testes unitários com **JUnit 5**, cobrindo regras de negócio das entidades

---

## Como rodar o projeto localmente

### Pré-requisitos
- Java 21 instalado
- Maven instalado (ou usar o wrapper `./mvnw`)
- PostgreSQL rodando localmente (opcional, o projeto também roda com H2)

### Passos

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/dscatalog.git

# Acesse a pasta do projeto
cd dscatalog

# Rode a aplicação
./mvnw spring-boot:run
```

A aplicação sobe por padrão na porta `8080`:
```
http://localhost:8080
```

### Banco de dados H2 (ambiente de desenvolvimento)

Console web disponível em:
```
http://localhost:8080/h2-console
```

### Variáveis de ambiente (PostgreSQL)

Caso queira rodar com PostgreSQL, configure as seguintes variáveis (ou ajuste diretamente o `application.properties`):

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/dscatalog
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

---

## Rodando os testes

```bash
./mvnw test
```

---

## Modelo de entidades

```
Category
├── id
├── name
├── createdAt
└── updatedAt

Product
├── id
├── name
├── description
├── price
├── imgUrl
├── date
└── categories (N:N com Category)
```
