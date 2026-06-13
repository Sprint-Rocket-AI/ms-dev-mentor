package cl.sprint_rocket_ai.ms_context_builder.documents.domain.models;

import cl.sprint_rocket_ai.ms_context_builder.documents.domain.enums.TipoDocumento;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

public class DocumentoSistema extends DocumentoContexto {

    @Field("url_repositorios")
    private List<String> urlRepos;

    private List<String> stack;

    private List<String> devs;

    public DocumentoSistema() {
        this.setTipoDocumento(TipoDocumento.SISTEMA);
    }

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


}
