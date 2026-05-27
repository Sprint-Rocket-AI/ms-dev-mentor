package cl.sprint_rocket_ai.ms_dev_mentor.doc_negocio.infrastructure.persistences.mongodb;

import cl.sprint_rocket_ai.ms_dev_mentor.doc_negocio.domain.models.DocumentoNegocio;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DocumentoNegocioMongoRepository extends MongoRepository<DocumentoNegocio, String> {
    List<DocumentoNegocio> findByProyectoId(String proyectoId);
}
