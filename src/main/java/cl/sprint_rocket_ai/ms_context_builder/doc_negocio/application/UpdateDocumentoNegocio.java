package cl.sprint_rocket_ai.ms_context_builder.doc_negocio.application;

import cl.sprint_rocket_ai.ms_context_builder.commons.domain.exceptions.EntityNotFoundException;
import cl.sprint_rocket_ai.ms_context_builder.doc_negocio.domain.models.DocumentoNegocio;
import cl.sprint_rocket_ai.ms_context_builder.doc_negocio.domain.ports.out.DocumentoNegocioPortOut;
import cl.sprint_rocket_ai.ms_context_builder.doc_negocio.infrastructure.in.dtos.DocumentoNegocioRequest;
import cl.sprint_rocket_ai.ms_context_builder.doc_negocio.infrastructure.in.dtos.DocumentoNegocioResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public final class UpdateDocumentoNegocio {

    private static final Logger log = LoggerFactory.getLogger(UpdateDocumentoNegocio.class);
    private final DocumentoNegocioPortOut documentoNegocioPortOut;

    public UpdateDocumentoNegocio(DocumentoNegocioPortOut documentoNegocioPortOut) {
        this.documentoNegocioPortOut = documentoNegocioPortOut;
    }

    public DocumentoNegocioResponse execute(String id, DocumentoNegocioRequest request) {
        log.info("Iniciando actualización de documento de negocio con id: {}", id);
        return documentoNegocioPortOut.findById(id)
                .map(existing -> {
                    request.applyTo(existing);
                    existing.setFechaActualizacion(LocalDateTime.now());
                    DocumentoNegocio updated = documentoNegocioPortOut.save(existing);
                    log.info("Fin de la actualización del documento de negocio con id: {}", id);
                    return updated;
                })
                .map(DocumentoNegocioResponse::from)
                .orElseThrow(() -> new EntityNotFoundException("documento de negocio con id: " + id));
    }
}
