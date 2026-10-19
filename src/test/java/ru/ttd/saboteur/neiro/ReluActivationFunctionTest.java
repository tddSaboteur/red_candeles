package ru.ttd.saboteur.neiro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.ttd.saboteur.neiro.activation.ReluActivationFunction;

import static org.junit.jupiter.api.Assertions.*;

class ReluActivationFunctionTest {

    ReluActivationFunction reluActivationFunction;

    @BeforeEach
    void setUp() {
        reluActivationFunction = new ReluActivationFunction();
    }
    @Test
    void activate_inputIsNegativeNumber_mustReturnZero(){
        double result = reluActivationFunction.activate(-1);
        assertEquals(0,result);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.5, 1, 5})
    void activate_inputAnyPositiveNumber_shouldReturnThatNumber(double x){
        double result = reluActivationFunction.activate(x);
        assertEquals(x,result);
    }

    @Test
    void derivative_inputPositive_shouldReturnOne(){
        double expected = 1;
        double input = 0.435;
        assertEquals(expected, reluActivationFunction.derivative(input));
    }
    @Test
    void derivative_inputNegative_shouldReturnZero(){
        double expected = 0;
        double input = - 0.435;
        assertEquals(expected, reluActivationFunction.derivative(input));
    }
}