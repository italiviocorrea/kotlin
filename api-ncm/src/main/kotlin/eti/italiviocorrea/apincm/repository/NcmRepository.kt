package eti.italiviocorrea.apincm.repository

import eti.italiviocorrea.apincm.model.Ncms
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.time.LocalDate


@Repository
interface NcmRepository : ReactiveCrudRepository<Ncms, Int> {

    @Query("insert into ncms(codigo,nome,iniciovigencia,fimvigencia,utrib,utribdescricao) values($1,$2,$3,$4,$5,$6)")
    fun inserir(codigo: Int, nome: String, iniciovigencia: LocalDate, fimvigencia: LocalDate?, utrib: String, utribdescricao: String): Mono<Ncms>

    @CircuitBreaker(name = "ncmsdb")
    @Query("select * from ncms where codigo = $1")
    fun buscarPorCodigo(codigo: Int): Mono<Ncms>

    @Query("delete from ncms where codigo = $1")
    fun remover(codigo: Int): Mono<Void?>?

    @Query("update ncms set nome=$1, iniciovigencia=$2, fimvigencia=$3, utrib = $4, utribdescricao = $5 where codigo = $6")
    fun atualizarNcm(nome: String,
                     iniciovigencia: LocalDate,
                     fimvigencia: LocalDate?,
                     utrib: String,
                     utribdescricao: String,
                     codigo: Int ): Mono<Ncms>

}
