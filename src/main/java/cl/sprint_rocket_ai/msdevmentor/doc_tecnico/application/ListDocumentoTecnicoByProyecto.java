package cl.sprint_rocket_ai.msdevmentor.doc_tecnico.application;

import cl.sprint_rocket_ai.msdevmentor.doc_tecnico.domain.ports.out.DocumentoTecnicoPortOut;
import cl.sprint_rocket_ai.msdevmentor.doc_tecnico.infrastructure.in.dtos.DocumentoTecnicoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class ListDocumentoTecnicoByProyecto {

    private static final Logger log = LoggerFactory.getLogger(ListDocumentoTecnicoByProyecto.class);
    private final DocumentoTecnicoPortOut documentoTecnicoPortOut;

    public ListDocumentoTecnicoByProyecto(DocumentoTecnicoPortOut documentoTecnicoPortOut) {
        this.documentoTecnicoPortOut = documentoTecnicoPortOut;
    }

    public List<DocumentoTecnicoResponse> execute(String proyectoId) {
        log.info("Iniciando listado de documentos técnicos por proyectoId: {}", proyectoId);
        List<DocumentoTecnicoResponse> docs = documentoTecnicoPortOut.findByProyectoId(proyectoId)
                .stream()
                .map(DocumentoTecnicoResponse::from)
                .toList();
        log.info("Fin de la obtención de la lista de documentos técnicos por proyecto, total: {}", docs.size());
        return docs;
    }
}
