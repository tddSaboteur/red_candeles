package ru.ttd.saboteur.neiro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PerceptronTest {
    public static final int COUNT_SIGNALS = 10;

    @Mock
    WeightGenerator weightGenerator;
    Perceptron perceptron;

    @BeforeEach
    void before(){
        perceptron = new Perceptron(weightGenerator);
    }

    @Test
    void createInputTest(){
        perceptron.createSignal(COUNT_SIGNALS);
        assertEquals(COUNT_SIGNALS, perceptron.getSignals().length);
        assertEquals(COUNT_SIGNALS, perceptron.getWeights().length);
    }

    @Test
    void calculateTest(){
        assertEquals(0,perceptron.calculate());
    }

    @Test
    void initWeightRandomValue(){
        when(weightGenerator.getRandomValue()).thenReturn(1.0);
        perceptron.createSignal(COUNT_SIGNALS);
        verify(weightGenerator,times(COUNT_SIGNALS)).getRandomValue();
    }
}
