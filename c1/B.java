import java.util.Scanner;

public class B {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int[] arr = new int[201];
    arr[200] = sc.nextInt();
    arr[100] = sc.nextInt();
    arr[50] = sc.nextInt();
    arr[20] = sc.nextInt();
    arr[10] = sc.nextInt();
    arr[5] = sc.nextInt();

    int count = 0;
    int trans = 0;
    int qr = 0;
    while (true) {
      int q = sc.nextInt() * 100;
      q += sc.nextInt();
      if (q == 0)
        break;
      int inserted = 0;
      for (int coin = sc.nextInt(); coin != 0; coin = sc.nextInt()) {
        switch (coin) {
          case 2:
            arr[200]++;
            inserted += 200;
            break;
          case 1:
            inserted += 100;
            arr[100]++;
            break;
          case 50:
            inserted += 50;
            arr[50]++;
            break;
          case 20:
            inserted += 20;
            arr[20]++;
            break;
          case 10:
            inserted += 10;
            arr[10]++;
            break;
          case 5:
            inserted += 5;
            arr[5]++;
            break;
        }
      }
      int qCopy = inserted - q;
      for (int i : new int[] { 200, 100, 50, 20, 10, 5 }) {
        if (arr[i] != 0) {
          int remove_coins = Math.min(qCopy / i, arr[i]);
          qCopy -= i * remove_coins;
          arr[i] -= remove_coins;
          if (qCopy == 0) { // o troco foi todo devolvido, acabou
            break; // acho que acaba o for loop | transacao esta ok | troco completamente devolvido
          }
        }
      }
      // quantidade ainda existe para devolver
      if (qCopy != 0) {
        qr += qCopy;
        trans++;
      }
      count++;
    }
    System.out.println(qr / 100 + " " + qr % 100);
    System.out.println(trans + "/" + count);

  }
}
