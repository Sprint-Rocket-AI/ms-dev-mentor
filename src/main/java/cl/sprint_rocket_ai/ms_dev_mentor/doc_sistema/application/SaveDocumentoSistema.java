package cl.sprint_rocket_ai.ms_dev_mentor.doc_sistema.application;

import cl.sprint_rocket_ai.ms_dev_mentor.doc_sistema.domain.models.DocumentoSistema;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_sistema.domain.ports.out.DocumentoSistemaPortOut;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_sistema.infrastructure.in.dtos.DocumentoSistemaRequest;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_sistema.infrastructure.in.dtos.DocumentoSistemaResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public final class SaveDocumentoSistema {

    private static final Logger log = LoggerFactory.getLogger(SaveDocumentoSistema.class);
    private final DocumentoSistemaPortOut documentoSistemaPortOut;

    public SaveDocumentoSistema(DocumentoSistemaPortOut documentoSistemaPortOut) {
        this.documentoSistemaPortOut = documentoSistemaPortOut;
    }

    public DocumentoSistemaResponse execute(DocumentoSistemaRequest request) {
        log.info("Iniciando creación de documento sistema");
        DocumentoSistema documentoSistema = new DocumentoSistema();
        documentoSistema.setTitulo(request.titulo());
        documentoSistema.setContenido(request.contenido());
        documentoSistema.setProyectoId(request.proyectoId());
        documentoSistema.setEstado(request.estado());
        DocumentoSistema documentoPersistido = documentoSistemaPortOut.save(documentoSistema);
        log.info("Fin de la creación del documento sistema con id: {}", documentoPersistido.getId());
        return DocumentoSistemaResponse.from(documentoPersistido);
    }
}
