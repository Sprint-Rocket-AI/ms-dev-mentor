package cl.sprint_rocket_ai.ms_dev_mentor.doc_ddl.infrastructure.persistences.mongodb;

import cl.sprint_rocket_ai.ms_dev_mentor.doc_ddl.domain.models.DocumentoDDL;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface DocumentoDDLMongoRepository extends MongoRepository<DocumentoDDL, String> {

}

