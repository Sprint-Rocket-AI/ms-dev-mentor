package cl.sprint_rocket_ai.ms_context_builder.ai_index.domain.models;

import java.util.List;
import java.util.Map;

public record AIIndexRequest(
        String id,
        String tipo,
        String contenido,
        List<String> tags,
        Map<String, Object> metadata
) {
}

