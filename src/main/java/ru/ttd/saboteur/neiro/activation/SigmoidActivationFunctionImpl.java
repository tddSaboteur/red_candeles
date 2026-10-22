package ru.ttd.saboteur.neiro.activation;

public class SigmoidActivationFunctionImpl implements ActivationFunction {
    @Override
    public double activate(double input) {
        return 1.0 / (1.0 + Math.exp(-input));
    }

    @Override
    public double derivative(double weightedSum) {
        double s = activate(weightedSum);
        return s * (1.0 - s);
    }
}
