package Algorithm;

public class Algorithm {
	/* GA parameters */
    private static final double uniformRate = 1.5;
    private static final double mutationRate = 0.5;
    private static final int tournamentSize = 40;
    private static final boolean elitism = true;
    private static int elitismOffset = 40;
    private int distinctLetterCount = 0;
    /* Public methods */
    
    // Possible evolution
    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.size(), false);
        
        //setting Offset for healty chromosomes sets
        if (!elitism)
            elitismOffset = 0;
        
        // place isolated chromosomes first in new population
        newPopulation.saveChromosome(0, pop.getFittest(elitismOffset));
        // Crossover population
        for (int i = elitismOffset; i < pop.size(); i++) {
            Chromosome chrFirst = tournamentSelection(pop);
            Chromosome chrSecond = tournamentSelection(pop);
            Chromosome newChromosome = crossover(chrFirst, chrSecond);
            newPopulation.saveChromosome(i, newChromosome);
        }

        // Loop through and mutate population, except the isolated ones
        for (int i = elitismOffset; i < newPopulation.size(); i++) {
            mutate(newPopulation.getIndividual(i));
        }

        return newPopulation;
    }
    
    // Crossover chromosomes
    private static Chromosome crossover(Chromosome chrFirst, Chromosome chrSecond) {
        Chromosome newChromosome = new Chromosome();
        // Loop through genes
        for (int i = 0; i < chrFirst.size(); i++) {
            // Crossover
        	int existedIndex = Chromosome.check(i);
            if (Math.random() <= uniformRate) {
                if(existedIndex > -1){
                	newChromosome.setGene(i, chrFirst.getGene(existedIndex));
                }
                else{
                	newChromosome.setGene(i, chrFirst.getGene(i));
                }
            } else {
            	if(existedIndex > -1){
            		newChromosome.setGene(i, chrSecond.getGene(existedIndex));
                }
                else{
                	newChromosome.setGene(i, chrFirst.getGene(i));
                }
            }
        }
        return newChromosome;
    }

    // Mutate an individual
    private static void mutate(Chromosome chr) {
        // Loop through genes
        for (int i = 0; i < chr.size(); i++) {
            if (Math.random() <= mutationRate) {
                // Create random gene
                int gene = (int) Math.round(Math.random() * 26);
                int existedIndex = Chromosome.check(i);
                if(existedIndex > -1)
                	chr.setGene(i, chr.getGene(existedIndex));
                else
                	chr.setGene(i, Chromosome.alphabet[gene]);
            }
        }
    }

    // Select chromosomes for crossover
    private static Chromosome tournamentSelection(Population pop) {
        // Create a population for tournament
        Population tournament = new Population(tournamentSize, false);
        // Select random chromosomes for tournament population
        for (int i = 0; i < tournamentSize; i++) {
            int rnd = (int) (Math.random() * pop.size());
            tournament.saveChromosome(i, pop.getIndividual(rnd));
        }
        // Get the fittest
        Chromosome fittest = tournament.getFittest();
        return fittest;
    }
    
    // Calculate distinct letter count
}
