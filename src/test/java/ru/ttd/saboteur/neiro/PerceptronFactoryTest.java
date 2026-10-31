package ru.ttd.saboteur.neiro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ttd.saboteur.neiro.activation.ActivationFunction;
import ru.ttd.saboteur.neiro.activation.PerceptronFactory;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PerceptronFactoryTest {
    PerceptronFactory perceptronFactory;
    int countSignals = 4;
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
        when(weightGenerator.getRandomValue()).thenReturn(1.0);

        Perceptron perceptron = perceptronFactory.createPerceptron();
        perceptron.calculate(dataset);
    }
}
