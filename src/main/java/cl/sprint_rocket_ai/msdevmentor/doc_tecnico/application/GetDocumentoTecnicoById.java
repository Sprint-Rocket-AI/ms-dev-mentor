package cl.sprint_rocket_ai.msdevmentor.doc_tecnico.application;

import cl.sprint_rocket_ai.msdevmentor.commons.domain.exceptions.EntityNotFoundException;
import cl.sprint_rocket_ai.msdevmentor.doc_tecnico.domain.models.DocumentoTecnico;
import cl.sprint_rocket_ai.msdevmentor.doc_tecnico.domain.ports.out.DocumentoTecnicoPortOut;
import cl.sprint_rocket_ai.msdevmentor.doc_tecnico.infrastructure.in.dtos.DocumentoTecnicoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public final class GetDocumentoTecnicoById {

    private static final Logger log = LoggerFactory.getLogger(GetDocumentoTecnicoById.class);
    private final DocumentoTecnicoPortOut documentoTecnicoPortOut;

    public GetDocumentoTecnicoById(DocumentoTecnicoPortOut documentoTecnicoPortOut) {
        this.documentoTecnicoPortOut = documentoTecnicoPortOut;
    }

    public DocumentoTecnicoResponse execute(String id) {
        log.info("Iniciando busqueda de documento técnico con id: {}",id);
        DocumentoTecnico doc = documentoTecnicoPortOut.findById(id)
                                .orElseThrow(() -> new EntityNotFoundException("Documento técnico con id: " + id));
        log.info("Fin de la busqueda, documento técnico encontrado");
        return DocumentoTecnicoResponse.from(doc);
    }
}

