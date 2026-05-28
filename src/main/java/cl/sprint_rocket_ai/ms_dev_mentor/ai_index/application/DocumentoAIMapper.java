package cl.sprint_rocket_ai.ms_dev_mentor.ai_index.application;

import cl.sprint_rocket_ai.ms_dev_mentor.ai_index.application.factory.DocumentoAIMapperFactory;
import cl.sprint_rocket_ai.ms_dev_mentor.ai_index.domain.models.AIIndexRequest;
import cl.sprint_rocket_ai.ms_dev_mentor.commons.domain.models.Documento;
import org.springframework.stereotype.Component;

/**
 * Facade pública del subsistema de indexación AI. Delega a la
 * {@link DocumentoAIMapperFactory} la elección de la estrategia
 * adecuada según {@code doc.getTipoDocumento()}.
 *
 * <p>Mantiene la API previa ({@link #toAIIndexRequest(Documento)})
 * para no romper los consumidores.</p>
 */
@Component
public class DocumentoAIMapper {

    private final DocumentoAIMapperFactory factory;

    public DocumentoAIMapper(DocumentoAIMapperFactory factory) {
        this.factory = factory;
    }

    public AIIndexRequest toAIIndexRequest(Documento documento) {
        return factory.getMapperByDocumentType(documento.getTipoDocumento()).map(documento);
    }
}
