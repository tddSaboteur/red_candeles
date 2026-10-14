package ru.ttd.saboteur.neiro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
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
    ReluActivationFunction activationFunction;

    Perceptron perceptron;

    public static Stream<Arguments> providedInputs() {
        return Stream.of(
                Arguments.of(new double[]{1,1},2),
                Arguments.of(new double[]{2,2},4)
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
    void calculateTest(double[] inputs,double expected){
        when(weightGenerator.getRandomValue()).thenReturn(1.0);
        when(activationFunction.activate(expected)).thenReturn(1.0);
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
    @Test
    void updateWeightsTest(){
        double learningRate = 0.1;
        double[] inputs = {1,1};
        double actual = 1;

        when(weightGenerator.getRandomValue()).thenReturn(0.5);
        when(activationFunction.activate(1*0.5+1*0.5)).thenReturn(0.5);
        when(activationFunction.derivative(1d)).thenReturn(0.5);

        perceptron.createSignal(inputs.length);
        double error = actual-perceptron.calculate(inputs);

        perceptron.updateWeights(inputs,error,learningRate,actual);
        double expected = 0.5 + (error * learningRate * 0.5 * 1);

        assertEquals(expected,perceptron.getWeights()[0]);
    }
}
