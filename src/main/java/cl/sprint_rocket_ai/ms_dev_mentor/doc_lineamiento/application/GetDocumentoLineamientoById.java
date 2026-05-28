package cl.sprint_rocket_ai.ms_dev_mentor.doc_lineamiento.application;

import cl.sprint_rocket_ai.ms_dev_mentor.commons.domain.exceptions.EntityNotFoundException;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_lineamiento.domain.models.DocumentoLineamiento;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_lineamiento.domain.ports.out.DocumentoLineamientoPortOut;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_lineamiento.infrastructure.in.dtos.DocumentoLineamientoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public final class GetDocumentoLineamientoById {

    private static final Logger log = LoggerFactory.getLogger(GetDocumentoLineamientoById.class);
    private final DocumentoLineamientoPortOut documentoLineamientoPortOut;

    public GetDocumentoLineamientoById(DocumentoLineamientoPortOut documentoLineamientoPortOut) {
        this.documentoLineamientoPortOut = documentoLineamientoPortOut;
    }

    public DocumentoLineamientoResponse execute(String id) {
        log.info("Iniciando busqueda de documento lineamiento con id: {}", id);
        DocumentoLineamiento doc = documentoLineamientoPortOut.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Documento lineamiento con id: " + id));
        log.info("Fin de la busqueda, documento lineamiento encontrado");
        return DocumentoLineamientoResponse.from(doc);
    }
}

