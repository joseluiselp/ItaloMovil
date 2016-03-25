package italo.com.app.italomovil.service.modelos;

import java.util.Date;

public class MArea{


    private int idArea;

    private int idTipoArea;

    private String nombreArea;

    private String descripcionArea;

    private String lugar;

    private java.util.Date fechaInaguracionArea;

    private java.util.Date fechaCreacionArea;

    private float precioReserva;

    private boolean esReservable;

    private boolean esConcesionable;

    private String rutaImagen;


    public MArea() {
        this.esConcesionable = false;
        this.idArea = -1;
        this.idTipoArea = -1;
        this.nombreArea = "";
        this.descripcionArea = "";
        this.lugar = "";
        this.fechaInaguracionArea = new Date();
        this.fechaCreacionArea = new Date();
        this.precioReserva = -1;
        this.esReservable = false;
        this.rutaImagen = "";
    }

    public MArea(int idArea, int idTipoArea, String nombreArea, String descripcionArea, String lugar, Date fechaInaguracionArea, Date fechaCreacionArea, float precioReserva, boolean esReservable, boolean esConcesionable, String rutaImagen) {
        this.idArea = idArea;
        this.idTipoArea = idTipoArea;
        this.nombreArea = nombreArea;
        this.descripcionArea = descripcionArea;
        this.lugar = lugar;
        this.fechaInaguracionArea = fechaInaguracionArea;
        this.fechaCreacionArea = fechaCreacionArea;
        this.precioReserva = precioReserva;
        this.esReservable = esReservable;
        this.esConcesionable = esConcesionable;
        this.rutaImagen = rutaImagen;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public boolean getEsReservable() {
        return esReservable;
    }

    public void setEsReservable(boolean esReservable) {
        this.esReservable = esReservable;
    }

    public boolean getEsConcesionable() {
        return esConcesionable;
    }

    public void setEsConcesionable(boolean esConcesionable) {
        this.esConcesionable = esConcesionable;
    }

    public void setIdArea(int value) {
        this.idArea = value;
    }

    public int getIdArea() {
        return idArea;
    }

    public float getPrecioReserva() {
        return precioReserva;
    }

    public void setPrecioReserva(float precioReserva) {
        this.precioReserva = precioReserva;
    }

    public int getORMID() {
        return getIdArea();
    }

    public void setNombreArea(String value) {
        this.nombreArea = value;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setDescripcionArea(String value) {
        this.descripcionArea = value;
    }

    public String getDescripcionArea() {
        return descripcionArea;
    }

    public void setLugar(String value) {
        this.lugar = value;
    }

    public String getLugar() {
        return lugar;
    }

    public void setFechaInaguracionArea(java.util.Date value) {
        this.fechaInaguracionArea = value;
    }

    public java.util.Date getFechaInaguracionArea() {
        return fechaInaguracionArea;
    }

    public void setFechaCreacionArea(java.util.Date value) {
        this.fechaCreacionArea = value;
    }

    public java.util.Date getFechaCreacionArea() {
        return fechaCreacionArea;
    }

    public int getIdTipoArea() {
        return idTipoArea;
    }

    public void setIdTipoArea(int idTipoArea) {
        this.idTipoArea = idTipoArea;
    }

    public String toString() {
        return String.valueOf(getIdArea() + " " + getNombreArea());
    }

}
