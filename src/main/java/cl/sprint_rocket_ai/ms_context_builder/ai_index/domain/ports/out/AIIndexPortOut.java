package cl.sprint_rocket_ai.ms_context_builder.ai_index.domain.ports.out;

import cl.sprint_rocket_ai.ms_context_builder.ai_index.domain.models.AIIndexRequest;

/**
 * Puerto de salida hacia el servicio externo de indexación AI (RAG).
 */
public interface AIIndexPortOut {

    void index(AIIndexRequest request);

    void update(String id, AIIndexRequest request);

    void deleteById(String id);
}

