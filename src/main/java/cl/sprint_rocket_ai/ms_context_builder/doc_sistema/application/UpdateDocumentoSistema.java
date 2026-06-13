package cl.sprint_rocket_ai.ms_context_builder.doc_sistema.application;

import cl.sprint_rocket_ai.ms_context_builder.commons.domain.exceptions.EntityNotFoundException;
import cl.sprint_rocket_ai.ms_context_builder.doc_sistema.domain.models.DocumentoSistema;
import cl.sprint_rocket_ai.ms_context_builder.doc_sistema.domain.ports.out.DocumentoSistemaPortOut;
import cl.sprint_rocket_ai.ms_context_builder.doc_sistema.infrastructure.in.dtos.DocumentoSistemaRequest;
import cl.sprint_rocket_ai.ms_context_builder.doc_sistema.infrastructure.in.dtos.DocumentoSistemaResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public final class UpdateDocumentoSistema {

    private static final Logger log = LoggerFactory.getLogger(UpdateDocumentoSistema.class);
    private final DocumentoSistemaPortOut documentoSistemaPortOut;

    public UpdateDocumentoSistema(DocumentoSistemaPortOut documentoSistemaPortOut) {
        this.documentoSistemaPortOut = documentoSistemaPortOut;
    }

    public DocumentoSistemaResponse execute(String id, DocumentoSistemaRequest documentoSistema) {
        log.info("Iniciando actualización de documento sistema con id: {}", id);
        return documentoSistemaPortOut.findById(id)
                .map(existingDocument -> {
                    existingDocument.setTitulo(documentoSistema.titulo());
                    existingDocument.setContenido(documentoSistema.contenido());
                    existingDocument.setUrlRepos(documentoSistema.urlRepos());
                    existingDocument.setStack(documentoSistema.stack());
                    existingDocument.setDevs(documentoSistema.devs());
                    DocumentoSistema updatedDocument = documentoSistemaPortOut.save(existingDocument);
                    log.info("Fin de la actualizacion del documento sistema con id: {}", id);
                    return updatedDocument;
                })
                .map(DocumentoSistemaResponse::from)
                .orElseThrow(()-> new EntityNotFoundException("documento sistema con id: " + id));
    }
}
