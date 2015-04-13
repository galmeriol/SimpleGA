package Algorithm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		//Create file
		File file = new File("decoding_history.txt");
        BufferedWriter output = null;
		try {
			output = new BufferedWriter(new FileWriter(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		String encryptedMessage="";
		System.out.print("Enter something as a test :");
		encryptedMessage = in.nextLine();
		Chromosome.setEncryptedMessage(encryptedMessage.toUpperCase());
		FitnessCalc.setSolution(encryptedMessage);
        // Create an initial population
	 	Chromosome.setDefaultGeneLength(encryptedMessage.length());
        Population myPop = new Population(1000, true);
        
        // Evolve our population until we reach an optimum solution
        int generationCount = 0;
        int fittest = myPop.getFittest().getFitness();
        while (fittest > 0) {
        	fittest = myPop.getFittest().getFitness();
            generationCount++;
            String out = "Generation: " + generationCount + " 	Fittest: " + myPop.getFittest().toString();
            System.out.println(out);
            try {
				output.append(out + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
            myPop = Algorithm.evolvePopulation(myPop);
            FitnessCalc.setSolution(myPop.getFittest().toString());
        }
        try {
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        System.out.println("Solution found!");
        System.out.println("Generation: " + generationCount);
        System.out.println("Genes:");
        System.out.println(myPop.getFittest().toString());
	}
}
