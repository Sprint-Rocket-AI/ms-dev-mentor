package cl.sprint_rocket_ai.ms_dev_mentor.doc_negocio.domain.models;

import cl.sprint_rocket_ai.ms_dev_mentor.commons.domain.models.Documento;

import cl.sprint_rocket_ai.ms_dev_mentor.doc_negocio.domain.enums.FuenteDocumento;
import cl.sprint_rocket_ai.ms_dev_mentor.commons.domain.enums.TipoDocumento;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "documentos_negocio")
public class DocumentoNegocio extends Documento {

    @Field("fuente")
    private FuenteDocumento fuente;

    @Field("url_fuente")
    private String urlFuente;

    @Field("tags")
    private List<String> tags;

    @Field("criterios_aceptacion")
    private List<String> criteriosAceptacion;

    @Field("resumen")
    private String resumen;

    @Override
    public TipoDocumento getTipoDocumento() {
        return TipoDocumento.NEGOCIO;
    }

    public FuenteDocumento getFuente() {
        return fuente;
    }

    public void setFuente(FuenteDocumento fuente) {
        this.fuente = fuente;
    }

    public String getUrlFuente() {
        return urlFuente;
    }

    public void setUrlFuente(String urlFuente) {
        this.urlFuente = urlFuente;
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

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

}

