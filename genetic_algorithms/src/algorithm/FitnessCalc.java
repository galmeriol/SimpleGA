package algorithm;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class FitnessCalc {
	 static String solution;

	    /* Kullanıcıdan  gelen metin çözüm olarak atanır
	     * Alfabe dışında karakter varsa temizlenir */
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
	    // Verilen kromozom için doğruluk değeri hesaplar
	    static int getFitness(Chromosome chromosome) {
	        int fitness = 0;
	        /* Kromozom için fitness hesaplanırken kromozomdaki her harfin
	         * kullanıcıdan gelen metindeki karşılıklarıyla arasındaki uzaklık
	         * hesaplanır ve her harf için hesaplanan uzaklıklar toplanır */
	        for (int i = 0; i < chromosome.size(); i++) {
	        	int distance = chromosome.getGene(i).compareTo(chromosome.getEncryptedMessage().substring(i,i + 1).toUpperCase()); 
        		if(Math.abs(distance) <= 26)
        			fitness += Math.abs(distance);
        		else
        			fitness += 27;
            }
	        return fitness;
	    }
	    
	    // Doğru sonuca ulaşıldığında oluşacak fitness değeri belirlenir
	    static int getMaxFitness() {
	        return 0;
	    }
}
