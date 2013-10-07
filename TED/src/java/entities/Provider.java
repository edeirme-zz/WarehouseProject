package entities;

public class Provider
{
    private String provName;
    private String provAddress;
    private long provSSN;
    
   
    public boolean valid;
    
    public Provider()
    {}
    
    public Provider(String provName,  String provAddress, long provSSN)
    {
        this.provName = provName;
        
        this.provAddress = provAddress;
        this.provSSN = provSSN;
    }
    
    
    
    public void setprovName(String provName)
    {
        this.provName = provName;
    }

    public String getprovName()
    { 
        return provName; 
    }
    
    
     public void setprovAddress(String provAddress)
    {
        this.provAddress = provAddress;
    }

    public String getprovAddress()
    { 
        return provAddress; 
    }
   
    /**
     *
     * @param prodWeight
     */
    public void setprovSSN(long provSSN)
    {
        this.provSSN = provSSN;
    }

    public long getprovSSN()
    { 
        return provSSN; 
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
