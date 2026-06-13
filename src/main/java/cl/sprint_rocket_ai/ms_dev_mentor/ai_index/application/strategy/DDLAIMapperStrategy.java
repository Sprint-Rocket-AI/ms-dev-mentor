package cl.sprint_rocket_ai.ms_dev_mentor.ai_index.application.strategy;

import cl.sprint_rocket_ai.ms_dev_mentor.ai_index.domain.models.AIIndexRequest;
import cl.sprint_rocket_ai.ms_dev_mentor.commons.domain.enums.TipoDocumento;
import cl.sprint_rocket_ai.ms_dev_mentor.commons.domain.models.Documento;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class DDLAIMapperStrategy extends AbstractDocumentoAIMapperStrategy {

    @Override
    public AIIndexRequest map(Documento documento) {
        String contenido = """
                Titulo: %s

                Contenido:
                %s""".formatted(
                nullSafe(documento.getTitulo()),
                nullSafe(documento.getContenido())
        );

        Map<String, Object> metadata = baseMetadata(documento);

        return new AIIndexRequest(
                documento.getId(),
                TipoDocumento.DDL.name(),
                contenido,
                List.of(),
                metadata
        );
    }
}
