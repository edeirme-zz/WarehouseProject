package db;

import static db.UserDb.getNthDigit;
import entities.Product;
import entities.Provider;
import entities.ProviderhasProducts;
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
 
  
public class ProvProdDb {
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

    public static boolean updateProvProd(ProviderhasProducts provprod){
 
        boolean inserted = false;
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        PreparedStatement statement = null;
        
        ResultSet set = null;
        
        
        String provprovname=provprod.getpprovName();
       String prodprodname=provprod.getpprodName();
        long provssn=provprod.getpprovSSN();
        int price = provprod.getprice();
        
        if (conn != null) {
            try {
                String sql="update provider_has_product set  price='"+price+"' where provider_provider_name='"+ provprovname +"' and product_product_name='"+prodprodname+"'and product_serial_number='"+provssn+"' ";
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
  
      public static boolean insertProvProd(ProviderhasProducts provprod){
 
        boolean inserted = false;
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        PreparedStatement statement = null;
        
        ResultSet set = null;
        
        if (conn != null) {
            try {
                String sql = "insert into provider_has_product (provider_provider_name, product_product_name, product_serial_number, price) VALUES (?,?,?,?)";
                
                statement =  conn.prepareStatement(sql);
                statement.setString(1, provprod.getpprovName());
                statement.setString(2, provprod.getpprodName());
                statement.setLong(3, provprod.getpprovSSN());
                statement.setInt(4, provprod.getprice());
               
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
      
      public static ProviderhasProducts searchProvProd(ProviderhasProducts provprod){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
        String ppvname = provprod.getpprovName();
         
       String prodprodname=provprod.getpprodName();
        long provssn=provprod.getpprovSSN();
       
        String searchQuery = "select * from provider_has_product where provider_provider_name='" + ppvname + "' and product_product_name='"+prodprodname+"'and product_serial_number='"+provssn+"' ";
        if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery(searchQuery);
                boolean userExists = set.next();
                if (!userExists)
                {
                  
                  provprod.setValid(false);
                }
                else if (userExists){
                  
                    provprod.setValid(true);
                }
            } catch (SQLException ex) {
                //ex.printStackTrace();
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
         
            }
            finally {
                closeResources(set, statement, conn);
            }
        }
        return provprod;

    }
 
       public static boolean searchProvProdbyname(ProviderhasProducts provprod){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
        String ppvname = provprod.getpprovName();
         
      boolean found=false;
       
        String searchQuery = "select * from provider_has_product where provider_provider_name='" + ppvname + "' ";
        if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery(searchQuery);
                boolean userExists = set.next();
                if (!userExists)
                {
                  found=false;
                  provprod.setValid(false);
                }
                else if (userExists){
                  found=true;
                }
            } catch (SQLException ex) {
                //ex.printStackTrace();
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
         
            }
            finally {
                closeResources(set, statement, conn);
            }
        }
        return found;

    }
         public static boolean searchProvProdbyprod(ProviderhasProducts provprod){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
        String ppdname = provprod.getpprodName();
         
      boolean found=false;
       
        String searchQuery = "select * from provider_has_product where product_product_name='" + ppdname + "' ";
        if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery(searchQuery);
                boolean userExists = set.next();
                if (!userExists)
                {
                  found=false;
                  provprod.setValid(false);
                }
                else if (userExists){
                  found=true;
                }
            } catch (SQLException ex) {
                //ex.printStackTrace();
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
         
            }
            finally {
                closeResources(set, statement, conn);
            }
        }
        return found;

    }
        public static List<ProviderhasProducts> searchProvProdNameList(ProviderhasProducts provider){
        List<ProviderhasProducts> provList = new ArrayList<ProviderhasProducts>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
        String provname = provider.getpprovName();
        String searchQuery = "select * from provider_has_product where provider_provider_name='" + provname + "'";
        
        if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery(searchQuery);
                provList= new ArrayList<ProviderhasProducts>();
               while (set.next())
               {
                   ProviderhasProducts provider1=new ProviderhasProducts();
                    provider1.setpprodName(set.getString("product_product_name"));
                    provider1.setpprovName(set.getString("provider_provider_name"));
                  provList.add(provider1);
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
       
        public static boolean deleteProvProd (ProviderhasProducts provprod){
 
        boolean inserted = false;
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        PreparedStatement statement = null;
        
        ResultSet set = null;
        
        String provname = provprod.getpprovName();
       
        
        if (conn != null) {
            try {
                String sql="delete from provider_has_product where provider_provider_name='"+provname+"'";
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
            public static boolean deleteProvProdbyprod (ProviderhasProducts provprod){
 
        boolean inserted = false;
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        PreparedStatement statement = null;
        
        ResultSet set = null;
        
        String prodname = provprod.getpprodName();
       
        
        if (conn != null) {
            try {
                String sql="delete from provider_has_product where product_product_name='"+prodname+"'";
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
         public static List<ProviderhasProducts> returnProvProds(ProviderhasProducts provprod){
        List<ProviderhasProducts> provprodList = new ArrayList<ProviderhasProducts>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
        String provname = provprod.getpprovName();
        String searchQuery = "select * from provider_has_product where provider_provider_name='" + provname + "'";
        
        if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery(searchQuery);
                 while (set.next())
                {
                   
                   ProviderhasProducts provprod1= new ProviderhasProducts();
                    provprod1.setpprodName(set.getString("product_product_name"));
                    provprod1.setprice(set.getInt("price"));
                   
                    provprodList.add(provprod1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                closeResources(set, statement, conn);
            }
        }
        return provprodList;

    }
           public static List<ProviderhasProducts> returnProvProdsPrice (ProviderhasProducts provprod){
        List<ProviderhasProducts> provprodList = new ArrayList<ProviderhasProducts>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
        String prodname = provprod.getpprodName();
        String searchQuery = "select * from provider_has_product where product_product_name='" + prodname + "'";
        
        if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery(searchQuery);
                 while (set.next())
                {
                   
                   ProviderhasProducts provprod1= new ProviderhasProducts();
                    
                    provprod1.setprice(set.getInt("price"));
                    provprod1.setpprovName(set.getString("provider_provider_name"));
                   provprod1.setpprovSSN(set.getLong("product_serial_number"));
                    provprodList.add(provprod1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                closeResources(set, statement, conn);
            }
        }
        return provprodList;

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
}
