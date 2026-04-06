import java.util.*;

public class EKahn {
  static ArrayList<ArrayList<Integer>> adj;
  static LinkedList<Integer> s = new LinkedList<>(); // pode ser um fila ou uma pilha
  static LinkedList<Integer> q = new LinkedList<>();
  static int[] degree;

  static void kahn() {
    for (int j = 1; j < adj.size(); j++) {
      if (degree[j] == 0)
        s.add(j); // ser um grafo aciclico garante que ha um no de entrada pelo menos
    }
    while (s.size() != 0) {
      int v = s.remove();
      q.add(v);
      for (int j : adj.get(v)) {
        degree[j]--;
        if (degree[j] == 0)
          s.add(j);
      }
    }
    if (q.size() < adj.size() - 1) {
      System.out.println("IMPOSSIBLE");
      System.exit(0);
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();
    adj = new ArrayList<>(n + 1);
    for (int i = 0; i < n + 1; i++)
      adj.add(new ArrayList<>());
    degree = new int[n + 1];
    for (int i = 0; i < m; i++) {
      int a = sc.nextInt();
      int b = sc.nextInt();
      adj.get(a).add(b);
      degree[b]++;
    }
    kahn();
    for (int i = 0; i < q.size(); i++) {
      System.out.print(q.get(i) + ((i == q.size() - 1) ? "\n" : " "));
    }
  }
}
