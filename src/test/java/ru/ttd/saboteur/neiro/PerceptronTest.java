package ru.ttd.saboteur.neiro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PerceptronTest {
    public static final int COUNT_SIGNALS = 10;
    Perceptron perceptron = new Perceptron();

    @BeforeEach
    void before(){
        perceptron.createSignal(COUNT_SIGNALS);
    }

    @Test
    void createInputTest(){

        assertEquals(COUNT_SIGNALS, perceptron.getSignals().length);
        assertEquals(COUNT_SIGNALS, perceptron.getWeights().length);
    }

    @Test
    void calculateTest(){
        assertEquals(0,perceptron.calculate());
    }
}
