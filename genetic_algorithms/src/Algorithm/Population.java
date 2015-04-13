package Algorithm;

import java.util.Arrays;
import java.util.List;

public class Population {
	Chromosome[] chromosomes;
	public String encryptedMessage = "";
    /*
     * Constructors
     */
    // Create a chromosome population
    public Population(int populationSize, boolean initialise) {
        chromosomes = new Chromosome[populationSize];
        // Initialise population with randomly created chromosomes
        if (initialise) {
            // Loop and create chromosomes
            for (int i = 0; i < size(); i++) {
                Chromosome newChromosome = new Chromosome();
                newChromosome.generateChromosome();
                saveChromosome(i, newChromosome);
            }
        }
    }

    /* Getters */
    public Chromosome getIndividual(int index) {
        return chromosomes[index];
    }

    public Chromosome getFittest() {
        Chromosome fittest = chromosomes[0];
        // Loop through chromosomes to find fittest in population
        for (int i = 0; i < size(); i++) {
            if (fittest.getFitness() > getIndividual(i).getFitness()) {
                fittest = getIndividual(i);
            }
        }
        return fittest;
    }
    public List<Chromosome> getFittest(int size) {
        // Loop through chromosomes to find fittest set of chromosomes in given size
    	// For isolated chromosomes
        return Arrays.asList(doInsertionSort(chromosomes)).subList(0, size);
    }
    /* Public methods */
    // Get population size
    public int size() {
        return chromosomes.length;
    }

    // Save chromosome
    public void saveChromosome(int index, Chromosome chr) {
        chromosomes[index] = chr;
    }
    public void saveChromosome(int index, List<Chromosome> chr) {
    	for (int i = 0; i < chr.size(); i++) {
			Chromosome chromosome = chr.get(i);
			chromosomes[i] = chromosome;
		}
    }
    // Sort for deciding the fittest chromosome set in given size
    public static Chromosome[] doInsertionSort(Chromosome[] input){
    	Chromosome temp;
    	for (int i = 1; i < input.length; i++) {
    		for(int j = i ; j > 0 ; j--){
    			if(input[j].getFitness() < input[j-1].getFitness()){
    				temp = input[j];
    				input[j] = input[j-1];
    				input[j-1] = temp;
    				}
    			}
    		}
    	return input;
	}
}
