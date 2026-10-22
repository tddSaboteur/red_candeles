package ru.ttd.saboteur.neiro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ttd.saboteur.neiro.activation.ReluActivationFunction;
import ru.ttd.saboteur.neiro.activation.SigmoidActivationFunctionImpl;

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
        epoch = 100;
        accuracy = 0.00001;
        trainer = new PerceptronTrainer(learningRate, epoch, accuracy);
    }

    @Test
    void train_mustRunAsManyProcessAsThereAreEpochs() {
        double[][] dataset = {
                {1, 1},
                {1, 0},
                {0, 1},
                {0, 0}
        };

        when(perceptron.calculate(any())).thenReturn(0.5);
        doNothing().when(perceptron).updateWeights(any(), eq(0.5), eq(learningRate));

        double[] actual = {1, 1, 1, 1};
        trainer.train(perceptron, new DataSet(dataset, actual));

        verify(perceptron, times(epoch * actual.length)).updateWeights(any(), eq(0.5), eq(learningRate));
        verify(perceptron, times(epoch * actual.length)).calculate(any());

    }
    @Test
    void train_whenAccuracyIsSufficient_mustFinishTrain(){
        double[][] dataset = {
                {1, 1},
                {1, 0},
                {0, 1},
                {0, 0}
        };

        when(perceptron.calculate(any())).thenReturn(0.5,0.5,1.0);
        doNothing().when(perceptron).updateWeights(any(), eq(0.5), eq(learningRate));
        double[] actual = {1, 1, 1, 1};
        trainer.train(perceptron, new DataSet(dataset, actual));

        verify(perceptron, times(2 * actual.length)).updateWeights(any(), anyDouble(), eq(learningRate));
    }

}
