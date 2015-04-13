package Algorithm;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class FitnessCalc {
	 static String solution;

	    /* Public methods */
	    // set a solution
	    static void setSolution(String newSolution) {
	        solution = new String();
	        // Loop through each character of our string and save it in our byte 
	        // array
	        for (int i = 0; i < newSolution.length(); i++) {
	            String character = newSolution.substring(i, i + 1);
	            if (Arrays.asList(Chromosome.alphabet).contains(character.toUpperCase())) {
	                solution += character;
	            } else {
	                solution += "";
	            }
	        }
	    }
	    // Calculate chromosome's fittness by comparing it to our candidate solution
	    static int getFitness(Chromosome chromosome) {
	        int fitness = 0;
	        // Loop through our chromosomes genes and compare them to our cadidates
	        for (int i = 0; i < chromosome.size(); i++) {
	        	// Calculating fitness by calculating the distance every letter in chromosome from
	        	// corresponding solution letter
	        	int distance = chromosome.getGene(i).compareTo(chromosome.getEncryptedMessage().substring(i,i + 1).toUpperCase()); 
        		if(Math.abs(distance) <= 26)
        			fitness += Math.abs(distance);
        		else
        			fitness += 27;
	        		//System.out.println(Math.abs(distance) + " ");
            }
	        //System.out.println("\n");
	        return fitness;
	    }
	    
	    // Get optimum fitness
	    static int getMaxFitness() {
	        return 0;
	    }
}
