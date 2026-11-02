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
}

