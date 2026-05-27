package cl.sprint_rocket_ai.ms_dev_mentor.commons.domain.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(String.format("No se ha encontrado: %s",message));
    }
}
