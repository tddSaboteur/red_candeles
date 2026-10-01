package ru.ttd.saboteur.neiro;

public class Perceptrom {
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
}
