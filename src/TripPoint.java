import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TripPoint {

	private double lat;
    private double lon;
    private int time;
    private static ArrayList<TripPoint> trip = new ArrayList<>();
    
    public TripPoint(int time, double lat, double lon) {
    	this.time = time;
    	this.lat = lat;
    	this.lon = lon;
    }
    
    public int getTime() {
    	return this.time;
    }
    
    public double getLat() {
    	return this.lat;
    }
    
    public double getLon() {
    	return this.lon;
    }
    
    public static ArrayList<TripPoint> getTrip() {
    	return new ArrayList<>(trip);
    }
    
    public static void readFile(String filename) {
    	trip.clear();
    	try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
    		String line;
    		while ((line = br.readLine()) != null) {
    			line = line.trim();
    			if (line.isEmpty() || line.startsWith("Time")) {
    				continue;
    			}
    			String[] parts = line.split(",");
    			if (parts.length >= 3) {
    				int time = Integer.parseInt(parts[0].trim());
    				double lat = Double.parseDouble(parts[1].trim());
    				double lon = Double.parseDouble(parts[2].trim());
    				trip.add(new TripPoint(time, lat, lon));
    			}
    		}
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    public static double totalTime() {
    	if (trip.isEmpty()) {
    		return 0.0;
    	}
    	int startTime = trip.get(0).getTime();
    	int endTime = trip.get(trip.size() - 1).getTime();
    	return (endTime - startTime) / 60.0;
    }
}

