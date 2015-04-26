package algorithm;

import java.util.Arrays;
import java.util.List;

public class Population {
	Chromosome[] chromosomes;
	public String encryptedMessage = "";
   
    // Popülasyon oluştur
    public Population(int populationSize, boolean initialise) {
        chromosomes = new Chromosome[populationSize];
        // Eğer popülasyon ilk popülasyonsa random popülasyon oluşturur
        if (initialise) {
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
    // Kromozom popülasyonundan çözüme en yakın kromozom döner
    public Chromosome getFittest() {
        Chromosome fittest = chromosomes[0];
        for (int i = 0; i < size(); i++) {
            if (fittest.getFitness() > getIndividual(i).getFitness()) {
                fittest = getIndividual(i);
            }
        }
        return fittest;
    }
    
    // Elitism ile belirtilen ayrıcalıklı kromozom sayısı kadar kromozom döner
    // Bunu yaparken kromozomları en uyumlular başta olacak şekilde sıralar
    public List<Chromosome> getFittest(int size) {
        return Arrays.asList(doInsertionSort(chromosomes)).subList(0, size);
    }
    
    public int size() {
        return chromosomes.length;
    }

    public void saveChromosome(int index, Chromosome chr) {
        chromosomes[index] = chr;
    }
    public void saveChromosome(int index, List<Chromosome> chr) {
    	for (int i = 0; i < chr.size(); i++) {
			Chromosome chromosome = chr.get(i);
			chromosomes[i] = chromosome;
		}
    }

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
