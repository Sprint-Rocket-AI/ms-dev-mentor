package cl.sprint_rocket_ai.ms_dev_mentor.doc_tecnico.application;

import cl.sprint_rocket_ai.ms_dev_mentor.commons.domain.exceptions.EntityNotFoundException;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_tecnico.domain.models.DocumentoTecnico;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_tecnico.domain.ports.out.DocumentoTecnicoPortOut;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_tecnico.infrastructure.in.dtos.DocumentoTecnicoRequest;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_tecnico.infrastructure.in.dtos.DocumentoTecnicoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public final class UpdateDocumentoTecnico {

    private static final Logger log = LoggerFactory.getLogger(UpdateDocumentoTecnico.class);
    private final DocumentoTecnicoPortOut documentoTecnicoPortOut;

    public UpdateDocumentoTecnico(DocumentoTecnicoPortOut documentoTecnicoPortOut) {
        this.documentoTecnicoPortOut = documentoTecnicoPortOut;
    }

    public DocumentoTecnicoResponse execute(String id, DocumentoTecnicoRequest documentoTecnico) {
        log.info("Iniciando actualización de documento técnico con id: {}", id);
        return documentoTecnicoPortOut.findById(id)
                .map(existingDocument -> {
                    existingDocument.setTitulo(documentoTecnico.titulo());
                    existingDocument.setContenido(documentoTecnico.contenido());
                    existingDocument.setEstado(documentoTecnico.estado());
                    existingDocument.setProyectoId(documentoTecnico.proyectoId());
                    DocumentoTecnico updatedDocument = documentoTecnicoPortOut.save(existingDocument);
                    log.info("Fin de la actualizacion del documento técnico con id: {}", id);
                    return updatedDocument;
                })
                .map(DocumentoTecnicoResponse::from)
                .orElseThrow(()-> new EntityNotFoundException("documento técnico con id: " + id));
    }
}
