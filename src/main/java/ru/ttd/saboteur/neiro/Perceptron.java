package ru.ttd.saboteur.neiro;

import ru.ttd.saboteur.neiro.activation.ActivationFunction;
import ru.ttd.saboteur.neiro.activation.ReluActivationFunction;

public class Perceptron {
    private double[] weight;
    private static final double bias = 1;
    private final WeightGenerator weightGenerator;
    private final ActivationFunction activationFunction;

    public Perceptron(WeightGenerator weightGenerator, ActivationFunction activationFunction) {
        this.weightGenerator = weightGenerator;
        this.activationFunction = activationFunction;
    }

    public void createSignal(int countSignals) {
        weight = new double[countSignals + 1];
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
        summ += bias * weight[0];
        for (int i = 0; i < signals.length; i++) {
            summ += signals[i] * weight[i + 1];
        }
        return summ;
    }

    public void updateWeights(double[] inputs, double error, double learningRate) {
        double deltaWeight = error * learningRate * activationFunction.derivative(calculateSignals(inputs));
        weight[0] += deltaWeight * bias;

        for (int i = 0; i < inputs.length; i++) {
            weight[i+1] += deltaWeight * inputs[i];
        }
    }
}
