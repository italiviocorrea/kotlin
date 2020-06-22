package br.gov.ms.sefaz.bdfaz.ufibge.model

import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("uf_ibge")
data class Ufibge(
        @Column("uf_ibge_codigo")
        var  codigo: Int,
        @Column("uf_ibge_nome")
        var  nome: String,
        @Column("uf_ibge_sigla")
        var  sigla: String
)