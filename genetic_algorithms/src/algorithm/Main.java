package algorithm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
			
		/* Console çıktısının dosyaya yazdırılması için 
		 * File tipli bir değişken tanımlandı */
		File file = new File("decoding_history.txt");
        BufferedWriter output = null;
		try {
			output = new BufferedWriter(new FileWriter(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		/* Algoritmamızın ulaşmaya çalışacağı, bir fiziksel nesnenin veya
		 * sistem davranışının şifrelenmiş -bizim uygulamamızda kullanıcı girdisi-
		 * hali çözüm olarak atanır  */
		String encryptedMessage="";
		System.out.print("test string :");
		encryptedMessage = in.nextLine();
		Chromosome.setEncryptedMessage(encryptedMessage.toUpperCase());
		FitnessCalc.setSolution(encryptedMessage);
		
        // İlk popülasyonu oluştura
	 	Chromosome.setDefaultGeneLength(encryptedMessage.length());
        Population myPop = new Population(10000, true);
        
        /* maxFitness() ile belirtilen değere kadar popülasyonu
         * evrim döngüsüne sokar */
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
            Algorithm alg = new Algorithm();
            myPop = alg.evolvePopulation(myPop);
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
