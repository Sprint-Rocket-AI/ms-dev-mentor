package cl.sprint_rocket_ai.ms_dev_mentor.doc_lineamiento.application;

import cl.sprint_rocket_ai.ms_dev_mentor.ai_index.application.AIIndexService;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_lineamiento.domain.models.DocumentoLineamiento;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_lineamiento.domain.ports.out.DocumentoLineamientoPortOut;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_lineamiento.infrastructure.in.dtos.DocumentoLineamientoRequest;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_lineamiento.infrastructure.in.dtos.DocumentoLineamientoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public final class SaveDocumentoLineamiento {

    private static final Logger log = LoggerFactory.getLogger(SaveDocumentoLineamiento.class);
    private final DocumentoLineamientoPortOut documentoLineamientoPortOut;
    private final AIIndexService aiIndexService;

    public SaveDocumentoLineamiento(DocumentoLineamientoPortOut documentoLineamientoPortOut,
                                    AIIndexService aiIndexService) {
        this.documentoLineamientoPortOut = documentoLineamientoPortOut;
        this.aiIndexService = aiIndexService;
    }

    public DocumentoLineamientoResponse execute(DocumentoLineamientoRequest request) {
        log.info("Iniciando creación de documento lineamiento");
        DocumentoLineamiento documentoLineamiento = new DocumentoLineamiento();
        request.applyTo(documentoLineamiento);
        documentoLineamiento.setFechaCreacion(LocalDateTime.now());
        DocumentoLineamiento persistido = documentoLineamientoPortOut.save(documentoLineamiento);
        aiIndexService.index(persistido);
        log.info("Fin de la creación del documento lineamiento con id: {}", persistido.getId());
        return DocumentoLineamientoResponse.from(persistido);
    }
}
