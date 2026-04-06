import java.util.*;

public class FMap {

  static HashMap<Character, ArrayList<Character>> map = new HashMap<>();
  static HashMap<Character, Integer> degree = new HashMap<>();

  static void kahnTopSort() {
    ArrayDeque<Character> s = new ArrayDeque<>();
    // System.out.println(degree);
    for (char k : degree.keySet()) {
      if (degree.get(k) == 0) {
        s.add(k);
        // degree.remove(k);
      }
    }
    while (s.size() != 0) {
      char v = s.remove();
      System.out.print(v);
      ArrayList<Character> adj = map.remove(v);
      if (adj == null)
        continue;
      for (char w : adj) {
        int wDeg = degree.get(w);
        degree.put(w, wDeg - 1);
        if (wDeg - 1 == 0) {
          s.add(w);
          // degree.remove(w);
        }
      }
    }
    System.out.println();
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    String w1;
    String w2 = sc.next();
    for (int k = 0; k < n - 1; k++) {
      w1 = w2;
      w2 = sc.next();
      for (int i = 0; i < Math.min(w1.length(), w2.length()); i++) {
        char c1 = w1.charAt(i);
        char c2 = w2.charAt(i);
        if (c1 != c2) {
          // isto retorna a lista se ja existir, se nao existir retorna o novo objeto
          ap.computeIfAbsent(c1, key -> new ArrayList<>()).add(c2);
          // ArrayList<Character> tmp = map.get(c1);
          // if (tmp == null) {
          // ArrayList<Character> lista = new ArrayList<>();
          // lista.add(c2);
          // map.put(c1, lista);
          // }
          // if (tmp != null) {
          // tmp.add(c2);
          // }
          Integer value = degree.putIfAbsent(c2, 1);
          if (value != null) {
            degree.put(c2, value + 1);
          }
          Integer sla = degree.putIfAbsent(c1, 0);
          break;
        }
      }
    }
    kahnTopSort();

  }
}
