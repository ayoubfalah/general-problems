package dispatcher;

import java.util.HashMap;


/**
 * @author AyoubFalah
 * @version 1.0
 * @created 25-Nov-2016 18:17:11
 */
public class Dispatcher 
{
    private static HashMap<Integer, Worker> workers = new HashMap();

    /**
     * 
     * @param id
     * @return 
     */
    public Worker acquireWorker(int id)
    {
        Worker w = workers.getOrDefault(id, null);
        if (w == null) {
            w = new Worker(id);
            workers.put(id, w);
        }
        return w;
    }

    public Iterable<Worker> getWorkers()
    {
        return workers.values();
    }

    /**
     * 
     * @param id
     */
    public void releaseWorker(int id)
    {
        Worker w = workers.getOrDefault(id, null);
        if (w == null)
            throw new IllegalArgumentException();

        workers.remove(id);
    }
}