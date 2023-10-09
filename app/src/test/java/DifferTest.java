import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;


public class DifferTest {
    @Test
    void generateTestJson() throws IOException {
        Differ diff = new Differ();
        String result =
                "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";

        assertThat(diff.generate("filepath1.json", "filepath2.json")).isEqualTo(result);
    }
}
