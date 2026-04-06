import java.util.*;

public class H {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int d = sc.nextInt();
    int t = sc.nextInt();
    ArrayList<ArrayList<Integer>> numbers = new ArrayList<>(d);
    boolean[] needsExist = new boolean[10]; // 0-9
    for (int i = 0; i < d; i++) {
      numbers.add(new ArrayList<>());
      for (int j = 0; j < 10; j++) {
        numbers.get(i).add(j);
      }
    }
    for (int k = 0; k < t; k++) {
      String tentativa = sc.next();
      String feedback = sc.next();

      for (int i = 0; i < d; i++) {
        if (feedback.charAt(i) == 'X') {
          int number = tentativa.charAt(i) - '0';
          // System.out.println(number);
          for (ArrayList<Integer> arr : numbers) {
            // System.out.println(arr);
            arr.remove(Integer.valueOf(number)); // se nao for Integer tira o indice
          }
        } else if (feedback.charAt(i) == '-') {
          int number = tentativa.charAt(i) - '0';
          numbers.get(i).remove(Integer.valueOf(number));
          needsExist[number] = true;
        } else {
          int number = tentativa.charAt(i) - '0';
          numbers.get(i).removeAll(numbers.get(i));
          numbers.get(i).add(number);
          needsExist[number] = false;
        }
      }
    }

    boolean mudou = true;
    while (mudou) {
      mudou = false;
      for (int i = 0; i < 10; i++) {
        if (needsExist[i]) {
          int count = 0;
          int pos = -1;
          for (int idx = 0; idx < d; idx++) {
            if (numbers.get(idx).contains(i)) {
              count++;
              pos = idx;
            }
          }
          if (count == 1 && numbers.get(pos).size() > 1) { // um bocado ineficiente mas é para o idx que tem o 7 por ex
                                                           // no 1
            mudou = true;
            numbers.get(pos).clear();
            numbers.get(pos).add(i);
          }
        }
      }
    }
    String res = "";
    boolean resolvido = true;
    for (int i = 0; i < d; i++) {
      if (numbers.get(i).size() == 1) {
        res += numbers.get(i).get(0);
      } else {
        resolvido = false;
      }
    }

    if (resolvido) {
      System.out.println("RESPOSTA " + res);
    } else {
      System.out.println("AINDA NAO SEI");
    }

  }
}
