package ru.ttd.saboteur.neiro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

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

    public static Stream<double[]> providedInputs() {
        return Stream.of(
                new double[]{1,1},
                new double[]{2,2}
        );
    }

    @BeforeEach
    void before(){
        perceptron = new Perceptron(weightGenerator, activationFunction);
    }

    @Test
    void createInputTest(){
        perceptron.createSignal(COUNT_SIGNALS);
        assertEquals(COUNT_SIGNALS, perceptron.getWeights().length);
    }

    @ParameterizedTest
    @MethodSource("providedInputs")
    void calculateTest(double[] inputs){
        when(weightGenerator.getRandomValue()).thenReturn(1.0);
        when(activationFunction.activate(anyDouble())).thenReturn(1.0);
        perceptron.createSignal(2);

        assertEquals(1,perceptron.calculate(inputs));
        verify(activationFunction, times(1)).activate(anyDouble());
    }

    @Test
    void initWeightRandomValue(){
        when(weightGenerator.getRandomValue()).thenReturn(1.0);
        perceptron.createSignal(COUNT_SIGNALS);
        verify(weightGenerator,times(COUNT_SIGNALS)).getRandomValue();
    }
}
