import java.io.File;
import java.io.FileReader;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) throws Exception {

        System.setProperty("java.util.logging.SimpleFormatter.format",
                "%4$s: %5$s%6$s%n");
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        FileReader fr = new FileReader(args[0]);
        Properties properties = new Properties();
        properties.load(fr);
        Operation operation = new Operation();
        logger.setLevel(Level.ALL);

        String files = properties.getProperty("files", null);
        String mode = properties.getProperty("mode");
        mode = mode.toUpperCase();
        String suffix = properties.getProperty("suffix", null);


        if (files == null || files.length() == 0) {
            logger.warning("No files are configured to be copied/moved");
            return;
        }
        if (suffix == null) {
            logger.severe("No suffix is configured");
            return;
        }


        for (String file: files.split(":")) {
            File file1 = null;
            File file2 = null;
            file1 = new File(file);
            if (!file1.exists()) {
                logger.severe("No such file: " + file);
                continue;
            }
            String[] split = file.split("\\.");
            String newPath = file + suffix;

            if (split.length == 2) {

                newPath = split[0] + suffix + "." + split[1];
            }
            if (mode.equals("MOVE")) {

                file2 = operation.fileMove(file1, suffix);
                logger.info(file + " => " + newPath);
            } else if (mode.equals("COPY")) {
                file2 = operation.fileCopy(file1, suffix);
                logger.info(file + " -> " + newPath);
            } else logger.severe("Mode is not recognized: " + mode);
        }
    }
}
