package db;

import static db.UserDb.getNthDigit;
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
import java.util.Iterator;
import java.util.List;
 
  
public class ProvProdWareDb {
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

    public static boolean updateProvProdWare(ProvProdWare provprodware){
 
        boolean inserted = false;
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        PreparedStatement statement = null;
        
        ResultSet set = null;
        
        
        String provprovname=provprodware.getpprovName();
       String prodprodname=provprodware.getpprodName();
        long provssn=provprodware.getpprovSSN();
        String ppwname=provprodware.getwwName();
        int quantity = provprodware.getquantity();        
        if (conn != null) {
            try {
                String sql="update provider_has_product_has_warehouse set  quantity='"+quantity+"' where provider_has_product_provider_provider_name='"+ provprovname +"' and provider_has_product_product_product_name='"+prodprodname+"'and provider_has_product_product_serial_number='"+provssn+"' and warehouse_warehouse_name='"+ppwname+"' ";
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
     
   public static ProvProdWare returnPPWquant(ProvProdWare provprodware) {
         ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
        String provprovname=provprodware.getpprovName();
       String prodprodname=provprodware.getpprodName();
        long provssn=provprodware.getpprovSSN();
        String ppwname=provprodware.getwwName();
       String sql="select * from provider_has_product_has_warehouse where provider_has_product_provider_provider_name='"+ provprovname +"' and provider_has_product_product_product_name='"+prodprodname+"'and provider_has_product_product_serial_number='"+provssn+"' and warehouse_warehouse_name='"+ppwname+"' ";
              if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery(sql);
                boolean userExists = set.next();
                if (!userExists)
                {
                  provprodware.setValid(false);
                }
                else if (userExists){
                   int quant= set.getInt("quantity");
                    provprodware.setquantity(quant);
                    provprodware.setValid(true);
                }
            } catch (SQLException ex) {
                
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
         
            }
            finally {
                closeResources(set, statement, conn);
            }
        }
        return provprodware;

    }
      public static boolean insertProvProdWare(ProvProdWare provprodware){
 
        boolean inserted = false;
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        PreparedStatement statement = null;
        
        ResultSet set = null;
        
        if (conn != null) {
            try {
                String sql = "insert into provider_has_product_has_warehouse (provider_has_product_provider_provider_name, provider_has_product_product_product_name, provider_has_product_product_serial_number, warehouse_warehouse_name, quantity) VALUES (?,?,?,?,?)";
                
                statement =  conn.prepareStatement(sql);
                statement.setString(1, provprodware.getpprovName());
                statement.setString(2, provprodware.getpprodName());
                statement.setLong(3, provprodware.getpprovSSN());
                statement.setString(4, provprodware.getwwName());
                statement.setInt(5, provprodware.getquantity());
               
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
      
      public static ProvProdWare searchProvProdWare(ProvProdWare provprodware){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
        String ppvname = provprodware.getpprovName();
         
       String prodprodname=provprodware.getpprodName();
        long provssn=provprodware.getpprovSSN();
        String ppwname=provprodware.getwwName();
       
        String searchQuery = "select * from provider_has_product_has_warehouse where provider_has_product_provider_provider_name='" + ppvname + "' and provider_has_product_product_product='"+prodprodname+"'and provider_has_product_product_serial='"+provssn+"' and warehouse_warehouse_name='"+ppwname+"' ";
        if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery(searchQuery);
                boolean userExists = set.next();
                if (!userExists)
                {
                  
                  provprodware.setValid(false);
                }
                else if (userExists){
                  
                    provprodware.setValid(true);
                }
            } catch (SQLException ex) {
                //ex.printStackTrace();
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
         
            }
            finally {
                closeResources(set, statement, conn);
            }
        }
        return provprodware;

    }
        public static boolean searchProvProdWarebyname(ProvProdWare ppw){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
        String ppvname = ppw.getpprovName();
         
      boolean found=false;
       
        String searchQuery = "select * from provider_has_product_has_warehouse where provider_has_product_provider_provider_name='" + ppvname + "' ";
        if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery(searchQuery);
                boolean userExists = set.next();
                if (!userExists)
                {
                  found=false;
                  ppw.setValid(false);
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
       
      
      
        public static boolean deleteProvProdWare (ProvProdWare ppw){
 
        boolean inserted = false;
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        PreparedStatement statement = null;
        
        ResultSet set = null;
        
        String provname = ppw.getpprovName();
       
        
        if (conn != null) {
            try {
                String sql="delete from provider_has_product_has_warehouse where provider_has_product_provider_provider_name='"+provname+"'";
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
           
        public static boolean deleteProvProdWarebyprod (ProvProdWare ppw){
 
        boolean inserted = false;
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        PreparedStatement statement = null;
        
        ResultSet set = null;
        
        String prodname = ppw.getpprodName();
       
        
        if (conn != null) {
            try {
                String sql="delete from provider_has_product_has_warehouse where provider_has_product_product_product_name='"+prodname+"'";
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
          public static boolean deleteProvProdWarebyprodnwh (ProvProdWare ppw){
 
        boolean inserted = false;
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        PreparedStatement statement = null;
        
        ResultSet set = null;
        
        String prodname = ppw.getpprodName();
       String whname= ppw.getwwName();
        
        if (conn != null) {
            try {
                String sql="delete from provider_has_product_has_warehouse where provider_has_product_product_product_name='"+prodname+"' and warehouse_warehouse_name='"+whname+"'";
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
        public static boolean deleteProvProdWareList (List<ProvProdWare> ppwlist){
           
        boolean inserted = false;
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        PreparedStatement statement = null;
        
        ResultSet set = null;
        
        ProvProdWare ppw=new ProvProdWare();
       for (int q=0; q<ppwlist.size(); q++) {
           inserted=false;
             ppw=ppwlist.get(q);
            String ppwprodname= ppw.getpprodName();
            String ppwwname= ppw.getwwName();
            String ppwprovname= ppw.getpprovName();
            long ppwssnname= ppw.getpprovSSN();
            int ppwq= ppw.getquantity();
        
        if (conn != null) {
            try {
                String sql="delete from provider_has_product_has_warehouse where provider_has_product_provider_provider_name='"+ppwprovname+"' and provider_has_product_product_product_name='"+ppwprodname+"'and provider_has_product_product_serial_number='"+ppwssnname+"'and warehouse_warehouse_name='"+ppwwname+"'and quantity='"+ppwq+"'";
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
           
        }
        
        return inserted;


    }
        
         public static boolean deleteProvProdWareList (ProvProdWare ppw){
           
        boolean inserted = false;
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        PreparedStatement statement = null;
        
        ResultSet set = null;
        
            String ppwprodname= ppw.getpprodName();
            String ppwwname= ppw.getwwName();
            String ppwprovname= ppw.getpprovName();
            long ppwssnname= ppw.getpprovSSN();
            int ppwq= ppw.getquantity();
        
        if (conn != null) {
            try {
                String sql="delete from provider_has_product_has_warehouse where provider_has_product_provider_provider_name='"+ppwprovname+"' and provider_has_product_product_product_name='"+ppwprodname+"'and provider_has_product_product_serial_number='"+ppwssnname+"'and warehouse_warehouse_name='"+ppwwname+"'and quantity='"+ppwq+"'";
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
          public static boolean insertProvProdWareList (ProvProdWare ppw){
           
        boolean inserted = false;
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        PreparedStatement statement = null;
        
        ResultSet set = null;
        
         
            String ppwprodname= ppw.getpprodName();
            String ppwwname= ppw.getwwName();
            String ppwprovname= ppw.getpprovName();
            long ppwssnname= ppw.getpprovSSN();
            int ppwq= ppw.getquantity();
        
        if (conn != null) {
            try {
                  
                String sql = "insert into provider_has_product_has_warehouse (provider_has_product_provider_provider_name, provider_has_product_product_product_name, provider_has_product_product_serial_number, warehouse_warehouse_name, quantity) VALUES (?,?,?,?,?)";
                
                statement =  conn.prepareStatement(sql);
                statement.setString(1, ppwprovname);
                statement.setString(2, ppwprodname);
                statement.setLong(3,ppwssnname);
                statement.setString(4, ppwwname);
                statement.setInt(5, ppwq);
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
        public static boolean insertProvProdWareList (List<ProvProdWare> ppwlist, String to){
           
        boolean inserted = false;
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        PreparedStatement statement = null;
        
        ResultSet set = null;
        
         for (int q=0; q<ppwlist.size(); q++) {
            ProvProdWare ppw=ppwlist.get(q);
            String ppwprodname= ppw.getpprodName();
            String ppwwname= to;
            String ppwprovname= ppw.getpprovName();
            long ppwssnname= ppw.getpprovSSN();
            int ppwq= ppw.getquantity();
        
        if (conn != null) {
            try {
                  
                String sql = "insert into provider_has_product_has_warehouse (provider_has_product_provider_provider_name, provider_has_product_product_product_name, provider_has_product_product_serial_number, warehouse_warehouse_name, quantity) VALUES (?,?,?,?,?)";
                
                statement =  conn.prepareStatement(sql);
                statement.setString(1, ppwprovname);
                statement.setString(2, ppwprodname);
                statement.setLong(3,ppwssnname);
                statement.setString(4, ppwwname);
                statement.setInt(5, ppwq);
                if (statement.executeUpdate() > 0)
                    inserted = true;
            } catch (SQLException ex) {
               
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                closeResources(set, statement, conn);
            }
        }
          
        }
        
        return inserted;


    }
    
          public static boolean searchProvProdWarebyprodname(ProvProdWare ppw){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
        String ppdname = ppw.getpprodName();
         
      boolean found=false;
       
        String searchQuery = "select * from provider_has_product_has_warehouse where provider_has_product_product_product_name='" + ppdname + "' ";
        if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery(searchQuery);
                boolean userExists = set.next();
                if (!userExists)
                {
                  found=false;
                  ppw.setValid(false);
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
          public static boolean searchProvProdWarebyall(ProvProdWare ppw){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
        String ppdname = ppw.getpprodName();
        String ppvname = ppw.getpprovName();
        String ppwname = ppw.getwwName();
        long ppwsn= ppw.getpprovSSN();
         
      boolean found=false;
       
        String searchQuery = "select * from provider_has_product_has_warehouse where provider_has_product_product_product_name='" + ppdname + "' and provider_has_product_provider_provider_name='"+ppvname+"' and warehouse_warehouse_name='"+ppwname+"' and provider_has_product_product_serial_number='"+ppwsn+"' ";
        if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery(searchQuery);
                boolean userExists = set.next();
                if (!userExists)
                {
                  found=false;
                  ppw.setValid(false);
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
          
           public static List<ProvProdWare> returnPPW(ProvProdWare ppw){
        List<ProvProdWare> ppwList = new ArrayList<ProvProdWare>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
        String prodname = ppw.getpprodName();
        String searchQuery = "select * from provider_has_product_has_warehouse where provider_has_product_product_product_name='" + prodname + "'";
        
        if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery(searchQuery);
                 while (set.next())
                {
                   ProvProdWare ppw1= new ProvProdWare();
                    ppw1.setpprovName(set.getString("provider_has_product_provider_provider_name"));
                    ppw1.setwwName(set.getString("warehouse_warehouse_name"));
                    ppwList.add(ppw1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                closeResources(set, statement, conn);
            }
        }
        return ppwList;

    }
      
           public static List<ProvProdWare> returnPPWDistinct(ProvProdWare ppw){
        List<ProvProdWare> ppwList = new ArrayList<ProvProdWare>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
        String prodname = ppw.getpprodName();
        String searchQuery = "select distinct warehouse_warehouse_name,quantity from provider_has_product_has_warehouse where provider_has_product_product_product_name='" + prodname + "'";
        
        if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery(searchQuery);
                 while (set.next())
                {
                   ProvProdWare ppw1= new ProvProdWare();
                   
                    ppw1.setwwName(set.getString("warehouse_warehouse_name"));
                    ppw1.setquantity(set.getInt("quantity"));
                    ppwList.add(ppw1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                closeResources(set, statement, conn);
            }
        }
        return ppwList;

    }
            public static List<ProvProdWare> returnPPWDistinctOpen(ProvProdWare ppw){
        List<ProvProdWare> ppwList = new ArrayList<ProvProdWare>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
        String prodname = ppw.getpprodName();
        String state= "Open";
        String searchQuery = "select distinct warehouse_warehouse_name from provider_has_product_has_warehouse where provider_has_product_product_product_name='" + prodname + "'and state='"+state+"'";
        
        if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery(searchQuery);
                 while (set.next())
                {
                   ProvProdWare ppw1= new ProvProdWare();
                   
                    ppw1.setwwName(set.getString("warehouse_warehouse_name"));
                    ppwList.add(ppw1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                closeResources(set, statement, conn);
            }
        }
        return ppwList;

    }
       
             public static List<ProvProdWare> returnPPWprovidernquant(ProvProdWare ppw){
        List<ProvProdWare> ppwList = new ArrayList<ProvProdWare>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
        String prodname = ppw.getpprodName();
        long prodsn = ppw.getpprovSSN();
        String prodwn = ppw.getwwName();
        String searchQuery = "select * from provider_has_product_has_warehouse where provider_has_product_product_product_name='" + prodname + "' and provider_has_product_product_serial_number='"+prodsn+"'and warehouse_warehouse_name='"+prodwn+"'";
        
        if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery(searchQuery);
                 while (set.next())
                {
                   ProvProdWare ppw1= new ProvProdWare();
                    ppw1.setpprovName(set.getString("provider_has_product_provider_provider_name"));
                    ppw1.setpprodName(set.getString("provider_has_product_product_product_name"));
                    ppw1.setpprovSSN(set.getLong("provider_has_product_product_serial_number"));
                    ppw1.setwwName(set.getString("warehouse_warehouse_name"));
                    ppw1.setquantity(set.getInt("quantity"));
                    
                    
                    ppwList.add(ppw1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                closeResources(set, statement, conn);
            }
        }
        return ppwList;

    }
                 
            public static List<ProvProdWare> returnPPWproducts(ProvProdWare ppw){
        List<ProvProdWare> ppwList = new ArrayList<ProvProdWare>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
        String wname = ppw.getwwName();
        String searchQuery = "select distinct provider_has_product_product_product_name, warehouse_warehouse_name from provider_has_product_has_warehouse where warehouse_warehouse_name='" + wname + "'";
        
        if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery(searchQuery);
                 while (set.next())
                {
                   ProvProdWare ppw1= new ProvProdWare();
                    
                    ppw1.setpprodName(set.getString("provider_has_product_product_product_name"));
                    ppw1.setwwName(set.getString("warehouse_warehouse_name"));
                    ppwList.add(ppw1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                closeResources(set, statement, conn);
            }
        }
        return ppwList;

    }
            public static ProvProdWare searchppwwhexist(ProvProdWare wareprod){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
        
       String wwname=wareprod.getwwName();
       
        
        String searchQuery = "select * from provider_has_product_has_warehouse where  warehouse_warehouse_name='"+ wwname +"'";
                if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery(searchQuery);
                boolean userExists = set.next();
                if (!userExists)
                {
                  
                  wareprod.setValid(false);
                }
                else if (userExists){
                  
                    wareprod.setValid(true);
                }
            } catch (SQLException ex) {
                //ex.printStackTrace();
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
         
            }
            finally {
                closeResources(set, statement, conn);
            }
        }
        return wareprod;

    }
 public static String searchprodcount(ProvProdWare wareprod){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
        String count=null;
       String wwname=wareprod.getwwName();
       
        
        String searchQuery = "select count(provider_has_product_product_product_name) from provider_has_product_has_warehouse where warehouse_warehouse_name='"+wwname+"'";
                if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery(searchQuery);
                boolean userExists = set.next();
                if (!userExists)
                {
                  
                  wareprod.setValid(false);
                }
                else if (userExists){
                  
                    wareprod.setValid(true);
                    count=(set.getString("count(provider_has_product_product_product_name)"));
                           
                }
            } catch (SQLException ex) {
                //ex.printStackTrace();
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
         
            }
            finally {
                closeResources(set, statement, conn);
            }
        }
        return count;

    }
}
