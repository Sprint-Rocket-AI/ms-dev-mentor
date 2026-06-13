package cl.sprint_rocket_ai.ms_context_builder.ai_index.application.strategy;

import cl.sprint_rocket_ai.ms_context_builder.ai_index.domain.models.AIIndexRequest;
import cl.sprint_rocket_ai.ms_context_builder.documents.domain.enums.TipoDocumento;
import cl.sprint_rocket_ai.ms_context_builder.documents.domain.models.DocumentoContexto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class DDLAIMapperStrategy extends AbstractDocumentoAIMapperStrategy {

    @Override
    public AIIndexRequest map(DocumentoContexto documentoContexto) {
        String contenido = """
                Titulo: %s

                Contenido:
                %s""".formatted(
                nullSafe(documentoContexto.getTitulo()),
                nullSafe(documentoContexto.getContenido())
        );

        Map<String, Object> metadata = baseMetadata(documentoContexto);

        return new AIIndexRequest(
                documentoContexto.getId(),
                TipoDocumento.DDL.name(),
                contenido,
                List.of(),
                metadata
        );
    }
}
