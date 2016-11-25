package dispatcher;

import java.util.ArrayList;

/**
 * @author AyoubFalah
 * @version 1.0
 * @created 25-Nov-2016 18:17:11
 */
public class Worker 
{
    private int id;
    private ArrayList<String> tasks = new ArrayList();

    /**
     * 
     * @param id
     */
    public Worker(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return this.id;
    }

    public Iterable<String> getTasks()
    {
       return this.tasks;
    }

    /**
     * 
     * @param task
     */
    public void performTask(String task)
    {
        if (this.tasks == null)
            throw new IllegalStateException(this.getClass().getName());
        
        this.tasks.add(task);
    }

    public void dispose()
    {
        this.tasks = null;
    }
}