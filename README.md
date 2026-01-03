# Smart Edital

## Visão Geral

**Smart Edital** é um projeto pessoal cujo objetivo é automatizar a **classificação e organização de editais voltados à inovação tecnológica e startups**. Editais costumam apresentar grande diversidade de critérios — como público-alvo, nível de maturidade, linhas de pesquisa e tipos de concessão — e este projeto surge para estruturar essas informações de forma inteligente e escalável, facilitando análises, filtros e futuras recomendações.

O sistema foi desenvolvido utilizando **arquitetura de microsserviços**, adotando o **ecossistema Spring** como base tecnológica, com foco em boas práticas de engenharia de software, desacoplamento, escalabilidade e organização de domínio.

---

## Problema que o Projeto Busca Resolver

Editais de inovação geralmente apresentam características como:

* Diferentes **níveis de maturidade** (ideação, validação, tração, escala)
* Diversas **linhas de pesquisa ou áreas tecnológicas**
* Tipos variados de **benefícios e concessões**, como:

  * Bolsas
  * Custeio
  * Equipamentos
  * Incubação / aceleração

Essas informações costumam estar descritas de forma textual e pouco padronizada, o que dificulta a análise sistemática e a comparação entre editais.

O **Smart Edital** foi concebido para estruturar esses dados, permitindo que editais sejam classificados e consumidos por sistemas ou usuários de forma mais objetiva e automatizada.

---

## Arquitetura

O projeto foi desenvolvido seguindo uma **arquitetura de microsserviços**, onde cada serviço possui responsabilidade bem definida, comunicação via APIs REST e **banco de dados isolado**.

### Principais características da arquitetura

* Microsserviços independentes
* Comunicação via HTTP (REST)
* Descoberta de serviços dinâmica
* Gateway centralizado
* Banco de dados por serviço
* Orquestração via Docker Compose

---

## Stack Tecnológica

### Backend / Infraestrutura

* **Java 8+**
* **Spring Boot**
* **Spring Web** – criação das APIs REST
* **Spring Data JPA** – persistência de dados
* **Spring Security** – segurança e autenticação
* **Spring Cloud Netflix Eureka** – service discovery
* **Spring Cloud Gateway** – API Gateway
* **PostgreSQL** – banco de dados relacional (um por microsserviço)
* **Docker & Docker Compose** – conteinerização e orquestração

---

## Microsserviços

Cada microsserviço possui:

* Responsabilidade de negócio específica
* API REST própria
* Banco de dados PostgreSQL dedicado
* Comunicação com outros serviços via API

Além dos serviços de negócio, o projeto conta com serviços de infraestrutura essenciais:

### Service Discovery (Eureka)

* Responsável por registrar e localizar os microsserviços
* Permite que os serviços descubram uns aos outros dinamicamente

### API Gateway

* Ponto único de entrada da aplicação
* Centraliza:

  * Roteamento
  * Segurança
  * Exposição das APIs

---

## Persistência de Dados

* Cada microsserviço possui seu **próprio banco PostgreSQL**, garantindo:

  * Isolamento de dados
  * Baixo acoplamento
  * Maior flexibilidade de evolução

* Persistência realizada via **Spring Data JPA**

---

## Conteinerização e Orquestração

Todo o ecossistema é executado através de **Docker Compose**, que é responsável por subir:

* Containers dos microsserviços de negócio
* Containers dos bancos de dados PostgreSQL
* Container do Eureka Server
* Container do API Gateway

O `docker-compose.yml` define:

* Dependências entre serviços
* Portas expostas
* Variáveis de ambiente
* Conexões com os bancos de dados

Com um único comando, todo o ambiente é inicializado de forma integrada.

---

## Objetivos do Projeto

Este projeto teve como principais objetivos:

* Aplicar conceitos de **arquitetura de microsserviços** na prática
* Explorar o **ecossistema Spring Cloud**
* Trabalhar com **service discovery e API gateway**
* Utilizar **banco de dados por serviço**
* Padronizar e estruturar informações complexas de editais
* Criar uma base sólida para futuras evoluções, como:

  * Classificação automática
  * Recomendação de editais
  * Integração com modelos de NLP ou ML

---

## Status do Projeto

O projeto representa um **estudo arquitetural e técnico**, com foco em design, infraestrutura e organização de serviços. Ele pode ser expandido para:

* Novas regras de classificação
* Integração com mecanismos de busca
* Dashboards analíticos
* Integração com modelos de IA

---

## Observações Finais

Este repositório demonstra a aplicação de boas práticas de backend moderno, microsserviços e DevOps em um contexto real de negócio, sendo um projeto pessoal voltado ao aprendizado avançado e consolidação de conceitos de arquitetura distribuída.

---

📌 *Este README complementa o README técnico já existente, que descreve os passos de configuração e execução do projeto.*


## Configuração do Ambiente

Para garantir o funcionamento correto da aplicação, é necessário definir algumas variáveis de ambiente no arquivo `.env`.

## Configurações no .env
No arquivo `.env`, defina as seguintes variáveis com os valores apropriados para o seu ambiente:

## Rodando na máquina:
- É preciso alterar os endereços dos containers no arquivo `application.properties` para `localhost`
- É preciso alterar o endereço do banco de dados no arquivo `application.properties` para `localhost`

## Rodando através do docker-compose

```ini
POSTGRES_EDITAL_USER=<seu_usuario>
POSTGRES_EDITAL_PASSWORD=<sua_senha>
POSTGRES_EDITAL_DB=<seu_banco>

SPRING_DATASOURCE_EDITAL_URL=<sua_url_de_conexao>
SPRING_DATASOURCE_EDITAL_USERNAME=<seu_usuario>
SPRING_DATASOURCE_EDITAL_PASSWORD=<sua_senha>

POSTGRES_PESQUISADOR_EMPREENDEDOR_USER=<seu_usuario>
POSTGRES_PESQUISADOR_EMPREENDEDOR_PASSWORD=<sua_senha>
POSTGRES_PESQUISADOR_EMPREENDEDOR_DB=<seu_banco>

SPRING_DATASOURCE_PESQUISADOR_EMPREENDEDOR_URL=<sua_url_de_conexao>
SPRING_DATASOURCE_PESQUISADOR_EMPREENDEDOR_USERNAME=<seu_usuario>
SPRING_DATASOURCE_PESQUISADOR_EMPREENDEDOR_PASSWORD=<sua_senha>

CHAVE_API_GEMINI=<sua_chave_de_api_do_google_gemini>

```

#### Descrição das Variáveis
##### Configurações do banco edital
- `POSTGRES_EDITAL_USER`: Nome do usuário do banco de dados PostgreSQL.
- `POSTGRES_EDITAL_PASSWORD`: Senha do usuário do banco de dados.
- `POSTGRES_EDITAL_DB`: Nome do banco de dados a ser utilizado.
##### Configurações do serviço de editais
- `SPRING_DATASOURCE_EDITAL_URL`: URL de conexão JDBC para o banco de dados PostgreSQL.
- `SPRING_DATASOURCE_EDITAL_USERNAME`: Nome de usuário para conexão via Spring Boot.
- `SPRING_DATASOURCE_EDITAL_PASSWORD`: Senha para conexão via Spring Boot.
##### Configurações do banco pesquisador empreendedor
- `POSTGRES_PESQUISADOR_EMPREENDEDOR_USER`: Nome do usuário do banco de dados PostgreSQL.
- `POSTGRES_PESQUISADOR_EMPREENDEDOR_PASSWORD`: Senha do usuário do banco de dados.
- `POSTGRES_PESQUISADOR_EMPREENDEDOR_DB`: Nome do banco de dados a ser utilizado.
##### Configurações do serviço de pesquisador empreendedor
- `SPRING_DATASOURCE_PESQUISADOR_EMPREENDEDOR_URL`: URL de conexão JDBC para o banco de dados PostgreSQL.
- `SPRING_DATASOURCE_PESQUISADOR_EMPREENDEDOR_USERNAME`: Nome de usuário para conexão via Spring Boot.
- `SPRING_DATASOURCE_PESQUISADOR_EMPREENDEDOR_PASSWORD`: Senha para conexão via Spring Boot.

##### Configurações do serviço de editais
- `SPRING_DATASOURCE_EDITAL_URL`: URL de conexão JDBC para o banco de dados PostgreSQL.
- `SPRING_DATASOURCE_EDITAL_USERNAME`: Nome de usuário para conexão via Spring Boot.
- `SPRING_DATASOURCE_EDITAL_PASSWORD`: Senha para conexão via Spring Boot.


