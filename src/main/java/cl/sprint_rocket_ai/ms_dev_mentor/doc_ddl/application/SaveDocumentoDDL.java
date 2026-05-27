package cl.sprint_rocket_ai.ms_dev_mentor.doc_ddl.application;

import cl.sprint_rocket_ai.ms_dev_mentor.doc_ddl.domain.models.DocumentoDDL;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_ddl.domain.ports.out.DocumentoDDLPortOut;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_ddl.infrastructure.in.dtos.DocumentoDDLRequest;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_ddl.infrastructure.in.dtos.DocumentoDDLResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public final class SaveDocumentoDDL {

    private static final Logger log = LoggerFactory.getLogger(SaveDocumentoDDL.class);
    private final DocumentoDDLPortOut documentoDDLPortOut;

    public SaveDocumentoDDL(DocumentoDDLPortOut documentoDDLPortOut) {
        this.documentoDDLPortOut = documentoDDLPortOut;
    }

    public DocumentoDDLResponse execute(DocumentoDDLRequest request) {
        log.info("Iniciando creación de documento DDL para el proyecto: {}", request.proyectoId());
        DocumentoDDL documentoDDL = new DocumentoDDL();
        request.applyTo(documentoDDL);
        documentoDDL.setFechaCreacion(LocalDateTime.now());
        DocumentoDDL saved = documentoDDLPortOut.save(documentoDDL);
        log.info("Fin de la creación del documento DDL con id: {}", saved.getId());
        return DocumentoDDLResponse.from(saved);
    }
}
