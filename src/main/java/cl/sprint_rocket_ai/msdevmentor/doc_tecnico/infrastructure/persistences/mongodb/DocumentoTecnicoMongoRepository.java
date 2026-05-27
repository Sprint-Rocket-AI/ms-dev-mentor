package cl.sprint_rocket_ai.msdevmentor.doc_tecnico.infrastructure.persistences.mongodb;

import cl.sprint_rocket_ai.msdevmentor.doc_tecnico.domain.models.DocumentoTecnico;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DocumentoTecnicoMongoRepository extends MongoRepository<DocumentoTecnico, String> {
	List<DocumentoTecnico> findByProyectoId(String proyectoId);
}

