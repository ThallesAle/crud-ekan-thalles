# crud kan java spring boot 

```
Descrição do Projeto: Gestão de Beneficiários

O Sistema de Gestão de Beneficiários é serviço backend desenvolvida para facilitar a administração e o controle de beneficiários em uma organização. Este sistema permite aos usuários realizar operações básicas de CRUD (Create, Read, Update, Delete) sobre os registros de beneficiários, garantindo eficiência e precisão na gestão de dados.

Funcionalidades Principais:

Cadastro de Beneficiários: Permite registrar novos beneficiários incluindo informações como nome, CPF, data de nascimento, endereço, entre outros dados relevantes.

Consulta de Beneficiários: Facilita a busca e visualização detalhada dos beneficiários cadastrados, possibilitando filtros por diversos critérios como nome, CPF ou status de benefício.

Atualização de Dados: Permite a edição de informações cadastradas dos beneficiários, assegurando a atualização precisa dos dados conforme necessário.

Remoção de Beneficiários: Possibilita a exclusão de registros de beneficiários quando necessário, garantindo a manutenção da base de dados atualizada e organizada.
```

# Pré Requisitos

Para que seja possível rodar essa aplicação é necessário atender alguns requisitos básicos.

- Java 17+
- Maven 3.3+ 

# Compilando e inicializando

Assim como todo projeto *Maven*, é necessário primeiramente realizarmos a geração dos fontes. Conforme o exemplo abaixo:

```bash
mvn clean install
```

`[INSIRA PARTICILARIDADES DO PROJETO AQUI]`

Certifique-se de o repositório do Maven está corretamente configurado. Após os fontes terem sido gerados, basta executar
o comando abaixo para inicializar a aplicação:

```
$ java -jar crud-kan-thalles-0.0.1.jar
```

Ou, se tiver importado por meio do IntelliJ, via classe `Application.java`. Para validar se a aplicação inicializou com
sucesso é necessario chamar o endpoint do *actuator* através do *link* abaixo:

```
http://localhost:8083/actuator/health
```

# Mostrando endpoints expostos

Assim que o projeto estiver sendo executado, é possível verificar as APIs expostas acessando a URL:

```
http://localhost:8083/swagger-ui/index.html
```
# Base de dados em Memória

ACESSANDO O CONSOLE DO H2
Agora, vamos acessar o console do H2 para ver se a tabela tb_beneficiario e tb_documento será criada, para isso será necessário subirmos a aplicação e acessarmos a rota: 

```
http://localhost:8083/h2-console
```

No caso JDBC URL usar as informações abaixo :


```
jdbc:h2:mem:testdb

user - sa
password - consulta o arquivo application.yml

```
