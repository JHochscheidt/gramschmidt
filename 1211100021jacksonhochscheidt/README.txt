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

README:
1 - O programa recebe como entrada um arquivo e o resultado é escrito no final do mesmo arquivo.
    - Se o conjunto de vetores encontrados for LD, será informado no final do arquivo,
    e não será gerado nenhum vetor como saída.
    Do contrário, os vetores ortogonalizados e ortonormalizados serão escritos ao final do arquivo.

2 - Para compilação ( caso o professor possua pacote JAVA para compilação - se não tiver, olhe o item 3):
  javac *.java

3 - Para execução :
  java ProgramaGram ortogonaliza.txt

  - ortogonaliza.txt é o arquivo texto conteno os vetores, como no exemplo que o professor havia passado. Segue o exemplo:
    //Estes são os vetores que devem ser usados
    (1,2,3,4,5)
    (1,2,3,4,4)
    (1,1,3,4,3)
    (1,1,2,4,2)
    (1,1,1,1,1)
    //Fim do arquivo

OBS: Caso o professor não possua os pacotes para compilação JAVA (JDK),
já enviei junto na pasta, o programa compilado, basta apenas seguir o item 3.
