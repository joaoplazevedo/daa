import java.util.*;

public class B {

  static ArrayList<ArrayList<Integer>> adj;
  static ArrayList<ArrayList<Integer>> adjT;
  static boolean visited[];
  static int visitedT[];
  static ArrayDeque<Integer> s = new ArrayDeque<>();
  static int count = 0;
  static int localCount = 0;

  static void dfs(int v) {
    visited[v] = true;
    for (int w : adj.get(v))
      if (!visited[w])
        dfs(w);
    s.push(v);
  }

  static void dfsT(int v) {
    visited[v] = true;
    localCount++;
    for (int w : adjT.get(v))
      if (!visited[w]) {
        dfsT(w);
        // System.out.println("ola");
      }

  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int C = sc.nextInt();
    for (int c = 1; c <= C; c++) {
      System.out.println("Caso #" + c);
      int n = sc.nextInt();
      int nn = 0;
      adj = new ArrayList<>(n + 1);
      for (int i = 0; i < n + 1; i++)
        adj.add(new ArrayList<>());

      adjT = new ArrayList<>(n + 1);
      for (int i = 0; i < n + 1; i++)
        adjT.add(new ArrayList<>());

      visited = new boolean[n + 1];
      visitedT = new int[n + 1];

      count = 0;

      for (int i = 0; i < n; i++) {
        int u = sc.nextInt();
        int sla = sc.nextInt();
        for (int j = 0; j < sla; j++) {
          int v = sc.nextInt();
          adj.get(u).add(v);
          adjT.get(v).add(u);
        }
      }

      for (int i = 1; i < n + 1; i++) {
        if (!visited[i])
          dfs(i);
      }
      Arrays.fill(visited, false);
      // adj = adjT;

      while (s.size() != 0) {
        int v = s.pop();
        if (!visited[v]) {
          localCount = 0;
          dfsT(v);
          // System.out.println(cfcCount + " " + count);
          if (localCount >= 4)
            count++;
          else {
            nn += localCount;
          }
        }
      }
      System.out.print(count + " " + nn + "\n");

    }
  }
}
