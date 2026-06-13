package cl.sprint_rocket_ai.ms_context_builder.documents.domain.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(String.format("No se ha encontrado: %s",message));
    }
}
