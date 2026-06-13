package cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.persistences.mongodb;

import cl.sprint_rocket_ai.ms_context_builder.documents.domain.models.DocumentoContexto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DocumentoContextoMongoRepository extends MongoRepository<DocumentoContexto, String> {
}

