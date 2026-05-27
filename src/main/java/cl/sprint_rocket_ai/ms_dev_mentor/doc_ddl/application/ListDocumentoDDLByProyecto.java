package cl.sprint_rocket_ai.ms_dev_mentor.doc_ddl.application;

import cl.sprint_rocket_ai.ms_dev_mentor.doc_ddl.domain.ports.out.DocumentoDDLPortOut;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_ddl.infrastructure.in.dtos.DocumentoDDLResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class ListDocumentoDDLByProyecto {
    private static final Logger log = LoggerFactory.getLogger(ListDocumentoDDLByProyecto.class);
    private final DocumentoDDLPortOut documentoDDLPortOut;

    public ListDocumentoDDLByProyecto(DocumentoDDLPortOut documentoDDLPortOut) {
        this.documentoDDLPortOut = documentoDDLPortOut;
    }

    public List<DocumentoDDLResponse> execute(String proyectoId) {
        log.info("Iniciando obtención de documentos DDL para el proyecto con id: {}", proyectoId);
        List<DocumentoDDLResponse> documentos = documentoDDLPortOut.findByProyectoId(proyectoId)
                .stream()
                .map(DocumentoDDLResponse::from)
                .toList();
        log.info("Fin de obtención de documentos DDL para el proyecto con id: {}, total: {}", proyectoId, documentos.size());
        return documentos;
    }
}

