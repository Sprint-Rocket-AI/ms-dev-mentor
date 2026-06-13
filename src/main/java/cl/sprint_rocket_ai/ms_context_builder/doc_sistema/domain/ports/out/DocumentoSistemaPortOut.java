package cl.sprint_rocket_ai.ms_context_builder.doc_sistema.domain.ports.out;

import cl.sprint_rocket_ai.ms_context_builder.doc_sistema.domain.models.DocumentoSistema;

import java.util.Optional;

public interface DocumentoSistemaPortOut {
    DocumentoSistema save(DocumentoSistema documentoSistema);

    Optional<DocumentoSistema> findById(String id);

}
