package ru.ttd.saboteur.neiro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class PerceptronTrainerTest {


    double learningRate;
    int epoch;
    double accuracy;
    @Mock
    Perceptron perceptron;

    PerceptronTrainer trainer;

    @BeforeEach
    void before() {
        learningRate = 0.1;
        epoch = 10_000_000;
        accuracy = 0.00001;
        trainer = new PerceptronTrainer(learningRate,epoch,accuracy);
    }
    @Test
    void train_mustRunAsManyProcessAsThereAreEpochs(){
        double[][] dataset = {
                {1, 1},
                {1, 0},
                {0, 1},
                {0, 0}
        };

        when(perceptron.calculate(any())).thenReturn(0.5);
        doNothing().when(perceptron).updateWeights(any(),0.5,learningRate);

        double[] actual = {1, 1, 1, 1};
        trainer.train(perceptron, new DataSet(dataset,actual));

        verify(perceptron, times(epoch* dataset[0].length)).updateWeights(any(),any(),any());
        verify(perceptron, times(epoch* dataset[0].length)).calculate(any());

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
    @Test
    void trainSpikeOr(){
        double[][] dataset = {
                {1, 1},
                {1, 0},
                {0, 1},
                {0, 0}
        };
        double[] actual = {1, 1, 1, 0};
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
