# Desafio 2: Crawlers - Parte 2

## Resolução - Crawler/API/Telegram Bot


##DOCKER
* Execute o script run.bat ou run.sh

ou na pasta do projeto:

* Execute docker build -t reddit-telegram-bot-api . 
* docker-compose up

## Build Convencional
Ao invés de utilizar o docker, pode ser utilizado o build maven convencional, para isso execute o comando: 
`maven clean package`

## Execução

`java -jar reddit-telegram-bot-cli-0.0.1-SNAPSHOT.jar`

* Para verificar se a API está UP: `http://localhost:8080/actuator/health`
* Para acessar o swagger-ui e testar a API: `http://localhost:8080/swagger-ui.html`

## Enpoint

* `[GET] - /api/v1/threads/{threads}`

#### Exemplo:

`http://localhost:8080/api/v1/threads/cats;leagueoflegends;gameofthrones`

#### Exemplo CURL:

`curl -X GET "http://localhost:8080/api/v1/threads/cats;leagueoflegends;gameofthrones" -H "accept: */*"`
