package ru.ttd.saboteur.neiro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PerceptronTest {
    Perceptrom perceptron = new Perceptrom();

    @BeforeEach
    void before(){
        perceptron.createSignal(10);
    }

    @Test
    void createInputTest(){
        int countSignals = 10;
        assertEquals(countSignals, perceptron.getSignals().length);
        assertEquals(countSignals, perceptron.getWeights().length);
    }

    @Test
    void calculateTest(){
        assertEquals(0,perceptron.calculate());
    }
}
