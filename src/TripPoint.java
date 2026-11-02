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
    
    public static double haversineDistance(TripPoint a, TripPoint b) {
    	final double EARTH_RADIUS_KM = 6371.0;
    	
    	double lat1 = Math.toRadians(a.getLat());
    	double lat2 = Math.toRadians(b.getLat());
    	double deltaLat = Math.toRadians(b.getLat() - a.getLat());
    	double deltaLon = Math.toRadians(b.getLon() - a.getLon());
    	
    	double hav = Math.pow(Math.sin(deltaLat / 2.0), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(deltaLon / 2.0), 2);
    	double c = 2.0 * Math.atan2(Math.sqrt(hav), Math.sqrt(1.0 - hav));
    	
    	return EARTH_RADIUS_KM * c;
    }
    
    public static double totalDistance() {
    	double total = 0.0;
    	for (int i = 0; i < trip.size() - 1; i++) {
    		total += haversineDistance(trip.get(i), trip.get(i + 1));
    	}
    	return total;
    }
}

