package ru.ttd.saboteur.neiro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PerceptronLayerTest {
    PerceptronLayer perceptronLayer;

    @BeforeEach
    void before(){
        perceptronLayer = new PercptronLayer(countPerceptron,countSignal,activationFunction,weightGenerator);
    }

    @Test
    void calculateTest(){
       double[] result =  perceptronLayer.calculate(dataSet);
    }
}
