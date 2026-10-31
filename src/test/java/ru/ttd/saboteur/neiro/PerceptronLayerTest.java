package ru.ttd.saboteur.neiro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.ttd.saboteur.neiro.activation.ActivationFunction;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PerceptronLayerTest {
    PerceptronLayer perceptronLayer;
    int countPerceptron = 3;
    int countSignal = 3;
    @Mock
    ActivationFunction activationFunction;
    @Mock
    WeightGenerator weightGenerator;

    @BeforeEach
    void before() {
        perceptronLayer = new PerceptronLayer(countPerceptron, countSignal, activationFunction, weightGenerator);
    }

    @Test
    void calculateTest() {
        double[][] dataSet = {
            {1,1,1},
        };
        double[] result = perceptronLayer.calculate(dataSet);
        assertNotNull(result);

    }
}
