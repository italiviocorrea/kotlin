package eti.italiviocorrea.apincm.utils

class NcmNotFoundException(id: Int) : RuntimeException("Ncms:$id não encontrado!.")