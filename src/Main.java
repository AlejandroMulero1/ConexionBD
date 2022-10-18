import com.mysql.cj.protocol.Resultset;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Conexion = new Conexion("ad2223", "nervion", "dns11036.phdns11.es");
            String conexionUrl="jdbc:mysql://dns11036.phdns11.es?"+
                  "user=ad2223&password=nervion";
            Connection con = DriverManager.getConnection(conexionUrl);
            System.out.println(con.toString());
            Statement statement= con.createStatement();
            String tabla="amulero";
            String[] campos=new String[]{"ID int", "nombre varchar(255)", "apellidos varchar(255)", "edad int"};
            //crearTabla( "Tabla1");
                System.out.println("Bien");

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    private static void crearTabla(Statement st, String tabla,String[] campos) throws SQLException {
        String sql="CREATE  TABLE ad2223." + tabla + " (";
        for (int i=0; i< campos.length; i++) {
            sql+=campos[i];
            if (i< campos.length-1){
                sql+=",";
            }
        }
        sql+=");";
        st.executeQuery(sql);
    }
}