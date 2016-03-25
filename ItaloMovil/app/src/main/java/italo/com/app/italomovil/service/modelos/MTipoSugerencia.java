package italo.com.app.italomovil.service.modelos;

/**
 * Created by root on 13/03/16.
 */
public class MTipoSugerencia {


    private int idTipoSugerencia;

    private String nombreTipoSugerencia;

    private String descripcionTipoSugerencia;

    public MTipoSugerencia(int idTipoSugerencia, String nombreTipoSugerencia, String descripcionTipoSugerencia) {
        this.idTipoSugerencia = idTipoSugerencia;
        this.nombreTipoSugerencia = nombreTipoSugerencia;
        this.descripcionTipoSugerencia = descripcionTipoSugerencia;
    }

    private void setIdTipoSugerencia(int value) {
        this.idTipoSugerencia = value;
    }

    public int getIdTipoSugerencia() {
        return idTipoSugerencia;
    }

    public int getORMID() {
        return getIdTipoSugerencia();
    }

    public void setNombreTipoSugerencia(String value) {
        this.nombreTipoSugerencia = value;
    }

    public String getNombreTipoSugerencia() {
        return nombreTipoSugerencia;
    }

    public void setDescripcionTipoSugerencia(String value) {
        this.descripcionTipoSugerencia = value;
    }

    public String getDescripcionTipoSugerencia() {
        return descripcionTipoSugerencia;
    }

    public String toString() {
        return String.valueOf(getIdTipoSugerencia());
    }
}
