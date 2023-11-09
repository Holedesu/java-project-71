import hexlet.code.Differ;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;


public final class DifferTest {

    private static String resultStylish;
    private static String resultPlain;
    private static String result;
    private static Differ diff;

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
        String filePath1 = getFixturePath("filepath1.json").toString();
        String filePath2 = getFixturePath("filepath2.json").toString();

        assertThat(Differ.generate(filePath1, filePath2)).isEqualTo(result);
    }
    @Test
    void generateTestYaml() throws Exception {
        String filePath1 = getFixturePath("filepath1.yaml").toString();
        String filePath2 = getFixturePath("filepath2.yaml").toString();

        assertThat(Differ.generate(filePath1, filePath2)).isEqualTo(result);
    }

    @Test
    public void testGenerate() throws Exception {
        String filePath1 = getFixturePath("filepath1.yaml").toString();
        String filePath2 = getFixturePath("filepath2.yaml").toString();
        String format = "stylish";

        String actual = Differ.generate(filePath1, filePath2, format);

        Assertions.assertEquals(result, actual);
    }

    @Test
    public void testStylish() throws Exception {
        String filePath1 = getFixturePath("file1.json").toString();
        String filePath2 = getFixturePath("file2.json").toString();
        String format = "stylish";

        String actual = Differ.generate(filePath1, filePath2, format);

        Assertions.assertEquals(resultStylish, actual);
    }

    @Test
    public void testPlain() throws Exception {
        String filePath1 = getFixturePath("file1.yaml").toString();
        String filePath2 = getFixturePath("file2.yaml").toString();
        String format = "plain";

        String actual = Differ.generate(filePath1, filePath2, format);

        Assertions.assertEquals(resultPlain, actual);
    }
}
