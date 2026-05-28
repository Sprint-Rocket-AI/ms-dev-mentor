package cl.sprint_rocket_ai.ms_dev_mentor.doc_sistema.domain.ports.out;

import cl.sprint_rocket_ai.ms_dev_mentor.doc_sistema.domain.models.DocumentoSistema;

import java.util.List;
import java.util.Optional;

public interface DocumentoSistemaPortOut {
    DocumentoSistema save(DocumentoSistema documentoSistema);

    Optional<DocumentoSistema> findById(String id);

    List<DocumentoSistema> findByProyectoId(String proyectoId);

}
