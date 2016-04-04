package italo.com.app.italomovil.service.modelos;

import java.util.Date;

/**
 * Created by root on 08/03/16.
 */
public class MPublicacion {

    private int idPublicacion;

    private int idTipoPublicacion;

    private String tituloPublicacion;

    private String contenidoPublicacion;

    private java.util.Date fechaInicio;

    private java.util.Date fechaFin;

    private Integer nroDiasPublicacion;

    private java.util.Date fechaCreacionPublicacion;

    private String rutaImagen;


    public MPublicacion(int idPublicacion, int idTipoPublicacion, String tituloPublicacion, String contenidoPublicacion, Date fechaInicio, Date fechaFin, Integer nroDiasPublicacion, Date fechaCreacionPublicacion, String rutaImagen) {
        this.idPublicacion = idPublicacion;
        this.idTipoPublicacion = idTipoPublicacion;
        this.tituloPublicacion = tituloPublicacion;
        this.contenidoPublicacion = contenidoPublicacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.nroDiasPublicacion = nroDiasPublicacion;
        this.fechaCreacionPublicacion = fechaCreacionPublicacion;
        this.rutaImagen = rutaImagen;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    private void setIdPublicacion(int value) {
        this.idPublicacion = value;
    }

    public int getIdPublicacion() {
        return idPublicacion;
    }

    public int getORMID() {
        return getIdPublicacion();
    }

    public void setTituloPublicacion(String value) {
        this.tituloPublicacion = value;
    }

    public String getTituloPublicacion() {
        return tituloPublicacion;
    }

    public void setContenidoPublicacion(String value) {
        this.contenidoPublicacion = value;
    }

    public String getContenidoPublicacion() {
        return contenidoPublicacion;
    }

    public void setFechaInicio(java.util.Date value) {
        this.fechaInicio = value;
    }

    public java.util.Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaFin(java.util.Date value) {
        this.fechaFin = value;
    }

    public java.util.Date getFechaFin() {
        return fechaFin;
    }

    public void setNroDiasPublicacion(int value) {
        setNroDiasPublicacion(new Integer(value));
    }

    public void setNroDiasPublicacion(Integer value) {
        this.nroDiasPublicacion = value;
    }

    public Integer getNroDiasPublicacion() {
        return nroDiasPublicacion;
    }

    public void setFechaCreacionPublicacion(java.util.Date value) {
        this.fechaCreacionPublicacion = value;
    }

    public java.util.Date getFechaCreacionPublicacion() {
        return fechaCreacionPublicacion;
    }

    public void setIdTipoPublicacion(int value) {
        this.idTipoPublicacion = value;
    }

    public int getIdTipoPublicacion() {
        return idTipoPublicacion;
    }

    public String toString() {
        return String.valueOf(getIdPublicacion());
    }
}
