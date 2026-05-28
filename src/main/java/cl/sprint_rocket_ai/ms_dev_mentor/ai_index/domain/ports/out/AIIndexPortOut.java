package cl.sprint_rocket_ai.ms_dev_mentor.ai_index.domain.ports.out;

import cl.sprint_rocket_ai.ms_dev_mentor.ai_index.domain.models.AIIndexRequest;

/**
 * Puerto de salida hacia el servicio externo de indexación AI (RAG).
 */
public interface AIIndexPortOut {

    /** Envía un documento al servicio de indexación. */
    void index(AIIndexRequest request);
}

