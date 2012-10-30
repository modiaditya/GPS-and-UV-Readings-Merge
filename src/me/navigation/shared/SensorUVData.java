package me.navigation.shared;


/**
 * @author Aditya
 * This class is used to capture the UV Sensor readings
 *
 */
public class SensorUVData {

	private double uva1;
	private double uva2;
	private double uvb1;
	private double uvb2;
	
	public static String getUVRange(String filename)
	{
		filename = filename.substring(filename.indexOf('_')+1, filename.indexOf('.'));
		return filename;
	}

	public double getUva1() {
		return uva1;
	}

	public void setUva1(double uva1) {
		this.uva1 = uva1;
	}

	public double getUva2() {
		return uva2;
	}

	public void setUva2(double uva2) {
		this.uva2 = uva2;
	}

	public double getUvb1() {
		return uvb1;
	}

	public void setUvb1(double uvb1) {
		this.uvb1 = uvb1;
	}

	public double getUvb2() {
		return uvb2;
	}

	public void setUvb2(double uvb2) {
		this.uvb2 = uvb2;
	}


}
