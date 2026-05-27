package cl.sprint_rocket_ai.ms_dev_mentor.doc_tecnico.domain.ports.out;

import cl.sprint_rocket_ai.ms_dev_mentor.doc_tecnico.domain.models.DocumentoTecnico;

import java.util.List;
import java.util.Optional;

public interface DocumentoTecnicoPortOut {
    DocumentoTecnico save(DocumentoTecnico documentoTecnico);

    Optional<DocumentoTecnico> findById(String id);

    List<DocumentoTecnico> findByProyectoId(String proyectoId);

}
