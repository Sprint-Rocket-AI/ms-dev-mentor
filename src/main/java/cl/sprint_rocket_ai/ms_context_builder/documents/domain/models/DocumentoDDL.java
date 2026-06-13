package cl.sprint_rocket_ai.ms_context_builder.documents.domain.models;

import cl.sprint_rocket_ai.ms_context_builder.documents.domain.enums.MotorBaseDatos;
import cl.sprint_rocket_ai.ms_context_builder.documents.domain.enums.TipoDocumento;
import cl.sprint_rocket_ai.ms_context_builder.documents.domain.models.doc_ddl.Tabla;
import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_ddl.dtos.DocumentoDDLResponse;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@TypeAlias("DDL")
public class DocumentoDDL extends DocumentoContexto {

    @Field("motor_bd")
    private MotorBaseDatos motorBd;

    private String version;

    private List<Tabla> tablas;

    public DocumentoDDL() {
        this.setTipoDocumento(TipoDocumento.DDL);
    }

    public MotorBaseDatos getMotorBd() {
        return motorBd;
    }

    public void setMotorBd(MotorBaseDatos motorBd) {
        this.motorBd = motorBd;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<Tabla> getTablas() {
        return tablas;
    }

    public void setTablas(List<Tabla> tablas) {
        this.tablas = tablas;
    }

    @Override
    public DocumentoResponse toResponse() {
        return DocumentoDDLResponse.from(this);
    }
}

