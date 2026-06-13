package cl.sprint_rocket_ai.ms_context_builder.doc_ddl.infrastructure.out;

import cl.sprint_rocket_ai.ms_context_builder.doc_ddl.domain.models.DocumentoDDL;
import cl.sprint_rocket_ai.ms_context_builder.doc_ddl.domain.ports.out.DocumentoDDLPortOut;
import cl.sprint_rocket_ai.ms_context_builder.doc_ddl.infrastructure.persistences.mongodb.DocumentoDDLMongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public final class DocumentoDDLAdapterOut implements DocumentoDDLPortOut {

    private static final Logger log = LoggerFactory.getLogger(DocumentoDDLAdapterOut.class);
    private final DocumentoDDLMongoRepository documentoDDLMongoRepository;

    public DocumentoDDLAdapterOut(DocumentoDDLMongoRepository documentoDDLMongoRepository) {
        this.documentoDDLMongoRepository = documentoDDLMongoRepository;
    }

    @Override
    public DocumentoDDL save(DocumentoDDL documentoDDL) {
        log.info("Guardando Documento DDL en MongoDB");
        DocumentoDDL persistido = documentoDDLMongoRepository.save(documentoDDL);
        log.info("Documento DDL guardado con ID: {}", persistido.getId());
        return persistido;
    }

    @Override
    public Optional<DocumentoDDL> findById(String id) {
        log.info("Buscando Documento DDL por ID: {}", id);
        return documentoDDLMongoRepository.findById(id);
    }

}

