public class Vertice {
    private int g;
    private int f;
    private int h;
    private int linha;
    private int coluna;
    private boolean ehBloqueado;
    private Vertice pai;

    public Vertice(int linha, int coluna) {
        super();
        this.linha = linha;
        this.coluna = coluna;
    }

    public void calcularHeuristica(Vertice verticeFinal) {
        this.h = Math.abs(verticeFinal.getLinha() - getLinha()) + Math.abs(verticeFinal.getColuna() - getColuna());
    }

    public void setVerticeData(Vertice verticeAtual, int custo) {
        int gCusto = verticeAtual.getG() + custo;
        setPai(verticeAtual);
        setG(gCusto);
        calcularCustoFinal();
    }

    public boolean verificaMelhorCaminho(Vertice verticeAtual, int custo) {
        int gCost = verticeAtual.getG() + custo;
        if (gCost < getG()) {
            setVerticeData(verticeAtual, custo);
            return true;
        }
        return false;
    }

    private void calcularCustoFinal() {
        int custoFinal = getG() + getH();
        setF(custoFinal);
    }

    @Override
    public boolean equals(Object arg0) {
        Vertice other = (Vertice) arg0;
        return this.getLinha() == other.getLinha() && this.getColuna() == other.getColuna();
    }

    @Override
    public String toString() {
        return "Node [row=" + linha + ", col=" + coluna + "]";
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public Vertice getPai() {
        return pai;
    }

    public void setPai(Vertice pai) {
        this.pai = pai;
    }

    public boolean isEhBloqueado() {
        return ehBloqueado;
    }

    public void setEhBloqueado(boolean isBlock) {
        this.ehBloqueado = isBlock;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }
}