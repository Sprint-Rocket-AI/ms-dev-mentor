package cl.sprint_rocket_ai.ms_dev_mentor.doc_negocio.infrastructure.out;

import cl.sprint_rocket_ai.ms_dev_mentor.doc_negocio.domain.models.DocumentoNegocio;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_negocio.domain.ports.out.DocumentoNegocioPortOut;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_negocio.infrastructure.persistences.mongodb.DocumentoNegocioMongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public final class DocumentoNegocioAdapterOut implements DocumentoNegocioPortOut {

    private static final Logger log = LoggerFactory.getLogger(DocumentoNegocioAdapterOut.class);

    private final DocumentoNegocioMongoRepository documentoNegocioMongoRepository;

    public DocumentoNegocioAdapterOut(DocumentoNegocioMongoRepository documentoNegocioMongoRepository) {
        this.documentoNegocioMongoRepository = documentoNegocioMongoRepository;
    }

    @Override
    public DocumentoNegocio save(DocumentoNegocio documentoNegocio) {
        log.info("Guardando Documento Negocio en MongoDB");
        DocumentoNegocio documentoToPersist = documentoNegocioMongoRepository.save(documentoNegocio);
        log.info("Documento Negocio guardado con ID: {}", documentoToPersist.getId());
        return documentoToPersist;
    }

    @Override
    public Optional<DocumentoNegocio> findById(String id) {
        log.info("Buscando Documento de Negocio por ID: {}", id);
        return documentoNegocioMongoRepository.findById(id);
    }


}
