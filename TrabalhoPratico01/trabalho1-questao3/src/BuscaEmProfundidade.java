/**
 * @author Filipe Gabriel Tobias
 */
public class BuscaEmProfundidade {
    private static final Vertice NIL = new Vertice("nil");
    private static final int INFINITO = Integer.MAX_VALUE;
    private String paiMatriz = "", dMatriz = "", cabecalhoMatriz = "";
    private int tempo;

    public void executaBuscaEmProfundidade(Grafo g) {
        for (Vertice v : g.getVertices()) {
            v.setStatus("BRANCO");
            v.setPai(NIL);
        }

        this.tempo = 1;

        for (Vertice v : g.getVertices()) {
            if (v.getStatus().equals("BRANCO")) {
                visit(v);
            }
        }

        setMatrizRoteamento(g);
    }

    public void setMatrizRoteamento(Grafo grafo) {
        cabecalhoMatriz = "";
        paiMatriz = "";
        dMatriz = "";

        for (Vertice v : grafo.getVertices()) {
            if (v.getDistancia() == INFINITO) {
                cabecalhoMatriz += v.getRotulo() + "\t";
                dMatriz += "- \t";
                paiMatriz += "- \t";
            } else {
                cabecalhoMatriz += v.getRotulo() + "\t";
                paiMatriz += v.getPai().getRotulo() + "\t";
                dMatriz +=  v.getAbertura() + "/" + v.getFechamento() + "\t";
            }
        }
    }

    private void visit(Vertice u) {
        u.setStatus("CINZA");
        u.setAbertura(tempo++);

        for (Vertice v : u.getAdjacentes(false)) {
            if (v.getStatus().equals("BRANCO")) {
                v.setPai(u);
                visit(v);
            }
        }

        u.setStatus("PRETO");
        u.setFechamento(tempo++);

    }

    public void printMatrizRoteamento() {
        System.out.println("\n\t" + cabecalhoMatriz);
        System.out.println("π:\t" + paiMatriz);
        System.out.println("d:\t" + dMatriz);
    }

    public String getMatrizRoteamento() {
        return "\nBusca em Profundidade - Matriz de Roteamento" +
                "\n\t" + cabecalhoMatriz +
                "\nπ:\t" + paiMatriz +
                "\nd:\t" + dMatriz + "\n";
    }
}