package cl.sprint_rocket_ai.ms_dev_mentor.ai_index.application.strategy;

import cl.sprint_rocket_ai.ms_dev_mentor.ai_index.domain.models.AIIndexRequest;
import cl.sprint_rocket_ai.ms_dev_mentor.commons.domain.models.Documento;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Base de las estrategias de mapeo Documento → AIIndexRequest.
 * Declara el método del contrato Strategy ({@link #map(Documento)}) y
 * centraliza los helpers null-safe y la construcción de la metadata base.
 */
public abstract class AbstractDocumentoAIMapperStrategy {

    protected static final String META_DOCUMENTO_ID = "documentoId";
    protected static final String META_TIPO = "tipo";
    protected static final String META_TITULO = "titulo";
    protected static final String META_PROYECTO_ID = "proyectoId";

    public abstract AIIndexRequest map(Documento documento);

    protected Map<String, Object> baseMetadata(Documento documento) {
        Map<String, Object> metadata = new HashMap<>();
        metadata.put(META_DOCUMENTO_ID, documento.getId());
        metadata.put(META_TIPO, documento.getTipoDocumento().name());
        metadata.put(META_TITULO, documento.getTitulo());
        return metadata;
    }

    protected String nullSafe(String value) {
        return value == null ? "" : value;
    }

    protected <T> List<T> safeList(List<T> value) {
        return value == null ? List.of() : value;
    }
}
