package ru.ttd.saboteur.neiro;

public class PerceptronTrainer {
    private final int epoch;
    private final double accuracy;
    private final double learningRate;

    public PerceptronTrainer(double learningRate, int epoch, double accuracy) {
        this.epoch = epoch;
        this.accuracy = accuracy;
        this.learningRate = learningRate;
    }

    public void train(PerceptronLayer perceptron, DataSet dataset) {

        for (int i = 0; i < epoch; i++) {

            double meanError = oneTry(dataset.inputs(), dataset.actual(), perceptron) / dataset.actual().length;

            if (i % 200 == 0) {
                System.out.println("epoch:" + i + " ERROR:" + meanError);
            }

            if (meanError < accuracy) {
                System.out.println("Final training! Epoch" + i);
                break;
            }
        }
    }

    private double oneTry(double[][] dataset, double[][] actual, PerceptronLayer perceptron) {
        double symError = 0;
        for (int i = 0; i < dataset.length; i++) {
            double[] data = dataset[i];
            double[] result = perceptron.calculate(data);
            double[] error = calculateErrors(result, actual[i]);
            symError += symError(error);
            perceptron.updateWeights(data, error, learningRate);
        }
        return symError;
    }

    private double symError(double[] errors) {
        double errorSym = 0;
        for (double error : errors) {
            errorSym += Math.abs(error);
        }
        return errorSym;
    }

    private double[] calculateErrors(double[] calculated, double[] actual) {
        double[] result = new double[actual.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = actual[i] - calculated[i];
        }
        return result;
    }
}
