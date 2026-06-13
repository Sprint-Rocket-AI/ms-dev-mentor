package cl.sprint_rocket_ai.ms_dev_mentor.doc_negocio.domain.models;

import cl.sprint_rocket_ai.ms_dev_mentor.commons.domain.models.Documento;

import cl.sprint_rocket_ai.ms_dev_mentor.commons.domain.enums.TipoDocumento;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "documentos_negocio")
public class DocumentoNegocio extends Documento {

    @Field("tags")
    private List<String> tags;

    @Field("criterios_aceptacion")
    private List<String> criteriosAceptacion;

    @Override
    public TipoDocumento getTipoDocumento() {
        return TipoDocumento.NEGOCIO;
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

