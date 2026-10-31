package ru.ttd.saboteur.neiro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ttd.saboteur.neiro.activation.PerceptronFactory;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PerceptronLayerTest {
    PerceptronLayer perceptronLayer;
    int countPerceptron = 3;
    @Mock
    PerceptronFactory perceptronFactory;
    @Mock
    Perceptron perceptron;
    double[] dataSet = {1, 2, 1, 2, 1, 2};

    @BeforeEach
    void before() {
        perceptronLayer = new PerceptronLayer(countPerceptron, perceptronFactory);
    }

    @Test
    void calculateTest() {


        when(perceptronFactory.createPerceptron()).thenReturn(perceptron);
        when(perceptron.calculate(dataSet)).thenReturn(1.0);

        double[] result = perceptronLayer.calculate(dataSet);
        verify(perceptronFactory, times(countPerceptron)).createPerceptron();

        assertArrayEquals(new double[]{1.0, 1.0, 1.0}, result);
    }

    @Test
    void updateWeights(){
        double[] error = {1,2,3};
        double learningRate = 0.01;
        when(perceptronFactory.createPerceptron()).thenReturn(perceptron);

        perceptronLayer.updateWeights(dataSet,error, learningRate);
        verify(perceptron).updateWeights(dataSet,error[0],learningRate);
        verify(perceptron).updateWeights(dataSet,error[1],learningRate);
        verify(perceptron).updateWeights(dataSet,error[2],learningRate);

    }
}
