package software.sistema.caja_ahorros.Controller.response;

public class InfoRest {

    private int codigo;
    private String mensaje;
    private int tipoInfo;

    public InfoRest(int codigo, String mensaje, int tipoInfo) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.tipoInfo = tipoInfo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getTipoInfo() {
        return tipoInfo;
    }

    public void setTipoInfo(int tipoInfo) {
        this.tipoInfo = tipoInfo;
    }

}
