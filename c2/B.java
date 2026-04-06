import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class B {

  static int best = 0;
  static int bestStock = 0;
  static ArrayList<ArrayList<Integer>> adj;
  static boolean[] visited;
  static int[] stock;
  static int[] bestStocks;
  static ArrayDeque<Integer> q = new ArrayDeque<>();

  static void dfs(int i) {
    if (stock[i] > bestStock || (stock[i] == bestStock && i < best)) {
      best = i;
      bestStock = stock[i];
    }
    visited[i] = true;
    q.add(i);
    for (int w : adj.get(i)) {
      if (!visited[w]) {
        dfs(w);
      }
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    adj = new ArrayList<>(n + 1);
    for (int i = 0; i < n + 1; i++)
      adj.add(new ArrayList<Integer>());
    stock = new int[n + 1];
    bestStocks = new int[n + 1];
    visited = new boolean[n + 1];
    for (int i = 1; i < n + 1; i++)
      stock[i] = sc.nextInt();
    int e = sc.nextInt();
    for (int i = 0; i < e; i++) {
      int u = sc.nextInt();
      int v = sc.nextInt();
      adj.get(u).add(v);
      adj.get(v).add(u);
    }
    for (int i = 1; i < n + 1; i++) {
      if (!visited[i]) {
        best = 0;
        bestStock = 0;
        dfs(i);
        while (q.size() != 0) {
          int v = q.remove();
          bestStocks[v] = best;
        }
      }
    }

    int k = sc.nextInt();
    for (int i = 0; i < k; i++) {
      int market = sc.nextInt();
      if (stock[market] > 0) {
        System.out.println(market);
        continue;
      }
      System.out.println((bestStocks[market] == 0) ? "Impossivel" : bestStocks[market]);
    }
  }
}
