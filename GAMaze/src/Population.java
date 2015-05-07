import java.util.ArrayList;

public class Population {
    Maze[] individuals;
    
    int numGenes = 300;

    /*
     * Constructors
     */
    // Create a population
    public Population(int populationSize, boolean initialise) {
        individuals = new Maze[populationSize];
        // Initialize population
        if (initialise) {
            // Loop and create individuals
            for (int i = 0; i < size(); i++) {
        		ArrayList<Double> genes = new ArrayList<Double>();
        		for (int j = 0; j < numGenes; j++) {
        			genes.add(Math.random());
        		}
        		
                Maze newIndividual = new Maze(genes);
                saveIndividual(i, newIndividual);
            }
        }
    }

    /* Getters */
    public Maze getIndividual(int index) {
        return individuals[index];
    }

    public Maze getFittest() {
        Maze fittest = individuals[0];
        // Loop through individuals to find fittest
        for (int i = 0; i < size(); i++) {
            if (fittest.getFitness() <= getIndividual(i).getFitness()) {
                fittest = getIndividual(i);
            }
        }
        return fittest;
    }

    /* Public methods */
    // Get population size
    public int size() {
        return individuals.length;
    }

    // Save individual
    public void saveIndividual(int index, Maze indiv) {
        individuals[index] = indiv;
    }
}