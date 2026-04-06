import java.util.Scanner;
import java.util.LinkedList;

// 9 horas = 0
//       tmp= q = 
// 9 2 10 |002|   _12
// 9 5 20 |005|   _12 _32(12+20)
// 9 7 10 |007|   _12 _32 _42(32+10) 
// 9 20 25 |020|  _32 _42 _67(42+25)
// 9 22 10 |022|  _32 _42 _67 _77(67+10)
// 9 25 15 |025|  _32 _42 _67 _77 full => perdeu
// 16 30 12 calcula mins, checka time com os da lista, se full count++ se nao enqueue e mete o tempo 
// 18 0 20
// 18 2 15
// 18 20 35
// 19 5 10

public class D {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int k = sc.nextInt();
    LinkedList<Integer> q = new LinkedList<>();
    int count = 0;
    for (int i = 0; i < k; i++) {
      int h = sc.nextInt();
      int m = sc.nextInt();
      int d = sc.nextInt(); // duration
      int tmp = (h - 9) * 60 + m;
      if (tmp < 0 || (tmp > 180 && tmp < 360) || tmp > 600) { // fora de horas
        count++;
        continue;
      }
      while (q.size() != 0 && q.peekFirst() <= tmp) {
        q.removeFirst();
      }
      if (q.size() == 0)
        q.addLast(tmp + d);
      else if (q.size() == 4)
        count++;
      else {
        q.addLast(q.get(q.size() - 1) + d);
      }
    }
    System.out.println("Perdeu = " + count);
  }
}
