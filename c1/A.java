import java.util.Scanner;
import java.util.Arrays;

public class A {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int m = sc.nextInt();
    int[] arr = new int[m];
    for (int i = 0; i < m; i++) {
      arr[i] = sc.nextInt();
    }
    int t = sc.nextInt();
    int count = 0;
    while (t != arr[t]) {
      if (arr[t] < 0 || arr[t] >= arr.length) {
        System.out.println("POLICIA");
        System.exit(0);
      }
      if (count == arr.length) {
        System.out.println("INCOMPETENTE");
        System.exit(0);
      }
      t = arr[t];
      count++;
    }
    System.out.println(count);
  }
}
