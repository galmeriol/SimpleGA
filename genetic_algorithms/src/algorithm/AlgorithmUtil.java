package algorithm;

import java.io.IOException;
import java.util.Properties;

public abstract class AlgorithmUtil {
	
	/* GA parametreleri */
    protected double uniformRate = 0.15;
    protected double mutationRate = 0.1;
    protected int tournamentSize = 100;
    protected boolean elitism = true;
    protected int elitismOffset = 300;
	
	Properties p = new Properties();
	void propertyInit(){
		
		/* .properties dosyasında belirtilen algoritmaya ait
		 * değişkenler okunuyor ve değişkenlere aktarılıyor*/
	    try {
			p.load(getClass().getResourceAsStream("/algorithm/algorithm.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    uniformRate = Double.parseDouble(p.getProperty("uniformRate"));
	    mutationRate = Double.parseDouble(p.getProperty("mutationRate"));
	    tournamentSize = Integer.parseInt(p.getProperty("tournamentSize"));
	    elitism = Boolean.parseBoolean(p.getProperty("elitism"));
	    elitismOffset = Integer.parseInt(p.getProperty("elitismOffset"));
	     
	}
}
