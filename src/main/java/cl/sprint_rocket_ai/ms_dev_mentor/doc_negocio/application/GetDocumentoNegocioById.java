package cl.sprint_rocket_ai.ms_dev_mentor.doc_negocio.application;

import cl.sprint_rocket_ai.ms_dev_mentor.commons.domain.exceptions.EntityNotFoundException;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_negocio.domain.ports.out.DocumentoNegocioPortOut;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_negocio.infrastructure.in.dtos.DocumentoNegocioResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public final class GetDocumentoNegocioById {
    private static final Logger log = LoggerFactory.getLogger(GetDocumentoNegocioById.class);

    private final DocumentoNegocioPortOut documentoNegocioPortOut;

    public GetDocumentoNegocioById(DocumentoNegocioPortOut documentoNegocioPortOut) {
        this.documentoNegocioPortOut = documentoNegocioPortOut;
    }

    public DocumentoNegocioResponse execute(String id) {
        log.info("Iniciando obtención de documento de negocio con id: {}", id);
        DocumentoNegocioResponse response = documentoNegocioPortOut.findById(id)
                .map(DocumentoNegocioResponse::from)
                .orElseThrow(() -> new EntityNotFoundException("documento de negocio con id: " + id));
        log.info("Fin de la busqueda, documento negocio encontrado");
        return response;
    }
}
