package cl.sprint_rocket_ai.ms_context_builder.ai_index.application;

import cl.sprint_rocket_ai.ms_context_builder.ai_index.application.factory.DocumentoAIMapperFactory;
import cl.sprint_rocket_ai.ms_context_builder.ai_index.application.strategy.AbstractDocumentoAIMapperStrategy;
import cl.sprint_rocket_ai.ms_context_builder.ai_index.domain.models.AIIndexRequest;
import cl.sprint_rocket_ai.ms_context_builder.ai_index.domain.ports.out.AIIndexPortOut;
import cl.sprint_rocket_ai.ms_context_builder.documents.domain.enums.TipoDocumento;
import cl.sprint_rocket_ai.ms_context_builder.documents.domain.models.DocumentoContexto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Orquesta el mapeo y el envío al indexador AI.
 */
@Service
public final class AIIndexService {

    private static final Logger log = LoggerFactory.getLogger(AIIndexService.class);
    private final DocumentoAIMapperFactory documentoAIMapperFactory;
    private final AIIndexPortOut aiIndexPortOut;

    public AIIndexService(DocumentoAIMapperFactory documentoAIMapperFactory,
                          AIIndexPortOut aiIndexPortOut) {
        this.documentoAIMapperFactory = documentoAIMapperFactory;
        this.aiIndexPortOut = aiIndexPortOut;
    }

    public void index(DocumentoContexto documentoContexto) {
        AIIndexRequest request = mapRequest(documentoContexto);
        log.info("Indexando Documento");
        aiIndexPortOut.index(request);
        log.info("Documento indexado");
    }

    public void update(DocumentoContexto documentoContexto) {
        AIIndexRequest request = mapRequest(documentoContexto);
        log.info("Actualizando índice del documento con id: {}", documentoContexto.getId());
        aiIndexPortOut.update(documentoContexto.getId(), request);
        log.info("Índice del documento {} actualizado", documentoContexto.getId());
    }

    public void deleteById(String id) {
        log.info("Eliminando índice del documento con id: {}", id);
        aiIndexPortOut.deleteById(id);
        log.info("Índice del documento {} eliminado", id);
    }

    private AIIndexRequest mapRequest(DocumentoContexto documentoContexto) {
        TipoDocumento tipoDocumento = documentoContexto.getTipoDocumento();
        log.info("Obtendiendo Mapper para tipo de documento: {}", tipoDocumento);
        AbstractDocumentoAIMapperStrategy mapper = documentoAIMapperFactory.getMapperByDocumentType(tipoDocumento);
        AIIndexRequest request = mapper.map(documentoContexto);
        log.info("Mapper obtenido: {}", mapper.getClass().getSimpleName());
        return request;
    }
}
