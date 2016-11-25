import java.util.concurrent.Callable;

interface IBird {
    Egg Lay();
}   

class Chicken implements IBird, Callable<IBird> { 
    public Chicken() {       
    }

    @Override
    public Egg Lay() 
    {
        return new Egg(this);
    }
    
    public static void main(String[] args) throws Exception 
    {
        Chicken chicken = new Chicken();
        System.out.println(chicken);
        System.out.println(chicken instanceof IBird);
        Egg egg = chicken.Lay();
        System.out.println(egg);
        try 
        {
            egg.hatch();            
        } catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    @Override
    public IBird call() throws Exception 
    {
        return new Chicken();
    }

    @Override
    public String toString()
    {
        return "chicken";
    }
}

class Egg 
{
    // Maintains the state of an Egg
    private boolean isHatched;
    
    /**
     * @param bird Specifying the typ of bild that will be created from this egg
     */
    public Egg(Callable<IBird> bird)
    {
        try 
        {
            bird.call();
            isHatched = true;   
        } catch (Exception ex) 
        {
            System.err.println(ex.getMessage());
        }
    }
    
    public IBird hatch() throws Exception 
    {
        if (!isHatched)
        {
            return new Chicken();
        }else
        {
            throw new IllegalStateException("An Egg can't hatch twice.");
        }        
    }

    @Override
    public String toString() 
    {
        return "Egg";
    }  
}