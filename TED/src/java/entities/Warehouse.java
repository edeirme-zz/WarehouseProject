package entities;

public class Warehouse
{
    private String wareName;
    private String wareDescr;
    private String wareAddress;
    private String wareState;
   
    public boolean valid;
    
    public Warehouse()
    {}
    
    public Warehouse(String wareName, String wareDescr, String wareAddress, String wareState)
    {
        this.wareName = wareName;
        this.wareDescr = wareDescr;
        this.wareAddress = wareAddress;
        this.wareState = wareState;
    }
    
    
    
    public void setwareName(String wareName)
    {
        this.wareName = wareName;
    }

    public String getwareName()
    { 
        return wareName; 
    }
    public void setwareDescr(String wareDescr)
    {
        this.wareDescr = wareDescr;
    }

    public String getwareDescr()
    { 
        return wareDescr; 
    }
    
     public void setwareAddress(String wareAddress)
    {
        this.wareAddress = wareAddress;
    }

    public String getwareAddress()
    { 
        return wareAddress; 
    }
    /**
     *
     * @param Role
     */
    public void setwareState(String wareState){
        this.wareState=wareState;
    }
    /**
     *
     * @return
     */
    public String getwareState(){
        return wareState;
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
