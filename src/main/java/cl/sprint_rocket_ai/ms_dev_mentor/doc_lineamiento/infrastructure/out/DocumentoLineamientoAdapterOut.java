package cl.sprint_rocket_ai.ms_dev_mentor.doc_lineamiento.infrastructure.out;

import cl.sprint_rocket_ai.ms_dev_mentor.doc_lineamiento.domain.models.DocumentoLineamiento;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_lineamiento.domain.ports.out.DocumentoLineamientoPortOut;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_lineamiento.infrastructure.persistences.mongodb.DocumentoLineamientoMongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public final class DocumentoLineamientoAdapterOut implements DocumentoLineamientoPortOut {

    private static final Logger log = LoggerFactory.getLogger(DocumentoLineamientoAdapterOut.class);
    private final DocumentoLineamientoMongoRepository documentoLineamientoMongoRepository;

    public DocumentoLineamientoAdapterOut(DocumentoLineamientoMongoRepository documentoLineamientoMongoRepository) {
        this.documentoLineamientoMongoRepository = documentoLineamientoMongoRepository;
    }

    @Override
    public DocumentoLineamiento save(DocumentoLineamiento documentoLineamiento) {
        log.info("Guardando Documento Lineamiento en MongoDB");
        DocumentoLineamiento persistido = documentoLineamientoMongoRepository.save(documentoLineamiento);
        log.info("Documento Lineamiento guardado con ID: {}", persistido.getId());
        return persistido;
    }

    @Override
    public Optional<DocumentoLineamiento> findById(String id) {
        log.info("Buscando Documento Lineamiento por ID: {}", id);
        return documentoLineamientoMongoRepository.findById(id);
    }

}

