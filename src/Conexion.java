public class Conexion {
    private String user;
    private String password;
    private String servidor;

    public Conexion(String user, String password, String servidor){
        this.user=user;
        this.password=password;
        this.servidor=servidor;
    }
    public static String conectarBD(String user, String password, String servidor){
        return "jdbc:mysql://" + servidor + "?" + "user=" + user + "&password=" + password;
    }
}
