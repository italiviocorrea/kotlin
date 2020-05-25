package eti.italiviocorrea.apincm.model

import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate

@Table
data class Ncms(
        var codigo: Int,
        var nome: String,
        var iniciovigencia: LocalDate,
        var fimvigencia: LocalDate?,
        var utrib: String,
        var utribdescricao: String

)
