package models;

import models.Conexion;
import org.mariadb.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class StudentDAO //Data Acces Object
{
    Conexion conectar=new Conexion();
    Connection oCon;
    PreparedStatement ps;
    ResultSet rs;
    
   //CREATE
   public int create(Student s)
   {
       String sql="INSERT INTO student (names, lastnames, genre, birth_date, age, adress,"
               + "phone, email, carnet, average_score) VALUES(?,?,?,?,?,?,?,?,?,?)";     
       try
       {
           oCon=(Connection) conectar.getConnection();
           ps=oCon.prepareStatement(sql);
           
           ps.setString(1, s.getNames());
           ps.setString(2, s.getLastnames());
           ps.setInt(3, s.getGenre());
           ps.setString(4, s.getBirth_Date());
           ps.setInt(5,s.getAge());
           ps.setString(6, s.getAdress());
           ps.setInt(7, s.getPhone());
           ps.setString(8, s.getEmail());
           ps.setString(9, s.getCarnet());
           ps.setDouble(10, s.getAverage_score());
           
           
           ps.executeUpdate();
       }
       catch(Exception e)
       {
           System.out.println(e.getMessage());
       }
       return 1;
   }
    
    //READ from DATABASE
   public List read()
   {
       List<Student> datos= new ArrayList<>();
       String sql="SELECT * FROM student";
       try
       {
           oCon=(Connection) conectar.getConnection();
           ps=oCon.prepareStatement(sql);
           rs=ps.executeQuery();
           
           while(rs.next())
           {
               Student p=new Student();
               p.setId(rs.getInt(1));
               p.setNames(rs.getString(2));
               p.setLastnames(rs.getString(3));
               p.setGenre(rs.getInt(4));
               p.setBirth_Date(rs.getString(5));
               p.setAge(rs.getInt(6));
               p.setAdress(rs.getString(7));
               p.setPhone(rs.getInt(8));
               p.setEmail(rs.getString(9));
               p.setCarnet(rs.getString(10));
               p.setAverage_score(rs.getDouble(11));
               
               datos.add(p);
           }         
       }
       catch(Exception e)
       {
           //Exception report
            System.out.println(e.getMessage());
       }
       return datos;
   }
   
   //Update
   public int Update(Student s)
   {
       int r=0;
       String sql="UPDATE student SET names=? , lastnames=? , genre=? , birth_date=? , age=? , adress=? , phone=? , email=? , carnet=? , average_score=?  WHERE id=?";
       try
       {
           oCon=(Connection) conectar.getConnection();
           ps=oCon.prepareStatement(sql);
           
           ps.setString(1, s.getNames());
           ps.setString(2, s.getLastnames());
           ps.setInt(3, s.getGenre());
           ps.setString(4, s.getBirth_Date());
           ps.setInt(5,s.getAge());
           ps.setString(6, s.getAdress());
           ps.setInt(7, s.getPhone());
           ps.setString(8, s.getEmail());
           ps.setString(9, s.getCarnet());
           ps.setDouble(10, s.getAverage_score());
           ps.setInt(11, s.getId());
           
           r= ps.executeUpdate();
           if(r==1)
           {
               return 1;
           }
           else
           {
               return 0;
           }   
       }
       catch(Exception e)
       {
           System.out.println(e.getMessage());
       }
       return r;      
   }
   
   //Delete
   public void delete(int id)
    {
        String sql="DELETE FROM student WHERE id="+id;
        try
        {
            oCon=(Connection) conectar.getConnection();
            ps=oCon.prepareStatement(sql);
            ps.executeUpdate();
        }
        catch(Exception e)
        {
        System.out.println(e.getMessage());
        }
    }
}
