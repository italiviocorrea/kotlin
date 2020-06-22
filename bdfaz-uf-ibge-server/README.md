# Projeto API REST UF IBGE com Spring Boot Kotlin + WebFlux + R2DBC com banco de dados MSSQL Server

Neste projeto, o objetivo principal é testar o uso do spring boot webflux no desenvolvimento de uma API REST reativa.
O assunto a ser automatizado é a tabela de UF do IBGE  (Unidade Federativa do Brasil).
O objetivo principal deste projeto é criar uma API reativa que faça consulta ao banco de dados MSSQL Server, podendo ser
facilmente utilizado qualquer outro banco de dados relacional, mas recursos de health check (disco, memória e banco de dados),
métricas com prometheus, rastreamento (tracing) com opentracing e tolerância a falhas usando o framework Resilience4j.
Também estou aproveitando este projeto para aprender um pouco mais sobre a linguagem Kotlin, poderia fazer facilmente em
Java ou GO, mas estou gostando de kotlin. A minha primeira impressão com esta linguagem, é que ela parece um meio termo
entre Java e Scala.
Segue em anexo, um arquivo docker-compose.yaml, que permite subir as dependências necessárias para executar a API.
Mas no MSSQL Server é necessário criar manualmente o banco de dados e a tabela.




