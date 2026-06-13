package cl.sprint_rocket_ai.ms_context_builder.doc_negocio.application;

import cl.sprint_rocket_ai.ms_context_builder.ai_index.application.AIIndexService;
import cl.sprint_rocket_ai.ms_context_builder.doc_negocio.domain.models.DocumentoNegocio;
import cl.sprint_rocket_ai.ms_context_builder.doc_negocio.domain.ports.out.DocumentoNegocioPortOut;
import cl.sprint_rocket_ai.ms_context_builder.doc_negocio.infrastructure.in.dtos.DocumentoNegocioRequest;
import cl.sprint_rocket_ai.ms_context_builder.doc_negocio.infrastructure.in.dtos.DocumentoNegocioResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public final class SaveDocumentoNegocio {

    private static final Logger log = LoggerFactory.getLogger(SaveDocumentoNegocio.class);
    private final DocumentoNegocioPortOut documentoNegocioPortOut;
    private final AIIndexService aiIndexService;

    public SaveDocumentoNegocio(DocumentoNegocioPortOut documentoNegocioPortOut,
                                AIIndexService aiIndexService) {
        this.documentoNegocioPortOut = documentoNegocioPortOut;
        this.aiIndexService = aiIndexService;
    }

    public DocumentoNegocioResponse execute(DocumentoNegocioRequest request) {
        log.info("Iniciando creación de documento negocio");
        DocumentoNegocio documentoNegocio = new DocumentoNegocio();
        request.applyTo(documentoNegocio);
        documentoNegocio.setFechaCreacion(LocalDateTime.now());
        DocumentoNegocio saved = documentoNegocioPortOut.save(documentoNegocio);
        aiIndexService.index(saved);
        log.info("Fin de la creación del documento negocio con id: {}", saved.getId());
        return DocumentoNegocioResponse.from(saved);
    }
}
