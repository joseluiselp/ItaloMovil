package italo.com.app.italomovil.service.modelos;

import java.util.Date;

/**
 * Created by root on 09/03/16.
 */
public class MSolicitudAlquiler {


    private int idSolicitudAlquiler;

    private int idSocio;

    private MArea idArea;

    private int idCalendario;

    private int idEstadoSolicitudAlquiler;

    private java.util.Date fechaSolicitudAlquiler;

    private java.util.Date fechaAlquiler;

    private Integer invitadosEstimados;

    public MSolicitudAlquiler(int idSolicitudAlquiler, int idSocio, MArea idArea, int idCalendario, int idEstadoSolicitudAlquiler, Date fechaSolicitudAlquiler, Date fechaAlquiler, Integer invitadosEstimados) {
        this.idSolicitudAlquiler = idSolicitudAlquiler;
        this.idSocio = idSocio;
        this.idArea = idArea;
        this.idCalendario = idCalendario;
        this.idEstadoSolicitudAlquiler = idEstadoSolicitudAlquiler;
        this.fechaSolicitudAlquiler = fechaSolicitudAlquiler;
        this.fechaAlquiler = fechaAlquiler;
        this.invitadosEstimados = invitadosEstimados;
    }

    private void setIdSolicitudAlquiler(int value) {
        this.idSolicitudAlquiler = value;
    }

    public int getIdSolicitudAlquiler() {
        return idSolicitudAlquiler;
    }

    public int getORMID() {
        return getIdSolicitudAlquiler();
    }

    public void setFechaSolicitudAlquiler(java.util.Date value) {
        this.fechaSolicitudAlquiler = value;
    }

    public java.util.Date getFechaSolicitudAlquiler() {
        return fechaSolicitudAlquiler;
    }

    public void setFechaAlquiler(java.util.Date value) {
        this.fechaAlquiler = value;
    }

    public java.util.Date getFechaAlquiler() {
        return fechaAlquiler;
    }

    public void setInvitadosEstimados(int value) {
        setInvitadosEstimados(new Integer(value));
    }

    public void setInvitadosEstimados(Integer value) {
        this.invitadosEstimados = value;
    }

    public Integer getInvitadosEstimados() {
        return invitadosEstimados;
    }

    public void setIdSocio(int value) {
        this.idSocio = value;
    }

    public int getIdSocio() {
        return idSocio;
    }

    public void setIdArea(MArea value) {
        this.idArea = value;
    }

    public MArea getIdArea() {
        return idArea;
    }

    public void setIdCalendario(int value) {
        this.idCalendario = value;
    }

    public int getIdCalendario() {
        return idCalendario;
    }

    public void setIdEstadoSolicitudMAlquiler(int value) {
        this.idEstadoSolicitudAlquiler = value;
    }

    public int getIdEstadoSolicitudAlquiler() {
        return idEstadoSolicitudAlquiler;
    }


    public String toString() {
        return String.valueOf(getIdSolicitudAlquiler());
    }
}
