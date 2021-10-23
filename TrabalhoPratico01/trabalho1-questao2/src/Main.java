import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Node initialNode = new Node(6, 0);
        Node finalNode = new Node(2, 5);
        int rows = 7;
        int cols = 7;
        AStar aStar = new AStar(rows, cols, initialNode, finalNode);
        int[][] blocksArray = new int[][]{
                {2, 1}, {2, 2}, {4, 1}, {4, 2},
                {6, 1}, {6, 2}, {1, 4}, {1, 5},
                {2, 4}, {3, 4}, {4, 4}, {4, 5}
        };
        aStar.setBlocks(blocksArray);
        List<Node> path = aStar.findPath();
        System.out.println("Caminho:");
        for (int i=0; i < path.size(); i++) {
            System.out.print("(" + path.get(i).getRow() + "," + path.get(i).getCol() + ")" + ((i != path.size()-1) ? " -> " : "\n"));
        }
        System.out.println("Custo Total:");
        List<Integer> collect = path.stream().map(Node::getF).collect(Collectors.toList());
        System.out.println(collect.stream().reduce(0, Integer::sum));
    }
}
