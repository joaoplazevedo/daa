import java.util.Arrays;
import java.util.Scanner;

public class E {
  static int[] compute(int[] arr) {
    int n = arr.length / 2;
    int[] squared = new int[4 * n];

    for (int i = n * 2 - 1; i >= 0; i--) { // denominador
      int carryOver = 0; //
      for (int j = n * 2 - 1; j >= 0; j--) {// numerador
        squared[i + j + 1] += carryOver + arr[i] * arr[j];
        carryOver = squared[i + j + 1] / 10; //
        squared[i + j + 1] %= 10; //
      }
      squared[i] += carryOver; //
    }
    // tirase as que tem // e com este loop faz a normalizacao só no final
    // int carryOver = 0;
    // for (int i = 4 * n - 1; i >= 0; i--) {
    // squared[i] += carryOver;
    // carryOver = squared[i] / 10;
    // squared[i] %= 10;
    // }
    int[] copy_squared = new int[2 * n];
    for (int i = n; i < 3 * n; i++)
      copy_squared[i - n] = squared[i];
    return copy_squared;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int m = sc.nextInt();
    int n = sc.nextInt();
    int[] arr = new int[2 * n];
    for (int i = 0; i < 2 * n; i++)
      arr[i] = sc.nextInt();

    for (int i = 0; i < m; i++) {
      arr = compute(arr);

      System.out.print("0,");
      for (int digit : arr)
        System.out.print(digit);
      System.out.println();
    }
  }
}
