# Desafio 2: Crawlers - Parte 2

## Resolução - Crawler/API/Telegram Bot

A resolução do desafio proposto passou pela construção de um web crawler para que fosse possível 
extrair as informações necessárias do site Reddit.

Para isso foi utilizado a ferramenta JSoup.

* Foi desenvolvimento um Bot para Telegram (@EsmaelIdWallBot). Try it now =) - (A aplicação está up na AWS)
* Para a validação dos testes utilizar (@TstEsmaelIdWallBot).

### Amazon AWS

* A API/Crawler está disponível na AWS para testes em: `http://ec2-54-207-16-239.sa-east-1.compute.amazonaws.com:8080`
* SWAGGER: `http://ec2-54-207-16-239.sa-east-1.compute.amazonaws.com:8080/swagger-ui.html`

##Para rodar local
Configurações da aplicação:

    telegram.bot.name= (name telegram bot - definido no @BotFather)
    telegram.bot.username= (username telegram bot - definido no @BotFather)
    telegram.bot.token= (token de acesso telegram bot - gerado pelo @BotFather)
    reddit.url.base= (url de base do reddit - http://old.reddit.com)
    reddit.min-votes= (quantidade mínimas de votos para considerar uma thread)
    
###DOCKER
* Execute o script run.bat ou run.sh

ou na pasta do projeto:

* Execute docker build -t reddit-telegram-bot-api . 
* docker-compose up

### Build Convencional
Ao invés de utilizar o docker, pode ser utilizado o build maven convencional, para isso execute o comando: 
`maven clean package`

### Execução

`java -jar reddit-telegram-bot-cli-0.0.1-SNAPSHOT.jar`

* Para verificar se a API está UP: `http://localhost:8080/actuator/health`
* Para acessar o swagger-ui e testar a API: `http://localhost:8080/swagger-ui.html`

### Enpoint

* `[GET] - /api/v1/threads/{threads}`

#### Exemplo:

`http://localhost:8080/api/v1/threads/cats;leagueoflegends;gameofthrones`

#### Exemplo CURL:

`curl -X GET "http://localhost:8080/api/v1/threads/cats;leagueoflegends;gameofthrones" -H "accept: */*"`


