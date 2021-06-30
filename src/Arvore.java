import java.util.ArrayList;


public class Arvore {

  private No raiz;


  public No getRaiz(){
    return this.raiz;
  }

  public boolean vazia(){
    return raiz==null;
  }

  public void insereElemento(String dado){

    ArrayList<No> lista = new ArrayList<>();

    No novo = new No(dado);

    if (vazia()){
      this.raiz = novo;
      return;
    }

    No raiz = this.raiz;

    while (raiz.esquerda != null && raiz.getDado().compareTo(dado) > 0 || raiz.direita != null && raiz.getDado().compareTo(dado) < 0){

      lista.add(raiz);

      if (raiz.getDado().compareTo(dado) > 0){
        raiz = raiz.esquerda;
      }
      else if (raiz.getDado().compareTo(dado) < 0){
        raiz = raiz.direita;
      }
    }

    if (raiz.getDado().compareTo(dado) > 0){
      raiz.esquerda = novo;
    }
    else if (raiz.getDado().compareTo(dado) < 0){
      raiz.direita = novo;
    }

    for(int i = lista.size() - 1; i >= 0 ; i--){
      rebalancear(lista.get(i));
    }

  }

  public void removeElemento(String dado){
    if (vazia()){
      System.out.println("Arvore vazia");
      return;
    }

    No elemento = encontraElemento(dado);

    if (elemento == null){
      System.out.println("Elemento não existe");
      return;
    }

    No pai = pai(elemento);

    if (elemento.esquerda == null && elemento.direita == null){
      if (elemento == raiz){
        raiz = null;
      }
      else if (pai.direita == elemento){
        pai.direita = null;
      }
      else{
        pai.esquerda = null;
      }
    }
    else if (elemento.esquerda == null){
      if (elemento == raiz){
        raiz = elemento.direita;
      }
      else if (pai.direita == elemento){
        pai.direita = elemento.direita;
      }
      else{
        pai.esquerda = elemento.direita;
      }
    }
    else if (elemento.direita == null){
      if (elemento == raiz){
        raiz = elemento.esquerda;
      }
      else if (pai.esquerda == elemento){
        pai.esquerda= elemento.esquerda;
      }
      else{
        pai.direita = elemento.esquerda;
      }
    }
    else{
      No aux = encontraMenor(elemento.direita);
      elemento.setDado(aux.getDado());
      removeElemento(aux);
    }

  }



  public boolean existeElemento(String dado){
    return encontraElemento(dado) != null;
  }

  public No encontraElemento(String dado){
    if (vazia()){
      System.out.println("Arvore vazia");
    }

    No atual = this.raiz;

    while (atual != null){
      if (atual.getDado().compareTo(dado) == 0){
        break;
      }
      else if (atual.getDado().compareTo(dado) > 0){
        atual = atual.esquerda;
      }
      else {
        atual = atual.direita;
      }
    }


    return atual;
  }

  void imprimePreordem(No no) {
    if (no == null){
      return;
    }
    System.out.println(no.getDado());
    imprimePreordem(no.esquerda);
    imprimePreordem(no.direita);
  }

  void imprimeInordem(No no) {
    if (no == null)
      return;
    imprimeInordem(no.esquerda);
    System.out.println(no.getDado());
    imprimeInordem(no.direita);
  }

  void imprimePosordem(No no) {
    if (no == null)
      return;
    imprimePosordem(no.esquerda);
    imprimePosordem(no.direita);
    System.out.println(no.getDado());
  }

  public No encontraMenor(No no){
    No atual = no;

    while(atual.esquerda != null){
      atual = atual.esquerda;
    }

    return atual;
  }

  public int altura(No no){
    if (no != null) {
      return 1 + Math.max(altura(no.esquerda),altura(no.direita));
    }
    return 0;
  }

  private void rebalancear(No no){
    if (balanceamento(no) < -1){
      if (balanceamento(no.esquerda) > 0){
        rotacaoEsquerda(no.esquerda);
      }
      rotacaoDireita(no);
    }
    else if (balanceamento(no) > 1){
      if (balanceamento(no.direita) < 0){
        rotacaoDireita(no.direita);
      }
      rotacaoEsquerda(no);
    }
  }

  private int balanceamento(No no){
    if (no == null){
      return 0;
    }
    return altura(no.direita) - altura(no.esquerda);
  }

  private void rotacaoEsquerda(No a){
    No pai = pai(a);
    No x = a.direita;

    if (a == raiz){
      raiz = x;
    }
    else if (pai.getDado().compareTo(x.getDado()) <= 0){
      pai.direita = x;
    }
    else{
      pai.esquerda = x;
    }

    a.direita = x.esquerda;
    x.esquerda = a;
  }

  private void rotacaoDireita(No no){
    No pai = pai(no);
    No x = no.esquerda;

    if (no == raiz){
      raiz = x;
    }
    else if (pai.getDado().compareTo(x.getDado()) <= 0){
      pai.direita = x;
    }
    else{
      pai.esquerda = x;
    }

    no.esquerda = x.direita;
    x.direita = no;
  }

  public No pai(No no){
    No atual = this.raiz;

    if (no == this.raiz){
      return raiz;
    }

    while(atual!=null){
      if (atual.esquerda == no || atual.direita == no){
        break;
      }
      else if (atual.getDado().compareTo(no.getDado()) > 0){
        atual = atual.esquerda;
      }
      else{
        atual = atual.direita;
      }
    }

    return atual;
  }

  public void removeElemento(No elemento){

    No pai = pai(elemento);

    if(elemento.esquerda == null && elemento.direita == null){
      if (elemento == raiz) {
        raiz = null;
      }
      else if (pai.direita == elemento) {
        pai.direita = null;
      }
      else {
        pai.esquerda = null;
      }
    }
    else if(elemento.esquerda == null){
      if (elemento == raiz) {
        raiz = elemento.direita;
      }
      else if (pai.direita == elemento) {
        pai.direita = elemento.direita;
      }
      else {
        pai.esquerda = elemento.direita;
      }
    }
    else if(elemento.direita == null){
      if (elemento == raiz) {
        raiz = elemento.esquerda;
      }
      else if (pai.esquerda == elemento) {
        pai.esquerda = elemento.esquerda;
      }
      else {
        pai.direita = elemento.esquerda;
      }
    }
    else {
      No aux = encontraMenor(elemento.direita);
      elemento.setDado(aux.getDado());
      removeElemento(aux);
    }

  }
}
