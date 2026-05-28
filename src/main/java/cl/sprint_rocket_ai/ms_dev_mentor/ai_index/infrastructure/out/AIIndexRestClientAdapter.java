package cl.sprint_rocket_ai.ms_dev_mentor.ai_index.infrastructure.out;

import cl.sprint_rocket_ai.ms_dev_mentor.ai_index.domain.models.AIIndexRequest;
import cl.sprint_rocket_ai.ms_dev_mentor.ai_index.domain.ports.out.AIIndexPortOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

/**
 * Adapter de salida que envía {@link AIIndexRequest} al servicio externo
 * de indexación AI usando {@link RestClient}.
 */
@Component
public final class AIIndexRestClientAdapter implements AIIndexPortOut {

    private static final Logger log = LoggerFactory.getLogger(AIIndexRestClientAdapter.class);
    private static final String INDEX_PATH = "/index";

    private final RestClient restClient;

    public AIIndexRestClientAdapter(@Qualifier("aiIndexRestClient") RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public void index(AIIndexRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("El AIIndexRequest no puede ser null");
        }
        log.info("Enviando documento {} (tipo={}) al indexador AI", request.id(), request.tipo());
        try {
            restClient.post()
                    .uri(INDEX_PATH)
                    .body(request)
                    .retrieve()
                    .toBodilessEntity();
            log.info("Documento {} indexado correctamente", request.id());
        } catch (RestClientResponseException ex) {
            log.error("Error HTTP {} al indexar documento {}: {}",
                    ex.getStatusCode(), request.id(), ex.getResponseBodyAsString());
            throw ex;
        }
    }
}
