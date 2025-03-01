# Configuração do Ambiente

Para garantir o funcionamento correto da aplicação, é necessário definir algumas variáveis de ambiente no arquivo `.env`.

## Configurações no .env
No arquivo `.env`, defina as seguintes variáveis com os valores apropriados para o seu ambiente:

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

```

## Descrição das Variáveis
### Configurações do banco edital
- `POSTGRES_EDITAL_USER`: Nome do usuário do banco de dados PostgreSQL.
- `POSTGRES_EDITAL_PASSWORD`: Senha do usuário do banco de dados.
- `POSTGRES_EDITAL_DB`: Nome do banco de dados a ser utilizado.
### Configurações do serviço de editais
- `SPRING_DATASOURCE_EDITAL_URL`: URL de conexão JDBC para o banco de dados PostgreSQL.
- `SPRING_DATASOURCE_EDITAL_USERNAME`: Nome de usuário para conexão via Spring Boot.
- `SPRING_DATASOURCE_EDITAL_PASSWORD`: Senha para conexão via Spring Boot.
### Configurações do banco pesquisador empreendedor
- `POSTGRES_PESQUISADOR_EMPREENDEDOR_USER`: Nome do usuário do banco de dados PostgreSQL.
- `POSTGRES_PESQUISADOR_EMPREENDEDOR_PASSWORD`: Senha do usuário do banco de dados.
- `POSTGRES_PESQUISADOR_EMPREENDEDOR_DB`: Nome do banco de dados a ser utilizado.
### Configurações do serviço de pesquisador empreendedor
- `SPRING_DATASOURCE_PESQUISADOR_EMPREENDEDOR_URL`: URL de conexão JDBC para o banco de dados PostgreSQL.
- `SPRING_DATASOURCE_PESQUISADOR_EMPREENDEDOR_USERNAME`: Nome de usuário para conexão via Spring Boot.
- `SPRING_DATASOURCE_PESQUISADOR_EMPREENDEDOR_PASSWORD`: Senha para conexão via Spring Boot.


### Configurações do serviço de editais
- `SPRING_DATASOURCE_EDITAL_URL`: URL de conexão JDBC para o banco de dados PostgreSQL.
- `SPRING_DATASOURCE_EDITAL_USERNAME`: Nome de usuário para conexão via Spring Boot.
- `SPRING_DATASOURCE_EDITAL_PASSWORD`: Senha para conexão via Spring Boot.


