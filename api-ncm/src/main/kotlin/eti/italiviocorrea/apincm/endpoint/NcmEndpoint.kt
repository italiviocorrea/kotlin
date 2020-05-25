package eti.italiviocorrea.apincm.endpoint

import eti.italiviocorrea.apincm.model.Ncms
import eti.italiviocorrea.apincm.repository.NcmRepository
import eti.italiviocorrea.apincm.utils.NcmNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono



@RestController
@RequestMapping("/dfe/api/v1/ncms")
class NcmEndpoint(val ncmRepository: NcmRepository) {

    private val logs = LoggerFactory.getLogger(NcmEndpoint::class.java)

    @GetMapping(value = ["/"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun buscarTodos(): Flux<Ncms> {
        return ncmRepository.findAll()
    }

    @GetMapping(value = ["/{codigo}"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun buscarPorCodigo(@PathVariable codigo: Int): Mono<Ncms?>? {
        return ncmRepository.buscarPorCodigo(codigo)
                ?.switchIfEmpty(Mono.error(NcmNotFoundException(codigo)))
    }

    @PostMapping(value = ["/"],
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.CREATED)
    fun inserir(@RequestBody ncm: Ncms): Mono<Void> {
        logs.info(ncm.toString())
        return ncmRepository.inserir(ncm.codigo,
                ncm.nome,
                ncm.iniciovigencia,
                ncm.fimvigencia,
                ncm.utrib,
                ncm.utribdescricao).then()
    }

    @DeleteMapping(
            value = ["/{codigo}"],
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    fun remover(@PathVariable("codigo") codigo: Int): Mono<Void?>? {
        return ncmRepository.buscarPorCodigo(codigo)
                ?.switchIfEmpty(Mono.error(NcmNotFoundException(codigo)))
                ?.flatMap { _ -> ncmRepository.remover(codigo) }
                .then(Mono.empty())
    }

    @PutMapping(value = ["/{codigo}"],
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    fun atualizar(@PathVariable codigo: Int, @RequestBody ncm: Ncms): Mono<Void?>? {
        return ncmRepository.buscarPorCodigo(codigo)
                .switchIfEmpty(Mono.error(NcmNotFoundException(codigo)))
                .flatMap { _ ->
                    ncmRepository.atualizarNcm(ncm.nome,
                            ncm.iniciovigencia,
                            ncm.fimvigencia,
                            ncm.utrib,
                            ncm.utribdescricao,
                            codigo)
                }.then()

    }

}

