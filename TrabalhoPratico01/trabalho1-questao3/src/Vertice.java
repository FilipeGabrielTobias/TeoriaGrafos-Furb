import java.util.ArrayList;
import java.util.List;

/**
 * @author Filipe Gabriel Tobias
 */
public class Vertice implements Comparable<Vertice> {
    private float distancia;
    private int grau, abertura, fechamento;
    private String rotulo, status;
    private Vertice pai;
    public List<Aresta> arestas;
    List<Vertice> verticesAdjacentes;
    int botCount;


    public Vertice(String rotuloVertice) {
        this.rotulo = rotuloVertice;
        this.verticesAdjacentes = new ArrayList<>();
        this.arestas = new ArrayList();
        this.botCount = 0;
    }

    public List<Vertice> getAdjacentes(boolean isDirigido) {
        for (Aresta aresta : arestas) {
            if (aresta.getVerticeOrigem().equals(this))
                verticesAdjacentes.add(aresta.getVerticeDestino());
            else if (!isDirigido && aresta.getVerticeDestino().equals(this))
                verticesAdjacentes.add(aresta.getVerticeOrigem());
        }

        return verticesAdjacentes;
    }

    public Aresta getArestaPorVertices(Vertice vertice) {
        for (Aresta aresta : arestas) {
            if (aresta.getVerticeOrigem().equals(this) && aresta.getVerticeDestino().equals(vertice))
                return aresta;
            else if (aresta.getVerticeDestino().equals(this) && aresta.getVerticeOrigem().equals(vertice))
                return aresta;
        }

        return null;
    }

    @Override
    public int compareTo(Vertice v) {
        return Float.compare(this.distancia, v.getDistancia());
    }

    public List<Vertice> getNaoAdjacente(Grafo grafo) {
        List<Vertice> naoAdjacentes = new ArrayList();

        for (Vertice vertice : grafo.getVertices())
            if (!this.getAdjacentes(grafo.isDirigido()).contains(vertice))
                naoAdjacentes.add(vertice);

        return naoAdjacentes;
    }

    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

    public Vertice getPai() {
        return pai;
    }

    public void setPai(Vertice pai) {
        this.pai = pai;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String Status) {
        this.status = Status;
    }

    public float getDistancia() {
        return distancia;
    }

    public void setDistancia(float distancia) {
        this.distancia = distancia;
    }

    public List<Aresta> getArestas() {
        return arestas;
    }

    public void setArestas(List<Aresta> arestas) {
        this.arestas = arestas;
    }

    public int getGrau() {
        return grau;
    }

    public void setGrau(int grau) {
        this.grau = grau;
    }

    public int getBotCount() {
        return botCount;
    }

    public void setBotCount(int botCount) {
        this.botCount = botCount;
    }

    public int getAbertura() {
        return abertura;
    }

    public void setAbertura(int abertura) {
        this.abertura = abertura;
    }

    public int getFechamento() {
        return fechamento;
    }

    public void setFechamento(int fechamento) {
        this.fechamento = fechamento;
    }

    public Vertice() {
    }
}