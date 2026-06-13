package cl.sprint_rocket_ai.ms_context_builder.doc_lineamiento.domain.ports.out;

import cl.sprint_rocket_ai.ms_context_builder.doc_lineamiento.domain.models.DocumentoLineamiento;

import java.util.Optional;

public interface DocumentoLineamientoPortOut {

    DocumentoLineamiento save(DocumentoLineamiento documentoLineamiento);

    Optional<DocumentoLineamiento> findById(String id);

}

