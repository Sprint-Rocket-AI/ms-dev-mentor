package cl.sprint_rocket_ai.msdevmentor.doc_negocio.domain.ports.out;

import cl.sprint_rocket_ai.msdevmentor.doc_negocio.domain.models.DocumentoNegocio;

import java.util.List;
import java.util.Optional;

public interface DocumentoNegocioPortOut {
    DocumentoNegocio save(DocumentoNegocio documentoNegocio);
    Optional<DocumentoNegocio> findById(String id);
    List<DocumentoNegocio> findByProyectoId(String proyectoId);
}
