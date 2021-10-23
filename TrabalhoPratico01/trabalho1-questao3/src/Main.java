import java.io.*;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Filipe Gabriel Tobias
 */
public class Main {
    public static void main(String[] args) throws IOException {
        FileReaderFactory fileReaderFactory = new FileReaderFactory();
        BufferedReader bufferedReader = fileReaderFactory.read();

        String stringBuffer = bufferedReader.lines().collect(Collectors.joining("\n"));
        String[] split = stringBuffer.split("\n");
        Grafo grafo = new Grafo(false);
        List<String> collect = Arrays.stream(split).skip(1).collect(Collectors.toList());
        collect.forEach(s -> {
            String[] s1 = s.split(" ");
            if (Objects.isNull(grafo.getVerticePorRotulo(s1[0]))) {
                grafo.addVertice(new Vertice(s1[0]));
            }
            if (Objects.isNull(grafo.getVerticePorRotulo(s1[1]))) {
                grafo.addVertice(new Vertice(s1[1]));
            }
            grafo.addAresta(s1[0] + " - " + s1[1], 0, grafo.getVerticePorRotulo(s1[0]), grafo.getVerticePorRotulo(s1[1]));
        });

        // Busca em Largura
        BuscaEmLargura bfs = new BuscaEmLargura();
        System.out.println(LocalTime.now());
        bfs.executaBuscaEmLargura(grafo, grafo.getVerticePorRotulo("1"));
        System.out.println(LocalTime.now());

        // Busca em Profundidade
        BuscaEmProfundidade dfs = new BuscaEmProfundidade();
        System.out.println(LocalTime.now());
        dfs.executaBuscaEmProfundidade(grafo);
        System.out.println(LocalTime.now());
        dfs.printMatrizRoteamento();

        // Grava no Arquvio
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\filip_5ocopck\\OneDrive\\Documentos\\temp\\saida.in"));
        bufferedWriter.write(bfs.getMatrizRoteamento());
        bufferedWriter.newLine();
        bufferedWriter.write(dfs.getMatrizRoteamento());
        bufferedWriter.newLine();
        bufferedWriter.close();
    }
}
