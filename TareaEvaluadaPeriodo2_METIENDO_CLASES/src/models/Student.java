package models;

import View.View;//Para JOptionPane
import javax.swing.JOptionPane;

public class Student 
{
    private int id;
    private String names;
    private String lastnames;
    private int genre;
    private String birth_date;
    private int age;
    private String adress;
    private int phone;
    private String carnet;
    private double average_score;
    private String email;

    //Constructor sin parámetros
   public Student (){}
    
    //Full constructor
    //Constructor con ID (Para Update)
    public Student( int pId,String pNames, String pLastnames, int pGenre,String pBirth_date, String pAdress, int pAge, int pPhone, String pEmail, String pCarnet, double pAverage_score) 
    {
        this.id=pId;
        this.names = pNames;
        this.lastnames = pLastnames;
        this.genre = pGenre;
        this.age = pAge;
        this.adress = pAdress;
        this.birth_date= pBirth_date;
        this.phone = pPhone;
        this.email=pEmail;
        this.carnet = pCarnet;
        this.average_score = pAverage_score;
    }
    
    //Sin ID (para Crear)
    public Student( String pNames, String pLastnames, int pGenre,String pBirth_date, String pAdress, int pAge, int pPhone, String pEmail, String pCarnet, double pAverage_score) 
    {
        this.names = pNames;
        this.lastnames = pLastnames;
        this.genre = pGenre;
        this.age = pAge;
        this.adress = pAdress;
        this.birth_date= pBirth_date;
        this.phone = pPhone;
        this.email=pEmail;
        this.carnet = pCarnet;
        this.average_score = pAverage_score;
    }
    
    //Constructor without NULLABLES
    //NO USADO
    public Student( int pId,String pNames, String pLastnames, int pGenre,  String pBirth_date, int pAge, String pAdress) 
    {
        this.id = pId;
        this.names = pNames;
        this.lastnames = pLastnames;
        this.genre = pGenre;
        this.age = pAge;
        this.adress = pAdress;
        this.birth_date= pBirth_date;
    }
    
    //ID
    public int getId() 
    {
        return id;
    }

    public void setId(int id) 
    {
        this.id = id;
    }

    //Names
    public String getNames()
    {
        return names;
    }

    public void setNames(String names) 
    {
        this.names = names;
    }

    //Last Names
    public String getLastnames() 
    {
        return lastnames;
    }

    public void setLastnames(String lastnames)
    {
        this.lastnames = lastnames;
    }

    //Genre
    public int getGenre() 
    {
        return genre;
    }

    public void setGenre(int genre) 
    {
        this.genre = genre;
    }

    //Age
    public int getAge()
    {
        return age;
    }

    public void setAge(int age) 
    {
         this.age = age;  
    }

    //Adress
    public String getAdress() 
    {
        return adress;
    }

    public void setAdress(String adress)
    {
        this.adress = adress;
    }
    
    // Birth_date,
    public String getBirth_Date() 
    {
        return birth_date;
    }

    public void setBirth_Date(String b_date) 
    {
        if(b_date.isEmpty())
        {
            b_date="0000-00-00";
        }
        this.birth_date = b_date;
    }

    //Phone
    public int getPhone()
    {
        return phone;
    }

    public void setPhone(int phone)
    {      
        this.phone = phone;
    }

    //Carnet
    public String getCarnet() 
    {
        return carnet;
    }

    public void setCarnet(String carnet) 
    {
        this.carnet = carnet;
    }

    //Average Score
    public double getAverage_score() 
    {
        return average_score;
    }

    public void setAverage_score(double average_score) 
    {
        this.average_score = average_score;
    }

    //Email
    public String getEmail() 
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    //Validación de datos
    public boolean Validation(String names, String lastnames, int genre, String b_date, int age, String adress, int phone, String email, String carnet, double average_score)
    {
        View view=new View();
        if(names.isEmpty())
        {
            JOptionPane.showMessageDialog(view, "Ingrese un nombre");
            return false;
        }
        if(lastnames.isEmpty())
        {
            JOptionPane.showMessageDialog(view, "Ingrese un nombre");
            return false;
        }
        if(genre!=1&&genre!=2)
        {
            genre=1;
        }
        if(age<5)
        {
            JOptionPane.showMessageDialog(view, "Estudiante no tiene la edad minima");
            return false;
        }
        if(b_date.isEmpty())
        {
            JOptionPane.showMessageDialog(view, "Ingrese la fecha de nacimiento");
            return false;
        }
        if(adress.isEmpty())
        {
            JOptionPane.showMessageDialog(view, "Ingrese la dirección del estudiante");
            return false;
        }
        if(phone<10000000||phone>100000000)//El Telefono introducido debe tener 8 dígitos
        {
            JOptionPane.showMessageDialog(view, "Ingrese un número de telefono válido");
            return false;
        }
        if(carnet.length()>9||carnet.isEmpty())
        {
            JOptionPane.showMessageDialog(view, "Ingrese un carnet válido, máximo 9 carácteres");
            return false;
        }
        if(email.isEmpty()||email.length()<=11)
        {
            JOptionPane.showMessageDialog(view, "Ingrese un email válido");
            return false;
        }
        if(average_score>10)
        {
            average_score=10.00;
        }
        else if(average_score<0.00)
        {
            average_score=0;
        }
        else if(average_score>0 && average_score<10)
        {
            average_score=Math.round(average_score*100.0)/100.0;
        }
        else
        {
           average_score=0; 
        }
            
        return true;
    }

}
