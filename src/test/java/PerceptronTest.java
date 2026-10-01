import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PerceptronTest {
    Perceptrom perceptron = new Pecetron();
    @Test
    void createInputTest(){
        int countSignals = 10;
        perceptron.createSignal(10);
        assertEquals(perceptron.getSignals().size,countSignals);
    }
}
