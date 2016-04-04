package italo.com.app.italomovil.service.modelos;

import java.util.Date;

/**
 * Created by root on 09/03/16.
 */
public class MServicio {


    private int idServicio;

    private MArea idArea;

    private String rutaFoto;

    private String nombreServicio;

    private Double precioMensualidad;

    private Double precioMensualidadSocio;

    private int idCalendario;

    private int idHorario;

    private int idConcesionario;

    public MServicio(int idServicio, MArea idArea, String rutaFoto, String nombreServicio, Double precioMensualidad, Double precioMensualidadSocio, int idCalendario, int idHorario, int idConcesionario) {
        this.idServicio = idServicio;
        this.idArea = idArea;
        this.rutaFoto = rutaFoto;
        this.nombreServicio = nombreServicio;
        this.precioMensualidad = precioMensualidad;
        this.precioMensualidadSocio = precioMensualidadSocio;
        this.idCalendario = idCalendario;
        this.idHorario = idHorario;
        this.idConcesionario = idConcesionario;
    }

    public int getIdConcesionario() {
        return idConcesionario;
    }

    public void setIdConcesionario(int idConcesionario) {
        this.idConcesionario = idConcesionario;
    }

    public int getIdCalendario() {
        return idCalendario;
    }

    public void setIdCalendario(int idCalendario) {
        this.idCalendario = idCalendario;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public Double getPrecioMensualidadSocio() {
        return precioMensualidadSocio;
    }

    public void setPrecioMensualidadSocio(Double precioMensualidadSocio) {
        this.precioMensualidadSocio = precioMensualidadSocio;
    }

    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }


    private void setIdServicio(int value) {
        this.idServicio = value;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public int getORMID() {
        return getIdServicio();
    }

    public void setNombreServicio(String value) {
        this.nombreServicio = value;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setPrecioMensualidad(double value) {
        setPrecioMensualidad(new Double(value));
    }

    public void setPrecioMensualidad(Double value) {
        this.precioMensualidad = value;
    }

    public Double getPrecioMensualidad() {
        return precioMensualidad;
    }

    public void setIdArea(MArea value) {
        this.idArea = value;
    }

    public MArea getIdArea() {
        return idArea;
    }
	/*
	public void setIdCalendario(MCalendario value) {
		this.idCalendario = value;
	}

	public MCalendario getIdCalendario() {
		return idCalendario;
	}

	public void setIdHorario(MHorario value) {
		this.idHorario = value;
	}

	public MHorario getIdHorario() {
		return idHorario;
	}

	public void setIdPublicacion(java.util.Set<MPublicacion> value) {
		this.idPublicacion = value;
	}

	public java.util.Set<MPublicacion> getIdPublicacion() {
		return idPublicacion;
	}


	public void setInscripcion(java.util.Set<MInscripcion> value) {
		this.inscripcion = value;
	}

	public java.util.Set<MInscripcion> getInscripcion() {
		return inscripcion;
	}
	*/

    public String toString() {
        return String.valueOf(getIdServicio());
    }
}
