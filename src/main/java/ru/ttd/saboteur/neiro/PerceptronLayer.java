package ru.ttd.saboteur.neiro;

import ru.ttd.saboteur.neiro.activation.ActivationFunction;
import ru.ttd.saboteur.neiro.activation.PerceptronFactory;

public class PerceptronLayer {
    private final int countPerceptron;
    private final PerceptronFactory perceptronFactory;

    private Perceptron[] perceptrons;

    public PerceptronLayer(int countPerceptron, PerceptronFactory perceptronFactory) {
        this.countPerceptron = countPerceptron;
        this.perceptronFactory = perceptronFactory;
    }

    public double[] calculate(double[] dataSet) {
        if (perceptrons == null){
            initPerceptronArray();
        }

        double[] result = new double[perceptrons.length];

        for (int i = 0; i < result.length; i++) {
            result[i] = perceptrons[i].calculate(dataSet);
        }

        return result;
    }

    private void initPerceptronArray() {
        perceptrons = new Perceptron[countPerceptron];
        for (int i = 0; i < countPerceptron; i++) {
            perceptrons[i] = perceptronFactory.createPerceptron();
        }
    }
}
