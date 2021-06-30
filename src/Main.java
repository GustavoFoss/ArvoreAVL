public class Main {
  public static void main(String[] args) {
    Arvore arv = new Arvore();

    arv.insereElemento("boneco");
    arv.insereElemento("vitinho");
    arv.insereElemento("fabricio");
    arv.insereElemento("carro");
    arv.insereElemento("aviao");





    arv.imprimePreordem(arv.getRaiz());
    System.out.println();

    arv.removeElemento("boneco");
    arv.imprimePreordem(arv.getRaiz());
    System.out.println();
    arv.imprimePosordem(arv.getRaiz());
    System.out.println();
    arv.imprimeInordem(arv.getRaiz());






  }
}
