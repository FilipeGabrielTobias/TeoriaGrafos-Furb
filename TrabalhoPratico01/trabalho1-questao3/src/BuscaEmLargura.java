import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Filipe Gabriel Tobias
 */
public class BuscaEmLargura {
    private static final Vertice NIL = new Vertice("nil");
    private static final int INFINITO = Integer.MAX_VALUE;
    private final Queue<Vertice> q = new LinkedList<>();
    private final List<Vertice> arvore = new ArrayList<>();
    private String paiMatriz = "", dMatriz = "", cabecalhoMatriz = "";

    public void executaBuscaEmLargura(Grafo g, Vertice s) {
        for (Vertice v : g.getVertices()) {
            v.setStatus("BRANCO");
            v.setPai(NIL);
            v.setDistancia(INFINITO);
        }

        s.setDistancia(0);
        s.setStatus("CINZA");
        q.add(s);

        while (!q.isEmpty()) {
            Vertice u = q.poll();
            arvore.add(u);

            for (Vertice v : u.getAdjacentes(g.isDirigido())) {
                if (v.getStatus().equals("BRANCO")) {
                    q.add(v);
                    v.setStatus("CINZA");
                    v.setPai(u);
                    v.setDistancia(u.getDistancia() + 1);
                }
            }

            u.setStatus("PRETO");
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

                if (v.getDistancia() == (long) v.getDistancia())
                    dMatriz += String.format("%d", (long) v.getDistancia()) + "\t";
                else
                    dMatriz += String.format("%s", v.getDistancia()) + "\t";
            }
        }
    }

    public void printMatrizRoteamento() {
        System.out.println("\n\t" + cabecalhoMatriz);
        System.out.println("π:\t" + paiMatriz);
        System.out.println("d:\t" + dMatriz + "\n");
    }

    public String getMatrizRoteamento() {
        return "\nBusca em Largura - Matriz de Roteamento" +
                "\n\t" + cabecalhoMatriz +
                "\nπ:\t" + paiMatriz +
                "\nd:\t" + dMatriz + "\n";
    }
}