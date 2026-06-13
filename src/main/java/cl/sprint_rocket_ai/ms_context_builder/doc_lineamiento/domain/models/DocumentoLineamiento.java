package cl.sprint_rocket_ai.ms_context_builder.doc_lineamiento.domain.models;

import cl.sprint_rocket_ai.ms_context_builder.commons.domain.enums.TipoDocumento;
import cl.sprint_rocket_ai.ms_context_builder.commons.domain.models.Documento;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "documentos_lineamientos")
public class DocumentoLineamiento extends Documento {

    private List<String> tags;

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }


    @Override
    public TipoDocumento getTipoDocumento() {
        return TipoDocumento.LINEAMIENTO;
    }
}

