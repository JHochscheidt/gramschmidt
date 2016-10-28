/*  ##########################################################
    Universidade Federal da Fronteira Sul - Campus Chapecó - SC
    Ciência da Computação - 2016.2
    Álgebra Linear
    Docente : Antônio Marcos Correa Neri
    Discente/Autor do trabalho: Jackson Henrique Hochscheidt

    Código desenvolvido pelo discente Jackson Henrique Hochscheidt,
    Matrícula 1211100021,
    e-mail : jackson94h@gmail.com.

    Trabalho visa implementar o método de ortogonalização e ortonormalização
    atrás do método de Gram-Schmidt. O trabalho foi desenvolvido conforme as
    especificações solicitadas pelo professor.
*/

/*
    -----------------------------------------------------------------------
    Método de ortogonalização de Gram-Schmidt para vetores de dimensão 5
    -----------------------------------------------------------------------
*/

import java.io.*;
import java.util.regex.*;
public class ProgramaGram{

  public static void main(String[] args) throws IOException{
    String caminhoArquivo = args[0];
    ProgramaGram programa = new ProgramaGram();

    Vetor base[] = new Vetor[5];
    Vetor ortogonalizacao[] = new Vetor[base.length];

    programa.leitor(caminhoArquivo, base);
    ortogonalizacao = programa.Ortogonaliza(base);

    programa.EscreveResultado(ortogonalizacao,caminhoArquivo);
  }

  // metodo que escreve o resultado do metodo no arquivo
  public static void EscreveResultado(Vetor[] ortogonalizacao, String caminhoArquivo) throws IOException{
    FileWriter arq = new FileWriter(caminhoArquivo, true);
    PrintWriter gravarArq = new PrintWriter(arq);

    if( ortogonalizacao[0].norma == 0 ||ortogonalizacao[1].norma == 0 ||ortogonalizacao[2].norma == 0 ||ortogonalizacao[3].norma == 0 ||ortogonalizacao[4].norma == 0){
      gravarArq.printf("====================================================\n");
      gravarArq.printf("Base informada é LINEARMENTE DEPENDENTE");
    }else{
      gravarArq.printf("====================================================\n");
      gravarArq.printf("Espaço euclidiano ORTOGONAL\n");
      for (int i=0; i < ortogonalizacao.length; i++) {
          gravarArq.printf("(");
          for(int j = 0; j < ortogonalizacao[i].vet.length; j++){
            gravarArq.printf(" %.10f ", ortogonalizacao[i].vet[j]);
            if(j < ortogonalizacao[i].vet.length-1){
              gravarArq.printf(" ; ");
            }
          }
          gravarArq.printf(")\n");
          gravarArq.printf("----------------------------------------------------\n");
      }
      gravarArq.printf("====================================================\n");
      gravarArq.printf("Espaço euclidiano ORTONORMAL\n");
      for (int i=0; i < ortogonalizacao.length; i++) {
        gravarArq.printf("(");
        for(int j = 0; j < ortogonalizacao[i].vet.length; j++){
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
  // fim metodo de escrita do resultado no arquivo


  // metodo de ortogonalizacao
  public Vetor[] Ortogonaliza(Vetor[] base){
    // vetores de ortogonalizacao
    Vetor ortogonalizacao[] = new Vetor[base.length];

    ortogonalizacao[0] = base[0];
    ortogonalizacao[0].Norma();
    ortogonalizacao[0].VetorUnitario();

    ortogonalizacao[1] = SubtracaoVetores(base[1], Projecao(base[1],ortogonalizacao[0]));
    ortogonalizacao[1].Norma();
    ortogonalizacao[1].VetorUnitario();

    ortogonalizacao[2] = SubtracaoVetores(SubtracaoVetores(base[2], Projecao(base[2], ortogonalizacao[0])), Projecao(base[2], ortogonalizacao[1]));
    ortogonalizacao[2].Norma();
    ortogonalizacao[2].VetorUnitario();

    ortogonalizacao[3] = SubtracaoVetores(SubtracaoVetores(SubtracaoVetores(base[3], Projecao(base[3], ortogonalizacao[0])),Projecao(base[3], ortogonalizacao[1])),Projecao(base[3], ortogonalizacao[2]));
    ortogonalizacao[3].Norma();
    ortogonalizacao[3].VetorUnitario();

    ortogonalizacao[4] = SubtracaoVetores(SubtracaoVetores(SubtracaoVetores(SubtracaoVetores(base[4], Projecao(base[4], ortogonalizacao[0])), Projecao(base[4], ortogonalizacao[1])), Projecao(base[4], ortogonalizacao[2])), Projecao(base[4], ortogonalizacao[3]));
    ortogonalizacao[4].Norma();
    ortogonalizacao[4].VetorUnitario();

    return ortogonalizacao;

  }
  // fim do metodo de ortogonalizacao

  // metodo faz a subtracao entre vetores A e B
  public Vetor SubtracaoVetores(Vetor a, Vetor b){
    Vetor diferenca = new Vetor(a.vet.length);
    for (int i = 0; i < a.vet.length ; i++) {
      diferenca.vet[i] = a.vet[i] - b.vet[i];
    }
    diferenca.VetorUnitario();
    diferenca.Norma();
    return diferenca;
  }
  // fim do metodo subtracao

  // metodo que calcula a projecao entre 2 vetores, projecao de u sobre v
  public Vetor Projecao(Vetor u, Vetor v){
    // calcula proj vU
    double escalar = 0;
    Vetor projuV = new Vetor(v.vet.length);

    // calcula o produto interno entre os vetores
    for (int i = 0; i < u.vet.length ; i++) escalar = escalar + (u.vet[i] * v.vet[i]) ;

    // calcula a projecao dos vetores
    for (int i = 0; i < v.vet.length; i++ ) projuV.vet[i] = (escalar / (v.norma*v.norma)) * v.vet[i];

    return projuV;
  }
  // fim metodo projecao

  // metodo que faz a leitura do arquivo e passa-o para a base
  public void leitor(String caminhoArquivo, Vetor[] base) throws IOException{
    BufferedReader buffRead = new BufferedReader(new FileReader(caminhoArquivo));
    String linha = buffRead.readLine();
    linha = buffRead.readLine(); // para ficar na linha em que começam os vetores

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
