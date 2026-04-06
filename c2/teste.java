import java.util.*;

class Node {
  int[][] b;
  String path;

  Node(int[][] b, String path) {
    this.b = b;
    this.path = path;
  }
}

public class teste {
  static int[][] start = new int[][] { { 1, 2, 3, 4 }, { 8, 7, 6, 5 } };
  static int[][] target = new int[2][4];
  static boolean[] visited = new boolean[87654321 + 1];
  static ArrayDeque<Node> q = new ArrayDeque<>();

  static void bfs() {
    q.add(new Node(start, ""));
    visited[12345678] = true;

    while (q.size() != 0) {
      Node node = q.remove();
      if (Arrays.deepEquals(target, node.b)) {
        System.out.println(node.path.length());
        System.out.println(node.path);
        System.exit(0);
      }
      String idx = "";
      int[][] boardA = A(node.b);
      for (int i = 0; i < 4; i++) {
        idx += Integer.toString(boardA[0][i]);
      }
      for (int i = 3; i >= 0; i--) {
        idx += Integer.toString(boardA[1][i]);
      }
      if (!visited[Integer.parseInt(idx)]) {
        q.add(new Node(boardA, node.path + "A"));
        visited[Integer.parseInt(idx)] = true;
      }
      idx = "";
      int[][] boardB = B(node.b);
      for (int i = 0; i < 4; i++) {
        idx += Integer.toString(boardB[0][i]);
      }
      for (int i = 3; i >= 0; i--) {
        idx += Integer.toString(boardB[1][i]);
      }
      if (!visited[Integer.parseInt(idx)]) {
        q.add(new Node(boardB, node.path + "B"));
        visited[Integer.parseInt(idx)] = true;
      }

      idx = "";
      int[][] boardC = C(node.b);
      for (int i = 0; i < 4; i++) {
        idx += Integer.toString(boardC[0][i]);
      }
      for (int i = 3; i >= 0; i--) {
        idx += Integer.toString(boardC[1][i]);
      }
      if (!visited[Integer.parseInt(idx)]) {
        q.add(new Node(boardC, node.path + "C"));
        visited[Integer.parseInt(idx)] = true;
      }
    }
  }

  // e fazer as acoes retornarem copias
  static int[][] A(int[][] board) {
    int[][] copy = copyBoard(board);
    int[] tmp = copy[0];
    copy[0] = copy[1];
    copy[1] = tmp;
    return copy;
  }

  static int[][] B(int[][] board) {
    int[][] copy = copyBoard(board);
    for (int i = 0; i < 2; i++) {
      int tmp = copy[i][0];
      copy[i][0] = copy[i][3];
      copy[i][3] = copy[i][2];
      copy[i][2] = copy[i][1];
      copy[i][1] = tmp;
    }
    // System.out.println(Arrays.deepToString(board));
    // System.out.println(Arrays.deepToString(copy));
    return copy;
  }

  static int[][] copyBoard(int[][] baord) {
    int[][] copy = new int[2][4];
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 4; j++) {
        copy[i][j] = baord[i][j];
      }
    }
    return copy;
  }

  static int[][] C(int[][] board) {
    int[][] copy = copyBoard(board);
    int tmp = copy[0][1];
    copy[0][1] = copy[1][1];
    copy[1][1] = copy[1][2];
    copy[1][2] = copy[0][2];
    copy[0][2] = tmp;
    return copy;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    for (int i = 0; i < 4; i++)
      target[0][i] = sc.nextInt();
    for (int i = 3; i >= 0; i--)
      target[1][i] = sc.nextInt();
    bfs();
  }
}
