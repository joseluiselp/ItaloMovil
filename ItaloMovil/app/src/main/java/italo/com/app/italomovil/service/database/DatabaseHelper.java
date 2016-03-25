package italo.com.app.italomovil.service.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import italo.com.app.italomovil.service.modelos.MArea;
import italo.com.app.italomovil.service.modelos.MClub;
import italo.com.app.italomovil.service.modelos.MPersona;
import italo.com.app.italomovil.service.modelos.MPublicacion;
import italo.com.app.italomovil.service.modelos.MServicio;
import italo.com.app.italomovil.service.modelos.MTipoSugerencia;
import italo.com.app.italomovil.service.modelos.MUsuario;


/**
 * Created by root on 07/03/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "clubitalo";

    // Tables names
    private static final String TABLE_AREA = "area";
    private static final String TABLE_CLUB = "club";
    private static final String TABLE_CONCESION = "concesion";
    private static final String TABLE_SERVICIO = "servicio";
    private static final String TABLE_PUBLICACION = "publicacion";
    private static final String TABLE_USUARIO = "usuario";
    private static final String TABLE_TIPO_SUGE = "tipoSugerencia";
    private static final String TABLE_SUGERENCIA = "sugerencia";
    private static final String TABLE_INVITADO = "invitado";
    private static final String TABLE_PERSONA = "persona";
    private static final String TABLE_CALENDARIO = "calendario";
    private static final String TABLE_HORARIO = "horario";
    private static final String TABLE_SOLI_ALQUILER = "solicitudAlquiler";

    // Common column names
    private static final String KEY_ID = "id";
    private static final String RUTA_FOTO = "rutaFoto";

    // Table Area
    private static final String AREA_TIPO_AREA = "idTipoArea";
    private static final String AREA_NOMBRE = "nombreArea";
    private static final String AREA_DESCRIPCION = "descripcionArea";
    private static final String AREA_ES_RESERVABLE = "esReservable";
    private static final String AREA_PRECIO_RESERVA = "precioReserva";
    private static final String AREA_ES_CONCESIONABLE = "esConcesionable";

    private static final String CREATE_TABLE_AREA = "CREATE TABLE " + TABLE_AREA +
            " (" + KEY_ID + " INTEGER PRIMARY KEY, " +
            AREA_TIPO_AREA + " INTEGER, " +
            AREA_NOMBRE + " TEXT, " +
            AREA_DESCRIPCION + " TEXT, " +
            RUTA_FOTO + " TEXT, " +
            AREA_ES_RESERVABLE + " BOOLEAN, " +
            AREA_PRECIO_RESERVA + " NUMERIC, " +
            AREA_ES_CONCESIONABLE + " BOOLEAN)";


    // Table Club
    private static final String CLUB_NOMBRE = "nombreClub";
    private static final String CLUB_EMAIL = "email";
    private static final String CLUB_MISION = "mision";
    private static final String CLUB_VISION = "vision";
    private static final String CLUB_DIRECCION = "direccion";
    private static final String CLUB_HISTORIA = "historia";
    private static final String CLUB_NRO_TELF = "nroTelefono";
    private static final String CLUB_RUTA_LOGO = "rutaLogo";
    private static final String CLUB_MAX_INVI = "cantidadMaximaInvitados";

    private static final String CREATE_TABLE_CLUB = "CREATE TABLE " + TABLE_CLUB +
            " (" + KEY_ID + " INTEGER PRIMARY KEY, " +
            CLUB_NOMBRE + " TEXT, " +
            CLUB_EMAIL + " TEXT, " +
            CLUB_MISION + " TEXT, " +
            CLUB_VISION + " TEXT, " +
            CLUB_DIRECCION + " TEXT, " +
            CLUB_HISTORIA + " TEXT, " +
            CLUB_NRO_TELF + " TEXT," +
            CLUB_MAX_INVI + " INTEGER," +
            CLUB_RUTA_LOGO + " TEXT)";


    // Table Concesion
    private static final String CONCE_TIPO_CONCE = "idTipoConcesionario";
    private static final String CONCE_ID_PERSO = "idPersona";
    private static final String CONCE_ID_AREA = "idArea";
    private static final String CONCE_RIF = "rifConcesionario";
    private static final String CONCE_NOMBRE = "nombreConcesionario";

    private static final String CREATE_TABLE_CONCESION = "CREATE TABLE " + TABLE_CONCESION +
            " (" + KEY_ID + " INTEGER PRIMARY KEY, " +
            CONCE_TIPO_CONCE + " INTEGER, " +
            CONCE_ID_PERSO + " INTEGER, " +
            CONCE_ID_AREA + " INTEGER, " +
            CONCE_RIF + " TEXT, " +
            RUTA_FOTO + " TEXT, " +
            CONCE_NOMBRE + " TEXT)";


    //Table Servicio
    private static final String SERV_ID_CONCE = "idConcesionario";
    private static final String SERV_ID_AREA = "idArea";
    private static final String SERV_ID_CALEND = "idCalendario";
    private static final String SERV_ID_HORARIO = "idHorario";
    private static final String SERV_NOMBRE = "nombreServicio";
    private static final String SERV_PRECIO_MEN = "precioMensualidad";
    private static final String SERV_PRECIO_MEN_SOC = "precioMensualidadSocio";

    private static final String CREATE_TABLE_SERVICIO = "CREATE TABLE " + TABLE_SERVICIO +
            " (" + KEY_ID + " INTEGER PRIMARY KEY, " +
            SERV_ID_CONCE + " INTEGER, " +
            SERV_ID_AREA + " INTEGER, " +
            SERV_ID_CALEND + " INTEGER, " +
            SERV_ID_HORARIO + " INTEGER, " +
            SERV_NOMBRE + " TEXT, " +
            RUTA_FOTO + " TEXT, " +
            SERV_PRECIO_MEN + " NUMERIC, " +
            SERV_PRECIO_MEN_SOC + " NUMERIC)";

    // Table Publicacion
    private static final String PUBLI_TIPO_PUBLI = "idTipoPublicacion";
    private static final String PUBLI_ESTADO_PUBLI = "idEstadoPublicacion";
    private static final String PUBLI_TITULO = "tituloPublicacion";
    private static final String PUBLI_CONTENIDO = "contenidoPublicacion";
    private static final String PUBLI_FECHA_INICIO = "fechaInicio";
    private static final String PUBLI_FECHA_FIN = "fechaFin";

    private static final String CREATE_TABLE_PUBLICACION = "CREATE TABLE " + TABLE_PUBLICACION +
            " (" + KEY_ID + " INTEGER PRIMARY KEY, " +
            PUBLI_TIPO_PUBLI + " INTEGER, " +
            PUBLI_ESTADO_PUBLI + " INTEGER, " +
            PUBLI_TITULO + " TEXT, " +
            PUBLI_CONTENIDO + " TEXT," +
            RUTA_FOTO + " TEXT, " +
            PUBLI_FECHA_INICIO + " DATETIME, " +
            PUBLI_FECHA_FIN + " DATETIME)";


    // Table Usuario
    private static final String USUA_ID_PERSONA = "idPersona";
    private static final String USUA_ID_ESTADO_USUA = "idEstadoUsuario";
    private static final String USUA_LOGIN = "login";
    private static final String USUA_PASS = "password";
    private static final String USUA_PREGUNTA_SECRETA = "preguntaSecreta";
    private static final String USUA_RESPUESTA_SECRETA = "respuestaSecreta";

    private static final String CREATE_TABLE_USUARIO = "CREATE TABLE " + TABLE_USUARIO +
            " (" + KEY_ID + " INTEGER PRIMARY KEY, " +
            USUA_ID_PERSONA + " INTEGER, " +
            USUA_ID_ESTADO_USUA + " INTEGER, " +
            USUA_LOGIN + " TEXT, " +
            USUA_PASS + " TEXT, " +
            USUA_PREGUNTA_SECRETA + " TEXT, " +
            USUA_RESPUESTA_SECRETA + " TEXT)";

    // Table Sugerencia
    private static final String SUGE_ID_TIPO_SUGE = "idTipoSugerencia";
    private static final String SUGE_DESCRIPCION = "descripcionSugerencia";
    private static final String SUGE_LAST_TIME = "ultimaSugerencia";

    private static final String CREATE_TABLE_SUGERENCIA = "CREATE TABLE " + TABLE_SUGERENCIA +
            " (" + KEY_ID + " INTEGER PRIMARY KEY, " +
            SUGE_ID_TIPO_SUGE + " INTEGER, " +
            SUGE_LAST_TIME + " DATETIME, " +
            SUGE_DESCRIPCION + " TEXT)";

    // Table Tipo Sugerencia
    private static final String TIPO_SUGE_NOMBRE = "nombreTipoSugerencia";
    private static final String TIPO_SUGE_DESCRIPCION = "descripcionTipoSugerencia";

    private static final String CREATE_TABLE_TIPO_SUGE = "CREATE TABLE " + TABLE_TIPO_SUGE +
            " (" + KEY_ID + " INTEGER PRIMARY KEY, " +
            TIPO_SUGE_NOMBRE + " TEXT, " +
            TIPO_SUGE_DESCRIPCION + " TEXT)";

    // Table Invitado
    private static final String INVI_ID_PERSONA = "idPersona";
    private static final String INVI_ID_SOCIO = "idSocio";
    private static final String INVI_FECHA_INVI = "fechaInvitacion";

    private static final String CREATE_TABLE_INVITADO = "CREATE TABLE " + TABLE_INVITADO +
            " (" + KEY_ID + " INTEGER PRIMARY KEY, " +
            INVI_ID_PERSONA + " INTEGER, " +
            INVI_ID_SOCIO + " INTEGER, " +
            INVI_FECHA_INVI + " INTEGER)";

    // Table Persona
    private static final String PERSO_TIPO_CEDULA = "tipoCedula";
    private static final String PERSO_CEDULA = "cedulaPersona";
    private static final String PERSO_EMAIL = "emailPersona";
    private static final String PERSO_NOMBRES = "nombresPersona";
    private static final String PERSO_APELLIDOS = "apellidosPersona";
    private static final String PERSO_DIRECCION = "direccionPersona";
    private static final String PERSO_NRO_TELEF_FIJO = "nroTelefonoFijo";
    private static final String PERSO_NRO_TELEF_CEL = "nroTelefonoCelular";
    private static final String PERSO_FECHA_NACIMIENTO = "fechaNacimiento";
    private static final String PERSO_SEXO = "sexo";
    private static final String PERSO_ESTADO_CIVIL = "estadoCivil";

    private static final String CREATE_TABLE_PERSONA = "CREATE TABLE " + TABLE_PERSONA +
            " (" + KEY_ID + " INTEGER PRIMARY KEY, " +
            PERSO_TIPO_CEDULA + " TEXT, " +
            PERSO_CEDULA + " TEXT, " +
            PERSO_EMAIL + " TEXT, " +
            PERSO_NOMBRES + " TEXT, " +
            PERSO_APELLIDOS + " TEXT, " +
            PERSO_DIRECCION + " TEXT, " +
            PERSO_NRO_TELEF_FIJO + " TEXT, " +
            PERSO_NRO_TELEF_CEL + " TEXT, " +
            PERSO_FECHA_NACIMIENTO + " DATETIME, " +
            PERSO_SEXO + " TEXT, " +
            RUTA_FOTO + " TEXT, " +
            PERSO_ESTADO_CIVIL + " TEXT)";

    // Table Calendario
    private static final String CALEN_FECHA_INICIO = "fechaCompletaInicio";
    private static final String CALEN_FECHA_FIN = "fechaCompletaFin";

    private static final String CREATE_TABLE_CALENDARIO = "CREATE TABLE " + TABLE_CALENDARIO +
            " (" + KEY_ID + " INTEGER PRIMARY KEY, " +
            CALEN_FECHA_INICIO + " DATETIME, " +
            CALEN_FECHA_FIN + " DATETIME)";

    // Table Horario
    private static final String HOR_TIEMPO_COMPLETO_INICIO = "tiempoCompletoInicio";
    private static final String HOR_HORA_INICIO = "horaInicio";
    private static final String HOR_MINUTO_INICIO = "minutoInicio";
    private static final String HOR_TIEMPO_COMPLETO_FIN = "tiempoCompletoFin";
    private static final String HOR_HORA_FIN = "horaFin";
    private static final String HOR_MINUTO_FIN = "minutoFin";

    private static final String CREATE_TABLE_HORARIO = "CREATE TABLE " + TABLE_HORARIO +
            " (" + KEY_ID + " INTEGER PRIMARY KEY, " +
            HOR_TIEMPO_COMPLETO_INICIO + " TEXT, " +
            HOR_HORA_INICIO + " INTEGER, " +
            HOR_MINUTO_INICIO + " INTEGER, " +
            HOR_TIEMPO_COMPLETO_FIN + " TEXT, " +
            HOR_HORA_FIN + " INTEGER, " +
            HOR_MINUTO_FIN + " INTEGER)";

    // Table Solicitud Alquiler
    private static final String SOLIALQUI_ID_SOCIO = "idSocio";
    private static final String SOLIALQUI_ID_AREA = "idArea";
    private static final String SOLIALQUI_ID_CALEN = "idCalendario";
    private static final String SOLIALQUI_ID_EST_ALQ = "idEstadoAlquiler";
    private static final String SOLIALQUI_FECHA_SOLI = "fechaSolicitudAlquiler";
    private static final String SOLIALQUI_FECHA_ALQU = "fechaAlquiler";
    private static final String SOLIALQUI_INVI_ESTI = "invitadosEstimados";

    private static final String CREATE_TABLE_SOLIALQUI = "CREATE TABLE " + TABLE_SOLI_ALQUILER +
            " (" + KEY_ID + " INTEGER PRIMARY KEY, " +
            SOLIALQUI_ID_SOCIO + " INTEGER, " +
            SOLIALQUI_ID_AREA + " INTEGER, " +
            SOLIALQUI_ID_CALEN + " INTEGER, " +
            SOLIALQUI_ID_EST_ALQ + " INTEGER, " +
            SOLIALQUI_FECHA_SOLI + " DATETIME, " +
            SOLIALQUI_FECHA_ALQU + " DATETIME, " +
            SOLIALQUI_INVI_ESTI + " INTEGER)";

    private static DatabaseHelper db;

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DatabaseHelper getInstance(Context context) {
        if (db == null) {
            db = new DatabaseHelper(context);
        }
        return db;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_AREA);
        db.execSQL(CREATE_TABLE_CALENDARIO);
        db.execSQL(CREATE_TABLE_CLUB);
        db.execSQL(CREATE_TABLE_CONCESION);
        db.execSQL(CREATE_TABLE_HORARIO);
        db.execSQL(CREATE_TABLE_INVITADO);
        db.execSQL(CREATE_TABLE_PERSONA);
        db.execSQL(CREATE_TABLE_PUBLICACION);
        db.execSQL(CREATE_TABLE_SERVICIO);
        db.execSQL(CREATE_TABLE_SOLIALQUI);
        db.execSQL(CREATE_TABLE_TIPO_SUGE);
        db.execSQL(CREATE_TABLE_SUGERENCIA);
        db.execSQL(CREATE_TABLE_USUARIO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AREA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CALENDARIO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SOLI_ALQUILER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVITADO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUGERENCIA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLUB);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONCESION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HORARIO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSONA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PUBLICACION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICIO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TIPO_SUGE);

        onCreate(db);
    }

    /**
     * Creating method
     */
    public long createArea(MArea area) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, area.getIdArea());
        values.put(AREA_NOMBRE, area.getNombreArea());
        values.put(AREA_DESCRIPCION, area.getDescripcionArea());
        values.put(AREA_PRECIO_RESERVA, area.getPrecioReserva());
        values.put(AREA_TIPO_AREA, area.getIdTipoArea());
        values.put(AREA_ES_RESERVABLE, area.getEsReservable());
        values.put(AREA_ES_CONCESIONABLE, area.getEsConcesionable());
        values.put(RUTA_FOTO, area.getRutaImagen());

        long id;

        if (getArea(area.getIdArea()) == null) {
            // insert row
            id = db.insert(TABLE_AREA, null, values);
        } else {
            id = db.update(TABLE_AREA, values, KEY_ID + " = ?",
                    new String[]{String.valueOf(area.getIdArea())});
        }


        return id;
    }

    /**
     * get single method by id
     */
    public MArea getArea(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_AREA + " WHERE "
                + KEY_ID + " = " + id;


        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();
        if (c.getCount() > 0) {
            int ida = (c.getInt(c.getColumnIndex(KEY_ID)));
            String nombre = (c.getString(c.getColumnIndex(AREA_NOMBRE)));
            String descrip = (c.getString(c.getColumnIndex(AREA_DESCRIPCION)));
            int tipo = (c.getInt(c.getColumnIndex(AREA_TIPO_AREA)));
            boolean conc = (Boolean.getBoolean(c.getString(c.getColumnIndex(AREA_ES_CONCESIONABLE))));
            boolean reser = (Boolean.getBoolean(c.getString(c.getColumnIndex(AREA_ES_RESERVABLE))));
            float precio = (c.getFloat(c.getColumnIndex(AREA_PRECIO_RESERVA)));
            String rutaImg = c.getString(c.getColumnIndex(RUTA_FOTO));
            MArea area = new MArea(ida, tipo, nombre, descrip, "", null, null, precio, reser, conc, rutaImg);
            return area;
        }
        return null;

    }

    /**
     * getting all Method
     */
    public List<MArea> getAllAreas() {
        List<MArea> todos = new ArrayList<MArea>();
        String selectQuery = "SELECT  * FROM " + TABLE_AREA;


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                int ida = (c.getInt(c.getColumnIndex(KEY_ID)));
                String nombre = (c.getString(c.getColumnIndex(AREA_NOMBRE)));
                String descrip = (c.getString(c.getColumnIndex(AREA_DESCRIPCION)));
                int tipo = (c.getInt(c.getColumnIndex(AREA_TIPO_AREA)));
                boolean conc = (Boolean.getBoolean(c.getString(c.getColumnIndex(AREA_ES_CONCESIONABLE))));
                boolean reser = (Boolean.getBoolean(c.getString(c.getColumnIndex(AREA_ES_RESERVABLE))));
                float precio = (c.getFloat(c.getColumnIndex(AREA_PRECIO_RESERVA)));
                String rutaImg = c.getString(c.getColumnIndex(RUTA_FOTO));
                MArea area = new MArea(ida, tipo, nombre, descrip, "", null, null, precio, reser, conc, rutaImg);

                // adding to List
                todos.add(area);
            } while (c.moveToNext());
        }

        return todos;
    }

    /**
     * getting all Method under single tipo Constraint
     */
    public List<MArea> getAllAreasByTipoReservable() {
        List<MArea> todos = new ArrayList<MArea>();

        String selectQuery = "SELECT  * FROM " + TABLE_AREA + " td "
                + " WHERE "
                + AREA_ES_RESERVABLE + " AND NOT(" + AREA_ES_CONCESIONABLE
                + ")";


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                int ida = (c.getInt(c.getColumnIndex(KEY_ID)));
                String nombre = (c.getString(c.getColumnIndex(AREA_NOMBRE)));
                String descrip = (c.getString(c.getColumnIndex(AREA_DESCRIPCION)));
                int tipo = (c.getInt(c.getColumnIndex(AREA_TIPO_AREA)));
                boolean conc = (Boolean.getBoolean(c.getString(c.getColumnIndex(AREA_ES_CONCESIONABLE))));
                boolean reser = (Boolean.getBoolean(c.getString(c.getColumnIndex(AREA_ES_RESERVABLE))));
                float precio = (c.getFloat(c.getColumnIndex(AREA_PRECIO_RESERVA)));
                String rutaImg = c.getString(c.getColumnIndex(RUTA_FOTO));
                MArea area = new MArea(ida, tipo, nombre, descrip, "", null, null, precio, reser, conc, rutaImg);


                // adding to todo list
                todos.add(area);
            } while (c.moveToNext());
        }

        return todos;
    }

    /**
     * getting area count
     */
    public int getAreaCount() {
        String countQuery = "SELECT  * FROM " + TABLE_AREA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    /**
     * Deleting a Area
     */
    public void deleteArea(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_AREA, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public void dropArea() {
        this.getWritableDatabase().execSQL("DROP TABLE IF EXISTS " + TABLE_AREA);
        this.getWritableDatabase().execSQL(CREATE_TABLE_AREA);
    }


    /**
     * Creating method
     */
    public long createPublicacion(MPublicacion publicacion) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, publicacion.getIdPublicacion());
        values.put(PUBLI_TITULO, publicacion.getTituloPublicacion());
        values.put(PUBLI_CONTENIDO, publicacion.getContenidoPublicacion());
        values.put(PUBLI_TIPO_PUBLI, publicacion.getIdTipoPublicacion());
        values.put(PUBLI_FECHA_INICIO, publicacion.getFechaInicio().toString());
        values.put(PUBLI_FECHA_FIN, publicacion.getFechaFin().toString());
        values.put(RUTA_FOTO, publicacion.getRutaImagen());

        long id;

        if (getPublicacion(publicacion.getIdPublicacion()) == null) {
            // insert row
            id = db.insert(TABLE_PUBLICACION, null, values);
        } else {
            id = db.update(TABLE_PUBLICACION, values, KEY_ID + " = ?",
                    new String[]{String.valueOf(publicacion.getIdPublicacion())});
        }

        return id;
    }

    /**
     * get single method by id
     */
    public MPublicacion getPublicacion(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_PUBLICACION + " WHERE "
                + KEY_ID + " = " + id;


        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        if (c.getCount() > 0) {
            int idp = (c.getInt(c.getColumnIndex(KEY_ID)));
            String titulo = (c.getString(c.getColumnIndex(PUBLI_TITULO)));
            String contenido = (c.getString(c.getColumnIndex(PUBLI_CONTENIDO)));
            int tipo = (c.getInt(c.getColumnIndex(PUBLI_TIPO_PUBLI)));
            Date fi = (Date.valueOf(c.getString(c.getColumnIndex(PUBLI_FECHA_INICIO))));
            Date fin = (Date.valueOf(c.getString(c.getColumnIndex(PUBLI_FECHA_FIN))));
            String rutaFoto = c.getString(c.getColumnIndex(RUTA_FOTO));
            MPublicacion publi = new MPublicacion(idp, tipo, titulo, contenido, fi, fin, 5, fi, rutaFoto);
            return publi;
        }
        return null;
    }

    /**
     * getting all Method
     */
    public List<MPublicacion> getAllPublicaciones() {
        List<MPublicacion> todos = new ArrayList<MPublicacion>();
        String selectQuery = "SELECT  * FROM " + TABLE_PUBLICACION;


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                int idp = (c.getInt(c.getColumnIndex(KEY_ID)));
                String titulo = (c.getString(c.getColumnIndex(PUBLI_TITULO)));
                String contenido = (c.getString(c.getColumnIndex(PUBLI_CONTENIDO)));
                int tipo = (c.getInt(c.getColumnIndex(PUBLI_TIPO_PUBLI)));
                Date fi = (Date.valueOf(c.getString(c.getColumnIndex(PUBLI_FECHA_INICIO))));
                Date fin = (Date.valueOf(c.getString(c.getColumnIndex(PUBLI_FECHA_FIN))));
                String rutaFoto = c.getString(c.getColumnIndex(RUTA_FOTO));
                MPublicacion publi = new MPublicacion(idp, tipo, titulo, contenido, fi, fin, 5, fi, rutaFoto);

                // adding to List
                todos.add(publi);
            } while (c.moveToNext());
        }

        return todos;
    }

    /**
     * getting all Method under single tipo Constraint
     */
    public List<MPublicacion> getAllPublisByTipo(String tipo) {
        List<MPublicacion> todos = new ArrayList<MPublicacion>();

        String selectQuery = "SELECT  * FROM " + TABLE_PUBLICACION
                + " WHERE "
                + PUBLI_TIPO_PUBLI + " = " + tipo;


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                int idp = (c.getInt(c.getColumnIndex(KEY_ID)));
                String titulo = (c.getString(c.getColumnIndex(PUBLI_TITULO)));
                String contenido = (c.getString(c.getColumnIndex(PUBLI_CONTENIDO)));
                int tipop = (c.getInt(c.getColumnIndex(PUBLI_TIPO_PUBLI)));
                Date fi = (Date.valueOf(c.getString(c.getColumnIndex(PUBLI_FECHA_INICIO))));
                Date fin = (Date.valueOf(c.getString(c.getColumnIndex(PUBLI_FECHA_FIN))));
                String rutaFoto = c.getString(c.getColumnIndex(RUTA_FOTO));
                MPublicacion publi = new MPublicacion(idp, tipop, titulo, contenido, fi, fin, 5, fi, rutaFoto);

                // adding to todo list
                todos.add(publi);
            } while (c.moveToNext());
        }

        return todos;
    }

    /**
     * getting area count
     */
    public int getPubliCount() {
        String countQuery = "SELECT  * FROM " + TABLE_PUBLICACION;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    /**
     * Deleting a Area
     */
    public void deletePublicacion(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PUBLICACION, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public void dropPubli() {
        this.getWritableDatabase().execSQL("DROP TABLE IF EXISTS " + TABLE_PUBLICACION);
        this.getWritableDatabase().execSQL(CREATE_TABLE_PUBLICACION);
    }


    /**
     * Creating method
     */
    public long createClub(MClub club) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, club.getIdClub());
        values.put(CLUB_DIRECCION, club.getDireccion());
        values.put(CLUB_EMAIL, club.getEmail());
        values.put(CLUB_HISTORIA, club.getHistoria());
        values.put(CLUB_MISION, club.getMision());
        values.put(CLUB_NOMBRE, club.getNombreClub());
        values.put(CLUB_NRO_TELF, club.getNroTelefono());
        values.put(CLUB_RUTA_LOGO, club.getRutaLogo());
        values.put(CLUB_VISION, club.getVision());
        values.put(CLUB_MAX_INVI, club.getCantidadMaxInvitados());

        long id;

        if (getClub() == null) {
            // insert row
            id = db.insert(TABLE_CLUB, null, values);
        } else {
            id = db.update(TABLE_CLUB, values, KEY_ID + " = ?",
                    new String[]{String.valueOf(club.getIdClub())});
        }

        return id;
    }

    /**
     * get single method by id
     */
    public MClub getClub() {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_CLUB ;


        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        if (c.getCount() > 0) {
            int idc = (c.getInt(c.getColumnIndex(KEY_ID)));
            String nombre = (c.getString(c.getColumnIndex(CLUB_NOMBRE)));
            String mision = (c.getString(c.getColumnIndex(CLUB_MISION)));
            String vision = (c.getString(c.getColumnIndex(CLUB_VISION)));
            String historia = (c.getString(c.getColumnIndex(CLUB_HISTORIA)));
            String direccion = (c.getString(c.getColumnIndex(CLUB_DIRECCION)));
            String email = (c.getString(c.getColumnIndex(CLUB_EMAIL)));
            String nrotelef = (c.getString(c.getColumnIndex(CLUB_NRO_TELF)));
            String rutalogo = (c.getString(c.getColumnIndex(CLUB_RUTA_LOGO)));
            int maxInvi = c.getInt(c.getColumnIndex(CLUB_MAX_INVI));
            MClub club = new MClub(idc, nombre, email, mision, vision, direccion, historia, rutalogo, nrotelef,maxInvi);
            return club;
        }
        return null;
    }

    public void dropClub() {
        this.getWritableDatabase().execSQL("DROP TABLE IF EXISTS " + TABLE_CLUB);
        this.getWritableDatabase().execSQL(CREATE_TABLE_CLUB);
    }


    /**
     * Creating method
     */
    public long createUsuario(MUsuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, usuario.getIdUsuario());
        values.put(USUA_LOGIN, usuario.getLogin());
        values.put(USUA_PASS, usuario.getPassword());
        values.put(USUA_PREGUNTA_SECRETA, usuario.getPreguntaSecreta());
        values.put(USUA_RESPUESTA_SECRETA, usuario.getRespuestaSecreta());
        values.put(USUA_ID_PERSONA, usuario.getIdPersona());

        long id;

        if (getUsuario() == null) {
            // insert row
            id = db.insert(TABLE_USUARIO, null, values);
        } else {
            id = db.update(TABLE_USUARIO, values, KEY_ID + " = ?",
                    new String[]{String.valueOf(usuario.getIdUsuario())});
        }

        return id;
    }

    /**
     * get single method by id
     */
    public MUsuario getUsuario() {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_USUARIO;


        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        if (c.getCount() > 0) {
            int idc = (c.getInt(c.getColumnIndex(KEY_ID)));
            String login = (c.getString(c.getColumnIndex(USUA_LOGIN)));
            String pass = (c.getString(c.getColumnIndex(USUA_PASS)));
            String preg = (c.getString(c.getColumnIndex(USUA_PREGUNTA_SECRETA)));
            String resp = (c.getString(c.getColumnIndex(USUA_RESPUESTA_SECRETA)));
            int idp = c.getInt(c.getColumnIndex(USUA_ID_PERSONA));

            MUsuario usuario = new MUsuario(idc, idp, login, pass, preg, resp);
            return usuario;
        }
        return null;
    }

    public void login(MUsuario usuario) {
        this.dropUsuario();
        this.createUsuario(usuario);
    }

    public void dropUsuario() {
        this.getWritableDatabase().execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIO);
        this.getWritableDatabase().execSQL(CREATE_TABLE_USUARIO);
    }


    /**
     * Creating method
     */
    public long createPersona(MPersona persona) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, persona.getIdPersona());
        values.put(PERSO_NOMBRES, persona.getNombresPersona());
        values.put(PERSO_APELLIDOS, persona.getApellidosPersona());
        values.put(PERSO_EMAIL, persona.getEmailPersona());
        values.put(PERSO_CEDULA, persona.getCedulaPersona());
        values.put(PERSO_FECHA_NACIMIENTO, persona.getFechaNacimiento().toString());
        values.put(RUTA_FOTO, persona.getRutaFotoCarnet());

        long id;

        if (getPersona() == null) {
            // insert row
            id = db.insert(TABLE_PERSONA, null, values);
        } else {
            id = db.update(TABLE_PERSONA, values, KEY_ID + " = ?",
                    new String[]{String.valueOf(persona.getIdPersona())});
        }

        return id;
    }

    /**
     * get single method by id
     */
    public MPersona getPersona() {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_PERSONA;


        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        if (c.getCount() > 0) {
            int idc = (c.getInt(c.getColumnIndex(KEY_ID)));
            String nombres = (c.getString(c.getColumnIndex(PERSO_NOMBRES)));
            String apellidos = (c.getString(c.getColumnIndex(PERSO_APELLIDOS)));
            String email = (c.getString(c.getColumnIndex(PERSO_EMAIL)));
            String cedula = (c.getString(c.getColumnIndex(PERSO_CEDULA)));
            Date fechanac = Date.valueOf(c.getString(c.getColumnIndex(PERSO_FECHA_NACIMIENTO)));
            String rutafoto = c.getString(c.getColumnIndex(RUTA_FOTO));

            MPersona persona = new MPersona(idc, "V", Integer.parseInt(cedula), email, nombres, apellidos, "", "", "", fechanac, "", "", rutafoto);
            return persona;
        }
        return null;
    }

    public void dropPersona() {
        this.getWritableDatabase().execSQL("DROP TABLE IF EXISTS " + TABLE_PERSONA);
        this.getWritableDatabase().execSQL(CREATE_TABLE_PERSONA);
    }






    /**
     * Creating method
     */
    public long createServicio(MServicio servicio) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, servicio.getIdServicio());
        values.put(SERV_NOMBRE, servicio.getNombreServicio());
        values.put(SERV_PRECIO_MEN, servicio.getPrecioMensualidad());
        values.put(SERV_PRECIO_MEN_SOC, servicio.getPrecioMensualidadSocio());
        values.put(SERV_ID_AREA, servicio.getIdArea().getIdTipoArea());
        values.put(SERV_ID_CALEND, servicio.getIdCalendario());
        values.put(SERV_ID_HORARIO, servicio.getIdHorario());
        values.put(SERV_ID_CONCE, servicio.getIdConcesionario());
        values.put(RUTA_FOTO, servicio.getRutaFoto());

        long id;

        if (getServicio(servicio.getIdServicio()) == null) {
            // insert row
            id = db.insert(TABLE_SERVICIO, null, values);
        } else {
            id = db.update(TABLE_SERVICIO, values, KEY_ID + " = ?",
                    new String[]{String.valueOf(servicio.getIdServicio())});
        }

        return id;
    }

    public List<MServicio> getAllServicio() {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_SERVICIO;


        Cursor c = db.rawQuery(selectQuery, null);

        List<MServicio> list = new ArrayList<MServicio>();
        if (c.moveToFirst()) {
            do {
                int idc = (c.getInt(c.getColumnIndex(KEY_ID)));
                String nombre = (c.getString(c.getColumnIndex(SERV_NOMBRE)));
                double precio = (c.getDouble(c.getColumnIndex(SERV_PRECIO_MEN)));
                double preciosoc = (c.getDouble(c.getColumnIndex(SERV_PRECIO_MEN_SOC)));
                int idcalen = (c.getInt(c.getColumnIndex(SERV_ID_CALEND)));
                int idhor = (c.getInt(c.getColumnIndex(SERV_ID_HORARIO)));
                MArea area = getArea(c.getInt(c.getColumnIndex(SERV_ID_AREA)));
                String rutafoto = c.getString(c.getColumnIndex(RUTA_FOTO));
                int idcon = c.getInt(c.getColumnIndex(SERV_ID_CONCE));

                MServicio servicio = new MServicio(idc, area, rutafoto, nombre, precio, preciosoc, idcalen, idhor, idcon);
                list.add(servicio);
            }while(c.moveToNext());
        }
        return list;
    }

    /**
     * get single method by id
     */
    public MServicio getServicio(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_SERVICIO + " WHERE " +
                KEY_ID + " = " + id;


        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        if (c.getCount() > 0) {
            int idc = (c.getInt(c.getColumnIndex(KEY_ID)));
            String nombre = (c.getString(c.getColumnIndex(SERV_NOMBRE)));
            double precio = (c.getDouble(c.getColumnIndex(SERV_PRECIO_MEN)));
            double preciosoc = (c.getDouble(c.getColumnIndex(SERV_PRECIO_MEN_SOC)));
            int idcalen = (c.getInt(c.getColumnIndex(SERV_ID_CALEND)));
            int idhor = (c.getInt(c.getColumnIndex(SERV_ID_HORARIO)));
            MArea area = getArea(c.getInt(c.getColumnIndex(SERV_ID_AREA)));
            String rutafoto = c.getString(c.getColumnIndex(RUTA_FOTO));
            int idcon = c.getInt(c.getColumnIndex(SERV_ID_CONCE));

            MServicio servicio = new MServicio(idc, area, rutafoto, nombre, precio, preciosoc, idcalen, idhor, idcon);
            return servicio;
        }
        return null;
    }

    public void dropServicio() {
        this.getWritableDatabase().execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICIO);
        this.getWritableDatabase().execSQL(CREATE_TABLE_SERVICIO);
    }

    /**
     * getting area count
     */
    public int getServicioCount() {
        String countQuery = "SELECT  * FROM " + TABLE_SERVICIO;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }




    /**
     * Creating method
     */
    public long createSugerencia(java.util.Date fecha) {
        SQLiteDatabase db = this.getWritableDatabase();

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        ContentValues values = new ContentValues();
        values.put(KEY_ID, "1");
        values.put(SUGE_LAST_TIME, df.format(fecha));

        long id;

        if (getSugerencia() == null) {
            // insert row
            id = db.insert(TABLE_SUGERENCIA, null, values);
        } else {
            id = db.update(TABLE_SUGERENCIA, values, KEY_ID + " = ?",
                    new String[]{String.valueOf(1)});
        }

        return id;
    }

    /**
     * get single method by id
     */
    public String getSugerencia() {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT "+ SUGE_LAST_TIME+" FROM " + TABLE_SUGERENCIA;


        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        if (c.getCount() > 0) {
            String lastTime = c.getString(c.getColumnIndex(SUGE_LAST_TIME));
            return lastTime;
        }
        return null;
    }

    public void dropSugerencia() {
        this.getWritableDatabase().execSQL("DROP TABLE IF EXISTS " + TABLE_SUGERENCIA);
        this.getWritableDatabase().execSQL(CREATE_TABLE_SUGERENCIA);
    }





    /**
     * Creating method
     */
    public long createTipoSugerencia(MTipoSugerencia tipoSugerencia) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, tipoSugerencia.getIdTipoSugerencia());
        values.put(TIPO_SUGE_NOMBRE, tipoSugerencia.getNombreTipoSugerencia());
        values.put(TIPO_SUGE_DESCRIPCION, tipoSugerencia.getDescripcionTipoSugerencia());

        long id;

        if (getTipoSugerencia(tipoSugerencia.getIdTipoSugerencia()) == null) {
            // insert row
            id = db.insert(TABLE_TIPO_SUGE, null, values);
        } else {
            id = db.update(TABLE_TIPO_SUGE, values, KEY_ID + " = ?",
                    new String[]{String.valueOf(tipoSugerencia.getIdTipoSugerencia())});
        }

        return id;
    }

    /**
     * get single method by id
     */
    public MTipoSugerencia getTipoSugerencia(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_TIPO_SUGE + " WHERE " +
                KEY_ID + " = " + id;


        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        if (c.getCount() > 0) {
            int idc = (c.getInt(c.getColumnIndex(KEY_ID)));
            String nombre = c.getString(c.getColumnIndex(TIPO_SUGE_NOMBRE));
            String descrip = c.getString(c.getColumnIndex(TIPO_SUGE_DESCRIPCION));

            MTipoSugerencia tipoSuge = new MTipoSugerencia(idc, nombre, descrip);
            return tipoSuge;
        }
        return null;
    }

    public List<MTipoSugerencia> getAllTipoSugerencia() {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_TIPO_SUGE;


        Cursor c = db.rawQuery(selectQuery, null);

        List<MTipoSugerencia> list = new ArrayList<MTipoSugerencia>();
        if (c.moveToFirst()) {
            do {
                int idc = (c.getInt(c.getColumnIndex(KEY_ID)));
                String nombre = (c.getString(c.getColumnIndex(TIPO_SUGE_NOMBRE)));
                String descrip = c.getString(c.getColumnIndex(TIPO_SUGE_DESCRIPCION));

                MTipoSugerencia mts = new MTipoSugerencia(idc, nombre, descrip);
                list.add(mts);
            }while(c.moveToNext());
        }
        return list;
    }

    public void dropTipoSugerencia() {
        this.getWritableDatabase().execSQL("DROP TABLE IF EXISTS " + TABLE_TIPO_SUGE);
        this.getWritableDatabase().execSQL(CREATE_TABLE_TIPO_SUGE);
    }

}
