package cl.sprint_rocket_ai.ms_context_builder.ai_index.application.strategy;

import cl.sprint_rocket_ai.ms_context_builder.ai_index.domain.models.AIIndexRequest;
import cl.sprint_rocket_ai.ms_context_builder.commons.domain.enums.TipoDocumento;
import cl.sprint_rocket_ai.ms_context_builder.commons.domain.models.Documento;
import cl.sprint_rocket_ai.ms_context_builder.doc_sistema.domain.models.DocumentoSistema;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SistemaAIMapperStrategy extends AbstractDocumentoAIMapperStrategy {

    @Override
    public AIIndexRequest map(Documento documento) {
        DocumentoSistema doc = (DocumentoSistema) documento;

        List<String> stack = safeList(doc.getStack());
        String contenido = """
                Titulo: %s

                Contenido:
                %s

                Stack: %s""".formatted(
                nullSafe(doc.getTitulo()),
                nullSafe(doc.getContenido()),
                String.join(", ", stack)
        );

        Map<String, Object> metadata = baseMetadata(doc);
        metadata.put("repositorios", safeList(doc.getUrlRepos()));

        return new AIIndexRequest(
                doc.getId(),
                TipoDocumento.SISTEMA.name(),
                contenido,
                stack,
                metadata
        );
    }
}
