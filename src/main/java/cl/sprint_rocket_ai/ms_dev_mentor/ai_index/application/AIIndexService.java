package cl.sprint_rocket_ai.ms_dev_mentor.ai_index.application;

import cl.sprint_rocket_ai.ms_dev_mentor.ai_index.application.factory.DocumentoAIMapperFactory;
import cl.sprint_rocket_ai.ms_dev_mentor.ai_index.application.strategy.AbstractDocumentoAIMapperStrategy;
import cl.sprint_rocket_ai.ms_dev_mentor.ai_index.domain.models.AIIndexRequest;
import cl.sprint_rocket_ai.ms_dev_mentor.ai_index.domain.ports.out.AIIndexPortOut;
import cl.sprint_rocket_ai.ms_dev_mentor.commons.domain.enums.TipoDocumento;
import cl.sprint_rocket_ai.ms_dev_mentor.commons.domain.models.Documento;
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

    public void index(Documento documento) {
        TipoDocumento tipoDocumento = documento.getTipoDocumento();
        log.info("Obtendiendo Mapper para tipo de documento: {}", tipoDocumento);
        AbstractDocumentoAIMapperStrategy mapper = documentoAIMapperFactory.getMapperByDocumentType(tipoDocumento);
        AIIndexRequest request = mapper.map(documento);
        log.info("Mapper obtenido: {}", mapper.getClass().getSimpleName());
        log.info("Indexando Documento");
        aiIndexPortOut.index(request);
        log.info("Documento indexado");
    }
}
