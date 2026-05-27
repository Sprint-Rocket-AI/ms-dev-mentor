package cl.sprint_rocket_ai.msdevmentor.commons.domain.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(String.format("No se ha encontrado: %s",message));
    }
}
