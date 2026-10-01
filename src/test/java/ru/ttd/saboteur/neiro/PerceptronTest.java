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

    @Test
    void initWeightRandomValue(){
        final int[] count = {0};
        Perceptron perceptron = new Perceptron(){
            @Override
            double getRandomValue(){
                count[0] +=1;
                return 1.0;
            }
        };
        perceptron.createSignal(COUNT_SIGNALS);
        assertEquals(count[0],COUNT_SIGNALS);
    }
}
