package umg.progra2.DataBase.Model;

import java.sql.Timestamp;

public class EquipoChampions {

    private int idEquipo;
    private String nombre;
    private String pais;
    private String ciudad;
    private String estadio;
    private int fundacion;
    private String entrenador;
    private String webOficial;
    private String facebook;
    private String twitter;
    private String instagram;
    private String patrocinadorPrincipal;
    private Timestamp creadoEn;

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public int getFundacion() {
        return fundacion;
    }

    public void setFundacion(int fundacion) {
        this.fundacion = fundacion;
    }

    public String getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(String entrenador) {
        this.entrenador = entrenador;
    }

    public String getWebOficial() {
        return webOficial;
    }

    public void setWebOficial(String webOficial) {
        this.webOficial = webOficial;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getPatrocinadorPrincipal() {
        return patrocinadorPrincipal;
    }

    public void setPatrocinadorPrincipal(String patrocinadorPrincipal) {
        this.patrocinadorPrincipal = patrocinadorPrincipal;
    }

    public Timestamp getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(Timestamp creadoEn) {
        this.creadoEn = creadoEn;
    }

}
