package italo.com.app.italomovil.service.modelos;

/**
 * Created by root on 08/03/16.
 */
public class MClub {


    private int idClub;

    private String rif;

    private String nombreClub;

    private String email;

    private String mision;

    private String vision;

    private String direccion;

    private String descripcion;

    private String historia;

    private Integer nroAcciones;

    private String rutaLogo;

    private Double precioAccion;

    private Integer nroMesesMaxMorosidad;

    private String nroTelefono;

    private Integer cantidadMaxInvitados;

    private Integer edadMax;

    public MClub(int idClub, String nombreClub, String email, String mision, String vision, String direccion, String historia, String rutaLogo, String nroTelefono, int cantidadMaxInvitados) {
        this.idClub = idClub;
        this.nombreClub = nombreClub;
        this.email = email;
        this.mision = mision;
        this.vision = vision;
        this.direccion = direccion;
        this.historia = historia;
        this.rutaLogo = rutaLogo;
        this.nroTelefono = nroTelefono;
        this.cantidadMaxInvitados = cantidadMaxInvitados;
    }

    private void setIdClub(int value) {
        this.idClub = value;
    }

    public int getIdClub() {
        return idClub;
    }

    public int getORMID() {
        return getIdClub();
    }

    public void setRif(String value) {
        this.rif = value;
    }

    public String getRif() {
        return rif;
    }

    public void setNombreClub(String value) {
        this.nombreClub = value;
    }

    public String getNombreClub() {
        return nombreClub;
    }

    public void setEmail(String value) {
        this.email = value;
    }

    public String getEmail() {
        return email;
    }

    public void setMision(String value) {
        this.mision = value;
    }

    public String getMision() {
        return mision;
    }

    public void setVision(String value) {
        this.vision = value;
    }

    public String getVision() {
        return vision;
    }

    public void setDireccion(String value) {
        this.direccion = value;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setHistoria(String value) {
        this.historia = value;
    }

    public String getHistoria() {
        return historia;
    }

    public void setNroAcciones(int value) {
        setNroAcciones(new Integer(value));
    }

    public void setNroAcciones(Integer value) {
        this.nroAcciones = value;
    }

    public Integer getNroAcciones() {
        return nroAcciones;
    }

    public void setRutaLogo(String value) {
        this.rutaLogo = value;
    }

    public String getRutaLogo() {
        return rutaLogo;
    }

    public void setPrecioAccion(double value) {
        setPrecioAccion(new Double(value));
    }

    public void setPrecioAccion(Double value) {
        this.precioAccion = value;
    }
    public Double getPrecioAccion() {
        return precioAccion;
    }

    public void setNroMesesMaxMorosidad(int value) {
        setNroMesesMaxMorosidad(new Integer(value));
    }

    public void setNroMesesMaxMorosidad(Integer value) {
        this.nroMesesMaxMorosidad = value;
    }
    public Integer getNroMesesMaxMorosidad() {
        return nroMesesMaxMorosidad;
    }

    public void setNroTelefono(String value) {
        this.nroTelefono = value;
    }

    public String getNroTelefono() {
        return nroTelefono;
    }

    public void setCantidadMaxInvitados(int value) {
        setCantidadMaxInvitados(new Integer(value));
    }

    public void setCantidadMaxInvitados(Integer value) {
        this.cantidadMaxInvitados = value;
    }

    public Integer getCantidadMaxInvitados() {
        return cantidadMaxInvitados;
    }

    public void setEdadMax(int value) {
        setEdadMax(new Integer(value));
    }

    public void setEdadMax(Integer value) {
        this.edadMax = value;
    }

    public Integer getEdadMax() {
        return edadMax;
    }



    public String toString() {
        return String.valueOf(getIdClub());
    }
}
