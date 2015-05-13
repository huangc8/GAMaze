import java.util.ArrayList;

public class Algorithm {

    /* GA parameters */
    private static final double uniformRate = 0.5;
    private static final double mutationRate = 0.015;
    private static final int tournamentSize = 10;
    private static final boolean elitism = true;

    /* Public methods */
    
    // Evolve a population
    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.size(), false);

        // Keep our best individual
        if (elitism) {
            newPopulation.saveIndividual(0, pop.getFittest());
        }

        // Crossover population
        int elitismOffset;
        if (elitism) {
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }
        // Loop over the population size and create new individuals with
        // crossover
        for (int i = elitismOffset; i < pop.size(); i++) {
            Maze indiv1 = tournamentSelection(pop);
            Maze indiv2 = tournamentSelection(pop);
            Maze newIndiv = crossover(indiv1, indiv2);
            newPopulation.saveIndividual(i, newIndiv);
        }

        // Mutate population
        for (int i = elitismOffset; i < newPopulation.size(); i++) {
            mutate(newPopulation.getIndividual(i));
        }

        return newPopulation;
    }

    // Crossover individuals
    private static Maze crossover(Maze indiv1, Maze indiv2) {
    	ArrayList<Double> genes = new ArrayList<Double>();
        // Loop through genes
        for (int i = 0; i < indiv1.genes.size(); i++) {
            // Crossover
            if (Math.random() <= uniformRate) {
            	genes.add(indiv1.genes.get(i));
            } else {
            	genes.add(indiv2.genes.get(i));
            }
        }
        Maze newSol = new Maze(genes);
        return newSol;
    }

    // Mutate an individual
    private static void mutate(Maze indiv) {
        // Loop through genes
        for (int i = 0; i < indiv.genes.size(); i++) {
            if (Math.random() <= mutationRate) {
                // Create random gene
                byte gene = (byte) Math.round(Math.random());
                indiv.genes.set(i, Math.random());
            }
        }
    }

    // Select individuals for crossover
    private static Maze tournamentSelection(Population pop) {
        // Create a tournament population
        Population tournament = new Population(tournamentSize, false);
        // For each place in the tournament get a random individual
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.getIndividual(randomId));
        }
        // Get the fittest
        Maze fittest = tournament.getFittest();
        return fittest;
    }
}