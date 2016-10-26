public class Vetor{
  public double vet[];
  public double norma;
  public double vetUnitario[];

  public Vetor(double v[]){
    this.vet = new double[v.length];
    this.norma = 0;
    this.vetUnitario = new double[v.length];
    //System.out.println("VET");
    for(int i = 0; i < v.length; i++){
      //System.out.print("[" + v[i] + "] ");
      this.vet[i] = v[i];
      //System.out.println(" [" + this.vet[i] + "]");
      this.vetUnitario[i] = 0;
    }
  }

  // metodo que calcula a norma do vetor
  public void Norma(){
    double tam = 0;
    for(int i = 0; i < this.vet.length; i++){
      tam = tam + (this.vet[i]*this.vet[i]);
    }
    tam = Math.sqrt(tam);
    this.norma = tam;
  }
  // metodo que calcula o vetor unitario
  public void VetorUnitario(){
    for(int i = 0; i < this.vet.length; i++){
      this.vetUnitario[i] = this.vet[i] / this.norma ;
    }
  }
}
