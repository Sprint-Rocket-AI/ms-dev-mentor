package cl.sprint_rocket_ai.ms_context_builder.doc_ddl.application;

import cl.sprint_rocket_ai.ms_context_builder.commons.domain.exceptions.EntityNotFoundException;
import cl.sprint_rocket_ai.ms_context_builder.doc_ddl.domain.ports.out.DocumentoDDLPortOut;
import cl.sprint_rocket_ai.ms_context_builder.doc_ddl.infrastructure.in.dtos.DocumentoDDLResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public final class GetDocumentoDDLById {
    private static final Logger log = LoggerFactory.getLogger(GetDocumentoDDLById.class);

    private final DocumentoDDLPortOut documentoDDLPortOut;

    public GetDocumentoDDLById(DocumentoDDLPortOut documentoDDLPortOut) {
        this.documentoDDLPortOut = documentoDDLPortOut;
    }

    public DocumentoDDLResponse execute(String id) {
        log.info("Iniciando obtención de documento DDL con id: {}", id);
        DocumentoDDLResponse response = documentoDDLPortOut.findById(id)
                .map(DocumentoDDLResponse::from)
                .orElseThrow(() -> new EntityNotFoundException("documento DDL con id: " + id));
        log.info("Fin de la búsqueda, documento DDL encontrado");
        return response;
    }
}

