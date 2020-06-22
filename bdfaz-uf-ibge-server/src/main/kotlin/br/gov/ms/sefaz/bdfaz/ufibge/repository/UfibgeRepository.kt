package br.gov.ms.sefaz.bdfaz.ufibge.repository

import br.gov.ms.sefaz.bdfaz.ufibge.model.Ufibge
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import io.github.resilience4j.retry.annotation.Retry
import io.github.resilience4j.timelimiter.annotation.TimeLimiter
import org.eclipse.microprofile.opentracing.Traced
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@CircuitBreaker(name = "ufibgeBackend")
@TimeLimiter(name = "ufibgeBackend")
@Retry(name = "ufibgeBackend")
@Traced
interface UfibgeRepository : ReactiveCrudRepository<Ufibge, Int> {


    @Query("select * from uf_ibge where uf_ibge_codigo = :codigo")
    fun buscarPorCodigo(codigo: Int): Mono<Ufibge>

    @Query("select * from uf_ibge order by uf_ibge_nome ")
    fun buscarTodos(): Flux<Ufibge>

}