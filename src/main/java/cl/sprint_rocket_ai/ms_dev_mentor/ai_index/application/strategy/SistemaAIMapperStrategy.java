package cl.sprint_rocket_ai.ms_dev_mentor.ai_index.application.strategy;

import cl.sprint_rocket_ai.ms_dev_mentor.ai_index.domain.models.AIIndexRequest;
import cl.sprint_rocket_ai.ms_dev_mentor.commons.domain.enums.TipoDocumento;
import cl.sprint_rocket_ai.ms_dev_mentor.commons.domain.models.Documento;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_sistema.domain.models.DocumentoSistema;
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
