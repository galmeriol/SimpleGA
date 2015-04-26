package algorithm;

import java.io.IOException;
import java.util.Properties;

public class Algorithm extends AlgorithmUtil {
	/*Constructor*/
	public Algorithm(){
		propertyInit(); 
	}
	
	/* Kullanıcıdan gelen text içinde kaç farklı karakter olduğunu
	 * bu değişkende tutuluyor */
	private int distinctLetterCount = 0;
	
    /* Public methods */
    
    // Popülasyon üretilirken kullanılan fonksiyon
    public Population evolvePopulation(Population pop) {
    	
        Population newPopulation = new Population(pop.size(), false);
       
        if (!elitism)
            elitismOffset = 0;
        
        /* Bir sonraki popülasyona crossing-over ve mutasyona uğramadan 
         * geçecek kromozomları sonraki popülasyona direk kaydeder*/
        newPopulation.saveChromosome(0, pop.getFittest(elitismOffset));
        
        /* Tournament yöntemiyle tüm popülasyondan-elitism ile ayrılanlar hariç-
         * seçilen 2 kromozom arasında crossing-over gerçekleşir */
        for (int i = elitismOffset; i < pop.size(); i++) {
            Chromosome chrFirst = tournamentSelection(pop);
            Chromosome chrSecond = tournamentSelection(pop);
            Chromosome newChromosome = crossover(chrFirst, chrSecond);
            newPopulation.saveChromosome(i, newChromosome);
        }

        /* Tüm popülasyon-elitism ile ayrılanlar hariç- olası mutasyon fonksiyonuna girer */
        for (int i = elitismOffset; i < newPopulation.size(); i++) {
            mutate(newPopulation.getIndividual(i));
        }
    	/* Tüm bu işlemlerden sonra sonuç olarak yeni popülasyon döner */
        return newPopulation;
    }
    
	// crossing-over için tournament selection yöntemiyle kromozom seçimi
    private Chromosome tournamentSelection(Population pop) {
        // Tournament selection için popülasyon oluşturuyor
        Population tournament = new Population(tournamentSize, false);
        // Select random chromosomes for tournament population
        for (int i = 0; i < tournamentSize; i++) {
            int rnd = (int) (Math.random() * pop.size());
            tournament.saveChromosome(i, pop.getIndividual(rnd));
        }
        // Tournament popülasyonunun en iyisi crossing-over için döner
        Chromosome fittest = tournament.getFittest();
        return fittest;
    }
    
    // Crossing-over
    private Chromosome crossover(Chromosome chrFirst, Chromosome chrSecond) {
        Chromosome newChromosome = new Chromosome();
        
        /* Crossing-over için gelen iki kromozom için aksiyon gerçekleştirilir
         * Burada crossing-over, gelen kromozom karakterleri taranırken aynı karakterden
         * cümle içinde başka konumlarda da var ise oralara da uygulanır*/
        for (int i = 0; i < chrFirst.size(); i++) {
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

    // Mutatation
    private void mutate(Chromosome chr) {
        for (int i = 0; i < chr.size(); i++) {
            if (Math.random() <= mutationRate) {
                /* Mutasyon olasılık değişkenine göre aleller değiştirilir. 
                 * Yine aynı karakter konumları için aynı değişiklikler uygulanır*/
            	
                int gene = (int) Math.round(Math.random() * 26);
                int existedIndex = Chromosome.check(i);
                if(existedIndex > -1)
                	chr.setGene(i, chr.getGene(existedIndex));
                else
                	chr.setGene(i, Chromosome.alphabet[gene]);
            }
        }
    }

    
}
