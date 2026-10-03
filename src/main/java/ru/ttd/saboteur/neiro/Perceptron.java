package ru.ttd.saboteur.neiro;

public class Perceptron {
   private double[] weight;
    private final WeightGenerator weightGenerator;
    private final ActivateFunction activateFunction;
    public Perceptron(WeightGenerator weightGenerator, ActivateFunction activateFunction) {
        this.weightGenerator = weightGenerator;
        this.activateFunction = activateFunction;
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
        double summ = 0.0;
        for (int i = 0; i < signals.length; i++) {
            summ += signals[i] * weight[i];
        }
        return activateFunction.activate(summ);
    }
}
