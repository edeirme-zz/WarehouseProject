package db;

import static db.UserDb.getNthDigit;
import entities.Activity;
import entities.Product;
import entities.ProvProdWare;
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
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
 
  
public class ActivityDb {
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

    
  
      public static boolean insertActivity(Activity activity){
 
        boolean inserted = false;
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        PreparedStatement statement = null;
       
        ResultSet set = null;
        
        if (conn != null) {
            try {
                String sql = "insert into warehouse_activity (destination, product, timeT, actionA) VALUES (?,?,CURTIME(),?)";
                
                statement =  conn.prepareStatement(sql);
                
              
                
                statement.setString(1, activity.getto());
                statement.setString(2, activity.getproduct());
             
                statement.setString(3, activity.getaction());
               
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
      
    public static boolean insertActivitymove(Activity activity){
 
        boolean inserted = false;
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        PreparedStatement statement = null;
       
        ResultSet set = null;
        
        if (conn != null) {
            try {
                String sql = "insert into warehouse_activity (origin, destination, product, timeT, actionA) VALUES (?,?,?,CURTIME(),?)";
                
                statement =  conn.prepareStatement(sql);              
                statement.setString(1, activity.getfrom());
                statement.setString(2, activity.getto());
                statement.setString(3, activity.getproduct());             
                statement.setString(4, activity.getaction());
               
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
     public static boolean insertActivityextract(Activity activity){
 
        boolean inserted = false;
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        PreparedStatement statement = null;
       
        ResultSet set = null;
        
        if (conn != null) {
            try {
                String sql = "insert into warehouse_activity (origin,  product, timeT, actionA) VALUES (?,?,CURTIME(),?)";
                
                statement =  conn.prepareStatement(sql);              
                statement.setString(1, activity.getfrom());
                
                statement.setString(2, activity.getproduct());             
                statement.setString(3, activity.getaction());
               
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
       
     
           public static List<Activity> listactivity(String whname){
        List<Activity> actlist = new ArrayList<Activity>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
       
        if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery("SELECT * FROM warehouse_activity where origin='"+whname+"' or destination='"+whname+"'");
                actlist = new ArrayList<Activity>();
                while (set.next())
                {
                   
                    Activity activity= new Activity();
                    activity.setaction(set.getString("actionA"));
                    activity.setfrom(set.getString("origin"));
                    activity.setproduct(set.getString("product"));
                    activity.setto(set.getString("destination"));
                    activity.settime(set.getString("timeT"));
                    actlist.add(activity);
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                closeResources(set, statement, conn);
            }
        }
        return actlist;
    }       
       
}
