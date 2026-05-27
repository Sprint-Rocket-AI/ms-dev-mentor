package cl.sprint_rocket_ai.ms_dev_mentor.doc_negocio.application;

import cl.sprint_rocket_ai.ms_dev_mentor.doc_negocio.domain.ports.out.DocumentoNegocioPortOut;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_negocio.infrastructure.in.dtos.DocumentoNegocioResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class ListDocumentoNegocioByProyecto {
    private static final Logger log = LoggerFactory.getLogger(ListDocumentoNegocioByProyecto.class);
    private final DocumentoNegocioPortOut documentoNegocioPortOut;

    public ListDocumentoNegocioByProyecto(DocumentoNegocioPortOut documentoNegocioPortOut) {
        this.documentoNegocioPortOut = documentoNegocioPortOut;
    }

    public List<DocumentoNegocioResponse> execute(String proyectoId) {
        log.info("Iniciando obtención de documentos de negocio para el proyecto con id: {}", proyectoId);
        List<DocumentoNegocioResponse> documentos = documentoNegocioPortOut.findByProyectoId(proyectoId)
                .stream()
                .map(DocumentoNegocioResponse::from)
                .toList();
        log.info("Fin de obtención de documentos de negocio para el proyecto con id: {}, total: {}", proyectoId, documentos.size());
        return documentos;
    }
}
