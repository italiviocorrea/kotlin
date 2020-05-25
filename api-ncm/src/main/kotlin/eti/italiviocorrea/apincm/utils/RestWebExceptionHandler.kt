package eti.italiviocorrea.apincm.utils

import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebExceptionHandler
import reactor.core.publisher.Mono

@Component
@Order(-2)
class RestWebExceptionHandler : WebExceptionHandler {
    override fun handle(exchange: ServerWebExchange, ex: Throwable): Mono<Void> {
        if (ex is NcmNotFoundException) {
            exchange.response.statusCode = HttpStatus.NOT_FOUND

            // marks the response as complete and forbids writing to it
            return exchange.response.setComplete()
        }
        return Mono.error(ex)
    }
}