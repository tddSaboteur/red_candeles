package ru.ttd.saboteur.neiro;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TeacherTest {
    @Mock
    WeightGenerator weightGenerator;
    ReluActivationFunction reluActivationFunction = new ReluActivationFunction();
    @Test
    void trainSpike(){
        when(weightGenerator.getRandomValue()).thenReturn(0.5);
        Perceptron perceptron = new Perceptron(weightGenerator, reluActivationFunction);
        double[][] dataset = {
                {1,1},
                {1,0},
                {0,1},
                {0,0}
        };
        double[] actual = {1,0,0,0};
        perceptron.createSignal(2);
        double learningRate = 0.001;
        int epoch = 1000;

        for (int i = 0; i < epoch; i++) {
            for (int j = 0; j < dataset.length; j++) {
                double[] data = dataset[j];
                double result = perceptron.calculate(data);
                double error = actual[j] - result;

                System.out.println("ERROR:"+error);

                perceptron.updateWeights(data,error,learningRate);
            }
        }

        for (int i = 0; i < dataset.length; i++) {
            double[] data = dataset[i];
            System.out.println("index:"+i+" result:"+ perceptron.calculate(data));
        }
    }
}
