package eti.italiviocorrea.apincm.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.core.DatabaseClient

@Configuration
class DBConfiguration(db: DatabaseClient) {
    init {
        val initDb = db.execute {
            """ CREATE TABLE IF NOT EXISTS ncms (
                    codigo Integer PRIMARY KEY,
                    nome varchar(255),
                    inicioVigencia Date,
                    fimVigencia Date NULL,
                    uTrib varchar(20),
                    uTribDescricao varchar(255)
                );
            """
        }
        initDb.then().subscribe()
    }
}
