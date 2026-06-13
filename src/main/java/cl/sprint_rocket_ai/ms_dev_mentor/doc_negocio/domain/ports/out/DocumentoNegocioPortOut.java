package cl.sprint_rocket_ai.ms_dev_mentor.doc_negocio.domain.ports.out;

import cl.sprint_rocket_ai.ms_dev_mentor.doc_negocio.domain.models.DocumentoNegocio;

import java.util.Optional;

public interface DocumentoNegocioPortOut {
    DocumentoNegocio save(DocumentoNegocio documentoNegocio);
    Optional<DocumentoNegocio> findById(String id);
}
