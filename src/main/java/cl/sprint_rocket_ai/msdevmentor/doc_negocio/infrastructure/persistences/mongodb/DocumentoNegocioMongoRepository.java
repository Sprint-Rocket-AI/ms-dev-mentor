package cl.sprint_rocket_ai.msdevmentor.doc_negocio.infrastructure.persistences.mongodb;

import cl.sprint_rocket_ai.msdevmentor.doc_negocio.domain.models.DocumentoNegocio;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DocumentoNegocioMongoRepository extends MongoRepository<DocumentoNegocio, String> {
    List<DocumentoNegocio> findByProyectoId(String proyectoId);
}
