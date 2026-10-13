package ru.ttd.saboteur.neiro;

public class Perceptron {
   private double[] weight;
    private final WeightGenerator weightGenerator;
    private final ReluActivationFunction activationFunction;
    public Perceptron(WeightGenerator weightGenerator, ReluActivationFunction activationFunction) {
        this.weightGenerator = weightGenerator;
        this.activationFunction = activationFunction;
    }

    public void createSignal(int countSignals) {
        weight = new double[countSignals];
        initWeight();
    }

    private void initWeight() {
        for (int i = 0; i < weight.length; i++) {
            weight[i] = weightGenerator.getRandomValue();
        }
    }

    public double[] getWeights() {
        return weight;
    }

    public double calculate(double[] signals) {
        double summ = calculateSignals(signals);
        return activationFunction.activate(summ);
    }

    private double calculateSignals(double[] signals) {
        double summ = 0.0;
        for (int i = 0; i < signals.length; i++) {
            summ += signals[i] * weight[i];
        }
        return summ;
    }
}
