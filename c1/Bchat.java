import java.util.Scanner;

public class B {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int[] valor = { 200, 100, 50, 20, 10, 5 };
    int[] qtd = new int[6];

    for (int i = 0; i < 6; i++) {
      qtd[i] = sc.nextInt();
    }

    int faltaTotal = 0;
    int numIncompletas = 0;
    int numTrans = 0;

    int e = sc.nextInt();
    int c = sc.nextInt();
    int preco = e * 100 + c;

    while (preco != 0) {
      int inserido = 0;

      int moeda = sc.nextInt();
      while (moeda != 0) {
        if (moeda == 1 || moeda == 2)
          moeda *= 100;

        for (int i = 0; i < 6; i++) {
          if (valor[i] == moeda) {
            qtd[i]++;
            inserido += moeda;
            break;
          }
        }

        moeda = sc.nextInt();
      }

      int faltaTroco = inserido - preco;

      for (int i = 0; i < 6; i++) {
        int usa = Math.min(faltaTroco / valor[i], qtd[i]);
        faltaTroco -= usa * valor[i];
        qtd[i] -= usa;
      }

      if (faltaTroco > 0) {
        faltaTotal += faltaTroco;
        numIncompletas++;
      }

      numTrans++;

      e = sc.nextInt();
      c = sc.nextInt();
      preco = e * 100 + c;
    }

    System.out.println(faltaTotal / 100 + " " + faltaTotal % 100);
    System.out.println(numIncompletas + "/" + numTrans);
  }
}
