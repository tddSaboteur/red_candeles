package ru.ttd.saboteur.neiro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class PerceptronFactoryTest {
    PerceptronFactory percepronFactory;

    @BeforeEach
    void before() {
        percepronFactory = new PerceptronFactory(countSignals, weightGenerator, activationFunction);
    }

    @Test
    void createTest() {
        double[] dataset= {1,2,3,5};

        Perceptron perceptron = percepronFactory.createPerceptron();
        perceptron.calculate(dataset);
    }
}
