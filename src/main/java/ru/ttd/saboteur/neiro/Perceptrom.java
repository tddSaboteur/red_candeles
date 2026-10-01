package ru.ttd.saboteur.neiro;

public class Perceptrom {
    private double[] signals;
    public void createSignal(int countSignals) {
        signals = new double[countSignals];
    }

    public double[] getSignals() {
        return signals;
    }
}
