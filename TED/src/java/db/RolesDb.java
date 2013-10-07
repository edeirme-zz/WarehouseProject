package db;

import static db.UserDb.getNthDigit;
import entities.Product;
import entities.Roles;
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
 
  
public class RolesDb {
    /**
     *
     * @param number
     * @param base
     * @param n
     * @return
     */
     public static int getNthDigit(int number, int base, int n) {    
  return (int) ((number / Math.pow(base, n - 1)) % base);
}

   
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

   
  
    
    public static List<Roles> listRoles(){
        List<Roles> rList = new ArrayList<Roles>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
        if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery("SELECT * FROM roles");
                rList = new ArrayList<Roles>();
                while (set.next())
                {
                   
                    Roles role= new Roles();
                    role.setroleName(set.getString("rolename"));
                    role.setrole(set.getInt("roles"));
                      int x=getNthDigit(role.getrole(),10,1);
                   int y=getNthDigit(role.getrole(),10,2);
                   int z=getNthDigit(role.getrole(),10,3);
                   if(x!=0){
                       if(x==1)
                           role.setwarehouser("Read");
                       else
                           role.setwarehouser("Write");
                   }
                   else if(x==0){
                       role.setwarehouser("None");
                   }
                   if(y!=0){
                       if(y==1)
                           role.setproductr("Read");
                       else
                           role.setproductr("Write");
                   }
                   else if(y==0){
                       role.setproductr("None");
                   }
                   if(z!=0){
                       if(z==1)
                           role.setproviderr("Read");
                       else
                           role.setproviderr("Write");
                   }
                   else if(z==0){
                       role.setproviderr("None");
                   }
                    rList.add(role);
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                closeResources(set, statement, conn);
            }
        }
        return rList;
    }

    public static List<Roles> searchRolename(Roles role){
        List<Roles> roleList = new ArrayList<Roles>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
        String rolename = role.getroleName();
        String searchQuery = "select * from roles where rolename='" + rolename + "'";
        
        if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery(searchQuery);
                boolean userExists = set.next();
                if (!userExists)
                {
                  role.setValid(false);
                }
                else if (userExists){
                    role.setrole(set.getInt("roles"));
                    int x=getNthDigit(role.getrole(),10,1);
                   int y=getNthDigit(role.getrole(),10,2);
                   int z=getNthDigit(role.getrole(),10,3);
                   if(x!=0){
                       if(x==1)
                           role.setwarehouser("Read");
                       else
                           role.setwarehouser("Write");
                   }
                   else if(x==0){
                       role.setwarehouser("None");
                   }
                   if(y!=0){
                       if(y==1)
                           role.setproductr("Read");
                       else
                           role.setproductr("Write");
                   }
                   else if(y==0){
                       role.setproductr("None");
                   }
                   if(z!=0){
                       if(z==1)
                           role.setproviderr("Read");
                       else
                           role.setproviderr("Write");
                   }
                   else if(z==0){
                       role.setproviderr("None");
                   }
                   
                  roleList.add(role);
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                closeResources(set, statement, conn);
            }
        }
        return roleList;

    }
     public static Roles searchRole(Roles role) {
         ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
        String rolename = role.getroleName();
        String searchQuery = "select * from roles where rolename='" + rolename + "'";
        if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery(searchQuery);
                boolean userExists = set.next();
                if (!userExists)
                {
                  role.setValid(false);
                }
                else if (userExists){
                    role.setrole(set.getInt("roles"));
                    role.setValid(true);
                }
            } catch (SQLException ex) {
                
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
         
            }
            finally {
                closeResources(set, statement, conn);
            }
        }
        return role;

    }
     public static boolean updateRole(Roles role){
 
        boolean inserted = false;
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        PreparedStatement statement = null;
        
        ResultSet set = null;
        String rolename = role.getroleName();
        int rolenum = role.getrole();
        if (conn != null) {
            try {
                String sql="update roles set roles='"+ rolenum +"' where rolename='"+ rolename +"'";
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
       public static boolean deleteRole(Roles role){
 
        boolean inserted = false;
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        PreparedStatement statement = null;
        
        ResultSet set = null;
        
        String rname = role.getroleName();
       
        
        if (conn != null) {
            try {
                String sql="delete from roles where rolename='"+rname+"'";
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
       public static boolean searchexistsRlnname(Roles role){
       
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
         boolean inserted = false;
         String rolename=role.getroleName();
         int rolenum=role.getrole();
        String searchQuery = "select * from roles where roles='" + rolenum + "'or rolename='"+rolename+"'";
        
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
        public static boolean searchexistsRoleonly(Roles role){
       
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
         boolean inserted = false;
        
         int rolenum=role.getrole();
        String searchQuery = "select * from roles where roles='" + rolenum + "'";
        
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
       public static boolean insertRole(Roles role){
 
        boolean inserted = false;
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        PreparedStatement statement = null;
        
        ResultSet set = null;
        
        if (conn != null) {
            try {
                String sql = "insert into roles (roles, rolename) VALUES (?,?)";
                
                statement =  conn.prepareStatement(sql);
                statement.setInt(1,role.getrole());
                statement.setString(2, role.getroleName());
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
         public static boolean searchexistsRole(int role){
       // List<Provider> provList = new ArrayList<Provider>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
         boolean inserted = false;
        String searchQuery = "select * from roles where roles='" + role + "'";
        
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
