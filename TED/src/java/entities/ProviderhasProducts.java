package entities;

public class ProviderhasProducts
{
    private String pprovName;
    private String pprodName;
    private long pprovSSN;
    private int price;
    
   
    public boolean valid;
    
    public ProviderhasProducts()
    {}
    
    public ProviderhasProducts(String pprovName,  String pprodName, long pprovSSN, int price)
    {
        this.pprovName = pprovName;
        
        this.pprodName = pprodName;
        this.pprovSSN = pprovSSN;
        this.price = price;
    }
    
    
    
    public void setpprovName(String pprovName)
    {
        this.pprovName = pprovName;
    }

    public String getpprovName()
    { 
        return pprovName; 
    }
    
    
     public void setpprodName(String pprodName)
    {
        this.pprodName = pprodName;
    }

    public String getpprodName()
    { 
        return pprodName; 
    }
   
    /**
     *
     * @param prodWeight
     */
    public void setpprovSSN(long pprovSSN)
    {
        this.pprovSSN = pprovSSN;
    }

    public long getpprovSSN()
    { 
        return pprovSSN; 
    }
   public void setprice(int price)
    {
        this.price = price;
    }

    public int getprice()
    { 
        return price; 
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
