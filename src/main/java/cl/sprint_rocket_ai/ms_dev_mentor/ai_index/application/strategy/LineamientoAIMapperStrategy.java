package cl.sprint_rocket_ai.ms_dev_mentor.ai_index.application.strategy;

import cl.sprint_rocket_ai.ms_dev_mentor.ai_index.domain.models.AIIndexRequest;
import cl.sprint_rocket_ai.ms_dev_mentor.commons.domain.enums.TipoDocumento;
import cl.sprint_rocket_ai.ms_dev_mentor.commons.domain.models.Documento;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_lineamiento.domain.models.DocumentoLineamiento;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class LineamientoAIMapperStrategy extends AbstractDocumentoAIMapperStrategy {

    @Override
    public AIIndexRequest map(Documento documento) {
        DocumentoLineamiento doc = (DocumentoLineamiento) documento;

        String contenido = """
                Lineamiento: %s
                """.formatted(
                nullSafe(doc.getContenido())
        );

        Map<String, Object> metadata = baseMetadata(doc);

        return new AIIndexRequest(
                doc.getId(),
                TipoDocumento.LINEAMIENTO.name(),
                contenido,
                safeList(doc.getTags()),
                metadata
        );
    }
}
