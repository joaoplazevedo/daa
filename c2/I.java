import java.util.*;

class Node {
  int[][] b;
  String path;

  Node(int[][] b, String path) {
    this.b = b;
    this.path = path;
  }
}

public class I {
  static int[][] start = new int[][] { { 1, 2, 3, 4 }, { 8, 7, 6, 5 } };
  static int target;
  static boolean[] visited = new boolean[87654321 + 1];
  static LinkedList<Node> q = new LinkedList<>();

  static int getId(int[][] b) {
    int id = 0;
    for (int i = 0; i < 4; i++)
      id = id * 10 + b[0][i];
    for (int i = 3; i >= 0; i--)
      id = id * 10 + b[1][i];
    return id;
  }

  static void bfs() {
    q.add(new Node(start, ""));
    visited[12345678] = true;

    while (q.size() != 0) {
      Node node = q.remove();
      if (target == getId(node.b)) {
        System.out.println(node.path.length());
        System.out.println(node.path);
        System.exit(0);
      }
      int[][] boardA = A(node.b);
      int id = getId(boardA);
      if (!visited[id]) {
        q.add(new Node(boardA, node.path + "A"));
        visited[id] = true;
      }
      int[][] boardB = B(node.b);
      id = getId(boardB);
      if (!visited[id]) {
        q.add(new Node(boardB, node.path + "B"));
        visited[id] = true;
      }
      int[][] boardC = C(node.b);
      id = getId(boardC);
      if (!visited[id]) {
        q.add(new Node(boardC, node.path + "C"));
        visited[id] = true;
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
    String tmp = "";
    for (int i = 0; i < 4; i++)
      tmp += sc.next();
    for (int i = 3; i >= 0; i--)
      tmp += sc.next();
    target = Integer.parseInt(tmp);
    bfs();
  }
}
