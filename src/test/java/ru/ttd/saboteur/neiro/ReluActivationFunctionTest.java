package ru.ttd.saboteur.neiro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReluActivationFunctionTest {

    ReluActivationFunction reluActivationFunction;

    @BeforeEach
    void setUp() {
        reluActivationFunction = new ReluActivationFunction();
    }
    @Test
    void activateNegative(){
        double result = reluActivationFunction.activate(-1);
        assertEquals(0,result);
    }
}