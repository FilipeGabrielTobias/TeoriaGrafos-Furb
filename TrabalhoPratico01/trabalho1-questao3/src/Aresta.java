/**
 * @author Filipe Gabriel Tobias
 */
public class Aresta {

    private String rotulo;
    private float valor;
    private Vertice verticeOrigem, verticeDestino;

    public Aresta(String rotulo, float valor, Vertice verticeOrigem, Vertice verticeDestino) {
        this.rotulo = rotulo;
        this.valor = valor;
        this.verticeDestino = verticeDestino;
        this.verticeOrigem = verticeOrigem;
    }

    public Aresta() {
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }


    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

    public Vertice getVerticeOrigem() {
        return verticeOrigem;
    }

    public void setVerticeOrigem(Vertice verticeOrigem) {
        this.verticeOrigem = verticeOrigem;
    }

    public Vertice getVerticeDestino() {
        return verticeDestino;
    }

    public void setVerticeDestino(Vertice verticeDestino) {
        this.verticeDestino = verticeDestino;
    }
}