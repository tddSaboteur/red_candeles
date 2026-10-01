package ru.ttd.saboteur.neiro;

public class Perceptron {
    private double[] signals;
    private double[] weight;

    public void createSignal(int countSignals) {
        signals = new double[countSignals];
        weight = new double[countSignals];
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

    double getRandomValue(){
        return Math.random();
    }
}
