package entities;

public class Roles
{
    private String roleName;
    private int role;
     private String warehouser;
    private String providerr;
    private String productr;
   
    public boolean valid;
    
    public Roles()
    {}
    
    public Roles(String roleName, int role)
    {
        this.roleName = roleName;
        
        this.role = role;
        
    }
    
      public void setwarehouser(String warehouser)
    {
        this.warehouser = warehouser;
    }

    public String getwarehouser()
    { 
        return warehouser; 
    }
    public void setproviderr(String providerr)
    {
        this.providerr = providerr;
    }

    public String getproviderr()
    { 
        return providerr; 
    }
    
     public void setproductr(String productr)
    {
        this.productr = productr;
    }

    public String getproductr()
    { 
        return productr; 
    }
    
    public void setroleName(String roleName)
    {
        this.roleName = roleName;
    }

    public String getroleName()
    { 
        return roleName; 
    }
    
    
    
   
    public void setrole(int role)
    {
        this.role = role;
    }

    public int getrole()
    { 
        return role; 
    }
  
    public boolean isValid()
    {
        return valid;
    }
    public void setValid(boolean newValid)
    {
        valid = newValid;
    }
}
