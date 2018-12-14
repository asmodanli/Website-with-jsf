
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@ManagedBean(name="date_data")
@RequestScoped
public class Date_Data {
    private String date;
    private List<String> date_list=new ArrayList<>();
DataSource dataSource;
   


public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<String> get_date() throws SQLException{
                if ( dataSource == null )
            throw new SQLException( "Unable to obtain DataSource" );

            // obtain a connection from the connection pool
            Connection connection = dataSource.getConnection();

            // check whether connection was successful
            if ( connection == null )
            throw new SQLException( "Unable to connect to DataSource" );
       

          try{
                PreparedStatement ps=null;
                ps=connection.prepareStatement("select * from tarihler");
                ResultSet rs=ps.executeQuery();
                while(rs.next()){
                    date_list.add(rs.getString("tarih"));
                }
            }catch(SQLException e){
                System.out.println(e);
            }finally{
              connection.close();
          }
            return  date_list;

    }
    
  
    
    public String getDate() {
        return date;
    }

    public List<String> getDate_list() {
        return date_list;
    }

    public void setDate(String date) {
        this.date =date;
    }

    public void setTur_list(List<String> date_list) {
        this.date_list = date_list;
    }
    
    public Date_Data() {
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
    

