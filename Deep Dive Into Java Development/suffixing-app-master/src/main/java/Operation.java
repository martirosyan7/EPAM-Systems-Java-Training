import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;

public class Operation {


    Properties properties = new Properties();


    public File fileCopy(File file, String suffix) throws IOException {
        String path = file.getPath();
        String newPath = path + suffix;
        String[] split = path.split("\\.");

        if (split.length == 2) {
            newPath = split[0] + suffix + "." + split[1];
        }
        File destinationFile = new File(newPath);
        Files.copy(file.toPath(), destinationFile.toPath());
        return destinationFile;
    }
    public File fileMove(File file, String suffix) throws IOException {
        String path = file.getPath();
        String newPath = path + suffix;
        String[] split = path.split("\\.");

        if (split.length == 2) {
            newPath = split[0] + suffix + "." + split[1];
        }
        File destinationFile = new File(newPath);
        Files.copy(file.toPath(), destinationFile.toPath());
        file.delete();
        return destinationFile;
    }

}
