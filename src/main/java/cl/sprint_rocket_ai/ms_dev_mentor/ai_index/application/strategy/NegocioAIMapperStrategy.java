package cl.sprint_rocket_ai.ms_dev_mentor.ai_index.application.strategy;

import cl.sprint_rocket_ai.ms_dev_mentor.ai_index.domain.models.AIIndexRequest;
import cl.sprint_rocket_ai.ms_dev_mentor.commons.domain.enums.TipoDocumento;
import cl.sprint_rocket_ai.ms_dev_mentor.commons.domain.models.Documento;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_negocio.domain.models.DocumentoNegocio;
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
        metadata.put(META_PROYECTO_ID, doc.getProyectoId());
        metadata.put("fuentes", doc.getUrlFuente() == null
                ? List.<String>of()
                : List.of(doc.getUrlFuente()));

        return new AIIndexRequest(
                doc.getId(),
                TipoDocumento.NEGOCIO.name(),
                contenido,
                criterios,
                metadata
        );
    }
}
