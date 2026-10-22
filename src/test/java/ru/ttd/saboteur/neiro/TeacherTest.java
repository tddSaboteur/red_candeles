package ru.ttd.saboteur.neiro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ttd.saboteur.neiro.activation.ActivationFunction;
import ru.ttd.saboteur.neiro.activation.ReluActivationFunction;


@ExtendWith(MockitoExtension.class)
public class TeacherTest {

    WeightGenerator weightGenerator = new WeightGenerator();
    ActivationFunction reluActivationFunction = new ReluActivationFunction();
    double learningRate;
    int epoch;
    double accuracy;
    Perceptron perceptron;

    @BeforeEach
    void before() {
        weightGenerator = new WeightGenerator();
        reluActivationFunction = new ReluActivationFunction();
        learningRate = 0.01;
        epoch = 10_000;
        accuracy = 0.00001;
        perceptron = new Perceptron(weightGenerator, reluActivationFunction);

    }

    @Test
    void trainSpikeAnd() {
        double[][] dataset = {
                {1, 1},
                {1, 0},
                {0, 1},
                {0, 0}
        };
        double[] actual = {1, 0, 0, 0};
        perceptron.createSignal(dataset[0].length);

        train(dataset, actual);

        for (int i = 0; i < dataset.length; i++) {
            double[] data = dataset[i];
            System.out.println("index:" + i + " result:" + perceptron.calculate(data));
        }
    }

    private void train(double[][] dataset, double[] actual) {

        for (int i = 0; i < epoch; i++) {

            double meanError = oneTry(dataset, actual) / dataset[0].length;

            if (i % 200 == 0){
                System.out.println("epoch:"+i+" ERROR:" + meanError);
            }

            if (meanError < accuracy) {
                System.out.println("Final training! Epoch" + i);
                break;
            }
        }
    }

    private double oneTry(double[][] dataset, double[] actual) {
        double symError = 0;
        for (int j = 0; j < dataset.length; j++) {
            double[] data = dataset[j];
            double result = perceptron.calculate(data);
            double error = actual[j] - result;
            symError += Math.abs(error);
            perceptron.updateWeights(data, error, learningRate);
        }
        return symError;
    }
}
