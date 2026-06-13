package cl.sprint_rocket_ai.ms_context_builder.ai_index.application.strategy;

import cl.sprint_rocket_ai.ms_context_builder.ai_index.domain.models.AIIndexRequest;
import cl.sprint_rocket_ai.ms_context_builder.documents.domain.models.DocumentoContexto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Base de las estrategias de mapeo Documento → AIIndexRequest.
 * Declara el método del contrato Strategy ({@link #map(DocumentoContexto)}) y
 * centraliza los helpers null-safe y la construcción de la metadata base.
 */
public abstract class AbstractDocumentoAIMapperStrategy {

    protected static final String META_DOCUMENTO_ID = "documentoId";
    protected static final String META_TIPO = "tipo";
    protected static final String META_TITULO = "titulo";

    public abstract AIIndexRequest map(DocumentoContexto documentoContexto);

    protected Map<String, Object> baseMetadata(DocumentoContexto documentoContexto) {
        Map<String, Object> metadata = new HashMap<>();
        metadata.put(META_DOCUMENTO_ID, documentoContexto.getId());
        metadata.put(META_TIPO, documentoContexto.getTipoDocumento().name());
        metadata.put(META_TITULO, documentoContexto.getTitulo());
        return metadata;
    }

    protected String nullSafe(String value) {
        return value == null ? "" : value;
    }

    protected <T> List<T> safeList(List<T> value) {
        return value == null ? List.of() : value;
    }
}
