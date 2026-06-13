package cl.sprint_rocket_ai.ms_context_builder.documents.domain.models;


import cl.sprint_rocket_ai.ms_context_builder.documents.domain.enums.TipoDocumento;
import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_lineamiento.dtos.DocumentoLineamientoResponse;
import org.springframework.data.annotation.TypeAlias;

import java.util.List;

@TypeAlias("LINEAMIENTO")
public class DocumentoLineamiento extends DocumentoContexto {

    private List<String> tags;

    public DocumentoLineamiento() {
        this.setTipoDocumento(TipoDocumento.LINEAMIENTO);
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public DocumentoResponse toResponse() {
        return DocumentoLineamientoResponse.from(this);
    }
}

