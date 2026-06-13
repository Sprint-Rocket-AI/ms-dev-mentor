package cl.sprint_rocket_ai.ms_context_builder.doc_ddl.domain.models;

import cl.sprint_rocket_ai.ms_context_builder.doc_ddl.domain.enums.TipoRelacion;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Representa una relación (FK) entre la tabla contenedora y otra tabla
 * declarada en el mismo script DDL. Value object embebido en Tabla.
 */
public class Relacion {

    @Field("columna_origen")
    private String columnaOrigen;

    @Field("tabla_referenciada")
    private String tablaReferenciada;

    @Field("columna_referenciada")
    private String columnaReferenciada;

    @Field("tipo_relacion")
    private TipoRelacion tipoRelacion;

    @Field("on_delete")
    private String onDelete;

    @Field("on_update")
    private String onUpdate;

    public String getColumnaOrigen() {
        return columnaOrigen;
    }

    public void setColumnaOrigen(String columnaOrigen) {
        this.columnaOrigen = columnaOrigen;
    }

    public String getTablaReferenciada() {
        return tablaReferenciada;
    }

    public void setTablaReferenciada(String tablaReferenciada) {
        this.tablaReferenciada = tablaReferenciada;
    }

    public String getColumnaReferenciada() {
        return columnaReferenciada;
    }

    public void setColumnaReferenciada(String columnaReferenciada) {
        this.columnaReferenciada = columnaReferenciada;
    }

    public TipoRelacion getTipoRelacion() {
        return tipoRelacion;
    }

    public void setTipoRelacion(TipoRelacion tipoRelacion) {
        this.tipoRelacion = tipoRelacion;
    }

    public String getOnDelete() {
        return onDelete;
    }

    public void setOnDelete(String onDelete) {
        this.onDelete = onDelete;
    }

    public String getOnUpdate() {
        return onUpdate;
    }

    public void setOnUpdate(String onUpdate) {
        this.onUpdate = onUpdate;
    }
}

