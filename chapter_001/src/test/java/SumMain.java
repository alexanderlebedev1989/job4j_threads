import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SumMain {
    @Test

    public void operation() {
        int a = 3;
        int b = 4;
        int expected = 7;
        int result = Main.sum(a, b);
        assertThat(result, is(expected));
    }
}
