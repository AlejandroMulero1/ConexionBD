import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MetodosLectura {

    public static void  mostrarTodo(ResultSet rs) throws SQLException {
        while (rs.next()){
            
            System.out.print("ID:" +rs.getInt("id"));
            System.out.print(" | nombre: " +rs.getString("nombre"));
            System.out.print(" | apellidos: " +rs.getString("apellidos"));
            System.out.println(" | edad: " +rs.getString("edad"));
        }
    }

    public static String imprimirArray(String[] array){
        String arrayTexto="";
        for (int i=0; i<array.length; i++){
            arrayTexto+=array[i];
            if (i<array.length-1){
                arrayTexto+=", ";
            }
        }
        return arrayTexto;
    }
    public static void  mostrar(ResultSet rs, String[] campos) throws SQLException {
        if (campos[0]=="*"){
            mostrarTodo(rs);
        }
        else{
        while (rs.next()) {
            for (int i = 0; i < campos.length; i++) {
                System.out.print(campos[i] + ":" + rs.getString((String) campos[i]));
                if (i < campos.length - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
        }
        }
    }
    public static void leerDatos(Statement statement) throws SQLException {
        String campos[]={"nombre", "apellidos"};
        ResultSet rs=statement.executeQuery("SELECT " + imprimirArray(campos) + " FROM amulero");
        mostrar(rs, campos);
        statement.close();
    }
    public static void leerPorEdad(Statement statement) throws SQLException {
        String campos[]={"*"};
        ResultSet rs=statement.executeQuery("SELECT " + imprimirArray(campos) + " FROM amulero ORDER BY edad");
        mostrar(rs,campos);
        statement.close();
    }

    public static void leerPorApellidos(Statement statement) throws SQLException {
        String campos[]={"nombre", "apellidos"};
        ResultSet rs=statement.executeQuery("SELECT " + imprimirArray(campos) + " FROM amulero ORDER BY apellidos");
        mostrar(rs,campos );
        statement.close();
    }
    public static void leerPorEdadMayor30(Statement statement) throws SQLException {
        String campos[]={"nombre", "apellidos"};
        ResultSet rs=statement.executeQuery("SELECT " + imprimirArray(campos) + " FROM amulero WHERE (edad>30) ORDER BY edad");
        mostrar(rs, campos);
        statement.close();
    }
    public static void leerNombresJPorApellido(Statement statement) throws SQLException {
        String campos[]={"nombre"};
        ResultSet rs=statement.executeQuery("SELECT "  + imprimirArray(campos) + " FROM amulero WHERE (nombre LIKE 'J%') ORDER BY apellidos");
        mostrar(rs, campos);
        statement.close();
    }
    public static void leerNombresCApellidosAPorEdad(Statement statement) throws SQLException {
        String campos[]={"nombre"};
        ResultSet rs=statement.executeQuery("SELECT " + imprimirArray(campos) + " FROM amulero WHERE (nombre LIKE 'C%') AND (apellidos LIKE 'A%') ORDER BY apellidos desc ");
        mostrar(rs, campos);
        statement.close();
    }
    public static void leerMediaEdad(Statement statement) throws SQLException {;
        ResultSet rs=statement.executeQuery("SELECT AVG(edad) AS 'Edad Media' FROM amulero");
        while (rs.next()) {
            System.out.println("Edad media= " + rs.getInt("Edad Media"));
        }
        statement.close();
    }
    public static void leerApellidoEntreCaracteres(Statement statement) throws SQLException {
        String campos[]={"apellidos"};
        ResultSet rs=statement.executeQuery("SELECT " + imprimirArray(campos) + " FROM amulero WHERE (apellidos LIKE '%oh%') OR (apellidos LIKE '%ma%') ORDER BY apellidos desc ");
        mostrar(rs, campos);
        statement.close();
    }

    public static void leerPorEdadMayor65(Statement statement) throws SQLException {
        String campos[]={"*"};
        ResultSet rs=statement.executeQuery("SELECT " + imprimirArray(campos) + " FROM amulero WHERE (edad>65) ORDER BY edad");
        mostrar(rs, campos);
        statement.close();
    }
}
