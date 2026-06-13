package cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.persistences.mongodb;

import cl.sprint_rocket_ai.ms_context_builder.documents.domain.models.DocumentoDDL;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DocumentoDDLMongoRepository extends MongoRepository<DocumentoDDL, String> {
}
