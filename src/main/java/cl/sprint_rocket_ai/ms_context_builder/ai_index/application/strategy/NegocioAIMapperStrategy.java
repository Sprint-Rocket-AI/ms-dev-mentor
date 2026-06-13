package cl.sprint_rocket_ai.ms_context_builder.ai_index.application.strategy;

import cl.sprint_rocket_ai.ms_context_builder.ai_index.domain.models.AIIndexRequest;
import cl.sprint_rocket_ai.ms_context_builder.commons.domain.enums.TipoDocumento;
import cl.sprint_rocket_ai.ms_context_builder.commons.domain.models.Documento;
import cl.sprint_rocket_ai.ms_context_builder.doc_negocio.domain.models.DocumentoNegocio;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class NegocioAIMapperStrategy extends AbstractDocumentoAIMapperStrategy {

    @Override
    public AIIndexRequest map(Documento documento) {
        DocumentoNegocio doc = (DocumentoNegocio) documento;

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
