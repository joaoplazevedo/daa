import java.util.*;

public class G {
  static char[][] arr;
  static boolean[][] visited;
  static int maxCount = 0;

  static int[][] dirs = new int[][] { { 1, 1 }, { -1, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, -1 },
      { -1, 1 } };

  static int dfs(int i, int j) {
    if (i < 0 || j < 0 || i >= arr.length || j >= arr[0].length || visited[i][j] || arr[i][j] == '.')
      return 0;
    int count = 0;
    visited[i][j] = true;
    if (arr[i][j] == '#')
      count++;
    for (int[] d : dirs) {
      count += dfs(i + d[0], j + d[1]);
    }
    return count;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    for (int k = 0; k < n; k++) {
      maxCount = 0;
      int l = sc.nextInt();
      int c = sc.nextInt();
      arr = new char[l][c];
      visited = new boolean[l][c];
      for (int i = 0; i < l; i++) {
        arr[i] = sc.next().toCharArray();
      }
      for (int i = 0; i < l; i++) {
        for (int j = 0; j < c; j++) {
          if (arr[i][j] == '#' && !visited[i][j]) {
            int count = dfs(i, j);
            maxCount = Math.max(maxCount, count);
          }
        }
      }
      System.out.println(maxCount);
    }
  }
}
