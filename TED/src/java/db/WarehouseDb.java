package db;

import static db.UserDb.getNthDigit;
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
 
  
public class WarehouseDb {
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

   
  
    
    public static List<Warehouse> listwarehouses(){
        List<Warehouse> wList = new ArrayList<Warehouse>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
        if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery("SELECT * FROM warehouse");
                wList = new ArrayList<Warehouse>();
                while (set.next())
                {
                    Warehouse warehouse = new Warehouse();
                   warehouse.setwareName(set.getString("warehouse_name"));
                    warehouse.setwareDescr(set.getString("warehouse_description"));
                    warehouse.setwareAddress(set.getString("warehouse_address"));
                   warehouse.setwareState(set.getString("state"));
                    wList.add(warehouse);
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                closeResources(set, statement, conn);
            }
        }
        return wList;
    }
 public static List<Warehouse> listopenwarehouses(){
        List<Warehouse> wList = new ArrayList<Warehouse>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
        String state="Open";
        if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery("SELECT * FROM warehouse where state='"+state+"'");
                wList = new ArrayList<Warehouse>();
                while (set.next())
                {
                    Warehouse warehouse = new Warehouse();
                   warehouse.setwareName(set.getString("warehouse_name"));
                    warehouse.setwareDescr(set.getString("warehouse_description"));
                    warehouse.setwareAddress(set.getString("warehouse_address"));
                   warehouse.setwareState(set.getString("state"));
                    wList.add(warehouse);
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                closeResources(set, statement, conn);
            }
        }
        return wList;
    }
    public static boolean insertWarehouse(Warehouse warehouse){
 
        boolean inserted = false;
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        PreparedStatement statement = null;
        
        ResultSet set = null;
        
        if (conn != null) {
            try {
                String sql = "insert into warehouse (warehouse_name, warehouse_description, warehouse_address, state) VALUES (?,?,?,?)";
                
                statement =  conn.prepareStatement(sql);
                statement.setString(1, warehouse.getwareName());
                statement.setString(2, warehouse.getwareDescr());
                statement.setString(3, warehouse.getwareAddress());
                statement.setString(4, warehouse.getwareState());
               
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
    public static Warehouse stateWarehouse(Warehouse warehouse){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
        String wh = warehouse.getwareName();
        String searchQuery = "select * from warehouse where warehouse_name='" + wh + "'";
        if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery(searchQuery);
                boolean userExists = set.next();
               
                if (userExists){
                   String state = set.getString("state"); //pedio sth vash
                   
                   
                   warehouse.setwareState(state);
                    
                    warehouse.setValid(true);
                }
            } catch (SQLException ex) {
                //ex.printStackTrace();
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
       
            }
            finally {
                closeResources(set, statement, conn);
            }
        }
        return warehouse;

    }
      public static List<Warehouse> searchWname(Warehouse warehouse){
        List<Warehouse> wList = new ArrayList<Warehouse>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
        String wname = warehouse.getwareName();
        String searchQuery = "select * from warehouse where warehouse_name='" + wname + "'";
        
        if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery(searchQuery);
                boolean userExists = set.next();
                if (!userExists)
                {
                  warehouse.setValid(false);
                }
                else if (userExists){
                   warehouse.setwareAddress(set.getString("warehouse_address"));
                   warehouse.setwareDescr(set.getString("warehouse_description"));
                   warehouse.setwareState(set.getString("state"));
                     
                  
                  warehouse.setValid(true); 
                  wList.add(warehouse);
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                closeResources(set, statement, conn);
            }
        }
        return wList;

    }
       public static boolean updateWarehouse(Warehouse warehouse){
 
        boolean inserted = false;
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        PreparedStatement statement = null;
        
        ResultSet set = null;
        
        String wname = warehouse.getwareName();
        String waddr = warehouse.getwareAddress();
        String wdescr = warehouse.getwareDescr();
        String wstate = warehouse.getwareState();
        
        if (conn != null) {
            try {
                String sql="update warehouse set warehouse_address='"+ waddr +"', warehouse_description='"+wdescr+"', state='"+wstate+"' where warehouse_name='"+ wname +"'";
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
       
        public static boolean deleteWarehouse(Warehouse warehouse){
 
        boolean inserted = false;
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        PreparedStatement statement = null;
        
        ResultSet set = null;
        
        String wname = warehouse.getwareName();
       
        
        if (conn != null) {
            try {
                String sql="delete from warehouse where warehouse_name='"+wname+"'";
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
        
          public static boolean searchexistsWname(String provider){
       // List<Provider> provList = new ArrayList<Provider>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
         boolean inserted = false;
        String searchQuery = "select * from warehouse where warehouse_name='" + provider + "'";
        
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
