package db;

import static db.UserDb.getNthDigit;
import entities.Product;
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
 
  
public class ProductDb {
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

   
  
    
    public static List<Product> listProducts(){
        List<Product> pList = new ArrayList<Product>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
        if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery("SELECT * FROM product");
                pList = new ArrayList<Product>();
                while (set.next())
                {
                   
                    Product product= new Product();
                    product.setprodName(set.getString("product_name"));
                    product.setsn(set.getLong("serial_number"));
                    product.setprodDescr(set.getString("product_description"));
                    product.setprodType(set.getString("type"));
                    product.setprodWeight(set.getInt("weight"));
                    pList.add(product);
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                closeResources(set, statement, conn);
            }
        }
        return pList;
    }

    public static boolean insertProduct(Product product){
 
        boolean inserted = false;
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        PreparedStatement statement = null;
        
        ResultSet set = null;
        
        if (conn != null) {
            try {
                String sql = "insert into product (product_name, serial_number, product_description, type, weight) VALUES (?,?,?,?,?)";
                
                statement =  conn.prepareStatement(sql);
                statement.setString(1, product.getprodName());
                statement.setLong(2, product.getsn());
                statement.setString(3, product.getprodDescr());
                statement.setString(4, product.getprodType());
                statement.setInt(5,product.getprodWeight());
               
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
      public static List<Product> searchPname(Product product){
        List<Product> pList = new ArrayList<Product>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
        String pname = product.getprodName();
        String searchQuery = "select * from product where product_name='" + pname + "'";
        
        if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery(searchQuery);
                boolean userExists = set.next();
                if (!userExists)
                {
                  product.setValid(false);
                }
                else if (userExists){
                   product.setsn(set.getLong("serial_number"));
                   product.setprodDescr(set.getString("product_description"));
                   product.setprodType(set.getString("type"));
                   product.setprodWeight(set.getInt("weight"));
                  product.setValid(true); 
                  pList.add(product);
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                closeResources(set, statement, conn);
            }
        }
        return pList;

    }
        public static List<Product> searchPdescr(Product product){
        List<Product> pList = new ArrayList<Product>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
        String pdescr = product.getprodDescr();
        String searchQuery = "select * from product where product_description='" + pdescr + "'";
        
        if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery(searchQuery);
                boolean userExists = set.next();
                if (!userExists)
                {
                  product.setValid(false);
                }
                else if (userExists){
                   product.setsn(set.getLong("serial_number"));
                   product.setprodName(set.getString("product_name"));
                   product.setprodType(set.getString("type"));
                   product.setprodWeight(set.getInt("weight"));
                  product.setValid(true); 
                  pList.add(product);
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                closeResources(set, statement, conn);
            }
        }
        return pList;

    }
        
         public static List<Product> searchPSN(Product product){
        List<Product> pList = new ArrayList<Product>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
        long psn = product.getsn();
        String searchQuery = "select * from product where serial_number='" + psn + "'";
        
        if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery(searchQuery);
                boolean userExists = set.next();
                if (!userExists)
                {
                  product.setValid(false);
                }
                else if (userExists){
                   product.setsn(set.getLong("serial_number"));
                   product.setprodDescr(set.getString("product_description"));
                   product.setprodName(set.getString("product_name"));
                   product.setprodType(set.getString("type"));
                   product.setprodWeight(set.getInt("weight"));
                  product.setValid(true); 
                  pList.add(product);
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                closeResources(set, statement, conn);
            }
        }
        return pList;

    }
         
          public static List<Product> search(Product product){
        List<Product> pList = new ArrayList<Product>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
        String psn = product.getprodName();
        String searchQuery = "select * from product where product_name='"+psn+"'or serial_number='"+psn+"' or product_description='"+psn+"'";
        
        if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery(searchQuery);
                pList = new ArrayList<Product>();
                
                  while (set.next())
                {
                   
                    Product product1= new Product();
                    product1.setprodName(set.getString("product_name"));
                    product1.setsn(set.getLong("serial_number"));
                    product1.setprodDescr(set.getString("product_description"));
                    product1.setprodType(set.getString("type"));
                    product1.setprodWeight(set.getInt("weight"));
                    pList.add(product1);
                
               
            } 
            } catch (SQLException ex) {
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                closeResources(set, statement, conn);
            }
        }
        return pList;

    }
       public static boolean updateProduct(Product product){
 
        boolean inserted = false;
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        PreparedStatement statement = null;
        
        ResultSet set = null;
        
        String pname = product.getprodName();
        long psn = product.getsn();
        String pdescr = product.getprodDescr();
        String ptype = product.getprodType();
        int weight = product.getprodWeight();
        
        if (conn != null) {
            try {
                String sql="update product set product_description='"+ pdescr +"', type='"+ptype+"', weight='"+weight+"' where product_name='"+ pname +"'";
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
       public static boolean searchexistsProduct(String product){
       // List<Provider> provList = new ArrayList<Provider>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
         boolean inserted = false;
        String searchQuery = "select * from product where product_name='" + product + "'";
        
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
        public static boolean searchexistsSN(String product){
       // List<Provider> provList = new ArrayList<Provider>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
         boolean inserted = false;
        String searchQuery = "select * from product where serial_number='" + product + "'";
        
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
        public static boolean deleteProdbyprodnm (Product prod){
 
        boolean inserted = false;
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        PreparedStatement statement = null;
        
        ResultSet set = null;
        
        String prodname = prod.getprodName();
       
        
        if (conn != null) {
            try {
                String sql="delete from product where product_name='"+prodname+"'";
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
}
