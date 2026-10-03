package ru.ttd.saboteur.neiro;

public class Perceptron {
    private double[] signals;
    private double[] weight;
    private final WeightGenerator weightGenerator;

    public Perceptron(WeightGenerator weightGenerator) {
        this.weightGenerator = weightGenerator;
    }

    public void createSignal(int countSignals) {
        signals = new double[countSignals];
        weight = new double[countSignals];
        initWeight();
    }

    private void initWeight() {
        for (int i = 0; i < weight.length; i++) {
            weight[i] = weightGenerator.getRandomValue();
        }
    }

    public double[] getSignals() {
        return signals;
    }

    public double[] getWeights() {
        return weight;
    }

    public double calculate() {
        double summ = 0.0;
        for (int i = 0; i < signals.length; i++) {
            summ += signals[i] * weight[i];
        }
        return summ;
    }
}
