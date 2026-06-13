package cl.sprint_rocket_ai.ms_context_builder.doc_ddl.domain.ports.out;

import cl.sprint_rocket_ai.ms_context_builder.doc_ddl.domain.models.DocumentoDDL;

import java.util.Optional;

public interface DocumentoDDLPortOut {
    DocumentoDDL save(DocumentoDDL documentoDDL);
    Optional<DocumentoDDL> findById(String id);
}

