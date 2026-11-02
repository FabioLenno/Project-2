import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TripPoint {

	private double lat;
    private double lon;
    private int time;
    
    public TripPoint(int time, double lat, double lon) {
    	this.time = time;
    	this.lat = lat;
    	this.lon = lon;
    }
}
