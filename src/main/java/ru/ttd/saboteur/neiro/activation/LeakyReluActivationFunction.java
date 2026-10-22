package ru.ttd.saboteur.neiro.activation;

public class LeakyReluActivationFunction implements ActivationFunction{

    @Override
    public double activate(double input) {
        return Math.max(0.001,input);
    }
    @Override
    public double derivative(double weightedSum) {
        return weightedSum > 0 ? 1.0 : 0.001;
    }

}
