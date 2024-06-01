# ProducerAwardAnalyzer

Este projeto é uma API REST que mostra os produtores de filmes que ganharam prêmio com menor intervalo de tempo entre dois prêmios e com maior intervalo de tempo entre dois prêmios.

## Pré-requisitos
- Java 17 ou Docker

## Como rodar o projeto na JVM

1. Clone o repositório para a sua máquina local usando `git clone https://github.com/renatounai/texoit.git`.

2. Navegue até a pasta do projeto com `cd texoit`.

3. Para iniciar o servidor, execute o comando `./mvnw spring-boot:run`.

## Como rodar o projeto no Docker
1. Para criar a imagem do Docker, execute o comando `docker build -t producer-award-analyzer .`.
2. Para rodar o container, execute o comando `docker run -d -p 8080:8080 --name producer-award-analyzer producer-award-analyzer`.

## Como executar os testes

1. Navegue até a pasta do projeto.
2. Execute o comando `./mvnw test`.

## Endpoints

- GET `/api/summary`: Retorna os produtores de filmes que ganharam prêmio com menor intervalo de tempo entre dois prêmios e com maior intervalo de tempo entre dois prêmios.