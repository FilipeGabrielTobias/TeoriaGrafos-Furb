import java.util.List;

public class Main {
    public static void main(String[] args) {
        Vertice initialVertice = new Vertice(6, 0);
        Vertice finalVertice = new Vertice(2, 5);
        int rows = 7;
        int cols = 7;
        AStar aStar = new AStar(rows, cols, initialVertice, finalVertice);
        int[][] blocksArray = new int[][]{
                {2, 1}, {2, 2}, {4, 1}, {4, 2},
                {6, 1}, {6, 2}, {1, 4}, {1, 5},
                {2, 4}, {3, 4}, {4, 4}, {4, 5}
        };
        aStar.setBlocks(blocksArray);
        List<Vertice> caminho = aStar.findPath();
        System.out.println("Caminho:");
        for (int i=0; i < caminho.size(); i++) {
            System.out.print("(" + caminho.get(i).getLinha() + "," + caminho.get(i).getColuna() + ")" + ((i != caminho.size()-1) ? " -> " : "\n"));
        }
        System.out.println("Custo Total: " + caminho.get(caminho.size()-1).getF());
    }
}
