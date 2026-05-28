package cl.sprint_rocket_ai.ms_dev_mentor.doc_lineamiento.application;

import cl.sprint_rocket_ai.ms_dev_mentor.doc_lineamiento.domain.ports.out.DocumentoLineamientoPortOut;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_lineamiento.infrastructure.in.dtos.DocumentoLineamientoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class ListDocumentoLineamientoByProyecto {

    private static final Logger log = LoggerFactory.getLogger(ListDocumentoLineamientoByProyecto.class);
    private final DocumentoLineamientoPortOut documentoLineamientoPortOut;

    public ListDocumentoLineamientoByProyecto(DocumentoLineamientoPortOut documentoLineamientoPortOut) {
        this.documentoLineamientoPortOut = documentoLineamientoPortOut;
    }

    public List<DocumentoLineamientoResponse> execute(String proyectoId) {
        log.info("Iniciando listado de documentos lineamiento por proyectoId: {}", proyectoId);
        List<DocumentoLineamientoResponse> docs = documentoLineamientoPortOut.findByProyectoId(proyectoId)
                .stream()
                .map(DocumentoLineamientoResponse::from)
                .toList();
        log.info("Fin de la obtención de la lista de documentos lineamiento por proyecto, total: {}", docs.size());
        return docs;
    }
}

