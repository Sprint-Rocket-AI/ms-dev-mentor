package cl.sprint_rocket_ai.ms_dev_mentor.doc_lineamiento.domain.models;

import cl.sprint_rocket_ai.ms_dev_mentor.commons.domain.enums.TipoDocumento;
import cl.sprint_rocket_ai.ms_dev_mentor.commons.domain.models.Documento;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "documentos_lineamientos")
public class DocumentoLineamiento extends Documento {

    private String lineamiento;

    private String dominio;

    private String categoria;

    private List<String> tags;



    public String getLineamiento() {
        return lineamiento;
    }

    public void setLineamiento(String lineamiento) {
        this.lineamiento = lineamiento;
    }

    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

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

