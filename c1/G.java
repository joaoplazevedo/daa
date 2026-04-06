import java.util.*;

public class G {

  static boolean[][] problems; // 0-6 INDICES por cada equipa 7 problemas resolvidos ou nao
  static int[] totalTime;
  static int[] lastSubmission;
  static int[][] FailedTries; // preciso de arranjar maneira de manter track das submissoes falhadas que fazem

  public static void main(String[] args) {
    // vence: + problemas resolvidos
    // desempatar: tempo que demorou cada ex desde o inicio + 20 mins por caada
    // submissao errada
    // 7 problemas, 4 horas, blackout >= 10 mins do fim, instante=tempo desde
    // oinicio
    // submissao no instante do blackout já nao é visivel para as outras equipas
    // nao podem fazer submissoes para exs ja resolvidos
    // a equipa resolveu 6 antes do blackout e o ultimo durante o blackout
    // as outras nao resolveram os 7 antes do blackout
    // vese todas as submissoes antes do blackout e so as da V durante o blackout
    // ideia: contar o numero de problemas qeu cada equipa tem por resolver : n
    // se submetram em [b-d,b[ entao somar n*d a isso se nao é somar n*d a b
    // o que esta em baixo nao esta bem, é preciso é ver o tempo total
    // como o intervalo maximo é 30 e minim de 10 mins de blackout, uma equipa que
    // ainda nao tenha feito nada consegue acabar sempre
    // mas calcular o tempo total fica complicado
    // se v tiver submetido o ultimo antes/ao mesmo tmp disso entao GANHOU( ou
    // EMPATOU its the SAME), se nao: nao se sabe
    Scanner sc = new Scanner(System.in);
    int e = sc.nextInt(); // nº equipas
    int s = sc.nextInt(); // submissoes antes do blackout
    int d = sc.nextInt(); // duracao do intervalo minimo entre 2 submissoes da msm equipa (segs)
    int b = sc.nextInt(); // o instante inicial do blackout
    int v = sc.nextInt(); // a equipa para ver se é vencedora ou nao
    problems = new boolean[e][7];
    totalTime = new int[e];
    lastSubmission = new int[e];
    FailedTries = new int[e][7];

    for (int i = 0; i < s; i++) {
      int equipa = sc.nextInt();
      int instante = sc.nextInt();
      lastSubmission[equipa - 1] = instante;
      int problem = sc.nextInt();
      int res = sc.nextInt();
      if (res == 1) {
        problems[equipa - 1][problem - 1] = true;
        totalTime[equipa - 1] += instante + 20 * 60 * FailedTries[equipa - 1][problem - 1];
      } else if (res == 0) {
        FailedTries[equipa - 1][problem - 1]++;
      }
      // if (equipa == 2)
      // System.out.println(totalTime[equipa - 1]);
    }
    // System.out.println(Arrays.toString(problems[1]));
    // blackout
    int k = sc.nextInt();
    int vProblemLeft = 0;
    for (int i = 0; i < 7; i++) {
      if (!problems[v - 1][i])
        vProblemLeft = i;
    }
    for (int i = 0; i < k - 1; i++) {
      sc.nextInt();
      FailedTries[v - 1][vProblemLeft]++;
    }
    totalTime[v - 1] += sc.nextInt() + 20 * 60 * FailedTries[v - 1][vProblemLeft]; // temos o tempo total da equipa v
                                                                                   // com os 7 exs resolvidos
    // agora preciso de imaginar que as outras equipas tbm conseguiram rsolver os 7
    // problemas no menor tempo possivel
    for (int i = 0; i < e; i++) { // adicionar os tempos as outras equipas no melhor caso para elas
      if (i == v - 1)
        continue; // nos temos total conhecimento sobre a equipa v
      int time = lastSubmission[i] + d; // tempo que podem voltar a submeter quer seja antes ou depois do balckout
      time = Math.max(b, time); // isto agora é o proximo tempo em que pode submeter depois de o blackout
      for (int j = 0; j < 7; j++) {
        if (!problems[i][j]) {
          totalTime[i] += time + 20 * 60 * FailedTries[i][j];
          time += d;
        }
      }
    }
    // System.out.println(Arrays.toString(totalTime));
    for (int i = 0; i < e; i++) {
      if (i == v - 1)
        continue;
      if (totalTime[i] < totalTime[v - 1]) {
        System.out.println("Nao sabemos");
        System.exit(0);
      }
    }
    System.out.println("Vencemos");
  }
}
