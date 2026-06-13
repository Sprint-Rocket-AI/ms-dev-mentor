package cl.sprint_rocket_ai.ms_context_builder.doc_sistema.application;

import cl.sprint_rocket_ai.ms_context_builder.commons.domain.exceptions.EntityNotFoundException;
import cl.sprint_rocket_ai.ms_context_builder.doc_sistema.domain.models.DocumentoSistema;
import cl.sprint_rocket_ai.ms_context_builder.doc_sistema.domain.ports.out.DocumentoSistemaPortOut;
import cl.sprint_rocket_ai.ms_context_builder.doc_sistema.infrastructure.in.dtos.DocumentoSistemaResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public final class GetDocumentoSistemaById {

    private static final Logger log = LoggerFactory.getLogger(GetDocumentoSistemaById.class);
    private final DocumentoSistemaPortOut documentoSistemaPortOut;

    public GetDocumentoSistemaById(DocumentoSistemaPortOut documentoSistemaPortOut) {
        this.documentoSistemaPortOut = documentoSistemaPortOut;
    }

    public DocumentoSistemaResponse execute(String id) {
        log.info("Iniciando busqueda de documento sistema con id: {}",id);
        DocumentoSistema doc = documentoSistemaPortOut.findById(id)
                                .orElseThrow(() -> new EntityNotFoundException("Documento sistema con id: " + id));
        log.info("Fin de la busqueda, documento sistema encontrado");
        return DocumentoSistemaResponse.from(doc);
    }
}

