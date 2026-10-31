package ru.ttd.saboteur.neiro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ttd.saboteur.neiro.activation.ActivationFunction;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PerceptronTest {
    public static final int COUNT_SIGNALS = 10;

    @Mock
    WeightGenerator weightGenerator;
    @Mock
    ActivationFunction activationFunction;

    Perceptron perceptron;

    public static Stream<Arguments> providedInputs() {
        return Stream.of(
                Arguments.of(new double[]{1, 1}, 3),
                Arguments.of(new double[]{2, 2}, 5)
        );
    }

    @BeforeEach
    void before() {
        perceptron = new Perceptron(weightGenerator, activationFunction);
    }

    @Test
    void createInputTest() {
        perceptron.createSignal(COUNT_SIGNALS);
        assertEquals(COUNT_SIGNALS + 1, perceptron.getWeights().length);
    }

    @ParameterizedTest
    @MethodSource("providedInputs")
    void calculateTest(double[] inputs, double expected) {
        when(weightGenerator.getRandomValue()).thenReturn(1.0);
        when(activationFunction.activate(expected)).thenReturn(1.0);
        perceptron.createSignal(2);

        assertEquals(1, perceptron.calculate(inputs));
        verify(activationFunction, times(1)).activate(anyDouble());
    }

    @Test
    void initWeightRandomValue() {
        when(weightGenerator.getRandomValue()).thenReturn(1.0);
        perceptron.createSignal(COUNT_SIGNALS);
        verify(weightGenerator, times(COUNT_SIGNALS+1)).getRandomValue();
    }

    @ParameterizedTest
    @CsvSource({
            "0.5, 0.5, 0.2 , 1.0, 1.0, -0.5, 0.1, 0.475, 0.475, 0.17500000000000002, 0.5",
            //тест разные входы и веса, отрицательная ошибка
            "0.3, 0.7, 0.1, 2.0, 4.0, -0.5, 0.1, 0.25, 0.60,   0.07500000000000001,  0.5",
            //нулевой learning rate веса не должны меняться
            "0.5, 0.5, 0.3, 1.0, 1.0, -0.5, 0.0, 0.5,  0.5,    0.3,    0.5",
            //нулевой вход конкретный вес не меняется
            "0.4, 0.6, 0.4, 0.0, 5.0, 0.4,  0.2, 0.4,  0.8,    0.44000000000000006,   0.5",
            //нулевая ошибка
            "0.2, 0.8, 0.5, 9.0, 2.5, 0.0,  0.1, 0.2,  0.8,    0.5,0.5"
    })
    void updateWeightsTest(double startWeight1,
                           double startWeight2,
                           double startBias,
                           double in1,
                           double in2,
                           double error,
                           double learningRate,
                           double expectedW1,
                           double expectedW2,
                           double expectedBias,
                           double deriv) {
        double[] inputs = {in1, in2};

        when(weightGenerator.getRandomValue()).thenReturn(startBias,startWeight1, startWeight2);
        when(activationFunction.derivative(anyDouble())).thenReturn(deriv);

        perceptron.createSignal(inputs.length);

        perceptron.updateWeights(inputs, error, learningRate);

        double[] expectedWeights = {expectedBias,expectedW1, expectedW2};
        assertArrayEquals(expectedWeights, perceptron.getWeights());
    }
}
