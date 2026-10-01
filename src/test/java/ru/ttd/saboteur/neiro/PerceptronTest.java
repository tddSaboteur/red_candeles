package ru.ttd.saboteur.neiro;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PerceptronTest {
    Perceptrom perceptron = new Perceptrom();
    @Test
    void createInputTest(){
        int countSignals = 10;
        perceptron.createSignal(10);
        assertEquals(countSignals, perceptron.getSignals().length);
        assertEquals(countSignals, perceptron.getWeights().length);
    }

    @Test
    void calculateTest(){
        perceptron.calculate();
    }
}
