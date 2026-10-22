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

    public void train(Perceptron perceptron, DataSet dataset) {

        for (int i = 0; i < epoch; i++) {

            double meanError = oneTry(dataset.inputs(), dataset.actual(), perceptron) / dataset.actual().length;

            if (i % 200 == 0){
                System.out.println("epoch:"+i+" ERROR:" + meanError);
            }

            if (meanError < accuracy) {
                System.out.println("Final training! Epoch" + i);
                break;
            }
        }
    }

    private double oneTry(double[][] dataset, double[] actual, Perceptron perceptron) {
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
