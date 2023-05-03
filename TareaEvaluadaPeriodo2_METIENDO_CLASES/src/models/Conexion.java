package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion 
{
    Connection oCon;  

    public Connection getConnection()
    {
        String url="jdbc:mariadb://localhost:3307/TareaEvaluadaP2";
        String user="diego";
        String password="12345";
            try
            {
               Class.forName("org.mariadb.jdbc.Driver") ;
               oCon = DriverManager.getConnection(url,user, password);
               //Confirmation
               System.out.println("Conexion realizada con exito");
            }
            catch(SQLException | ClassNotFoundException e) 
            {
                //Exception report
                System.out.println(e.getMessage());
            }         
            return oCon;
    }
}
