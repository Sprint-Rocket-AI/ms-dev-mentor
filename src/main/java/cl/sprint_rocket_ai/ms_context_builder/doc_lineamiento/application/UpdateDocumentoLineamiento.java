package cl.sprint_rocket_ai.ms_context_builder.doc_lineamiento.application;

import cl.sprint_rocket_ai.ms_context_builder.commons.domain.exceptions.EntityNotFoundException;
import cl.sprint_rocket_ai.ms_context_builder.doc_lineamiento.domain.ports.out.DocumentoLineamientoPortOut;
import cl.sprint_rocket_ai.ms_context_builder.doc_lineamiento.infrastructure.in.dtos.DocumentoLineamientoRequest;
import cl.sprint_rocket_ai.ms_context_builder.doc_lineamiento.infrastructure.in.dtos.DocumentoLineamientoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public final class UpdateDocumentoLineamiento {

    private static final Logger log = LoggerFactory.getLogger(UpdateDocumentoLineamiento.class);
    private final DocumentoLineamientoPortOut documentoLineamientoPortOut;

    public UpdateDocumentoLineamiento(DocumentoLineamientoPortOut documentoLineamientoPortOut) {
        this.documentoLineamientoPortOut = documentoLineamientoPortOut;
    }

    public DocumentoLineamientoResponse execute(String id, DocumentoLineamientoRequest request) {
        log.info("Iniciando actualización de documento lineamiento con id: {}", id);
        return documentoLineamientoPortOut.findById(id)
                .map(existing -> {
                    request.applyTo(existing);
                    existing.setFechaActualizacion(LocalDateTime.now());
                    return documentoLineamientoPortOut.save(existing);
                })
                .map(DocumentoLineamientoResponse::from)
                .map(response -> {
                    log.info("Fin de la actualización del documento lineamiento con id: {}", id);
                    return response;
                })
                .orElseThrow(() -> new EntityNotFoundException("documento lineamiento con id: " + id));
    }
}

