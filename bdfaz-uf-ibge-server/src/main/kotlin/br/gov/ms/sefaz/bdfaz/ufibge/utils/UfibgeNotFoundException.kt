package br.gov.ms.sefaz.bdfaz.ufibge.utils

class UfibgeNotFoundException(id: Int) : RuntimeException("UF :$id não encontrado!.")
