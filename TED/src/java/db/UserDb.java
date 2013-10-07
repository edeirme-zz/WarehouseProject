package db;

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
 
  
public class UserDb {
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
    public static List<User> doSelectNewUser(){
        List<User> uListnew = new ArrayList<User>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
        
       
        
        if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery("SELECT * FROM user WHERE roles_roles = 0");
                uListnew = new ArrayList<User>();
                while (set.next())
                {
                    User user = new User();
                   user.setEmailAddress(set.getString("EMAIL"));
                    user.setUserName(set.getString("username"));
                    user.setRolerole(set.getInt("roles_roles"));
                   user.setFirstName(set.getString("name"));
                   user.setLastName(set.getString("lastname"));
                   
                    uListnew.add(user);
                }

            } catch (SQLException ex) {
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                closeResources(set, statement, conn);
            }
        }
        return uListnew;

    }
    public static List<User> doSelectApproved(){
        List<User> uList = new ArrayList<User>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
        
       
        
        if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery("SELECT * FROM user WHERE roles_roles !=0");
                uList = new ArrayList<User>();
                while (set.next())
                {
                    User user = new User();
                   user.setEmailAddress(set.getString("EMAIL"));
                    user.setUserName(set.getString("username"));
                    user.setRolerole(set.getInt("roles_roles"));
                   user.setFirstName(set.getString("name"));
                   user.setLastName(set.getString("lastname"));
                   int x=getNthDigit(user.getRolerole(),10,1);
                   int y=getNthDigit(user.getRolerole(),10,2);
                   int z=getNthDigit(user.getRolerole(),10,3);
                   if(x!=0){
                       if(x==1)
                           user.setwarehouser("Read");
                       else
                           user.setwarehouser("Write");
                   }
                   else if(x==0){
                       user.setwarehouser("None");
                   }
                   if(y!=0){
                       if(y==1)
                           user.setproductr("Read");
                       else
                           user.setproductr("Write");
                   }
                   else if(y==0){
                       user.setproductr("None");
                   }
                   if(z!=0){
                       if(z==1)
                           user.setproviderr("Read");
                       else
                           user.setproviderr("Write");
                   }
                   else if(z==0){
                       user.setproviderr("None");
                   }
                    uList.add(user);
                }

            } catch (SQLException ex) {
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                closeResources(set, statement, conn);
            }
        }
        return uList;

    }

    /**
     *
     * @param user
     * @return
     */
    public static User searchCredentials(User user){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
        String username = user.getUserName();
        String password = user.getpassword();
        String searchQuery = "select * from user where username='" + username + "' and password='" + password + "'";
        if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery(searchQuery);
                boolean userExists = set.next();
                if (!userExists)
                {
                  System.out.println("Username/Password entered is Incorrect or User doesnot Exists.");
                  user.setValid(false);
                }
                else if (userExists){
                   String firstName = set.getString("name"); //pedio sth vash
                   String lastName = set.getString("lastname");
                   int rolenum= set.getInt("roles_roles");
                   user.setRolerole(rolenum);
                   int x=getNthDigit(user.getRolerole(),10,1);
                   int y=getNthDigit(user.getRolerole(),10,2);
                   int z=getNthDigit(user.getRolerole(),10,3);
                   if(x!=0){
                       if(x==1)
                           user.setwarehouser("Read");
                       else
                           user.setwarehouser("Write");
                   }
                   else if(x==0){
                       user.setwarehouser("None");
                   }
                   if(y!=0){
                       if(y==1)
                           user.setproductr("Read");
                       else
                           user.setproductr("Write");
                   }
                   else if(y==0){
                       user.setproductr("None");
                   }
                   if(z!=0){
                       if(z==1)
                           user.setproviderr("Read");
                       else
                           user.setproviderr("Write");
                   }
                   else if(z==0){
                       user.setproviderr("None");
                   }
                   user.setFirstName(firstName);
                    user.setLastName(lastName);
                    
                    user.setValid(true);
                }
            } catch (SQLException ex) {
                //ex.printStackTrace();
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
         /*      ///
                
                 String filename = "c:" + File.separator + "JustExample.txt";
        File f = new File(filename);
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(f);
            String strContent = "Just Example";
            int intContent = 1;
            double doubleContent = Math.random();
            //convinient way to add new line while print content
            pw.println(strContent);
            //using printf to format content. SInce java 1.5
            pw.printf("Hello this is %s. I am %d years old. My lucky number is  %f", strContent, intContent, doubleContent);
            
            pw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally{
            //no matter what happen. close the output stream always.
            //note that closing a printer will not throw IOException
            if(pw!=null){
                pw.close();
            }
        }
        ///// */
            }
            finally {
                closeResources(set, statement, conn);
            }
        }
        return user;

    }

   
    public static List<User> searchUsername(User user){
        List<User> uList = new ArrayList<User>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
        String username = user.getUserName();
        String searchQuery = "select * from user where username='" + username + "'";
        
        if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery(searchQuery);
                boolean userExists = set.next();
                if (!userExists)
                {
                  user.setValid(false);
                }
                else if (userExists){
                   user.setEmailAddress(set.getString("EMAIL"));
                    user.setUserName(set.getString("username"));
                    user.setRolerole(set.getInt("roles_roles"));
                   user.setFirstName(set.getString("name"));
                   user.setLastName(set.getString("lastname"));
                     
                   int x=getNthDigit(user.getRolerole(),10,1);
                   int y=getNthDigit(user.getRolerole(),10,2);
                   int z=getNthDigit(user.getRolerole(),10,3);
                   if(x!=0){
                       if(x==1)
                           user.setwarehouser("Read");
                       else
                           user.setwarehouser("Write");
                   }
                   else if(x==0){
                       user.setwarehouser("None");
                   }
                   if(y!=0){
                       if(y==1)
                           user.setproductr("Read");
                       else
                           user.setproductr("Write");
                   }
                   else if(y==0){
                       user.setproductr("None");
                   }
                   if(z!=0){
                       if(z==1)
                           user.setproviderr("Read");
                       else
                           user.setproviderr("Write");
                   }
                   else if(z==0){
                       user.setproviderr("None");
                   }
                  user.setValid(true); 
                  uList.add(user);
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                closeResources(set, statement, conn);
            }
        }
        return uList;

    }
     public static List<User> searchRole2(User user){
        List<User> uList = new ArrayList<User>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
        long role = user.getRolerole();
        String searchQuery = "select * from user where roles_roles='" + role + "'";
        
        if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery(searchQuery);
                boolean userExists = set.next();
                if (!userExists)
                {
                  user.setValid(false);
                }
                else if (userExists){
                   user.setEmailAddress(set.getString("EMAIL"));
                    user.setUserName(set.getString("username"));
                    user.setUserName(set.getString("username"));
                   user.setFirstName(set.getString("name"));
                   user.setLastName(set.getString("lastname"));
                     
                   int x=getNthDigit(user.getRolerole(),10,1);
                   int y=getNthDigit(user.getRolerole(),10,2);
                   int z=getNthDigit(user.getRolerole(),10,3);
                   if(x!=0){
                       if(x==1)
                           user.setwarehouser("Read");
                       else
                           user.setwarehouser("Write");
                   }
                   else if(x==0){
                       user.setwarehouser("None");
                   }
                   if(y!=0){
                       if(y==1)
                           user.setproductr("Read");
                       else
                           user.setproductr("Write");
                   }
                   else if(y==0){
                       user.setproductr("None");
                   }
                   if(z!=0){
                       if(z==1)
                           user.setproviderr("Read");
                       else
                           user.setproviderr("Write");
                   }
                   else if(z==0){
                       user.setproviderr("None");
                   }
                  user.setValid(true); 
                  uList.add(user);
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                closeResources(set, statement, conn);
            }
        }
        return uList;

    }
    public static boolean insertUser(User user){
 
        boolean inserted = false;
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        PreparedStatement statement = null;
        
        ResultSet set = null;
        
        if (conn != null) {
            try {
                String sql = "insert into user (username, email, password, name, lastname, roles_roles) VALUES (?,?,?,?,?,?)";
                
                statement =  conn.prepareStatement(sql);
                statement.setString(1, user.getUserName());
                statement.setString(2, user.getEmailAddress());
                statement.setString(3, user.getpassword());
                statement.setString(4, user.getFirstName());
                statement.setString(5, user.getLastName());
                statement.setInt(6,user.getRolerole());
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

     public static boolean updateUser(User user){
 
        boolean inserted = false;
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        PreparedStatement statement = null;
        
        ResultSet set = null;
        String username = user.getUserName();
        int role = user.getRolerole();
        if (conn != null) {
            try {
                String sql="update user set roles_roles='"+ role +"' where username='"+ username +"'";
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

    /**
     *
     * @param user
     * @return
     */
    public static User searchRole(User user) {
         ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
        String username = user.getUserName();
        String searchQuery = "select * from user where username='" + username + "'";
        if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery(searchQuery);
                boolean userExists = set.next();
                if (!userExists)
                {
                  user.setValid(false);
                }
                else if (userExists){
                   int role= set.getInt("roles_roles");
                    user.setRolerole(role);
                    user.setValid(true);
                }
            } catch (SQLException ex) {
                
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
         
            }
            finally {
                closeResources(set, statement, conn);
            }
        }
        return user;

    }
     public static boolean searchexistsUname(String provider){
       // List<Provider> provList = new ArrayList<Provider>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
         boolean inserted = false;
        String searchQuery = "select * from user where username='" + provider + "'";
        
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
     public static boolean searchexistsRole(int role){
       // List<Provider> provList = new ArrayList<Provider>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement statement = null;
        ResultSet set = null;
         boolean inserted = false;
        String searchQuery = "select * from user where roles_roles='" + role + "'";
        
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
