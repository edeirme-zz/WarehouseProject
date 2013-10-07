package entities;

public class User
{
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String userName;
    private String password;
    private String warehouser;
    private String providerr;
    private String productr;
    private int Rolerole;
    public boolean valid;
    
    public User()
    {}
    
    public User(String userName, String firstName, String lastName, String emailAddress, String password)
    {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password= password;
    }
    public User(String userName, String firstName, String lastName, String emailAddress, String password, int Rolerole)
    {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password= password;
        this.Rolerole=Rolerole;
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
    /**
     *
     * @param Role
     */
    public void setRolerole(int Rolerole){
        this.Rolerole=Rolerole;
    }
    /**
     *
     * @return
     */
    public int getRolerole(){
        return Rolerole;
    }
    public void setpassword(String password)
    {
        this.password = password;
    }

    public String getpassword()
    { 
        return password; 
    }
     public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserName()
    { 
        return userName; 
    }
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getFirstName()
    { 
        return firstName; 
    }
    
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getLastName()
    { 
        return lastName; 
    }
    
    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress()
    { 
        return emailAddress; 
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
