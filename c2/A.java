import java.util.Scanner;
import java.util.ArrayList;

public class A {
  static ArrayList<ArrayList<Integer>> adj;
  static boolean[] visited;

  static void dfs(int i) {
    visited[i] = true;
    for (int n : adj.get(i)) {
      if (!visited[n])
        dfs(n);
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int e = sc.nextInt();
    adj = new ArrayList<>(n + 1);
    for (int i = 0; i < n + 1; i++)
      adj.add(new ArrayList<>());
    visited = new boolean[n + 1];

    for (int i = 0; i < e; i++) {
      int u = sc.nextInt();
      int v = sc.nextInt();
      adj.get(u).add(v);
      adj.get(v).add(u);
    }

    int count = 0;
    for (int i = 1; i <= n; i++) {
      if (!visited[i]) {
        count++;
        dfs(i);
      }
    }
    System.out.println(count);
  }
}
