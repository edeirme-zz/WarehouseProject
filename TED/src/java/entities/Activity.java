package entities;

public class Activity
{
    private String from;
    private String to;
    private String product;
    private String time;
    private String action;
   
    public boolean valid;
    
    public Activity()
    {}
    
    public Activity(String from, String to, String product, String time, String action)
    {
        this.from = from;
        this.to = to;
        this.product = product;
        this.time = time;
        this.action = action;
    }
    
    
    
    public void setfrom(String from)
    {
        this.from = from;
    }

    public String getfrom()
    { 
        return from; 
    }
    public void setto(String to)
    {
        this.to = to;
    }

    public String getto()
    { 
        return to; 
    }
    
     public void setproduct(String product)
    {
        this.product = product;
    }

    public String getproduct()
    { 
        return product; 
    }
    /**
     *
     * @param Role
     */
    public void settime(String time){
        this.time=time;
    }
    /**
     *
     * @return
     */
    public String gettime(){
        return time;
    }
    
     public void setaction(String action){
        this.action=action;
    }
    /**
     *
     * @return
     */
    public String getaction(){
        return action;
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
