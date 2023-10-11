import hexlet.code.App;
import hexlet.code.Differ;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class DifferTest {
    private String result;
    private Differ diff;

    @BeforeEach
    void setUp() {
        result =
                "{\n"
                        + "  - follow: false\n"
                        + "    host: hexlet.io\n"
                        + "  - proxy: 123.234.53.22\n"
                        + "  - timeout: 50\n"
                        + "  + timeout: 20\n"
                        + "  + verbose: true\n"
                        + "}";
        diff = new Differ();
    }

    @Test
    void generateTestJson() throws Exception {
        assertThat(diff.generate("filepath1.json", "filepath2.json", "json")).isEqualTo(result);
    }

    void generateTestYaml() throws Exception {
        assertThat(diff.generate("filepath1.json", "filepath2.json", "json")).isEqualTo(result);
    }

    @Test
    public void testAppMain() {
        // Assuming the main method in App class just delegates to CommandLine.execute(args)
        String[] args = {"filepath1.json", "filepath2.json"};
        Assertions.assertDoesNotThrow(() -> App.main(args));
    }

    @Test
    public void testGenerate() throws Exception {
        String filePath1 = "filepath1.json";
        String filePath2 = "filepath2.json";
        String format = "json";

        String actual = Differ.generate(filePath1, filePath2, format);

        Assertions.assertEquals(result, actual);
    }
}
