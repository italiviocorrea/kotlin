package br.gov.ms.sefaz.bdfaz.ufibge.endpoint

import br.gov.ms.sefaz.bdfaz.ufibge.model.Ufibge
import br.gov.ms.sefaz.bdfaz.ufibge.service.UfibgeService
import br.gov.ms.sefaz.bdfaz.ufibge.utils.UfibgeInternalErrorException
import br.gov.ms.sefaz.bdfaz.ufibge.utils.UfibgeNoContentException
import br.gov.ms.sefaz.bdfaz.ufibge.utils.UfibgeNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/bdfaz/api/v1/ufibge")
class UfibgeEndpoint(val ufibgeService: UfibgeService) {

    private val logs = LoggerFactory.getLogger(UfibgeEndpoint::class.java)


    @GetMapping(value = ["/"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun buscarTodos(): Flux<Ufibge?>? {
//        return ufibgeService.findAll()
//                .sort(Comparator.comparing(Ufibge::uf_ibge_nome))
//                .log("Buscar Todos")
        return ufibgeService.buscarTodos()
                .switchIfEmpty(Flux.error(UfibgeNoContentException()))
                .log("Buscar Todas UFs")
                .onErrorMap { _ -> UfibgeInternalErrorException() }
    }

    @GetMapping(value = ["/{codigo}"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun buscarPorCodigo(@PathVariable("codigo") codigo: Int): Mono<Ufibge?>? {
        return ufibgeService.buscarPorCodigo(codigo)
                ?.switchIfEmpty(Mono.error(UfibgeNotFoundException(codigo)))
                ?.log("Buscar UF com codigo :"+codigo)
                .onErrorMap { _ -> UfibgeInternalErrorException() }
    }
}