package cl.sprint_rocket_ai.ms_dev_mentor.doc_tecnico.application;

import cl.sprint_rocket_ai.ms_dev_mentor.doc_tecnico.domain.models.DocumentoTecnico;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_tecnico.domain.ports.out.DocumentoTecnicoPortOut;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_tecnico.infrastructure.in.dtos.DocumentoTecnicoRequest;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_tecnico.infrastructure.in.dtos.DocumentoTecnicoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public final class SaveDocumentoTecnico {

    private static final Logger log = LoggerFactory.getLogger(SaveDocumentoTecnico.class);
    private final DocumentoTecnicoPortOut documentoTecnicoPortOut;

    public SaveDocumentoTecnico(DocumentoTecnicoPortOut documentoTecnicoPortOut) {
        this.documentoTecnicoPortOut = documentoTecnicoPortOut;
    }

    public DocumentoTecnicoResponse execute(DocumentoTecnicoRequest request) {
        log.info("Iniciando creación de documento técnico");
        DocumentoTecnico documentoTecnico = new DocumentoTecnico();
        documentoTecnico.setTitulo(request.titulo());
        documentoTecnico.setContenido(request.contenido());
        documentoTecnico.setProyectoId(request.proyectoId());
        documentoTecnico.setEstado(request.estado());
        DocumentoTecnico documentoPersistido = documentoTecnicoPortOut.save(documentoTecnico);
        log.info("Fin de la creación del documento técnico con id: {}", documentoPersistido.getId());
        return DocumentoTecnicoResponse.from(documentoPersistido);
    }
}
