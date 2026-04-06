import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Arrays;

public class C {
  static ArrayList<ArrayList<Integer>> adj;
  static boolean[] visited;
  static int[] dist;
  static int[] exc;

  static void bfs(int i) {
    LinkedList<Integer> q = new LinkedList<>();
    q.add(i);
    visited[i] = true;
    dist[i] = 0;
    int maxDist = 0;
    while (q.size() != 0) {
      int v = q.remove();
      maxDist = dist[v];
      for (int w : adj.get(v)) {
        if (!visited[w]) {
          dist[w] = dist[v] + 1;
          visited[w] = true;
          q.add(w);
        }
      }
    }
    exc[i] = maxDist;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int e = sc.nextInt();

    adj = new ArrayList<>(n + 1);
    for (int i = 0; i < n + 1; i++)
      adj.add(new ArrayList<>());
    exc = new int[n + 1];
    visited = new boolean[n + 1];
    dist = new int[n + 1];

    for (int i = 0; i < e; i++) {
      int u = sc.nextInt();
      int v = sc.nextInt();
      adj.get(u).add(v);
      adj.get(v).add(u);
    }
    for (int i = 1; i < n + 1; i++) {
      Arrays.fill(visited, false); // mais eficiente do que tar a criar um novo sempre(garbace collector)
      Arrays.fill(dist, 0);
      bfs(i);
    }

    int diameter = 0;
    int radius = 5001;
    for (int d : exc) {
      if (d > diameter)
        diameter = d;
      if (d < radius && d != 0)
        radius = d;
    }
    ArrayList<Integer> central = new ArrayList<>();
    ArrayList<Integer> peripheral = new ArrayList<>();
    for (int i = 1; i < n + 1; i++) {
      if (exc[i] == diameter)
        peripheral.add(i);
      if (exc[i] == radius)
        central.add(i);
    }
    System.out.println(diameter);
    System.out.println(radius);
    for (int i = 0; i < central.size(); i++) {
      System.out.print(central.get(i) + ((i == central.size() - 1) ? "\n" : " "));
    }
    for (int i = 0; i < peripheral.size(); i++) {
      System.out.print(peripheral.get(i) + ((i == peripheral.size() - 1) ? "\n" : " "));
    }
  }
}
