import hexlet.code.Differ;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;


public class DifferTest {

    private static String resultStylish;
    private static String resultPlain;
    private static String resultJson;
    private String result;
    private Differ diff;

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }

    private static String getFixtureContent(String fileName) throws Exception {
        Path filePath = getFixturePath(fileName);
        return Files.readString(filePath).trim();
    }

    @BeforeAll
    public static void beforeAll() throws Exception {
        resultStylish = getFixtureContent("result_stylish.txt");
        resultPlain = getFixtureContent("result_plain.txt");
        resultJson = getFixtureContent("result_json.json");
    }
    @BeforeAll
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
        assertThat(diff.generate("filepath1.json", "filepath2.json", "Stylish")).isEqualTo(result);
    }
    @Test
    void generateTestYaml() throws Exception {
        assertThat(diff.generate("filepath1.yaml", "filepath2.yaml", "STYLISH")).isEqualTo(result);
    }

    @Test
    public void testGenerate() throws Exception {
        String filePath1 = "filepath1.json";
        String filePath2 = "filepath2.json";
        String format = "stylish";
        assertThat(Differ.generate(filePath1, filePath2))
                .isEqualTo(result);

        String actual = Differ.generate(filePath1, filePath2, format);

        Assertions.assertEquals(result, actual);
    }

    @Test
    public void testPlain() throws Exception {
        String filePath1 = "file1.json";
        String filePath2 = "file2.json";
        String format = "stylish";

        String actual = Differ.generate(filePath1, filePath2, format);

        Assertions.assertEquals(resultStylish, actual);
    }

    @Test
    public void testPlain2() throws Exception {
        String filePath1 = "file1.json";
        String filePath2 = "file2.json";
        String format = "plain";

        String actual = Differ.generate(filePath1, filePath2, format);

        Assertions.assertEquals(resultPlain, actual);
    }
}
