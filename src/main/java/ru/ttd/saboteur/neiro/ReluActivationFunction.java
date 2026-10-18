package ru.ttd.saboteur.neiro;

public class ReluActivationFunction {
    public double activate(double input) {
        return Math.max(0,input);
    }

    public double derivative(double WeightedSum) {
        return WeightedSum > 0 ? 1.0 : 0;
    }
}
