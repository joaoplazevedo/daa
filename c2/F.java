import java.util.*;

public class F {

  static ArrayList<ArrayList<Integer>> adj = new ArrayList<>(26); // 0-25 cada letra-'A'
  static LinkedList<Integer> s = new LinkedList<>();
  static LinkedList<Character> q = new LinkedList<>();
  static int[] degree = new int[26];
  static boolean[] exists = new boolean[26];

  static void kahn() {
    for (int i = 0; i < 26; i++) {
      if (degree[i] == 0 && exists[i])
        s.add(i);
    }
    while (s.size() != 0) {
      int v = s.remove();
      q.add((char) (v + 'A'));
      for (int i : adj.get(v)) {
        degree[i]--;
        if (degree[i] == 0)
          s.add(i);
      }
    }
  }

  public static void main(String[] args) {
    for (int i = 0; i < 26; i++)
      adj.add(new ArrayList<>());
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    String w1 = sc.next();
    String w2;
    for (int i = 1; i < n; i++) {
      w2 = sc.next();
      int len = Math.min(w1.length(), w2.length());
      for (int j = 0; j < len; j++) {
        char c1 = w1.charAt(j);
        char c2 = w2.charAt(j);
        if (c1 != c2) {
          adj.get(c1 - 'A').add(c2 - 'A');
          degree[c2 - 'A']++;
          exists[c1 - 'A'] = true;
          exists[c2 - 'A'] = true;
          break;
        }
      }
      w1 = w2;
    }
    kahn();
    for (char c : q)
      System.out.print(c);
    System.out.println();
  }
}
