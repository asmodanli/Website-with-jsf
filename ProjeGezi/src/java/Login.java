
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.*;

import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.Entity;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet; 
import javax.swing.JOptionPane;
import java.util.ArrayList; 
 import javax.faces.event.ActionEvent;



@ManagedBean(name = "user")
@SessionScoped

public class Login implements Serializable{

 @ManagedProperty(value = "#{form_data}")
 private Form_Data form_data;

    public Form_Data getForm_data() {
        return form_data;
    }

    public void setForm_data(Form_Data form_data) {
        this.form_data = form_data;
    }

    public String getIstek() {
        return istek;
    }

    public void setIstek(String istek) {
        this.istek = istek;
    }

    public String getSikayet() {
        return sikayet;
    }

    public void setSikayet(String sikayet) {
        this.sikayet = sikayet;
    }
 private String sikayet;  
private String istek;    
private String name;
private String password;
private String surname;
private String mail;
private String username="";
DataSource dataSource;

   



   
 public static ArrayList<Tur> Sepet = new ArrayList<>(); 

    public static ArrayList<Tur> getSepet() {
        return Sepet;
    }

   
public static ArrayList<Tur> turListesi = new ArrayList<>();
 
public class Tur {
    
    private String Name;
private String Gun; 
private String ID;
 private String Fiyat;
  public java.sql.Date  getDate() {
        return Date;
    }

    public void setDate(java.sql.Date Date) {
        this.Date = Date;
    }
private java.sql.Date Date;
 
    //constructor yani kurucu metot ile yeni bir obje yaratırken verileri    kolay bir şekilde atayabileceğiz.
    public Tur(String ID,String Gun, String Name,  String Fiyat, java.sql.Date Date) {
        this.ID=ID;
        this.Gun = Gun;
        this.Name = Name;
         this.Date= Date;    
         this.Fiyat = Fiyat;
    }
 
 
 
    public String getGeziName() {
        return Name;
    }
 
    public void setGeziAdi(String Name) {
        this.Name = Name;
    }
 
      public String getGeziGunu() {
        return Gun;
    }

    public void setGeziGunu(String Gun) {
        this.Gun = Gun;
    }

  
    public String getId() {
        return ID;
    }

    public void setId(String ID) {
        this.ID = ID;
    }

      public String getFiyat() {
        return Fiyat;
    }

    public void setFiyat(String fiyat) {
        this.Fiyat = fiyat;
    }
   
}

    





public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }






public Login() {
try
{
Context ctx = new InitialContext();
dataSource = (DataSource) ctx.lookup("jdbc/addressbook");
}
catch (NamingException e) 
{
e.printStackTrace();
}
}



public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getPassword() {
return password;
}

public void setPassword(String password) {
this.password = password;
}

public String getMail() {
return mail;
}

public void setMail(String mail) {
this.mail = mail;
}

public String getSurname() {
return surname;
}

public void setSurname(String surname) {
this.surname =  surname;
}
public String getUsername() {
return username;
}

public void setUsername(String username) {
this.username = username;
} 
public ArrayList<Tur> getTurListesi() {
      return turListesi;
}


 public ResultSet getAddresses() throws SQLException
 {
 // check whether dataSource was injected by the server
 if ( dataSource == null )
 throw new SQLException( "Unable to obtain DataSource" );

 // obtain a connection from the connection pool
 Connection connection = dataSource.getConnection();

 // check whether connection was successful
 if ( connection == null )
 throw new SQLException( "Unable to connect to DataSource" );

 try
 {
     // sql cümlesi yazmak için PreparedStatement oluşturmalıyız.    
 // create a PreparedStatement to select the records
 PreparedStatement object1 = connection.prepareStatement(
 "SELECT AD, SOYAD, LOGINNAME, MAIL, PASSWORD FROM LOGIN" );
 CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
  resultSet1.populate( object1.executeQuery() );
  return resultSet1;
 } // end try
 finally
 {
 connection.close(); // return this connection to pool
 } // end finally
 }

public String validate() throws SQLException
{
    // check whether dataSource was injected by the server
 if ( dataSource == null )
 throw new SQLException( "Unable to obtain DataSource" );

 // obtain a connection from the connection pool
 Connection connection = dataSource.getConnection();

 // check whether connection was successful
 if ( connection == null )
 throw new SQLException( "Unable to connect to DataSource" );

 try
 {
     PreparedStatement deneme=connection.prepareStatement("SELECT LOGINNAME,PASSWORD FROM LOGIN WHERE LOGINNAME=? AND PASSWORD=? ");
 deneme.setString(1, username);
 deneme.setString(2, password);
 // create a PreparedStatement to insert a new address book entry
 ResultSet rs = deneme.executeQuery();

			if (rs.next()) {
                            
                            
                                
				return "welcome";
			}
    else{
                            
                            
 FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Geçersiz ya da yanlış kullanıcı girişi", "lütfen kontrol ediniz");
FacesContext.getCurrentInstance().addMessage(null, msg);
                                
                                
			return "login";
                        }
}
finally
 {
 connection.close(); // return this connection to pool
 } 
 
}

public String update() throws SQLException
{
    int i = 0;
    if ( dataSource == null )
 throw new SQLException( "Unable to obtain DataSource" );

 // obtain a connection from the connection pool
 Connection connection = dataSource.getConnection();

 // check whether connection was successful
 if ( connection == null )
 throw new SQLException( "Unable to connect to DataSource" );

 try
 {
     PreparedStatement object3 =
 connection.prepareStatement( "UPDATE LOGIN  SET AD=?, SOYAD=?, MAIL=?, PASSWORD=? WHERE LOGINNAME=? " );
 
     object3.setString(1, name);
     object3.setString(2, surname);
     object3.setString(3, mail);
     object3.setString(4, password);
     object3.setString(5, username);
     
     object3.executeUpdate(); 
 
     return "profilim"; //
 } // end try
 finally
 {
 connection.close(); // return this connection to pool
 } // end finally
 
 
    }

public String delete() throws SQLException
{
 if ( dataSource == null )
 throw new SQLException( "Unable to obtain DataSource" );

 // obtain a connection from the connection pool
 Connection connection = dataSource.getConnection();

 // check whether connection was successful
 if ( connection == null )
 throw new SQLException( "Unable to connect to DataSource" );
 
 try
 {
     
 // create a PreparedStatement to insert a new address book entry
 PreparedStatement object4 =
 connection.prepareStatement("DELETE FROM LOGIN WHERE LOGINNAME=? AND PASSWORD=?");
 object4.setString(1, username);
 object4.setString(2, password);
 
         
 object4.executeUpdate();
return "register"; // go back to index.xhtml page
 } // end try
 finally
 {
 connection.close(); // return this connection to pool
 } // end finally
  
}

//public ArrayList<Tur> SepetiGoster() throws SQLException
//        {
//            if ( dataSource == null )
// throw new SQLException( "Unable to obtain DataSource" );
//
// // obtain a connection from the connection pool
// Connection connection = dataSource.getConnection();
//
// // check whether connection was successful
// if ( connection == null )
// throw new SQLException( "Unable to connect to DataSource" );       
//          
//     try {
//            
//    
//    PreparedStatement object2 =
// connection.prepareStatement( "SELECT STUR, GUN, SFIYAT, SDATE, ID  FROM SEPET WHERE SLOGIN=?" );
//    
//    object2.setString(1, getUsername());
//   
//    
//ResultSet rs = object2.executeQuery();
//
//			if (rs.next()) {
//                            
//                            
//                                
//                                String name =  rs.getString("STUR");
//                                String gun =  rs.getString("GUN");
//                                String fiyat =  rs.getString("SFIYAT");
//                                java.sql.Date date=rs.getDate("SDATE");
//                                String id=rs.getString("ID");
//
//                
//                
//                Sepet.add(new Tur(id,gun,name,fiyat,date));
//				
//                           
//			}
//
//    
//    
//     
//      return Sepet;
//         
//      
// 
//    }
//
//
// finally
// {
//   
// connection.close(); // return this connection to pool
// } 
//            
//        }

 public ArrayList<Tur> Ara(Form_Data f) throws SQLException {
      // turListesi.clear();
      if ( dataSource == null )
 throw new SQLException( "Unable to obtain DataSource" );

 // obtain a connection from the connection pool
 Connection connection = dataSource.getConnection();

 // check whether connection was successful
 if ( connection == null )
 throw new SQLException( "Unable to connect to DataSource" );       
          
     try {
            
    
    PreparedStatement object2 =
 connection.prepareStatement( "SELECT * FROM (SELECT * FROM yurtdısı UNION SELECT *FROM yurtici) as tumtablolar, TARIH"
         + "  WHERE tumtablolar.name=? AND TARIH.ID=TUMTABLOLAR.ID " );
    
    object2.setString(1, form_data.getTur_name());
   
    
ResultSet rs = object2.executeQuery();

			if (rs.next()) {
                            
                            
                                String id=rs.getString("ID");
                                String name =  rs.getString("NAME");
                                String gun =  rs.getString("GUN");
                                String fiyat =  rs.getString("FIYAT");
                                java.sql.Date date=rs.getDate("DATE");

                
                
                turListesi.add(new Tur(id,gun,name,fiyat,date));
				
                           
			}

    
    
     
      
         
        return turListesi;
 
    }


 finally
 {
   
 connection.close(); // return this connection to pool
 } 
 }

public ArrayList<Tur> Temizle( ) throws SQLException {
       turListesi.clear();    
                return turListesi;
 
   

 }








public String SepeteEkle(Tur  tur) throws SQLException
 {
      // Object row = evt.getComponent().getAttributes().get("currentRow");
     if ( dataSource == null )
 throw new SQLException( "Unable to obtain DataSource" );

 // obtain a connection from the connection pool
 Connection connection = dataSource.getConnection();

 // check whether connection was successful
 if ( connection == null )
 throw new SQLException( "Unable to connect to DataSource" );
 try
 {
    
  
     if (username.length()>0){
              
           
    
 

PreparedStatement object3 =
    connection.prepareStatement( "INSERT INTO SEPET " +
 "(STUR, GUN, SFIYAT, SDATE, SLOGIN, ID)" +
 "VALUES ( ?, ?, ?, ?, ?, ?)" );
     
     
     object3.setString(1, tur.Name);
     object3.setString(2, tur.Gun);
     object3.setString(3, tur.Fiyat);
     object3.setDate(4, tur.Date);
     object3.setString(5, username);
     object3.setString(6, tur.ID);
     
     object3.executeUpdate(); 
     
    
    
     
     }
     else {
       FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, username, "Lütfen öncelikle giriş yapınız");
FacesContext.getCurrentInstance().addMessage(null, msg);
     
    //return null;
     }
    
 } // end try
 finally
 {
 connection.close(); // return this connection to pool
 } // end finally
 //return "login";
// return "null";
return "welcome";
 }


 public void mesajYolla() throws SQLException
 {
     if ( dataSource == null )
 throw new SQLException( "Unable to obtain DataSource" );

 // obtain a connection from the connection pool
 Connection connection = dataSource.getConnection();

 // check whether connection was successful
 if ( connection == null )
 throw new SQLException( "Unable to connect to DataSource" );

 try
 {
     PreparedStatement object2 =
 connection.prepareStatement( "INSERT INTO ISTEK " +
 "(LOGINNAME, MESAJ)" +
 "VALUES ( ?, ?)" );
     
      object2.setString( 1, getUsername());
      object2.setString( 2, getIstek());
      
       object2.executeUpdate();
       
 }
 
  finally{
  connection.close();
 }
 }
        
public void sikayetYolla() throws SQLException
 {
     if ( dataSource == null )
 throw new SQLException( "Unable to obtain DataSource" );

 // obtain a connection from the connection pool
 Connection connection = dataSource.getConnection();

 // check whether connection was successful
 if ( connection == null )
 throw new SQLException( "Unable to connect to DataSource" );

 try
 {
     PreparedStatement object2 =
 connection.prepareStatement( "INSERT INTO SIKAYET " +
 "(LOGINNAME, MESAJ)" +
 "VALUES ( ?, ?)" );
     
      object2.setString( 1, getUsername());
      object2.setString( 2, getSikayet());
      
       object2.executeUpdate();
       
 }
 
  finally{
  connection.close();
 }
 }
 

         
 public String save() throws SQLException
 {
 // check whether dataSource was injected by the server
 if ( dataSource == null )
 throw new SQLException( "Unable to obtain DataSource" );

 // obtain a connection from the connection pool
 Connection connection = dataSource.getConnection();

 // check whether connection was successful
 if ( connection == null )
 throw new SQLException( "Unable to connect to DataSource" );

 try
 {
     
 // create a PreparedStatement to insert a new address book entry
 PreparedStatement object2 =
 connection.prepareStatement( "INSERT INTO LOGIN " +
 "(AD, SOYAD, LOGINNAME, MAIL, PASSWORD)" +
 "VALUES ( ?, ?, ?, ?, ?)" );

 // specify the PreparedStatement's arguments
 object2.setString( 1, getName());
 object2.setString( 2, getSurname());
 object2.setString( 3, getUsername());
 object2.setString( 4, getMail());
 object2.setString( 5, getPassword());


 object2.executeUpdate(); // sql cümlesinin çalışması için bunu yazmak şart

// PreparedStatement object3 =
// connection.prepareStatement( "INSERT INTO SEPET " +
// "(SLOGIN)" +
// "VALUES ( ?)" );
//object3.setString( 1, getUsername());
//object3.executeUpdate();
 // specify the PreparedStatement's arguments

 
 return "login"; // go back to index.xhtml page
 } // end try
 finally
 {
 connection.close(); // return this connection to pool
 } // end finally
 }
 }



