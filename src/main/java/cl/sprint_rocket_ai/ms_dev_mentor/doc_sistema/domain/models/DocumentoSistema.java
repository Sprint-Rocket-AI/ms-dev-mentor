package cl.sprint_rocket_ai.ms_dev_mentor.doc_sistema.domain.models;

import cl.sprint_rocket_ai.ms_dev_mentor.commons.domain.models.Documento;

import cl.sprint_rocket_ai.ms_dev_mentor.commons.domain.enums.TipoDocumento;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "documentos_sistemas")
public class DocumentoSistema extends Documento {

    @Field("url_repositorios")
    private List<String> urlRepos;

    private List<String> stack;

    private List<String> devs;

    public List<String> getUrlRepos() {
        return urlRepos;
    }

    public void setUrlRepos(List<String> urlRepos) {
        this.urlRepos = urlRepos;
    }

    public List<String> getStack() {
        return stack;
    }

    public void setStack(List<String> stack) {
        this.stack = stack;
    }

    public List<String> getDevs() {
        return devs;
    }

    public void setDevs(List<String> devs) {
        this.devs = devs;
    }

    @Override
    public TipoDocumento getTipoDocumento() {
        return TipoDocumento.SISTEMA;
    }

}
