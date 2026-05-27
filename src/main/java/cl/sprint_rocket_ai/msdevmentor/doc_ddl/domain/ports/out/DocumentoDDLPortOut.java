package cl.sprint_rocket_ai.msdevmentor.doc_ddl.domain.ports.out;

import cl.sprint_rocket_ai.msdevmentor.doc_ddl.domain.models.DocumentoDDL;

import java.util.List;
import java.util.Optional;

public interface DocumentoDDLPortOut {
    DocumentoDDL save(DocumentoDDL documentoDDL);
    Optional<DocumentoDDL> findById(String id);
    List<DocumentoDDL> findByProyectoId(String proyectoId);
}

