package db;

import static db.UserDb.getNthDigit;
import entities.Product;
import entities.Provider;
import entities.User;
import entities.Warehouse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;




import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
 
  
public class ProviderDb {
    /**
     *
     * @param number
     * @param base
     * @param n
     * @return
     */
   

   
    private static void closeResources(ResultSet set, Statement statement, Connection conn) {
        if (set != null) {
            try {
                set.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

   
  
    
    public static List<Provider> listProvider(){
        List<Provider> provList = new ArrayList<Provider>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
        if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery("SELECT * FROM provider");
                provList = new ArrayList<Provider>();
                while (set.next())
                {
                   
                    Provider provider= new Provider();
                    provider.setprovName(set.getString("provider_name"));
                    
                    provider.setprovAddress(set.getString("address"));
                    provider.setprovSSN(set.getLong("social_security_number"));
                    provList.add(provider);
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                closeResources(set, statement, conn);
            }
        }
        return provList;
    }

    public static boolean insertProvider(Provider provider){
 
        boolean inserted = false;
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        PreparedStatement statement = null;
        
        ResultSet set = null;
        
        if (conn != null) {
            try {
                String sql = "insert into provider (provider_name, address, social_security_number) VALUES (?,?,?)";
                
                statement =  conn.prepareStatement(sql);
                statement.setString(1, provider.getprovName());
                
                statement.setString(2, provider.getprovAddress());
                statement.setLong(3, provider.getprovSSN());
               
                if (statement.executeUpdate() > 0)
                    inserted = true;
            } catch (SQLException ex) {
                //ex.printStackTrace();
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                closeResources(set, statement, conn);
            }
        }
        return inserted;

    }
      public static List<Provider> searchProvname(Provider provider){
        List<Provider> provList = new ArrayList<Provider>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
        String provname = provider.getprovName();
        String searchQuery = "select * from provider where provider_name='" + provname + "'";
        
        if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery(searchQuery);
                boolean userExists = set.next();
                if (!userExists)
                {
                  provider.setValid(false);
                }
                else if (userExists){
                    provider.setprovAddress(set.getString("address"));
                    provider.setprovSSN(set.getLong("social_security_number"));
                  provList.add(provider);
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                closeResources(set, statement, conn);
            }
        }
        return provList;

    }
       public static boolean updateProvider(Provider provider){
 
        boolean inserted = false;
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        PreparedStatement statement = null;
        
        ResultSet set = null;
        
        
        String provname=provider.getprovName();
       
        String provadd=provider.getprovAddress();
        long pssn = provider.getprovSSN();
        
        if (conn != null) {
            try {
                String sql="update provider set  address='"+provadd+"', social_security_number='"+pssn+"' where provider_name='"+ provname +"'";
                statement =  conn.prepareStatement(sql);
                if (statement.executeUpdate() > 0)
                    inserted = true;
            } catch (SQLException ex) {
               
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                closeResources(set, statement, conn);
            }
        }
        return inserted;

    }
       
        public static boolean deleteProvider (Provider provider){
 
        boolean inserted = false;
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        PreparedStatement statement = null;
        
        ResultSet set = null;
        
        String provname = provider.getprovName();
       
        
        if (conn != null) {
            try {
                String sql="delete from provider where provider_name='"+provname+"'";
                statement =  conn.prepareStatement(sql);
                 
                if (statement.executeUpdate() > 0)
                    inserted = true;
            } catch (SQLException ex) {
               
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                closeResources(set, statement, conn);
            }
        }
        return inserted;

    }
         public static boolean searchexistsProvname(String provider){
       // List<Provider> provList = new ArrayList<Provider>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
         boolean inserted = false;
        String searchQuery = "select * from provider where provider_name='" + provider + "'";
        
        if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery(searchQuery);
                boolean userExists = set.next();
                if (!userExists)
                {
                  inserted=false;
                }
                else if (userExists){
                    inserted =true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                closeResources(set, statement, conn);
            }
        }
        return inserted;

    }
         
        
}
