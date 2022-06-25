# Desafio programação

Por favor leiam este documento do começo ao fim, com muita atenção.
O intuito deste teste é avaliar seus conhecimentos técnicos em programação.
O teste consiste em parsear [este arquivo de texto(CNAB)](https://github.com/ByCodersTec/desafio-ruby-on-rails/blob/master/CNAB.txt) e salvar suas informações(transações financeiras) em uma base de dados a critério do candidato.
Este desafio deve ser feito por você em sua casa. Gaste o tempo que você quiser, porém normalmente você não deve precisar de mais do que algumas horas.

# Descrição do projeto

Você recebeu um arquivo CNAB com os dados das movimentações finanaceira de várias lojas.
Precisamos criar uma maneira para que estes dados sejam importados para um banco de dados.

Sua tarefa é criar uma interface web que aceite upload do [arquivo CNAB](https://github.com/ByCodersTec/desafio-ruby-on-rails/blob/master/CNAB.txt), normalize os dados e armazene-os em um banco de dados relacional e exiba essas informações em tela.

# Tecnologias utilizadas
**Backend**
*Java 11
*Spring Boot
*Lombock
*Banco de dados: Postgres e H2

**Frontend**
*ReactJs
*Bootstrap
*Axios

# Como executar o projeto

**Executar ambiente Docker**

1. Acessar a pasta raiz do projeto(onde está o arquivo docker-compose.yml).
2. Executar o comando compose: **docker-compose up** (OBS: esse comando irá criar as imagens e executar os containers docker das aplicações.)
 
A Aplicação web estará disponível em: http://localhost:80

# Consumir endpoint's

Com os containers em execução os endpoints estão prontos para serem consumidos.

A api possui dois endpoint's:

1. **POST** http://localhost:8080/api/upload  (Recebe o arquivo CNAB txt para processamento)
2. **GET** http://localhost:8080/api/stores   (Retorna a lista de lojas e suas transações)

Para realizar o processamento do arquivo CNAB.txt

curl --location --request POST 'http://localhost:8080/api/upload' --header 'Content-Type: multipart/form-data' --form 'file=@"
[Path-arquivo-CNAB]/CNAB.txt"'

Para realizar a requisição para obter as lojas e suas transações:

curl --location --request GET 'http://localhost:8080/api/stores'

# Documentação Swagger:
http://localhost:8080/swagger-ui.html