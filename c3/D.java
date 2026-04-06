import java.util.*;

public class D {

  static ArrayList<ArrayList<Integer>> adj;
  static boolean[] visited;
  static int[][] d;
  static int dist = 0;
  static int totalD = 0;
  static int[] father;

  static k;

  //eu acho qeu tou a ver como fazer isto com um dfs mas um bfs not sure ainda
  static void bfs(int v){
    ArrayDeque<Integer> q = new ArrayDeque<>();
    q.addFirst(v);
    visited[v] = true;
    while (q.size() != 0){
      int u = q.removeFirst();
      dist++;
      totalD += d[v][u];
      if (u==k) return;

    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int a = sc.nextInt();
    k = sc.nextInt();
    adj = new ArrayList<>(n + 1);
    for (int i = 0; i < n + 1; i++)
      adj.add(new ArrayList<>());
    visited = new boolean[n + 1];
    duration = new int[a + 1][a + 1];
    father = new int[n + 1];

    for (int i = 0; i < a; i++) {
      int u = sc.nextInt();
      int v = sc.nextInt();
      adj.get(u).add(v);
      d[u][v] = sc.nextInt();
    }

    bfs(k);

    System.out.println(dist + " " + totalD);
  }
}
