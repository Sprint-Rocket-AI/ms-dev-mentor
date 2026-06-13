package cl.sprint_rocket_ai.ms_context_builder.ai_index.application.strategy;

import cl.sprint_rocket_ai.ms_context_builder.ai_index.domain.models.AIIndexRequest;
import cl.sprint_rocket_ai.ms_context_builder.documents.domain.enums.TipoDocumento;
import cl.sprint_rocket_ai.ms_context_builder.documents.domain.models.DocumentoContexto;
import cl.sprint_rocket_ai.ms_context_builder.documents.domain.models.DocumentoNegocio;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class NegocioAIMapperStrategy extends AbstractDocumentoAIMapperStrategy {

    @Override
    public AIIndexRequest map(DocumentoContexto documentoContexto) {
        DocumentoNegocio doc = (DocumentoNegocio) documentoContexto;

        List<String> criterios = safeList(doc.getCriteriosAceptacion());
        String contenido = """
                Titulo: %s

                Contenido:
                %s

                Criterios:
                %s""".formatted(
                nullSafe(doc.getTitulo()),
                nullSafe(doc.getContenido()),
                criterios.isEmpty() ? "" : "- " + String.join("\n- ", criterios)
        );

        Map<String, Object> metadata = baseMetadata(doc);

        return new AIIndexRequest(
                doc.getId(),
                TipoDocumento.NEGOCIO.name(),
                contenido,
                criterios,
                metadata
        );
    }
}
