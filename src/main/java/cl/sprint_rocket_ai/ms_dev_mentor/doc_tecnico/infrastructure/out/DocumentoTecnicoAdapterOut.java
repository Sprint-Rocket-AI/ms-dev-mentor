package cl.sprint_rocket_ai.ms_dev_mentor.doc_tecnico.infrastructure.out;

import cl.sprint_rocket_ai.ms_dev_mentor.doc_tecnico.domain.models.DocumentoTecnico;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_tecnico.domain.ports.out.DocumentoTecnicoPortOut;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_tecnico.infrastructure.persistences.mongodb.DocumentoTecnicoMongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public final class DocumentoTecnicoAdapterOut implements DocumentoTecnicoPortOut {

    private static final Logger log = LoggerFactory.getLogger(DocumentoTecnicoAdapterOut.class);
    private final DocumentoTecnicoMongoRepository documentoTecnicoMongoRepository;

    public DocumentoTecnicoAdapterOut(DocumentoTecnicoMongoRepository documentoTecnicoMongoRepository) {
        this.documentoTecnicoMongoRepository = documentoTecnicoMongoRepository;
    }

    @Override
    public DocumentoTecnico save(DocumentoTecnico documentoTecnico) {
        log.info("Guardando Documento Técnico en MongoDB");
        DocumentoTecnico documentoToPersist = documentoTecnicoMongoRepository.save(documentoTecnico);
        log.info("Documento Técnico guardado con ID: {}", documentoToPersist.getId());
        return documentoToPersist;
    }

    @Override
    public Optional<DocumentoTecnico> findById(String id) {
        log.info("Buscando Documento Técnico por ID: {}", id);
        return documentoTecnicoMongoRepository.findById(id);
    }

    @Override
    public List<DocumentoTecnico> findByProyectoId(String proyectoId) {
        log.info("Listando Documentos Técnicos por proyectoId: {}", proyectoId);
        List<DocumentoTecnico> docs = documentoTecnicoMongoRepository.findByProyectoId(proyectoId);
        log.info("Cantidad de documentos técnicos encontrados: {}",docs.size());
        return docs;
    }
}
