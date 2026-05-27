package cl.sprint_rocket_ai.ms_dev_mentor.doc_ddl.domain.models;

import cl.sprint_rocket_ai.ms_dev_mentor.commons.domain.models.Documento;

import cl.sprint_rocket_ai.ms_dev_mentor.doc_ddl.domain.enums.MotorBaseDatos;
import cl.sprint_rocket_ai.ms_dev_mentor.commons.domain.enums.TipoDocumento;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "documentos_ddl")
public class DocumentoDDL extends Documento {

    @Field("motor_bd")
    private MotorBaseDatos motorBd;

    private String version;

    private List<Tabla> tablas;

    @Override
    public TipoDocumento getTipoDocumento() {
        return TipoDocumento.DDL;
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
}

