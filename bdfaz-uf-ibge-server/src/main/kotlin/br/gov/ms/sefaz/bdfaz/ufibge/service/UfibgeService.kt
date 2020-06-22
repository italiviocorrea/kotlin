package br.gov.ms.sefaz.bdfaz.ufibge.service

import br.gov.ms.sefaz.bdfaz.ufibge.model.Ufibge
import br.gov.ms.sefaz.bdfaz.ufibge.repository.UfibgeRepository
import io.github.resilience4j.circuitbreaker.CircuitBreaker
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry
import org.eclipse.microprofile.opentracing.Traced
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Traced
@Service
class UfibgeService( val ufibgeRepository: UfibgeRepository, val circuitBreakerRegistry: CircuitBreakerRegistry) {

    fun buscarTodos(): Flux<Ufibge> {
        return CircuitBreaker.decorateSupplier(circuitBreakerRegistry.circuitBreaker("ufibgeBackend"),
                ufibgeRepository::buscarTodos).get()

    }

    fun buscarPorCodigo(codigo : Int): Mono<Ufibge> {
        return CircuitBreaker.decorateSupplier(circuitBreakerRegistry.circuitBreaker("ufibgeBackend")
        ) { ufibgeRepository.buscarPorCodigo(codigo) }.get()
    }

}