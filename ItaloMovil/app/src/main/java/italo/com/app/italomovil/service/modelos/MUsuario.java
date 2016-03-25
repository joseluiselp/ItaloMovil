package italo.com.app.italomovil.service.modelos;

/**
 * Created by root on 09/03/16.
 */
public class MUsuario {


    private int idUsuario;

    private int idPersona;

    private String login;

    private String password;

    private String preguntaSecreta;

    private String respuestaSecreta;

    private java.util.Date fechaCreacion;


    public MUsuario(int idUsuario, int idPersona ,String login, String password, String preguntaSecreta, String respuestaSecreta) {
        this.idUsuario = idUsuario;
        this.idPersona = idPersona;
        this.login = login;
        this.password = password;
        this.preguntaSecreta = preguntaSecreta;
        this.respuestaSecreta = respuestaSecreta;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getORMID() {
        return getIdUsuario();
    }

    public void setLogin(String value) {
        this.login = value;
    }

    public String getLogin() {
        return login;
    }

    public void setPassword(String value) {
        this.password = value;
    }

    public String getPassword() {
        return password;
    }

    public void setPreguntaSecreta(String value) {
        this.preguntaSecreta = value;
    }

    public String getPreguntaSecreta() {
        return preguntaSecreta;
    }

    public void setRespuestaSecreta(String value) {
        this.respuestaSecreta = value;
    }

    public String getRespuestaSecreta() {
        return respuestaSecreta;
    }

    public void setFechaCreacion(java.util.Date value) {
        this.fechaCreacion = value;
    }

    public java.util.Date getFechaCreacion() {
        return fechaCreacion;
    }

}
