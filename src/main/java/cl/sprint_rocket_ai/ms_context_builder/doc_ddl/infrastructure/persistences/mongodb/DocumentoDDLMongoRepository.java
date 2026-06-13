package cl.sprint_rocket_ai.ms_context_builder.doc_ddl.infrastructure.persistences.mongodb;

import cl.sprint_rocket_ai.ms_context_builder.doc_ddl.domain.models.DocumentoDDL;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface DocumentoDDLMongoRepository extends MongoRepository<DocumentoDDL, String> {

}

