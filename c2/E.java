/*
o objetivo é fazer uma ordenacao topologica
feito com dfs, só que precisa de detetar ciclos entao em vez de uma rray booleano para os visited usa um de ints
0-nao visitado
1-esta a ser percorrido por um dfs
2-visitado
se nos a meio de um caminho de exploracao do dfs voltarmos a um no isso é um ciclo
a ordenacao topologica consiste em fazer um dfs a partir de todos os nos iniciais
quando chegarmos ao fim de um caminho do dfs signfica que é uma folha então ja nao ha mais dependencias nesse caminho
por tnt adicionasse por backtracking a uma stack, se imaginar isto visualmente faz sentido pq vamos acabar com as dependencias
nao sei como explicar melhor por texto
uma necessidade é que o grafo seja aciclico, se nao vai estar tudo dependente uns dos outros
tambem se pode usar o kahn's algorithm que é isso que vou impolementar noutro ficheiro 
o kahn usa uma fila entao garante que nao haja stack overflow, mas i mean tbm posso fazer uma pilha com uma linkedlsit e nao diretamente a do pc
o kahn permite manter a ordem lexicografica se usarmos uma priority queue em vez de so uma fila normal, mas de resto quer um quer outro funcionam para tudo
*/
import java.util.*;

public class E {
  static ArrayList<ArrayList<Integer>> adj;
  // static boolean[] visited;
  static LinkedList<Integer> s = new LinkedList<>();
  static int[] cor; // 0 branco, 1 cinzento, 2 preto
                    // 0 por visitar, esta a ser, ja foi
  static boolean acabou;

  static void dfs(int i) {
    cor[i] = 1; // esta a ser visitado
    for (int w : adj.get(i)) {
      if (cor[w] == 1) {
        acabou = true;
        return;
      }
      if (cor[w] == 0) {
        dfs(w);
      }
    }
    cor[i] = 2; // acabou
    s.push(i);
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();
    adj = new ArrayList<>(n + 1);
    for (int i = 0; i < n + 1; i++)
      adj.add(new ArrayList<>());
    // visited = new boolean[n + 1];
    cor = new int[n + 1];
    for (int i = 0; i < m; i++) {
      int a = sc.nextInt();
      int b = sc.nextInt();
      adj.get(a).add(b);
    }
    for (int i = 1; i < n + 1; i++) {
      if (cor[i] == 0) {
        dfs(i);
        if (acabou) {
          System.out.println("IMPOSSIBLE");
          System.exit(0);
        }
      }
    }
    for (int i = 0; i < s.size(); i++) {
      System.out.print(s.get(i) + ((i == s.size() - 1) ? "\n" : " "));
    }
  }
}
