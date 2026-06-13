package cl.sprint_rocket_ai.ms_context_builder.doc_ddl.domain.models;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Representa una columna de una tabla extraída desde un script DDL.
 * Es un value object embebido en Tabla.
 */
public class Columna {

    private String nombre;

    @Field("tipo_dato")
    private String tipoDato;

    @Field("es_pk")
    private boolean esPk;

    @Field("es_fk")
    private boolean esFk;

    @Field("es_nullable")
    private boolean esNullable;

    @Field("es_unique")
    private boolean esUnique;

    @Field("valor_por_defecto")
    private String valorPorDefecto;

    private String descripcion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(String tipoDato) {
        this.tipoDato = tipoDato;
    }

    public boolean isEsPk() {
        return esPk;
    }

    public void setEsPk(boolean esPk) {
        this.esPk = esPk;
    }

    public boolean isEsFk() {
        return esFk;
    }

    public void setEsFk(boolean esFk) {
        this.esFk = esFk;
    }

    public boolean isEsNullable() {
        return esNullable;
    }

    public void setEsNullable(boolean esNullable) {
        this.esNullable = esNullable;
    }

    public boolean isEsUnique() {
        return esUnique;
    }

    public void setEsUnique(boolean esUnique) {
        this.esUnique = esUnique;
    }

    public String getValorPorDefecto() {
        return valorPorDefecto;
    }

    public void setValorPorDefecto(String valorPorDefecto) {
        this.valorPorDefecto = valorPorDefecto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

