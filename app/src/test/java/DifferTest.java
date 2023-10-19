import hexlet.code.App;
import hexlet.code.Differ;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class DifferTest {
    private String result;
    private Differ diff;

    private String plainResult;

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

        plainResult = "{\n" +
                "    chars1: [a, b, c]\n" +
                "  - chars2: [d, e, f]\n" +
                "  + chars2: false\n" +
                "  - checked: false\n" +
                "  + checked: true\n" +
                "  - default: null\n" +
                "  + default: [value1, value2]\n" +
                "  - id: 45\n" +
                "  + id: null\n" +
                "  - key1: value1\n" +
                "  + key2: value2\n" +
                "    numbers1: [1, 2, 3, 4]\n" +
                "  - numbers2: [2, 3, 4, 5]\n" +
                "  + numbers2: [22, 33, 44, 55]\n" +
                "  - numbers3: [3, 4, 5]\n" +
                "  + numbers4: [4, 5, 6]\n" +
                "  + obj1: {nestedKey=value, isNested=true}\n" +
                "  - setting1: Some value\n" +
                "  + setting1: Another value\n" +
                "  - setting2: 200\n" +
                "  + setting2: 300\n" +
                "  - setting3: true\n" +
                "  + setting3: none\n" +
                "}";

    }

    @Test
    void generateTestJson() throws Exception {
        assertThat(diff.generate("filepath1.json", "filepath2.json", "Stylish")).isEqualTo(result);
    }

    void generateTestYaml() throws Exception {
        assertThat(diff.generate("filepath1.json", "filepath2.json", "STYLISH")).isEqualTo(result);
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
        String format = "stylish";

        String actual = Differ.generate(filePath1, filePath2, format);

        Assertions.assertEquals(result, actual);
    }

//    @Test
//    public void testPlain() throws Exception {
//        String filePath1 = "file1.json";
//        String filePath2 = "file2.json";
//        String format = "plain";
//
//        String actual = Differ.generate(filePath1, filePath2, format);
//
//        Assertions.assertEquals(plainResult, actual);
//    }
}
