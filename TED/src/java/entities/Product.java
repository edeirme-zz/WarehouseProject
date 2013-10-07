package entities;

public class Product
{
    private String prodName;
    private long sn;
    private String prodDescr;
    private String prodType;
    private int prodWeight;
    
   
    public boolean valid;
    
    public Product()
    {}
    
    public Product(String prodName, long sn, String prodDescr, String prodType,int prodWeight)
    {
        this.prodName = prodName;
        this.sn = sn;
        this.prodDescr = prodDescr;
        this.prodType = prodType;
        this.prodWeight =prodWeight;
    }
    
    
    
    public void setprodName(String prodName)
    {
        this.prodName = prodName;
    }

    public String getprodName()
    { 
        return prodName; 
    }
    public void setsn(long sn)
    {
        this.sn = sn;
    }

    public long getsn()
    { 
        return sn; 
    }
    
     public void setprodDescr(String prodDescr)
    {
        this.prodDescr = prodDescr;
    }

    public String getprodDescr()
    { 
        return prodDescr; 
    }
    /**
     *
     * @param Role
     */
    public void setprodType(String prodType){
        this.prodType=prodType;
    }
    /**
     *
     * @return
     */
    public String getprodType(){
        return prodType;
    }
    /**
     *
     * @param prodWeight
     */
    public void setprodWeight(int prodWeight)
    {
        this.prodWeight = prodWeight;
    }

    public int getprodWeight()
    { 
        return prodWeight; 
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
