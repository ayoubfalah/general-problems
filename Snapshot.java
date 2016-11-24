import java.util.ArrayList;

public class Snapshot 
{
    private ArrayList<Integer> data;
    private ArrayList<Integer> snapshot;
    
    public Snapshot(ArrayList<Integer> data)
    {
        this.data = data;
        snapshot = new ArrayList(this.data);
        
    }
    
    public ArrayList<Integer> restore()
    {
        return new ArrayList(snapshot);
    }
    
    public static void main(String[] args)
    {
        ArrayList<Integer> list = new ArrayList();
        list.add(1);
        list.add(2);
        Snapshot snap = new Snapshot(list);
        list.set(0, 3);
        list = snap.restore();
        System.out.println(list); // Should output [1, 2]
        list.add(4);
        list = snap.restore();
        System.out.println(list); // Should output [1, 2]
    }
}