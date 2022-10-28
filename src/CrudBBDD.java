import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CrudBBDD {
    public static Connection conectarBD(){
        String password = "2003";
        String usuario = "amulero";
        String url = "jdbc:mysql://dns11036.phdns11.es:3306/ad2223_amulero?user=" + usuario
                + "&password=" + password;
        Connection con = null;

        try {
            con = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("No se pudo conectar a la base de datos");
            e.printStackTrace();
        }
        return con;
    }
    public static void crearTabla(Statement st, String tabla, String[] campos) throws SQLException {
        String sql="CREATE TABLE IF NOT EXISTS ad2223_amulero." + tabla + " (";
        for (int i=0; i< campos.length; i++) {
            sql+=campos[i];
            if (i< campos.length-1){
                sql+=",";
            }
        }
        sql+=");";
        st.execute(sql);
    }

    public static void insertar(){

    }
}
