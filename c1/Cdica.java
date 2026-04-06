import java.util.Scanner;

public class Cdica {
  static int[] analyse(int[] arr, int k, int s, int t, int a, int b) {
    int p = 0;
    int j = 1;
    while (k > 1 && arr[j] != s && arr[j] != t) { // percorrer ate chegar ao final do array/encontrar s/encontrar t o
                                                  // que seignifcia que nao é uma rota valida
      j += 3;
      p += arr[j - 2];
      k--;
    }
    if (arr[j] != s) // chegou ao fim ou ao t=>invalido
      return new int[] { 0, 0 };
    int c = b; // lugares maximo, ou seja 200, para atualizar
    k--; // s conta como um elemento, o while loop nao corre a linha necessaria
    while (arr[j] != t && c >= a && k > 0) { // ate encontrar t, ou numero de lugares nao ser o minimo, ou ja tivermos
                                             // visto todos os elementos
      j += 3;
      p += arr[j - 2];
      if (arr[j - 1] < c) // se o nuemro de lugares for menor
        c = arr[j - 1];
      k--;
    }
    if (arr[j] != t || c < a) // se nao tivermos chegado a um t, ou chegou ao fim ou o numero de lugares era
                              // menor que o minimo permitido, penso qeu pode chegar ao fim e na ultima iter o
                              // valor ficar menor que o perimitdo
      return new int[] { 0, 0 };
    return new int[] { c, p };
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int r = sc.nextInt(); // nº de rotas
    int n = sc.nextInt(); // nº pessoas
    int s = sc.nextInt(); // start
    int t = sc.nextInt(); // termino
    int[] bestR = new int[] { 0, n - 1, 30 }; // n-1 e 30 pq é menos um lugar e mais um problema do que o possivel
    for (int i = 1; i <= r; i++) {
      // ler o input de cada rota e meter num array para aplicar a funcao
      int k = sc.nextInt();
      int[] arr = new int[3 * k - 1]; // menos 1 pq eles comecam no 1 e é preciso dar shift a direita
      for (int j = 1; j <= 3 * k - 2; j++)
        arr[j] = sc.nextInt();
      int[] res = analyse(arr, k, s, t, n, 200); // que é o maximo
      if (res[0] == 0 && res[1] == 0)
        continue; // (0,0) invalido
      else if (res[1] < bestR[2]) { // problemas menor
        bestR[0] = i;
        bestR[1] = res[0];
        bestR[2] = res[1];
      } else if (res[1] == bestR[2] && res[0] > bestR[1]) { // lugares maior mesmo tendo o msm numero de probs
        bestR[0] = i;
        bestR[1] = res[0];
        bestR[2] = res[1];
      }
      // se nada disto acontecer fica a primeira que acontece, o que é garantido
    }
    if (bestR[0] == 0)
      System.out.println("Impossivel");
    else
      System.out.println("Rota = " + bestR[0] + " Probs = " + bestR[2] + " Lugares = " + bestR[1]);
  }
}
