import java.io.*;
import java.util.regex.*;
public class ProgramaGram{

public static void main(String[] args) throws IOException{
    String caminhoArquivo = args[0];
    System.out.println("nome do arquivo := " + caminhoArquivo);



        ProgramaGram.leitor(caminhoArquivo);


}





public static void leitor(String caminhoArquivo) throws IOException{
  BufferedReader buffRead = new BufferedReader(new FileReader(caminhoArquivo));
  String linha = "";
  String regex = " \\(,\\)";
  while(true){
    if (linha != null) {
      String subLinha[] = linha.split("[" + Pattern.quote(regex) + "]");
      char charr = ' ';

      for (int i = 0; i < subLinha.length ; i++ ) {
        System.out.print(subLinha[i]);
      }
      System.out.println();

    //  for (int i = 0; i < linha.length() ; i++ ) {
    //    charr = linha.charAt(i);
    //    System.out.print(charr);
    //  }
      //System.out.println(linha);
    }else{
      break;

    }
    linha = buffRead.readLine();
  }
  buffRead.close();
}


}
