package dispatcher;

/**
 * @author AyoubFalah
 * @version 1.0
 * @created 25-Nov-2016 18:17:11
 */
public class DispatcherClient 
{	
    public static void main(String[] args)
    {
        Dispatcher d = new Dispatcher();

        Worker worker1 = d.acquireWorker(1);
        worker1.performTask("Task11");
        
        Worker worker2 = d.acquireWorker(2);
        worker2.performTask("Task21");
        
        Iterable<String> tasks = worker2.getTasks();
        System.out.println(String.join(", ", tasks));
        
        d.releaseWorker(2);
        
        d.acquireWorker(1).performTask("Task12");
        
        System.out.println(String.join(", ", d.acquireWorker(1).getTasks()));
        
        d.releaseWorker(1);
    }
}