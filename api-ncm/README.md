# Projeto API REST NCM com Spring Boot WebFlux + R2DBC postgresql

Neste projeto, o objetivo principal é testar o uso do spring boot webflux no desenvolvimento de uma API REST reativa.
O assunto a ser automatizado é a tabela de NCM  (Nomenclatura Comum do Mercosul) do Portal Nacional da Nota Fiscal Eletrônica.
O webflux é a stack reativa do spring boot, é totalmente sem bloqueio, suporta contra pressão de fluxo reativo, pode ser
usado com servidores Netty, Undertown e Servlet 3.1+.
O objetivo do webflux é ser uma stack para desenvolvimento web sem bloqueio, para lidar com a simultaneadade com um pequeno número de threads,
e também menos uso de recurso de hardware.
