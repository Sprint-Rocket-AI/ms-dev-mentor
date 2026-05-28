package cl.sprint_rocket_ai.ms_dev_mentor.doc_lineamiento.infrastructure.persistences.mongodb;

import cl.sprint_rocket_ai.ms_dev_mentor.doc_lineamiento.domain.models.DocumentoLineamiento;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DocumentoLineamientoMongoRepository extends MongoRepository<DocumentoLineamiento, String> {

    List<DocumentoLineamiento> findByProyectoId(String proyectoId);
}

