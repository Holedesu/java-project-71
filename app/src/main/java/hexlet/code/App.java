package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.util.concurrent.Callable;

public class App {
    public static void main(String[] args) {
        new CommandLine(new Gendiff()).execute(args);
    }
}

@Command(name = "gendiff", description = "Compares two configuration files and shows a difference.")
class Gendiff implements Callable<Integer> {
    @Parameters(index = "0", description = "path to first file")
    private String filePath1;
    @Parameters(index = "1", description = "path to second file")
    private String filePath2;
    @Option(names = {"-f", "--format"}, defaultValue = "stylish", description = "output format [default: stylish]")
    private String format;
    @Option(names = {"-h", "--help"}, description = "Show this help message and exit.")
    private String help;
    @Option(names = {"-V", "--version"}, description = "Print version information and exit.")
    private String version;

    @Override
    public Integer call() throws Exception {
        try {
            System.out.println(Differ.generate(filePath1, filePath2, format));
        } catch (IOException e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }
}
