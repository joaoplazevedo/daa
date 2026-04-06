import java.util.*;

public class A {
  static ArrayList<ArrayList<Integer>> adj;
  static int[] cfc;
  static boolean[] visited;

  static void dfs(int v, int u) {
    visited[u] = true;
    cfc[u] = v;
    for (int w : adj.get(u)) {
      if (!visited[w])
        dfs(v, w);
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int r = sc.nextInt();
    cfc = new int[n + 1];
    adj = new ArrayList<>(n + 1);
    visited = new boolean[n + 1];
    for (int i = 0; i < n + 1; i++)
      adj.add(new ArrayList<>());

    for (int i = 0; i < r; i++) {
      int u = sc.nextInt();
      int v = sc.nextInt();
      adj.get(u).add(v);
      adj.get(v).add(u);
    }

    // componentes fortecemente conexas
    // criar uma pilha s
    for (int i = n; i >= 0; i--) {
      if (!visited[i])
        dfs(i, i);
    }

    // querys
    int q = sc.nextInt();
    for (int i = 0; i < q; i++) {
      int v = sc.nextInt();
      System.out.println("No " + v + ": " + cfc[v]);
    }
  }
}
