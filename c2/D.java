import java.util.*;

public class D {
  static ArrayList<ArrayList<Integer>> adj;
  static boolean[] visited;
  static int[] dist;
  static int[] stock;
  static int count = 0;

  static void bfs(int i, int maxDist) {
    LinkedList<Integer> q = new LinkedList<>();
    q.add(i);
    visited[i] = true;
    while (q.size() > 0) {
      int v = q.remove();
      for (int w : adj.get(v)) {
        if (!visited[w]) {
          visited[w] = true;
          q.add(w);
          dist[w] = dist[v] + 1;
          if (dist[w] > maxDist) {
            return;
          }
          if (stock[w] > 0)
            count++;
        }
      }
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    visited = new boolean[n + 1];
    dist = new int[n + 1];
    stock = new int[n + 1];
    adj = new ArrayList<>(n + 1);
    for (int i = 0; i < n + 1; i++)
      adj.add(new ArrayList<>());
    for (int i = 1; i < n + 1; i++)
      stock[i] = sc.nextInt();
    int e = sc.nextInt();
    for (int i = 0; i < e; i++) {
      int u = sc.nextInt();
      int v = sc.nextInt();
      adj.get(u).add(v);
      adj.get(v).add(u);
    }
    int p = sc.nextInt();
    int k = sc.nextInt();
    if (stock[p] > 0)
      System.out.println("Que sorte");
    else {
      bfs(p, k);
      System.out.println(count);
    }

  }
}
