/*
Olá!
Eu tinha dito que queria que vocês implementassem o método de Gram-Schmidt.
Então eis como eu o quero:
-Entrada:
Deve ler o arquivo texto ortogonaliza.txt que terá as sete linhas como abaixo
//Estes são os vetores que devem ser usados
(1,2,3,4,5)
(1,2,3,4,4)
(1,1,3,4,3)
(1,1,2,4,2)
(1,1,1,1,1)
Fim do arquivo
-Saída: deve me apresentar o resultado do processo no mesmo arquivo lido, logo abaixo da linha "fim do arquivo".
Observações.
- O arquivo pode ter, eventualmente, vetores que não formam uma base.
O programa deve decidir antes, se os vetores lidos são LI (se não forem, o que acontece com o programa?)
- Eu só sei compilar em C e no linux.
Então seu programa deve ter instruções sobre compilação se você fugir do padrão ou precisar de algo diferente.
- Você deve apresentar o exercício num arquivo compactado (zipado) com uma pasta contendo o código fonte do programa.
Tanto a pasta quanto o arquivo zipado devem conter seu número de matrícula, seu nome e último sobrenome como no exemplo
1049374937antonioneri.zip
O nome do arquivo fonte deve ser programagram.c ou equivalente.
Me pergunte neste tópico do fórum dúvidas sobre a apresentação.
Até logo.
*/
import java.io.*;
import java.util.regex.*;
public class ProgramaGram{

  public static void main(String[] args) throws IOException{
    String caminhoArquivo = args[0];
    //System.out.println("nome do arquivo := " + caminhoArquivo);


    Vetor base[] = new Vetor[5];
    ProgramaGram programa = new ProgramaGram();
    programa.leitor(caminhoArquivo, base);

    //double matrix[][] = new double[base.length][base.length];

    //for(int i = 0; i< base.length; i++){
    //  for(int j = 0; j < base.length; j++){
    //    matrix[i][j] = base[i].vet[j];
    //    System.out.print("[" + matrix[i][j] + "]");
    //  }
    //  System.out.println();
    //}



    //Det det = new Det();
    //double determinante = det.det(matrix);
  //  System.out.println("DETERMIMANTE " + determinante);
//


    Vetor ortogonalizacao[] = new Vetor[base.length];
    ortogonalizacao = programa.Ortogonaliza(base);
    //  System.out.println("ORTOGONALIZACAO");
    for (int i = 0; i < ortogonalizacao.length ; i++) {
      for (int j = 0;  j < ortogonalizacao[i].vet.length; j++ ) {
        System.out.println("[" + ortogonalizacao[i].vet[j] + "]");
      }
      System.out.println("Norma " + ortogonalizacao[i].norma);
      System.out.println("NORMALIZACAO");
      //System.out.println("NORMALIZACAO");
      for ( int k = 0; k < ortogonalizacao.length; k++) {
        // System.out.println("[" + ortogonalizacao[i].vetUnitario[k] + "]");
      }
    }
    programa.EscreveResultado(ortogonalizacao,caminhoArquivo);

  }

  public static void EscreveResultado(Vetor[] ortogonalizacao, String caminhoArquivo) throws IOException{
    FileWriter arq = new FileWriter(caminhoArquivo, true);

    PrintWriter gravarArq = new PrintWriter(arq);

    if( ortogonalizacao[0].norma == 0
      ||ortogonalizacao[1].norma == 0
      ||ortogonalizacao[2].norma == 0
      ||ortogonalizacao[3].norma == 0
      ||ortogonalizacao[4].norma == 0
      ){
        gravarArq.printf("====================================================\n");
        gravarArq.printf("Base informada é LINEARMENTE DEPENDENTE");
      }else{
        gravarArq.printf("====================================================\n");
        gravarArq.printf("Espaço euclidiano ortoGONAL\n");
        for (int i=0; i < ortogonalizacao.length; i++) {
          gravarArq.printf("(");
          for(int j = 0; j < ortogonalizacao[i].vet.length; j++){
            //gravarArq.printf("| %2d X %d = %2d |%n", i, n, (i*n));
            gravarArq.printf(" %.10f ", ortogonalizacao[i].vet[j]);
            if(j < ortogonalizacao[i].vet.length-1){
              gravarArq.printf(" ; ");
            }
          }
          gravarArq.printf(")\n");
          gravarArq.printf("----------------------------------------------------\n");
        }
        gravarArq.printf("====================================================\n");
        gravarArq.printf("Espaço euclidiano ortoNORMAL\n");
        for (int i=0; i < ortogonalizacao.length; i++) {
          gravarArq.printf("(");
          for(int j = 0; j < ortogonalizacao[i].vet.length; j++){
            //gravarArq.printf("| %2d X %d = %2d |%n", i, n, (i*n));
            gravarArq.printf(" %.10f ", ortogonalizacao[i].vetUnitario[j]);
            if(j < ortogonalizacao[i].vet.length-1){
              gravarArq.printf(" ; ");
            }
          }
          gravarArq.printf(")\n");
          gravarArq.printf("----------------------------------------------------\n");
        }
      }
    arq.close();
  }


  // metodo de ortogonalizacao
  public Vetor[] Ortogonaliza(Vetor[] base){
    //  System.out.println();
    //  System.out.println();
    //  System.out.println("ORTOGONALIZACAO");
    //  System.out.println("ORTOGONALIZACAO");

    // variaveis de ortogonalizacao
    Vetor ortogonalizacao[] = new Vetor[base.length];

    ortogonalizacao[0] = base[0];
    ortogonalizacao[0].Norma();
    ortogonalizacao[0].VetorUnitario();
    //  System.out.println("Norma 0 " + ortogonalizacao[0].norma + " Unit 0 ");
    //  System.out.println("ORT 0 --> ");
    for(int i = 0; i < ortogonalizacao[0].vet.length; i++){
      //    System.out.println("[" + ortogonalizacao[0].vet[i] + "]");
    }
    //  System.out.println();


    //  for(int i = 0; i < base[1].vet.length; i++){
    //System.out.print("[" + base[1].vet[i] + "]");
    //  }
    //System.out.println(base[1].norma);

    ortogonalizacao[1] = SubtracaoVetores(base[1], Projecao(base[1],ortogonalizacao[0]));
    ortogonalizacao[1].Norma();
    ortogonalizacao[1].VetorUnitario();
    //  System.out.println("Norma 1 " + ortogonalizacao[1].norma + " Unit 1 ");
    //  System.out.println("ORT 1 --> ");
    for(int i = 0; i < ortogonalizacao[1].vet.length; i++){
      System.out.println("[" + ortogonalizacao[1].vet[i] + "]");
    }
    //  System.out.println();

    //System.out.println("BASE");
    //for(int i = 0; i < base[2].vet.length; i++){
    //    System.out.print("[" + base[2].vet[i] + "]");
    //  }
    //  System.out.println(base[2].norma);

    ortogonalizacao[2] = SubtracaoVetores(SubtracaoVetores(base[2], Projecao(base[2], ortogonalizacao[0])), Projecao(base[2], ortogonalizacao[1]));
    ortogonalizacao[2].Norma();
    ortogonalizacao[2].VetorUnitario();
    //  System.out.println("Norma 2 " + ortogonalizacao[2].norma + " Unit 2 ");
    //  System.out.println("ORT 2 --> ");
    for(int i = 0; i < ortogonalizacao[2].vet.length; i++){
      //  System.out.println("[" + ortogonalizacao[2].vet[i] + "]");
    }
    //  System.out.println();


    ortogonalizacao[3] = SubtracaoVetores(SubtracaoVetores(SubtracaoVetores(base[3], Projecao(base[3], ortogonalizacao[0])),Projecao(base[3], ortogonalizacao[1])),Projecao(base[3], ortogonalizacao[2]));
    ortogonalizacao[3].Norma();
    ortogonalizacao[3].VetorUnitario();
    //  System.out.println("Norma 3 " + ortogonalizacao[3].norma + " Unit 3 ");
    //  System.out.println("ORT 3 --> ");
    for(int i = 0; i < ortogonalizacao[3].vet.length; i++){
      //    System.out.println("[" + ortogonalizacao[3].vet[i] + "]");
    }
    //  System.out.println();



    ortogonalizacao[4] = SubtracaoVetores(SubtracaoVetores(SubtracaoVetores(SubtracaoVetores(base[4], Projecao(base[4], ortogonalizacao[0])), Projecao(base[4], ortogonalizacao[1])), Projecao(base[4], ortogonalizacao[2])), Projecao(base[4], ortogonalizacao[3]));
    //SubtracaoVetores(base[4],    SubtracaoVetores( Projecao(base[4], ortogonalizacao[0]) ,  SubtracaoVetores( Projecao(base[4], ortogonalizacao[1])  ,      SubtracaoVetores(     Projecao(base[4], ortogonalizacao[2])  ,   Projecao(base[4], ortogonalizacao[3])    )   )));
    ortogonalizacao[4].Norma();
    ortogonalizacao[4].VetorUnitario();

    //  System.out.println("Norma 4 " + ortogonalizacao[4].norma + " Unit 4 ");
    //  System.out.println("ORT 4 --> ");
    for(int i = 0; i < ortogonalizacao[4].vet.length; i++){
      //  System.out.println("[" + ortogonalizacao[4].vet[i] + "]");
    }
    //  System.out.println();



    return ortogonalizacao;

  }

    // faz a subtracao entre vetores A e B
    public Vetor SubtracaoVetores(Vetor a, Vetor b){
      //System.out.println("tamanho de A " + a.vet.length);
      Vetor diferenca = new Vetor(a.vet.length);
      for (int i = 0; i < a.vet.length ; i++) {
        diferenca.vet[i] = a.vet[i] - b.vet[i];
        //System.out.println("i = " + a.vet[i] + " - " + b.vet[i]);
      }
      diferenca.VetorUnitario();
      diferenca.Norma();
      return diferenca;
    }



  // metodo que calcula a projecao entre 2 vetores, projecao de u sobre v
  public Vetor Projecao(Vetor u, Vetor v){
    // calcula proj vU
    double escalar = 0;
    Vetor projuV = new Vetor(v.vet.length);

    // calcula o produto interno entre os vetores
    for (int i = 0; i < u.vet.length ; i++){
      escalar = escalar + (u.vet[i] * v.vet[i]) ;
    }
    //  System.out.println("escalar " + escalar);
    //  System.out.println("norma " + v.norma);

    // calcula a projecao dos vetores
    for (int i = 0; i < v.vet.length; i++ ){
      projuV.vet[i] = (escalar / (v.norma*v.norma)) * v.vet[i];
      //  System.out.println(projuV.vet[i]);
    }
    //System.out.println();
    return projuV;
  }
  // fim metodo projecao



  // metodo que faz a leitura do arquivo e passa-o para a base
  public void leitor(String caminhoArquivo, Vetor[] base) throws IOException{
    BufferedReader buffRead = new BufferedReader(new FileReader(caminhoArquivo));
    String linha = buffRead.readLine();
    linha = buffRead.readLine(); // para ficar na linha em que começam os vetores
    //System.out.println("[" + linha + "]");

    String regex = "\\(,\\)";
    double vetor[] = new double[5];
    int contLinha = 0;
    while(linha != null){
      if(contLinha < 5){
        linha = linha.replaceAll("\\s{1,}","");
        String newLinha[] = linha.split("[" + Pattern.quote(regex) + "]");

        for (int i=0; i < newLinha.length ; i++) {
          if(i < newLinha.length-1){
            vetor[i] = Double.parseDouble(newLinha[i+1]);
          }
        }
        base[contLinha] = new Vetor(vetor);
        contLinha++;
        linha = buffRead.readLine();
      }else{
        break;
      }
    }
    buffRead.close();
  }
  // fim metodo leitor
}
