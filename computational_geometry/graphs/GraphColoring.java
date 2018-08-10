package computational_geometry.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ayoubfalah
 */
public class GraphColoring 
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        List<Integer>[] adj = new ArrayList[n];
//        Arrays.fill(adj, new ArrayList());
        for (int i = 0; i < adj.length; i++)
            adj[i] = new ArrayList();
        
        for (int i = 0; i < m; i++) 
        {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            adj[u - 1].add(v - 1);
            adj[v - 1].add(u - 1);            
        }
        
        int[] colors = DFS(adj);
        for (int i = 0; i < colors.length; i++)
            System.out.println("(" + (i+1) + ", " + colors[i] + ")");
    }

    private static int[] DFS(List<Integer>[] adj) 
    {
        int n = adj.length;
        int[] colors = new int[n];
        for (int v = 0; v < n; v++) 
        {
            if (colors[v] == 0) 
            {
                colors[v] = 1;
                colorGraph(colors, adj, v);
            }           
        }
        return colors;
    }

    private static void colorGraph(int[] colors, List<Integer>[] adj, int v)
    {
        for (int w : adj[v])
        {
            if (colors[w] == 0)
            {
                colors[w] = 3 - colors[v];
                colorGraph(colors, adj, w);
            }            
        }
    }
}
