/*  ##########################################################
    Universidade Federal da Fronteira Sul - Campus Chapecó - SC
    Ciência da Computação - 2016.2
    Álgebra Linear
    Docente : Antônio Marcos Correa Neri
    Discente/Autor do trabalho: Jackson Henrique Hochscheidt
    e-mail : jackson94h@gmail.com

    Trabalho visa implementar o método de ortogonalização e ortonormalização
    atrás do método de Gram-Schmidt. O trabalho foi desenvolvido conforme as
    especificações solicitadas pelo professor. Que estão descritas logo abaixo.
*/

public class Vetor{
  public double vet[];
  public double norma;
  public double vetUnitario[];

  // contrutor apenas com o tamanho
  public Vetor(int tamanho){
    this.vet = new double[tamanho];
    this.norma = 0;
    this.vetUnitario = new double[tamanho];
  }

  // construtor passando por parametro um vetor
  public Vetor(double v[]){
    this.vet = new double[v.length];
    this.vetUnitario = new double[v.length];
    for(int i = 0; i < v.length; i++){
      this.vet[i] = v[i];
      this.vetUnitario[i] = 0;
    }
    this.VetorUnitario();
    this.Norma();
  }

  // metodo que calcula a norma do vetor
  public void Norma(){
    double tam = 0;
    for(int i = 0; i < this.vet.length; i++) tam = tam + (this.vet[i]*this.vet[i]);

    tam = Math.sqrt(tam);
    this.norma = tam;
  }

  // metodo que calcula o vetor unitario
  public void VetorUnitario(){
    double[] vetUnitario = new double[this.vet.length];
    for(int i = 0; i < this.vet.length; i++) this.vetUnitario[i] = this.vet[i] / this.norma ;

  }

}
