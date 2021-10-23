import java.util.*;

public class AStar {
    private static int CUSTO_PADRAO = 1;
    private int custo;
    private Vertice[][] areaBusca;
    private PriorityQueue<Vertice> verticesAbertos;
    private Set<Vertice> verticesFechados;
    private Vertice verticeInicial;
    private Vertice verticeFinal;

    public AStar(int linhas, int colunas, Vertice verticeInicial, Vertice verticeFinal, int custo) {
        this.custo = custo;
        setVerticeInicial(verticeInicial);
        setVerticeFinal(verticeFinal);
        this.areaBusca = new Vertice[linhas][colunas];
        this.verticesAbertos = new PriorityQueue<Vertice>(new Comparator<Vertice>() {
            @Override
            public int compare(Vertice vertice0, Vertice vertice1) {
                return Integer.compare(vertice0.getF(), vertice1.getF());
            }
        });
        setVertices();
        this.verticesFechados = new HashSet<>();
    }

    public AStar(int linhas, int colunas, Vertice verticeInicial, Vertice verticeFinal) {
        this(linhas, colunas, verticeInicial, verticeFinal, CUSTO_PADRAO);
    }

    private void setVertices() {
        for (int i = 0; i < areaBusca.length; i++) {
            for (int j = 0; j < areaBusca[0].length; j++) {
                Vertice vertice = new Vertice(i, j);
                vertice.calcularHeuristica(getVerticeFinal());
                this.areaBusca[i][j] = vertice;
            }
        }
    }

    public void setBlocks(int[][] blocksArray) {
        for (int i = 0; i < blocksArray.length; i++) {
            int row = blocksArray[i][0];
            int col = blocksArray[i][1];
            setBloqueio(row, col);
        }
    }

    public List<Vertice> findPath() {
        verticesAbertos.add(verticeInicial);
        while (!ehVazio(verticesAbertos)) {
            Vertice currentVertice = verticesAbertos.poll();
            verticesFechados.add(currentVertice);
            if (ehVerticeFinal(currentVertice)) {
                return getPath(currentVertice);
            } else {
                addAdjacentNodes(currentVertice);
            }
        }
        return new ArrayList<Vertice>();
    }

    private List<Vertice> getPath(Vertice currentVertice) {
        List<Vertice> path = new ArrayList<Vertice>();
        path.add(currentVertice);
        Vertice parent;
        while ((parent = currentVertice.getPai()) != null) {
            path.add(0, parent);
            currentVertice = parent;
        }
        return path;
    }

    private void addAdjacentNodes(Vertice verticeAtual) {
        addAdjacentUpperRow(verticeAtual);
        addAdjacentMiddleRow(verticeAtual);
        addAdjacentLowerRow(verticeAtual);
    }

    private void addAdjacentLowerRow(Vertice verticeAtual) {
        int row = verticeAtual.getLinha();
        int col = verticeAtual.getColuna();
        int lowerRow = row + 1;
        if (lowerRow < getAreaBusca().length) {
            verificaVertice(verticeAtual, col, lowerRow, getCusto());
        }
    }

    private void addAdjacentMiddleRow(Vertice verticeAtual) {
        int row = verticeAtual.getLinha();
        int col = verticeAtual.getColuna();
        if (col - 1 >= 0) {
            verificaVertice(verticeAtual, col - 1, row, getCusto());
        }
        if (col + 1 < getAreaBusca()[0].length) {
            verificaVertice(verticeAtual, col + 1, row, getCusto());
        }
    }

    private void addAdjacentUpperRow(Vertice verticeAtual) {
        int linha = verticeAtual.getLinha();
        int coluna = verticeAtual.getColuna();
        int fileiraSuperior = linha - 1;
        if (fileiraSuperior >= 0) {
            verificaVertice(verticeAtual, coluna, fileiraSuperior, getCusto());
        }
    }

    private void verificaVertice(Vertice verticeAtual, int coluna, int linha, int custo) {
        Vertice verticeAdjacente = getAreaBusca()[linha][coluna];
        if (!verticeAdjacente.isEhBloqueado() && !getVerticesFechados().contains(verticeAdjacente)) {
            if (!getVerticesAbertos().contains(verticeAdjacente)) {
                verticeAdjacente.setVerticeData(verticeAtual, custo);
                getVerticesAbertos().add(verticeAdjacente);
            } else {
                boolean mudado = verticeAdjacente.verificaMelhorCaminho(verticeAtual, custo);
                if (mudado) {
                    getVerticesAbertos().remove(verticeAdjacente);
                    getVerticesAbertos().add(verticeAdjacente);
                }
            }
        }
    }

    private boolean ehVerticeFinal(Vertice verticeAtual) {
        return verticeAtual.equals(verticeFinal);
    }

    private boolean ehVazio(PriorityQueue<Vertice> verticesAbertos) {
        return verticesAbertos.size() == 0;
    }

    private void setBloqueio(int linha, int coluna) {
        this.areaBusca[linha][coluna].setEhBloqueado(true);
    }

    public Vertice getVerticeInicial() {
        return verticeInicial;
    }

    public void setVerticeInicial(Vertice verticeInicial) {
        this.verticeInicial = verticeInicial;
    }

    public Vertice getVerticeFinal() {
        return verticeFinal;
    }

    public void setVerticeFinal(Vertice verticeFinal) {
        this.verticeFinal = verticeFinal;
    }

    public Vertice[][] getAreaBusca() {
        return areaBusca;
    }

    public void setAreaBusca(Vertice[][] areaBusca) {
        this.areaBusca = areaBusca;
    }

    public PriorityQueue<Vertice> getVerticesAbertos() {
        return verticesAbertos;
    }

    public void setVerticesAbertos(PriorityQueue<Vertice> verticesAbertos) {
        this.verticesAbertos = verticesAbertos;
    }

    public Set<Vertice> getVerticesFechados() {
        return verticesFechados;
    }

    public void setVerticesFechados(Set<Vertice> verticesFechados) {
        this.verticesFechados = verticesFechados;
    }

    public int getCusto() {
        return custo;
    }

    public void setCusto(int custo) {
        this.custo = custo;
    }
}