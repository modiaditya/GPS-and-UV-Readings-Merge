package me.navigation.shared;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SensorGPSData {

	
	private LatLong position;
	private Date time;	
	static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static Date getTime(String timeString) throws ParseException
	{
		timeString = timeString.split(",")[0].replace('T',' ');
		timeString = timeString.substring(0,timeString.length()-1);
		//System.out.println("Time String"+timeString);
		return dateFormat.parse(timeString);
	}
	
	
	public LatLong getPosition() {
		return position;
	}
	public void setPosition(LatLong position) {
		this.position = position;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	public String toString()
	{
		return this.getPosition().getLatitude()+" "+this.getPosition().getLatitude()+" "+this.getTime();
	}
}
