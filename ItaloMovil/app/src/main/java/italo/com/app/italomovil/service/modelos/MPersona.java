package italo.com.app.italomovil.service.modelos;

import java.util.Date;

/**
 * Created by root on 09/03/16.
 */
public class MPersona {

    private int idPersona;

    private String tipoCedulaPersona;

    private Integer cedulaPersona;

    private String emailPersona;

    private String nombresPersona;

    private String apellidosPersona;

    private String direccionPersona;

    private String nroTelefonoFijo;

    private String nroTelofonoCelular;

    private String rutaFotoCarnet;

    private java.util.Date fechaNacimiento;

    private String sexo;

    private String estadoCivil;

    public MPersona(int idPersona, String tipoCedulaPersona, Integer cedulaPersona, String emailPersona, String nombresPersona, String apellidosPersona, String direccionPersona, String nroTelefonoFijo, String nroTelofonoCelular, Date fechaNacimiento, String sexo, String estadoCivil, String rutaFotoCarnet) {
        this.idPersona = idPersona;
        this.tipoCedulaPersona = tipoCedulaPersona;
        this.cedulaPersona = cedulaPersona;
        this.emailPersona = emailPersona;
        this.nombresPersona = nombresPersona;
        this.apellidosPersona = apellidosPersona;
        this.direccionPersona = direccionPersona;
        this.nroTelefonoFijo = nroTelefonoFijo;
        this.nroTelofonoCelular = nroTelofonoCelular;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
        this.rutaFotoCarnet = rutaFotoCarnet;
    }

    public void setIdPersona(int value) {
        this.idPersona = value;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public int getORMID() {
        return getIdPersona();
    }

    public void setTipoCedulaPersona(String value) {
        this.tipoCedulaPersona = value;
    }

    public String getTipoCedulaPersona() {
        return tipoCedulaPersona;
    }

    public void setCedulaPersona(int value) {
        setCedulaPersona(new Integer(value));
    }

    public void setCedulaPersona(Integer value) {
        this.cedulaPersona = value;
    }

    public Integer getCedulaPersona() {
        return cedulaPersona;
    }

    public void setEmailPersona(String value) {
        this.emailPersona = value;
    }

    public String getEmailPersona() {
        return emailPersona;
    }

    public void setNombresPersona(String value) {
        this.nombresPersona = value;
    }

    public String getNombresPersona() {
        return nombresPersona;
    }

    public void setApellidosPersona(String value) {
        this.apellidosPersona = value;
    }

    public String getApellidosPersona() {
        return apellidosPersona;
    }

    public void setDireccionPersona(String value) {
        this.direccionPersona = value;
    }

    public String getDireccionPersona() {
        return direccionPersona;
    }

    public void setNroTelefonoFijo(String value) {
        this.nroTelefonoFijo = value;
    }

    public String getNroTelefonoFijo() {
        return nroTelefonoFijo;
    }

    public void setNroTelofonoCelular(String value) {
        this.nroTelofonoCelular = value;
    }

    public String getNroTelofonoCelular() {
        return nroTelofonoCelular;
    }

    public void setRutaFotoCarnet(String value) {
        this.rutaFotoCarnet = value;
    }

    public String getRutaFotoCarnet() {
        return rutaFotoCarnet;
    }

    public void setFechaNacimiento(java.util.Date value) {
        this.fechaNacimiento = value;
    }

    public java.util.Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setSexo(String value) {
        this.sexo = value;
    }

    public String getSexo() {
        return sexo;
    }

    public void setEstadoCivil(String value) {
        this.estadoCivil = value;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

}
