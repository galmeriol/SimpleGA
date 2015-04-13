package Algorithm;

import java.util.Arrays;

public class Chromosome {
	static int defaultGeneLength = 0;
	private static String encryptedMessage = "";
	//Using alphabet and space character as a allele
	public static String[] alphabet = {"A", "B", "C", "D","E","F","G","H","I","J","K","L","M","N",
								"O","P","Q", "R","S","T","U","V","W","X","Y","Z", " "};
    private int[] genes = new int[defaultGeneLength];
    private int fitness = 0;
    // Check if the letter is exist
    public static int check(int index){
    	String curr = getEncryptedMessage().substring(index, index + 1);
		for (int i = 0; i < index; i++) {
			String prev = getEncryptedMessage().substring(i, i + 1);
			if(prev.compareTo(curr) == 0)
				return i;
		}
		return -1;
	}
    // Create a random chromosome 
    public void generateChromosome() {
        for (int i = 0; i < size(); i++) {
            int gene = (int) Math.round(Math.random() * 26);
            int existedIndex = check(i);
            if(existedIndex > -1)
            	genes[i] = genes[existedIndex];
            else
            	genes[i] = gene;
        }
    }

    /* Getters and setters */
    
    // create chromosomes with different gene lengths
    public static void setDefaultGeneLength(int length) {
        defaultGeneLength = length;
    }
    
    public String getGene(int index) {
    	return alphabet[genes[index]];
    }

    public void setGene(int index, String value) {
        genes[index] = Arrays.asList(alphabet).indexOf(value);
        fitness = 0;
    }

    /* Public methods */
    public int size() {
        return genes.length;
    }

    public int getFitness() {
        if (fitness == 0) {
            fitness = FitnessCalc.getFitness(this);
        }
        return fitness;
    }
    @Override
    public String toString() {
        String chromosomeStr = "";
        for (int i = 0; i < size(); i++) {
        	chromosomeStr += getGene(i);
        }
        return chromosomeStr;
    }

	public static String getEncryptedMessage() {
		return encryptedMessage;
	}
	public static void setEncryptedMessage(String _encryptedMessage) {
		encryptedMessage = _encryptedMessage;
	}
}
