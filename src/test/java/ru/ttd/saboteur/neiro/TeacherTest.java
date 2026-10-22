package ru.ttd.saboteur.neiro;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ttd.saboteur.neiro.activation.ActivationFunction;
import ru.ttd.saboteur.neiro.activation.LeakyReluActivationFunction;
import ru.ttd.saboteur.neiro.activation.ReluActivationFunction;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TeacherTest {
    @Mock
    WeightGenerator weightGenerator;
    ActivationFunction reluActivationFunction = new ReluActivationFunction();

    @Test
    void trainSpike() {
        when(weightGenerator.getRandomValue()).thenReturn(0.5);
        Perceptron perceptron = new Perceptron(weightGenerator, reluActivationFunction);

        double[][] dataset = {
                {1, 1},
                {1, 0},
                {0, 1},
                {0, 0}
        };
        double[] actual = {1, 0, 0, 0};
        perceptron.createSignal(2);
        double learningRate = 0.01;
        int epoch = 10000;
        double accuaracy = 0.00001;

        for (int i = 0; i < epoch; i++) {
            System.out.println("epoch:" + i);
            double symError = 0;
            for (int j = 0; j < dataset.length; j++) {
                double[] data = dataset[j];
                double result = perceptron.calculate(data);
                double error = actual[j] - result;
                symError += error;


                perceptron.updateWeights(data, error, learningRate);
            }
            double meanError = (symError/4);
            System.out.println("ERROR:" + meanError);
            if (meanError < accuaracy && meanError > accuaracy*-1){
                System.out.println("Final training! Epoch"+i);
                break;
            }
        }

        for (int i = 0; i < dataset.length; i++) {
            double[] data = dataset[i];
            System.out.println("index:" + i + " result:" + perceptron.calculate(data));
        }
    }
    /*

    bias = 1; e 10000 lr = 0.01;af= RL
      index:0 result:0.9999999872548376
      index:1 result:8.741393497935235E-9
      index:2 result:8.8502539741242E-9
      index:3 result:0.0
    */
}
