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
        egg.hatch(); // first hatch -> valid
        egg.hatch(); // second hatch invalid
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
    Callable<IBird> nextGenerationBird;
    private boolean isHatched;
    
    /**
     * @param bird Specifying the typ of bild that will be created from this egg
     */
    public Egg(Callable<IBird> bird)
    {
        nextGenerationBird = bird;
    }
    
    public IBird hatch() throws Exception 
    {
        if (!isHatched)
        {
            IBird newBird = nextGenerationBird.call();
            isHatched = true;
            return newBird;
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