package cl.sprint_rocket_ai.msdevmentor.doc_ddl.application;

import cl.sprint_rocket_ai.msdevmentor.commons.domain.exceptions.EntityNotFoundException;
import cl.sprint_rocket_ai.msdevmentor.doc_ddl.domain.models.DocumentoDDL;
import cl.sprint_rocket_ai.msdevmentor.doc_ddl.domain.ports.out.DocumentoDDLPortOut;
import cl.sprint_rocket_ai.msdevmentor.doc_ddl.infrastructure.in.dtos.DocumentoDDLRequest;
import cl.sprint_rocket_ai.msdevmentor.doc_ddl.infrastructure.in.dtos.DocumentoDDLResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public final class UpdateDocumentoDDL {

    private static final Logger log = LoggerFactory.getLogger(UpdateDocumentoDDL.class);
    private final DocumentoDDLPortOut documentoDDLPortOut;

    public UpdateDocumentoDDL(DocumentoDDLPortOut documentoDDLPortOut) {
        this.documentoDDLPortOut = documentoDDLPortOut;
    }

    public DocumentoDDLResponse execute(String id, DocumentoDDLRequest request) {
        log.info("Iniciando actualización de documento DDL con id: {}", id);
        return documentoDDLPortOut.findById(id)
                .map(existing -> {
                    request.applyTo(existing);
                    existing.setFechaActualizacion(LocalDateTime.now());
                    DocumentoDDL updated = documentoDDLPortOut.save(existing);
                    log.info("Fin de la actualización del documento DDL con id: {}", id);
                    return updated;
                })
                .map(DocumentoDDLResponse::from)
                .orElseThrow(() -> new EntityNotFoundException("documento DDL con id: " + id));
    }
}
