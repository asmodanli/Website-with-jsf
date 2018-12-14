

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.*;
import javax.faces.bean.SessionScoped;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@ManagedBean(name="form_data")
@SessionScoped
public class Form_Data implements Serializable{

 
 
    
 
    
    public int getGun() {
        return gun;
    }

    public void setGun(int gun) {
        this.gun = gun;
    }
    int gun;
    public String tur_name;
    private List<String> tur_list=new ArrayList<>(4);
    DataSource dataSource;
   


public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<String> get_tur_name() throws SQLException{
            tur_list.clear();


                if ( dataSource == null )
            throw new SQLException( "Unable to obtain DataSource" );

            // obtain a connection from the connection pool
            Connection connection = dataSource.getConnection();

            // check whether connection was successful
            if ( connection == null )
            throw new SQLException( "Unable to connect to DataSource" );
       

          try{
                PreparedStatement ps=null;
                ps=connection.prepareStatement("select * from yurtici ");
                ResultSet rs=ps.executeQuery();
                while(rs.next()){
                    tur_list.add(rs.getString("name"));
                }
            }catch(SQLException e){
                System.out.println(e);
            }
           try{
                PreparedStatement ps=null;
                ps=connection.prepareStatement("select * from yurtdisi ");
                ResultSet rs=ps.executeQuery();
                while(rs.next()){
                    tur_list.add(rs.getString("name"));
                }
            }catch(SQLException e){
                System.out.println(e);
            }finally{
              connection.close();
          }
            return tur_list;

    }
    
  
    
    public String getTur_name() {
        return tur_name;
    }

    public List<String> getTur_list() {
        return tur_list;
    }

    public void setTur_name(String tur_name) {
        this.tur_name = tur_name;
    }

    public void setTur_list(List<String> tur_list) {
        this.tur_list = tur_list;
    }
    
    public Form_Data() {
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
   
    
    
    }
    

