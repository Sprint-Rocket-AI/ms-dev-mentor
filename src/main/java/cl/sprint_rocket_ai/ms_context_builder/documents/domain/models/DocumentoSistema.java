package cl.sprint_rocket_ai.ms_context_builder.documents.domain.models;

import cl.sprint_rocket_ai.ms_context_builder.documents.domain.enums.TipoDocumento;
import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_sistema.dtos.DocumentoSistemaResponse;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.annotation.TypeAlias;

import java.util.List;

@TypeAlias("SISTEMA")
public class DocumentoSistema extends DocumentoContexto {

    @Field("url_repositorios")
    private List<String> urlRepos;

    private List<String> stack;

    private List<String> tags;

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

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public DocumentoResponse toResponse() {
        return DocumentoSistemaResponse.from(this);
    }
}
