package cl.sprint_rocket_ai.ms_dev_mentor.doc_lineamiento.domain.ports.out;

import cl.sprint_rocket_ai.ms_dev_mentor.doc_lineamiento.domain.models.DocumentoLineamiento;

import java.util.List;
import java.util.Optional;

public interface DocumentoLineamientoPortOut {

    DocumentoLineamiento save(DocumentoLineamiento documentoLineamiento);

    Optional<DocumentoLineamiento> findById(String id);

}

