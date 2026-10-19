package ru.ttd.saboteur.neiro.activation;

public class ReluActivationFunction implements ActivationFunction{
    @Override
    public double activate(double input) {
        return Math.max(0,input);
    }
    @Override
    public double derivative(double WeightedSum) {
        return WeightedSum > 0 ? 1.0 : 0;
    }
}
