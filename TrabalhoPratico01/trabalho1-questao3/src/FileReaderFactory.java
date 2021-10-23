import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 * @author Filipe Gabriel Tobias
 */
public class FileReaderFactory {

    private final FileSystem fileSystem = FileSystems.getDefault();
    private final String defaultPath = "c:\\temp";
    private final String FILE_NAME = "\\entrada.in";
    private Path path;

    public FileReaderFactory() {
        path = fileSystem.getPath(defaultPath + FILE_NAME);
    }

    public Path getPath() {
        return path;
    }

    public void setPath(String path, String fileName) {
        if (fileName.isEmpty()) {
            throw new IllegalArgumentException("Nome de arquivo inválido");
        }

        this.path = fileSystem.getPath(path + fileName);
    }

    public void setPath(String fileName) {
        if (fileName.isEmpty()) {
            throw new IllegalArgumentException("Nome de arquivo inválido");
        }

        this.path = fileSystem.getPath(defaultPath + fileName);
    }

    public BufferedReader read() {
        validarArquivoExistente();

        try {
            FileReader fileReader = new FileReader(path.toFile());
            return new BufferedReader(fileReader);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao efetuar leitura de arquivo: " + e);
        }
    }

    private void validarArquivoExistente() {
        if (!Files.exists(path)) {
            throw new RuntimeException("O arquivo " + FILE_NAME + " não existe no diretório " + defaultPath);
        }
    }
}
