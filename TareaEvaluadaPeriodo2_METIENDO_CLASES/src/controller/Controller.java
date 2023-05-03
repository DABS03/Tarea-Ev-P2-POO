package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.View;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import models.Student;
import models.StudentDAO;

public class Controller implements ActionListener, ListSelectionListener
{
    StudentDAO dao=new StudentDAO();
    Student s=new Student();
    View view=new View();
    DefaultTableModel model=new DefaultTableModel();
    
    public Controller(View v)
    {
        this.view=v;
        this.view.btnCleanID.addActionListener(this);//Limpiar ID
        this.view.btnEnter.addActionListener(this);//Aceptar
        this.view.btnDelete.addActionListener(this); //Eliminar
        this.view.btnClean.addActionListener(this);//Limpiar
        this.view.tableData.getSelectionModel().addListSelectionListener(this);//Seleccionar filas
        read(view.tableData);
        cleanBoxes();
    }
    
    //Controlador de acciones
    
    @Override
    public void valueChanged(ListSelectionEvent e) 
    {
        // Obtener la fila seleccionada
        int row = view.tableData.getSelectedRow();
        if (row != -1) 
        {
            // Obtener los datos de la fila seleccionada
            int id = Integer.parseInt(view.tableData.getValueAt(row, 0).toString());
            String names = (String) view.tableData.getValueAt(row, 1);
            String lastnames = (String) view.tableData.getValueAt(row, 2);
            int genre = (int) view.tableData.getValueAt(row, 3);
            String b_date = (String) view.tableData.getValueAt(row, 4);
            int age = (int) view.tableData.getValueAt(row, 5);
            String adress = (String) view.tableData.getValueAt(row, 6);
            int phone = (int) view.tableData.getValueAt(row, 7);
            String email = (String) view.tableData.getValueAt(row, 8);
            String carnet = (String) view.tableData.getValueAt(row, 9);
            double average_score = (double) view.tableData.getValueAt(row, 10);
            
            // Asignar los datos a los campos de texto correspondientes
            view.txtID.setText("" + id);
            view.txtNames.setText(names);
            view.txtLastNames.setText(lastnames);
            if (genre == 1) 
            {
                view.cmbGenre.setSelectedItem("Mujer");
            } 
            else if (genre == 2) 
            {
                view.cmbGenre.setSelectedItem("Hombre");
            }
            view.txtAge.setText("" + age);
            view.txtAdress.setText(adress);
            view.txtPhone.setText("" + phone);
            view.txtEmail.setText(email);
            view.txtCarnet.setText(carnet);
            view.txtAverage.setText("" + average_score);
            // Formateo del String al JDataChooser
            try 
            {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(b_date);
                view.txtBDateChooser.setDate(date);
            } 
            catch (ParseException ex) 
            {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
       try
       {
           //clean ID - bonton X
           
           if(e.getSource()==view.btnCleanID)
           {
               cleanID();
           }
           //Button Ingresar
           
           if(e.getSource()==view.btnEnter)
           {
           //false= Create true=Update
              
              
              if(view.txtID.getText().isEmpty())
              {
                  create();
              }
              else
              {
                  Update();
              }
              
              cleanTable();
              read(view.tableData);           
           }        
            
            //Delete
            if(e.getSource()==view.btnDelete)
            {
                delete();
                cleanBoxes();
                cleanTable();
                read(view.tableData);
            }
            
            //Limpiar
            if(e.getSource()==view.btnClean)
            {
                cleanBoxes();
            }
       }
       catch(Exception ex)
       {
           //Exception report
           System.out.println(ex);
       }
    }  
    
    //Limpiar tableData
    void cleanTable()
    {
        for(int i=0; i<view.tableData.getRowCount();i++)
        {
            model.removeRow(i);
            i-=1;
        }
    }
    //Clean ID
    public void cleanID()
    {
        view.txtID.setText("");
    }
    
    //Limpiar cajas
    
    void cleanBoxes()
    {
        view.txtID.setText("");
        view.txtNames.setText("");
        view.txtLastNames.setText("");
        view.cmbGenre.setSelectedItem("Mujer");
        view.txtAge.setText("");
        view.txtAdress.setText("");
        view.txtEmail.setText("");
        view.txtCarnet.setText("");
        view.txtAverage.setText("");
        view.txtPhone.setText("");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, 0, 1);
        Date nDte=calendar.getTime();
        view.txtBDateChooser.setDate(nDte);
    }
    
    //Create 
    public void create() throws ParseException
    {
        try
        {
            //Lectura de datos
        String names=view.txtNames.getText();
        String lastnames=view.txtLastNames.getText();
        int genre=0;
        if(view.cmbGenre.getSelectedItem().toString()=="Mujer")
            {
                genre=1;   
            }
            else if(view.cmbGenre.getSelectedItem().toString()=="Hombre")
            {
                genre=2;   
            }
        
        int age=Integer.parseInt(view.txtAge.getText());
        
        String adress=view.txtAdress.getText();
        int phone=Integer.parseInt(view.txtPhone.getText());
        String email=view.txtEmail.getText();
        String carnet=view.txtCarnet.getText();
        double average_score=Double.parseDouble(view.txtAverage.getText());
        
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String date= sdf.format(view.txtBDateChooser.getDate());
        
        //VALIDACION DE DATOS
        if(s.Validation(names, lastnames, genre, date, age, adress, phone, email, carnet, average_score)==false)
        {
            return;
        }
        
        Student s=new Student(names, lastnames,genre,date,adress, age, phone,email, carnet, average_score);
        
            int r=dao.create(s);
            if(r==1)
            {
                JOptionPane.showMessageDialog(view, "Estudiante agregado");
                cleanBoxes();
            }
        }
        catch(Exception e)
        {
            cleanTable();
            read(view.tableData);
            JOptionPane.showMessageDialog(view, "Error");
        }
    }
    
    //Read
    public void read(JTable tableData)
    {
        model=(DefaultTableModel)tableData.getModel();
        model.setRowCount(0);
        List<Student>lista=dao.read();
        Object[]object=new Object[11];
        try
        {
        for(int i =0;i<lista.size();i++)
        {
                object[0]=lista.get(i).getId();
                object[1]=lista.get(i).getNames();
                object[2]=lista.get(i).getLastnames();
                object[3]=lista.get(i).getGenre();
                object[4]=lista.get(i).getBirth_Date();
                object[5]=lista.get(i).getAge();
                object[6]=lista.get(i).getAdress();
                object[7]=lista.get(i).getPhone();
                object[8]=lista.get(i).getEmail();
                object[9]=lista.get(i).getCarnet();
                object[10]=lista.get(i).getAverage_score();
                
                model.addRow(object);
            }
            view.tableData.setModel(model);  
        }
        catch(Exception e)
        {
            //Exception report
            System.out.println(e.getMessage());
        }
    }
    
    //UPDATE
    public void Update()
    {
        try
        {
            //Lectura de datos
        int id=Integer.parseInt(view.txtID.getText());
        String names=view.txtNames.getText();
        String lastnames=view.txtLastNames.getText();
        int genre=0;
        if(view.cmbGenre.getSelectedItem().toString()=="Mujer")
            {
                genre=1;   
            }
            else if(view.cmbGenre.getSelectedItem().toString()=="Hombre")
            {
                genre=2;   
            }
        int age=Integer.parseInt(view.txtAge.getText());
        String adress=view.txtAdress.getText();
        int phone=Integer.parseInt(view.txtPhone.getText());
        String email=view.txtEmail.getText();
        String carnet=view.txtCarnet.getText();
        double average_score=Double.parseDouble(view.txtAverage.getText());
        
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String date= sdf.format(view.txtBDateChooser.getDate());
        
        //VALIDACION DE DATOS
        if(s.Validation(names, lastnames, genre, date, age, adress, phone, email, carnet, average_score)==false)
        {
            return;
        }
        //Constructor con ID
        Student s=new Student(id,names, lastnames,genre,date,adress, age, phone,email, carnet, average_score);
        
            int r=dao.Update(s);
            if(r==1)
            {
                JOptionPane.showMessageDialog(view, "Usuario actualizado");     
            }
            else
            {
                JOptionPane.showMessageDialog(view, "Error, usuario no actualizado");
            }
        }
        catch(Exception e)
        {
            cleanTable();
            read(view.tableData);
            JOptionPane.showMessageDialog(view, "ERROR");
        }
    } 

    //DELETE
    public void delete()
    {
        //Fila seleccionada
        int row=view.tableData.getSelectedRow();
        try
        {
            if(row==-1)
                {
                    JOptionPane.showMessageDialog(view, "Seleccione una fila");
                }
                else
                {
                  int resultado= JOptionPane.showConfirmDialog(view, "¿Desea borrar este registro?", "Confirmar", JOptionPane.YES_NO_OPTION);
                  
                  if(resultado==JOptionPane.YES_OPTION)
                  {
                    int id=Integer.parseInt((String)view.tableData.getValueAt(row, 0).toString());
                    dao.delete(id);
                    cleanBoxes();
                    read(view.tableData);
                    JOptionPane.showMessageDialog(view, "Usuario eliminado");  
                  }
                  else
                  {
                      return;
                  }    
                }
        }
        catch(Exception e)
        {
            cleanBoxes();
            cleanTable();
            read(view.tableData);
            JOptionPane.showMessageDialog(view, "Error");
        }
    }
}
