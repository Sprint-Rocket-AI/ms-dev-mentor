package cl.sprint_rocket_ai.ms_context_builder.doc_lineamiento.infrastructure.persistences.mongodb;

import cl.sprint_rocket_ai.ms_context_builder.doc_lineamiento.domain.models.DocumentoLineamiento;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface DocumentoLineamientoMongoRepository extends MongoRepository<DocumentoLineamiento, String> {
}

