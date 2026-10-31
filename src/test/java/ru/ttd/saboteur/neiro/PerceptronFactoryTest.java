package ru.ttd.saboteur.neiro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.ttd.saboteur.neiro.activation.ActivationFunction;
import ru.ttd.saboteur.neiro.activation.PerceptronFactory;

public class PerceptronFactoryTest {
    PerceptronFactory perceptronFactory;
    int countSignals = 3;
    @Mock
    ActivationFunction activationFunction;
    @Mock
    WeightGenerator weightGenerator;
    @BeforeEach
    void before() {
        perceptronFactory = new PerceptronFactory(countSignals, weightGenerator, activationFunction);
    }

    @Test
    void createTest() {
        double[] dataset= {1,2,3,5};

        Perceptron perceptron = perceptronFactory.createPerceptron();
        perceptron.calculate(dataset);
    }
}
