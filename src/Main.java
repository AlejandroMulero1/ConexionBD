

import java.sql.*;

//ad2223_amulero amulero amulero
public class Main {
    public static void main(String[] args) {
        try {
            Connection con=CrudBBDD.conectarBD();
            Statement statement=con.createStatement();
            String sql="USE ad2223_amulero";
            statement.execute(sql);
            CrudBBDD.crearTabla(statement, "Profesores", new String[]{"idProfesor INT AUTO_INCREMENT",
                                                                            "Nombre VARCHAR(45)",
                                                                            "Apellidos VARCHAR(45)",
                                                                            "FechaNacimiento DATE",
                                                                            "Antiguedad INT",
                                                                            "PRIMARY KEY (idProfesor)"
            });
            CrudBBDD.crearTabla(statement, "Alumnado", new String[]{"idAlumnado INT AUTO_INCREMENT",
                                                                        "Nombre VARCHAR(45)",
                                                                        "Apellidos VARCHAR(45)",
                                                                        "FechaNacimiento DATE",
                                                                        "PRIMARY KEY (idAlumnado)"
            });

            CrudBBDD.crearTabla(statement, "Matricula", new String[]{"id INT AUTO_INCREMENT",
                                                                        "idProfesorado INT",
                                                                        "idAlumnado INT",
                                                                        "Asignatura VARCHAR(45)",
                                                                        "Curso INT",
                                                                        "PRIMARY KEY (id)",
                                                                        "FOREIGN KEY (idProfesorado) REFERENCES Profesores (idProfesor)",
                                                                        "FOREIGN KEY (idAlumnado) REFERENCES Alumnado (idAlumnado)"});



        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}