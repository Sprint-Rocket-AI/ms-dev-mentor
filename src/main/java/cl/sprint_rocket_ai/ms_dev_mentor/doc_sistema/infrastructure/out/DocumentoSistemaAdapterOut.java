package cl.sprint_rocket_ai.ms_dev_mentor.doc_sistema.infrastructure.out;

import cl.sprint_rocket_ai.ms_dev_mentor.doc_sistema.domain.models.DocumentoSistema;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_sistema.domain.ports.out.DocumentoSistemaPortOut;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_sistema.infrastructure.persistences.mongodb.DocumentoSistemaMongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public final class DocumentoSistemaAdapterOut implements DocumentoSistemaPortOut {

    private static final Logger log = LoggerFactory.getLogger(DocumentoSistemaAdapterOut.class);
    private final DocumentoSistemaMongoRepository documentoSistemaMongoRepository;

    public DocumentoSistemaAdapterOut(DocumentoSistemaMongoRepository documentoSistemaMongoRepository) {
        this.documentoSistemaMongoRepository = documentoSistemaMongoRepository;
    }

    @Override
    public DocumentoSistema save(DocumentoSistema documentoSistema) {
        log.info("Guardando Documento Sistema en MongoDB");
        DocumentoSistema documentoToPersist = documentoSistemaMongoRepository.save(documentoSistema);
        log.info("Documento Sistema guardado con ID: {}", documentoToPersist.getId());
        return documentoToPersist;
    }

    @Override
    public Optional<DocumentoSistema> findById(String id) {
        log.info("Buscando Documento Sistema por ID: {}", id);
        return documentoSistemaMongoRepository.findById(id);
    }

}
