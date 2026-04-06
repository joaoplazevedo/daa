import java.util.*;

public class H {

  static int[][] dirs = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
  static char[][] arr;
  static LinkedList<int[]> q = new LinkedList<>();
  static boolean[][] visited;
  static int[][] dists;
  static int countA = 0;
  static int curCount = 0;

  static void bfs() {
    while (q.size() != 0) {
      int[] v = q.remove();
      if (arr[v[0]][v[1]] == 'A') {
        if (curCount == 0) {
          System.out.print(dists[v[0]][v[1]] + " ");
        }
        curCount++;
        if (curCount == countA) {
          System.out.println(dists[v[0]][v[1]]);
          System.exit(0);
        }
      }
      for (int[] d : dirs) {
        int i = v[0] + d[0];
        int j = v[1] + d[1];
        if (i < 0 || j < 0 || i >= arr.length || j >= arr[0].length || visited[i][j])
          continue;
        if (!visited[i][j]) {
          q.add(new int[] { i, j });
          visited[i][j] = true;
          dists[i][j] = dists[v[0]][v[1]] + 1;
        }
      }
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int l = sc.nextInt();
    int c = sc.nextInt();

    arr = new char[l][c];
    visited = new boolean[l][c];
    dists = new int[l][c];

    for (int i = 0; i < l; i++) {
      char[] line = sc.next().toCharArray();
      for (int j = 0; j < c; j++) {
        arr[i][j] = line[j];
        if (line[j] == 'A')
          countA++;
        if (line[j] == '#') {
          q.add(new int[] { i, j });
          visited[i][j] = true;
        }
      }
    }
    bfs();
  }
}
