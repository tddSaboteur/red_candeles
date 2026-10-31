package ru.ttd.saboteur.neiro.activation;

public interface ActivationFunction {
    double activate(double input);

    double derivative(double weightedSum);
}
