# Desafio 2: Crawlers - Parte 1

## Resolução - CLI
Foi desenvolvido um CLI que se conecta a uma API (reddit-telegram-bot) que fornece o endpoint: `[GET] - /api/v1/threads/{threads}`.
Essa API utiliza o Crawler desenvolvido e busca as informações relevantes dos tópicos solicitados.

Apenas para simplificar a resolução do desafio, não foram adicionadas configurações de segurança a API e o CLI.
**Observação**: Em um caso real para produção, o ideal é adicionar as features de segurança necessárias (Autenticação - Basic/Oauth2 e TOKEN_API).

## Build

`maven clean package`

## Execução com Configuração DEFAULT
`java -jar reddit-telegram-bot-cli-1.0-SNAPSHOT.jar`

## Execução Parametrizada
`arg1 - Endereço da API`

`arg2 - tópicos do reddit`

`java -jar reddit-telegram-bot-cli-1.0-SNAPSHOT.jar [arg1] [arg2]`

Exemplo:
`java -jar reddit-telegram-bot-cli-1.0-SNAPSHOT.jar "http://localhost:8080/" cats`

Exemplo de Saída:
       
    Inputs: 
    Base URL: http://localhost:8080/
    Threads: cats
    =========================
 
    Aguarde estamos buscando o conteúdo solicitado.
 
    Veja só o que encontramos para você:
 
    Título: Photogenic cat.
    Sub Reddit: cats
    Votos: 10449
    Acesse a thread pelo link: https://old.reddit.com/r/cats/comments/d5971y/photogenic_cat/
    Veja os comentarios pelo link: https://old.reddit.com/r/cats/comments/d5971y/photogenic_cat/
 
    Título: The face my cat makes when she has to sneeze
    Sub Reddit: cats
    Votos: 8143
    Acesse a thread pelo link: https://old.reddit.com/r/cats/comments/d5406c/the_face_my_cat_makes_when_she_has_to_sneeze/
    Veja os comentarios pelo link: https://old.reddit.com/r/cats/comments/d5406c/the_face_my_cat_makes_when_she_has_to_sneeze/
 
    Título: High paw
    Sub Reddit: cats
    Votos: 6264
    Acesse a thread pelo link: https://old.reddit.com/r/cats/comments/d54rr8/high_paw/
    Veja os comentarios pelo link: https://old.reddit.com/r/cats/comments/d54rr8/high_paw/
 
    Título: My 17 year old cat
    Sub Reddit: cats
    Votos: 5613
    Acesse a thread pelo link: https://old.reddit.com/r/cats/comments/d5esgu/my_17_year_old_cat/
    Veja os comentarios pelo link: https://old.reddit.com/r/cats/comments/d5esgu/my_17_year_old_cat/`