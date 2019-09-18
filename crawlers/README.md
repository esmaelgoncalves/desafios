# ARQUITETURA

## COMPONENTES

* CLI - Aplicação de linha de comando java que se conecta a API fornecida para realizar as consultas solicitadas.

* API/CRAWLER - API para recebimento de requisições do CLI, encaminha as solicitações recebidas para o serviço que implementar o Crawler.
* BOT/CRAWLER - Implementação do Bot Telegram, utilizando as APIS disponibilizadas pelo Telegram. Implementa a lógica do BOT. Recebe, trata e responde as solicitações vindas do BOT que está disponível no APP Telegram.
