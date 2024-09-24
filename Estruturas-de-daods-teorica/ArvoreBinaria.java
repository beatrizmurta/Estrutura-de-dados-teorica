import java.util.*;

public class ArvoreBinaria {
    
    No raiz;
    List<No> nos;

    public ArvoreBinaria(){ 
        raiz = null; 
        nos = new ArrayList<>(); 
    } 

    public void adicionar(int valor) {
        raiz = adicionarNo(raiz, valor);
    }

    public void remover(int valor){
        raiz = removerNo(raiz, valor);
    }

    public void imprimirArvore(){
        List<Integer> valores = new ArrayList<>();
        preencherListaEmOrdem(raiz, valores);
        for (Integer valor : valores) {
            System.out.println(valor);
        }
    }

    private void preencherListaEmOrdem(No noAtual, List<Integer> valores) {
        if (noAtual != null) {
            preencherListaEmOrdem(noAtual.esquerda, valores);
            valores.add(noAtual.valor);
            preencherListaEmOrdem(noAtual.direita, valores);
        }
    }

    public No adicionarNo(No noAtual, int valor) {
        if (noAtual == null) {
            return new No(valor);
        }
        if (valor < noAtual.valor) {
            noAtual.esquerda = adicionarNo(noAtual.esquerda, valor);
        }
        else if (valor > noAtual.valor) {
            noAtual.direita = adicionarNo(noAtual.direita, valor);
        }

        return noAtual;
    }

    public No removerNo(No noAtual, int valor) {
        if (noAtual == null) {
            return null;
        }
        if (valor < noAtual.valor) {
            noAtual.esquerda = removerNo(noAtual.esquerda, valor);
        }
        else if (valor > noAtual.valor) {
            noAtual.direita = removerNo(noAtual.direita, valor);
        }
        else {
            // Caso o nó não tenha filhos ou tenha apenas um filho
            if (noAtual.esquerda == null && noAtual.direita == null){
                noAtual = null;
            }
            else if(noAtual.esquerda == null){
                noAtual = noAtual.direita;
            }
            else if(noAtual.direita == null){
                noAtual = noAtual.esquerda;
            }
            else {
                // Caso o nó tenha dois filhos
                No menorNo = encontrarMenorNo(noAtual.direita);
                noAtual.valor = menorNo.valor;
                noAtual.direita = removerNo(noAtual.direita, menorNo.valor);
            }
        }

        return noAtual;
    }

    private No encontrarMenorNo(No noAtual) {
        while (noAtual.esquerda != null) {
            noAtual = noAtual.esquerda;
        }
        return noAtual;
    }
    
    public static void main(String[] args) {
        ArvoreBinaria arvore = new ArvoreBinaria();
        
        arvore.adicionar(5);
        arvore.adicionar(3);
        arvore.adicionar(7);
        arvore.adicionar(2);
        arvore.adicionar(4);
        arvore.adicionar(6);
        arvore.adicionar(8);

        System.out.println("Árvore em ordem:");
        arvore.imprimirArvore();

        arvore.remover(3);
        System.out.println("Árvore após remover 3:");
        arvore.imprimirArvore();
    }
}

class No {
    int valor;
    No esquerda, direita;

    public No(int valor) {
        this.valor = valor;
        esquerda = direita = null;
    }
}