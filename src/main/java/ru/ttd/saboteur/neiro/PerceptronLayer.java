package ru.ttd.saboteur.neiro;

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
        initPerceptronArray();

        double[] result = new double[perceptrons.length];

        for (int i = 0; i < result.length; i++) {
            result[i] = perceptrons[i].calculate(dataSet);
        }

        return result;
    }

    public void updateWeights(double[] dataSet, double[] error, double learningRate) {
        initPerceptronArray();
        for (int i = 0; i < perceptrons.length; i++) {
            perceptrons[i].updateWeights(dataSet, error[i], learningRate);
        }
    }

    private void initPerceptronArray() {
        if(perceptrons == null) {
            perceptrons = new Perceptron[countPerceptron];
            for (int i = 0; i < countPerceptron; i++) {
                perceptrons[i] = perceptronFactory.createPerceptron();
            }
        }
    }
}
