package cl.sprint_rocket_ai.ms_context_builder.documents.domain.models;

import cl.sprint_rocket_ai.ms_context_builder.documents.domain.enums.TipoDocumento;

import java.time.LocalDateTime;

public interface DocumentoResponse {
    String id();
    String titulo();
    String contenido();
    TipoDocumento tipo();
    LocalDateTime fechaCreacion();
    LocalDateTime fechaActualizacion();
}
