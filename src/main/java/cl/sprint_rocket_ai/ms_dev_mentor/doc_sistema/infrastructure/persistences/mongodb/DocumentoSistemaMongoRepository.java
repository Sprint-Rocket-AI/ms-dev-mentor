package cl.sprint_rocket_ai.ms_dev_mentor.doc_sistema.infrastructure.persistences.mongodb;

import cl.sprint_rocket_ai.ms_dev_mentor.doc_sistema.domain.models.DocumentoSistema;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface DocumentoSistemaMongoRepository extends MongoRepository<DocumentoSistema, String> {
}

