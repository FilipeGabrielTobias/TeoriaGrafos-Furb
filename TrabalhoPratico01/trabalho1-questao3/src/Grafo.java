import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Filipe Gabriel Tobias
 */
public class Grafo {
    private int[][] matrizAdjacencia;
    private boolean isDirigido;
    private List<Vertice> verticesGrafo = new ArrayList<>();
    private List<Aresta> arestasGrafo = new ArrayList<>();

    public Grafo(boolean isDirigido) {
        this.isDirigido = isDirigido;
    }

    public void addAresta(String rotulo, float valor, Vertice verticeOrigem, Vertice verticeDestino) {
        Aresta aresta = new Aresta(rotulo, valor, verticeOrigem, verticeDestino);

        verticeOrigem.arestas.add(aresta);
        verticeDestino.arestas.add(aresta);

        if (isDirigido)
            verticeOrigem.setGrau(verticeOrigem.getGrau() + 1);
        else {
            verticeOrigem.setGrau(verticeOrigem.getGrau() + 1);
            verticeDestino.setGrau(verticeDestino.getGrau() + 1);
        }

        arestasGrafo.add(aresta);

        if (!verticesGrafo.contains(verticeOrigem))
            verticesGrafo.add(verticeOrigem);
        if (!verticesGrafo.contains(verticeDestino))
            verticesGrafo.add(verticeDestino);
    }

    public void addVertice(Vertice v) {
        verticesGrafo.add(v);
    }

    @Override
    public String toString() {
        String grafoString = "Grafo: \n";

        for (Aresta a : arestasGrafo)
            grafoString += "(" + a.getVerticeOrigem().getRotulo() + ", " + a.getVerticeDestino().getRotulo() + ")\n";

        return grafoString;
    }

    public String printListaAdjacencia() {
        String adjacentesString = "";

        for (Vertice vertice : verticesGrafo) {
            adjacentesString += "\n" + vertice.getRotulo() + ": ";

            for (Vertice vAdj : vertice.getAdjacentes(this.isDirigido))
                adjacentesString += vAdj.getRotulo() + " - ";
        }

        return adjacentesString;
    }

    public boolean isNulo() {
        return arestasGrafo.isEmpty();
    }

    public boolean isSimples() {
        for (Aresta a : arestasGrafo) {
            if (a.getVerticeOrigem().equals(a.getVerticeDestino()))
                return false;

        }
        return true;
    }

    public int getOrdem() {
        return verticesGrafo.size();
    }

    public int getTamanho() {
        return arestasGrafo.size();
    }

    public Vertice getVerticePorRotulo(String rotuloVertice) {
        for (Vertice vertice : this.verticesGrafo)
            if (vertice.getRotulo().equals(rotuloVertice))
                return vertice;
        return null;
    }

    public Aresta getArestaPorRotulo(String rotuloAresta) {
        for (Aresta aresta : this.arestasGrafo)
            if (aresta.getRotulo().equals(rotuloAresta))
                return aresta;
        return null;
    }

    public void buildGrafo(int ordem, int tamanho) {
        List<Vertice> vertices = new ArrayList<>();
        int randIndexV1, randIndexV2, randValue;

        for (int i = 0; i <= ordem; i++)
            vertices.add(new Vertice("v" + i));

        for (int i = 0; i <= tamanho; i++) {
            Random rand = new Random();
            randIndexV1 = rand.nextInt((ordem - 1) + 1) + 1;
            randIndexV2 = rand.nextInt((ordem - 1) + 1) + 1;
            randValue = rand.nextInt((1000 - 1) + 1) + 1;

            this.addAresta("aresta" + i, randValue, vertices.get(randIndexV1), vertices.get(randIndexV2));
        }
    }

    public int[][] getMatrizAdjacencia() {
        return matrizAdjacencia;
    }

    public void setMatrizAdjacencia(int[][] matrizAdjacencia) {
        this.matrizAdjacencia = matrizAdjacencia;
    }

    public List<Vertice> getVertices() {
        return verticesGrafo;
    }

    public void setVertices(List<Vertice> vertices) {
        this.verticesGrafo = vertices;
    }

    public List<Aresta> getArestas() {
        return arestasGrafo;
    }

    public void setArestas(List<Aresta> arestas) {
        this.arestasGrafo = arestas;
    }

    public boolean isDirigido() {
        return isDirigido;
    }

    public void setIsDirigido(boolean isDirigido) {
        this.isDirigido = isDirigido;
    }
}