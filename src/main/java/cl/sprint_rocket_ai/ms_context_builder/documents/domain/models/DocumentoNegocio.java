package cl.sprint_rocket_ai.ms_context_builder.documents.domain.models;

import cl.sprint_rocket_ai.ms_context_builder.documents.domain.enums.TipoDocumento;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

public class DocumentoNegocio extends DocumentoContexto {

    @Field("tags")
    private List<String> tags;

    @Field("criterios_aceptacion")
    private List<String> criteriosAceptacion;

    public DocumentoNegocio() {
        this.setTipoDocumento(TipoDocumento.NEGOCIO);
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getCriteriosAceptacion() {
        return criteriosAceptacion;
    }

    public void setCriteriosAceptacion(List<String> criteriosAceptacion) {
        this.criteriosAceptacion = criteriosAceptacion;
    }


}

