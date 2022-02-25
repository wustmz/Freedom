import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JvmTest {

    @Test
    public void test() {
        Runtime runtime = Runtime.getRuntime();
        int i = runtime.availableProcessors();
        long memory = runtime.freeMemory();
        System.out.println(memory);
    }
}
