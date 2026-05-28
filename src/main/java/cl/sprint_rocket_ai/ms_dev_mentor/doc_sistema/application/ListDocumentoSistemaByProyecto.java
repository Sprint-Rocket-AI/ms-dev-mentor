package cl.sprint_rocket_ai.ms_dev_mentor.doc_sistema.application;

import cl.sprint_rocket_ai.ms_dev_mentor.doc_sistema.domain.ports.out.DocumentoSistemaPortOut;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_sistema.infrastructure.in.dtos.DocumentoSistemaResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class ListDocumentoSistemaByProyecto {

    private static final Logger log = LoggerFactory.getLogger(ListDocumentoSistemaByProyecto.class);
    private final DocumentoSistemaPortOut documentoSistemaPortOut;

    public ListDocumentoSistemaByProyecto(DocumentoSistemaPortOut documentoSistemaPortOut) {
        this.documentoSistemaPortOut = documentoSistemaPortOut;
    }

    public List<DocumentoSistemaResponse> execute(String proyectoId) {
        log.info("Iniciando listado de documentos sistemas por proyectoId: {}", proyectoId);
        List<DocumentoSistemaResponse> docs = documentoSistemaPortOut.findByProyectoId(proyectoId)
                .stream()
                .map(DocumentoSistemaResponse::from)
                .toList();
        log.info("Fin de la obtención de la lista de documentos sistemas por proyecto, total: {}", docs.size());
        return docs;
    }
}
