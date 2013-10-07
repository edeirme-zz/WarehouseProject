package entities;

public class ProvProdWare
{
    private String pprovName;
    private String pprodName;
    private long pprovSSN;
    private String wwName;
    private int quantity;
    
   
    public boolean valid;
    
    public ProvProdWare()
    {}
    
    public ProvProdWare(String pprovName,  String pprodName, long pprovSSN,String wwName, int quantity)
    {
        this.pprovName = pprovName;
        
        this.pprodName = pprodName;
        this.pprovSSN = pprovSSN;
        this.wwName=wwName;
        this.quantity = quantity;
    }
    
    
    
    public void setpprovName(String pprovName)
    {
        this.pprovName = pprovName;
    }

    public String getpprovName()
    { 
        return pprovName; 
    }
     public void setwwName(String wwName)
    {
        this.wwName = wwName;
    }

    public String getwwName()
    { 
        return wwName; 
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
   public void setquantity(int quantity)
    {
        this.quantity = quantity;
    }

    public int getquantity()
    { 
        return quantity; 
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
