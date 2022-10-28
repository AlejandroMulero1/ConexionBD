import java.sql.*;
import java.util.Scanner;

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
    public static void leerNombresJPorApellidoPS(Connection con) throws SQLException {
        String letraNombre;
        Scanner sc= new Scanner(System.in);
        PreparedStatement pstmt = con.prepareStatement("SELECT nombre FROM amulero WHERE (nombre LIKE (?)) ORDER BY apellidos desc");

        System.out.println("Introduzca la letra por la que desee que empieze el nombre");
        letraNombre=sc.nextLine();

        pstmt.setString(1, letraNombre + "%");
        ResultSet rs=pstmt.executeQuery();

        while (rs.next()){
            System.out.println("Nombre: " +rs.getString("nombre"));
        }
        pstmt.close();
    }

    public static void leerNombresLetraApellidosLetraPorEdadPS(Connection con) throws SQLException {
        String letraNombre,letraApellido;
        Scanner sc= new Scanner(System.in);
        PreparedStatement pstmt = con.prepareStatement("SELECT nombre FROM amulero WHERE (nombre LIKE ?) AND (apellidos LIKE ?) ORDER BY edad");

        System.out.println("Introduzca la letra por la que desee que empieze el nombre");
        letraNombre=sc.nextLine();
        System.out.println("Introduzca la letra por la que desee que empieze el apellido");
        letraApellido=sc.nextLine();
        pstmt.setString(1, letraNombre + "%");
        pstmt.setString(2, letraApellido + "%");
        ResultSet rs=pstmt.executeQuery();

        while (rs.next()){
            System.out.println("Nombre: " +rs.getString("nombre"));
        }
        pstmt.close();
    }

    public static void leerPorEdadPS(Connection con) throws SQLException {
        int num1, num2;
        Scanner sc= new Scanner(System.in);
        PreparedStatement pstmt = con.prepareStatement("SELECT nombre FROM amulero WHERE edad BETWEEN ? AND ?");

        System.out.println("Introduzca la letra por la que desee que empieze el nombre");
        num1=sc.nextInt();
        System.out.println("Introduzca la letra por la que desee que empieze el apellido");
        num2=sc.nextInt();
        pstmt.setInt(1, num1);
        pstmt.setInt(2, num2);
        ResultSet rs=pstmt.executeQuery();

        while (rs.next()){
            System.out.println("Nombre: " +rs.getString("nombre"));
        }
        pstmt.close();
    }
    public static void insertarDatos(Statement st) throws SQLException {
        String[] datos= {"insert into amulero (id, nombre, apellidos, edad) values (1, 'Bron', 'Ghidini', 20)," +
                " (2, 'Merridie', 'Uttridge', 99)," +
                " (3, 'Ashlee', 'Hoogendorp', 66)," +
                " (4, 'Brion', 'Inglish', 91)," +
                " (5, 'Edeline', 'Mapledorum', 21)," +
                " (6, 'August', 'Sagar', 65)," +
                " (7, 'Nyssa', 'Magister', 78)," +
                " (8, 'Celisse', 'Chuck', 18)," +
                " (9, 'Corbet', 'Lorain', 25)," +
                " (10, 'Allyn', 'Sackes', 89)," +
                " (11, 'Adelheid', 'Yuranovev', 52)," +
                " (12, 'Kennan', 'Bracken', 94)," +
                " (13, 'Moira', 'Wiltshear', 90)," +
                " (14, 'Tracey', 'Ruddiforth', 79)," +
                " (15, 'Doyle', 'Frisdick', 46)," +
                " (16, 'Elaina', 'Allcock', 77)," +
                " (17, 'Arabela', 'Cummins', 45)," +
                " (18, 'Evvie', 'Bovis', 78)," +
                " (19, 'Codi', 'Cultcheth', 74)," +
                " (20, 'Creigh', 'Kilalea', 79)," +
                " (21, 'Teodora', 'Katte', 21)," +
                " (22, 'Cindy', 'Leibold', 81)," +
                " (23, 'Jo-ann', 'Poland', 54)," +
                " (24, 'Ivie', 'Arnolds', 4)," +
                " (25, 'Carter', 'Abreheart', 79)," +
                " (26, 'Oates', 'Archambault', 7)," +
                " (27, 'Melanie', 'Jepson', 3)," +
                " (28, 'Griffy', 'Liddle', 63)," +
                " (29, 'Haywood', 'Sabate', 66)," +
                " (30, 'Wendell', 'Coggin', 89)," +
                " (31, 'Osborne', 'Micklewright', 88)," +
                " (32, 'Tull', 'Cash', 38)," +
                " (33, 'Claudie', 'Heggs', 85)," +
                " (34, 'Kendra', 'Paine', 69)," +
                " (35, 'Phaidra', 'Pletts', 12)," +
                " (36, 'Mufi', 'Brian', 60)," +
                " (37, 'Hana', 'Vasic', 64)," +
                " (38, 'Itch', 'Louisot', 96)," +
                " (39, 'Franky', 'Wikey', 86)," +
                " (40, 'Juana', 'Blandamore', 63)," +
                " (41, 'Even', 'Lenihan', 64)," +
                " (42, 'Dorisa', 'Arnet', 100)," +
                " (43, 'Dermot', 'Prinnett', 81)," +
                " (44, 'Edwin', 'Frude', 68)," +
                " (45, 'Hamilton', 'Feathers', 93)," +
                " (46, 'Alfonso', 'Brecknall', 59)," +
                " (47, 'Phineas', 'Verrier', 82)," +
                " (48, 'Stephana', 'Labbet', 12)," +
                " (49, 'Saunderson', 'Matuszinski', 54)," +
                " (50, 'Stu', 'Lippo', 69)," +
                " (51, 'Clayborn', 'Galey', 2)," +
                " (52, 'Kipper', 'Symons', 65)," +
                " (53, 'Rosetta', 'Kobus', 41)," +
                " (54, 'Georgia', 'Matteotti', 10)," +
                " (55, 'Alane', 'Radwell', 47)," +
                " (56, 'Dacia', 'Donat', 55)," +
                " (57, 'Dasha', 'Crafter', 59)," +
                " (58, 'Micki', 'Innerstone', 4)," +
                " (59, 'Ariela', 'Gaber', 64)," +
                " (60, 'Celestina', 'Sheppard', 4)," +
                " (61, 'Kathleen', 'Lent', 59)," +
                " (62, 'Dolly', 'Fewster', 61)," +
                " (63, 'Stephanie', 'Toffoloni', 56)," +
                " (64, 'Kimberli', 'Precious', 21)," +
                " (65, 'Ashlan', 'Iacovo', 96)," +
                " (66, 'Verla', 'Legonidec', 66)," +
                " (67, 'Orelia', 'McSherry', 69)," +
                " (68, 'Felipa', 'Girardi', 25)," +
                " (69, 'Berton', 'Wheeler', 56)," +
                " (70, 'Sharon', 'Wansbury', 49)," +
                " (71, 'Dorita', 'Streeton', 51)," +
                " (72, 'Libbi', 'Aberkirdo', 88)," +
                " (73, 'Jacobo', 'Bolstridge', 30)," +
                " (74, 'Dinnie', 'Eyes', 97)," +
                " (75, 'Lorain', 'Garaghan', 27)," +
                " (76, 'Terrijo', 'Venney', 31)," +
                " (77, 'Shalne', 'Jope', 48)," +
                " (78, 'Kele', 'Foulks', 79)," +
                " (79, 'Adelind', 'Barczewski', 8)," +
                " (80, 'Elie', 'Castellan', 72)," +
                " (81, 'Amy', 'Evered', 3)," +
                " (82, 'Nita', 'Smieton', 48)," +
                " (83, 'Lorrie', 'Fearne', 42)," +
                " (84, 'Fowler', 'Kittles', 25)," +
                " (85, 'Sandra', 'De Dantesie', 16)," +
                " (86, 'Giffard', 'Klazenga', 14)," +
                " (87, 'Donalt', 'O''Shields', 50)," +
                " (88, 'Daryn', 'Feldmann', 28)," +
                " (89, 'Lenette', 'Levett', 58)," +
                " (90, 'Rosalind', 'Novak', 64)," +
                " (91, 'Hedy', 'Braitling', 86)," +
                " (92, 'Shelton', 'Bartol', 9)," +
                " (93, 'Jordana', 'Thornborrow', 84)," +
                " (94, 'Demetre', 'Pawlata', 64)," +
                " (95, 'Shina', 'Bramhill', 34)," +
                " (96, 'Filberte', 'Robrose', 16)," +
                " (97, 'Mace', 'Martynikhin', 32)," +
                " (98, 'Lev', 'Ebbin', 50)," +
                " (99, 'Shari', 'Ife', 66)," +
                " (100, 'Silvano', 'Reast', 94)," +
                " (101, 'Sigismond', 'Klossek', 98)," +
                " (102, 'Tandie', 'Huyge', 77)," +
                " (103, 'Lorri', 'Newbury', 59)," +
                " (104, 'Salvidor', 'Ewbanks', 11)," +
                " (105, 'Truda', 'Gobell', 67)," +
                " (106, 'Paolo', 'Kacheller', 36)," +
                " (107, 'Kaylee', 'Breckell', 100)," +
                " (108, 'Zachariah', 'Innocenti', 73)," +
                " (109, 'Wallis', 'Dollen', 31)," +
                " (110, 'Joey', 'Lambourne', 61)," +
                " (111, 'Nanete', 'Dishmon', 11)," +
                " (112, 'Skipton', 'Kristoffersen', 67)," +
                " (113, 'Allyson', 'Hourahan', 65)," +
                " (114, 'Cecilius', 'Skaid', 4)," +
                " (115, 'Marlo', 'Strathe', 5)," +
                " (116, 'Cyb', 'Spira', 57)," +
                " (117, 'Phaidra', 'Haugg', 100)," +
                " (118, 'Murry', 'Le Barr', 23)," +
                " (119, 'Siana', 'Ridehalgh', 74)," +
                " (120, 'Bat', 'Segrott', 68)," +
                " (121, 'Dasie', 'Presslee', 14)," +
                " (122, 'Currey', 'Huard', 47)," +
                " (123, 'Ogden', 'De Biasio', 76)," +
                " (124, 'Marena', 'Eite', 78)," +
                " (125, 'Huntington', 'Leach', 10)," +
                " (126, 'Kain', 'Finby', 40)," +
                " (127, 'Ward', 'Insole', 24)," +
                " (128, 'Amelie', 'Rodger', 87)," +
                " (129, 'Thain', 'Sam', 48)," +
                " (130, 'Trefor', 'Agett', 94)," +
                " (131, 'Deane', 'Caughan', 28)," +
                " (132, 'Jessalin', 'Skitt', 72)," +
                " (133, 'Munroe', 'Lyddyard', 72)," +
                " (134, 'Bonny', 'Muckeen', 39)," +
                " (135, 'Avie', 'Halso', 82)," +
                " (136, 'Simonette', 'Abate', 68)," +
                " (137, 'Keir', 'Stanmer', 82)," +
                " (138, 'Marika', 'Caldayrou', 11)," +
                " (139, 'Roz', 'Cousans', 64)," +
                " (140, 'Ashton', 'Zannini', 59)," +
                " (141, 'Lorianne', 'Longstaff', 11)," +
                " (142, 'Remy', 'Flear', 42)," +
                " (143, 'Anita', 'Tomaino', 9)," +
                " (144, 'Emanuel', 'Scrammage', 48)," +
                " (145, 'Waylan', 'Pizer', 72)," +
                " (146, 'Lee', 'Postle', 11)," +
                " (147, 'Wake', 'Eversfield', 92)," +
                " (148, 'Alfredo', 'Wollen', 5)," +
                " (149, 'Aldwin', 'Sylvester', 70)," +
                " (150, 'Gregor', 'Looker', 32)," +
                " (151, 'Cassie', 'Rubinfeld', 28)," +
                " (152, 'Jerrie', 'Tubb', 36)," +
                " (153, 'Alvie', 'Phillott', 14)," +
                " (154, 'Lisbeth', 'Lemery', 70)," +
                " (155, 'Nerti', 'Durrett', 10)," +
                " (156, 'Rayna', 'Shoreson', 90)," +
                " (157, 'Krishna', 'Eidelman', 92)," +
                " (158, 'Megan', 'Treffrey', 45)," +
                " (159, 'Angelina', 'Pagden', 59)," +
                " (160, 'Skippy', 'Toop', 82)," +
                " (161, 'Cyrus', 'Jobbins', 38)," +
                " (162, 'Kelley', 'Bronger', 87)," +
                " (163, 'Abraham', 'Bawden', 91)," +
                " (164, 'Kyla', 'Alexandersson', 10)," +
                " (165, 'Rachele', 'Noyes', 88)," +
                " (166, 'Ronalda', 'Larmet', 88)," +
                " (167, 'Antonie', 'Pickburn', 87)," +
                " (168, 'Caria', 'Stiger', 78)," +
                " (169, 'Shandee', 'Cabrera', 60)," +
                " (170, 'Toddy', 'Crocroft', 60)," +
                " (171, 'Sunny', 'Earnshaw', 42)," +
                " (172, 'Jerri', 'Hewes', 23)," +
                " (173, 'Giraud', 'Blonden', 19)," +
                " (174, 'Dorothy', 'Valance', 50)," +
                " (175, 'Gilly', 'Shambrooke', 73)," +
                " (176, 'Karisa', 'Cogin', 43)," +
                " (177, 'Lockwood', 'Castenda', 87)," +
                " (178, 'Jimmie', 'De Francesco', 94)," +
                " (179, 'Shaina', 'Quarles', 35)," +
                " (180, 'Gallagher', 'Karpfen', 62)," +
                " (181, 'Wyndham', 'Freddi', 42)," +
                " (182, 'Rudd', 'Honisch', 9)," +
                " (183, 'Marlyn', 'Bradburne', 35)," +
                " (184, 'Shanan', 'Kingham', 75)," +
                " (185, 'Felizio', 'Pass', 23)," +
                " (186, 'Lilian', 'Gudgin', 64)," +
                " (187, 'Ulla', 'McReedy', 47)," +
                " (188, 'Sher', 'Gladtbach', 49)," +
                " (189, 'Emmott', 'Polkinhorn', 60)," +
                " (190, 'Taddeusz', 'Cranston', 69)," +
                " (191, 'Gwenni', 'Deme', 1)," +
                " (192, 'Geno', 'Selvey', 22)," +
                " (193, 'Gray', 'Burdus', 57)," +
                " (194, 'Nikolaos', 'McGrah', 56)," +
                " (195, 'Maura', 'Tuxill', 98)," +
                " (196, 'Andree', 'Maghull', 26)," +
                " (197, 'Milissent', 'Volcker', 10)," +
                " (198, 'Shandee', 'Strong', 58)," +
                " (199, 'Kriste', 'Glynne', 19)," +
                " (200, 'Minny', 'Lidgely', 71);"
        };
        for (int i=0; i< datos.length; i++){
            st.execute(datos[i]);
            st.close();
        }
        System.out.println(datos.length);
    }
}
