package ru.ttd.saboteur.neiro.activation;

import ru.ttd.saboteur.neiro.Perceptron;
import ru.ttd.saboteur.neiro.WeightGenerator;

public class PerceptronFactory {
    private final int countSignals;
    private final WeightGenerator weightGenerator;
    private final ActivationFunction activationFunction;

    public PerceptronFactory(int countSignals, WeightGenerator weightGenerator, ActivationFunction activationFunction) {
        this.countSignals = countSignals;
        this.activationFunction = activationFunction;
        this.weightGenerator = weightGenerator;
    }

    public Perceptron createPerceptron() {
        var perceptron = new Perceptron(weightGenerator,activationFunction);
        perceptron.initWeights(countSignals);
        return perceptron;
    }
}
