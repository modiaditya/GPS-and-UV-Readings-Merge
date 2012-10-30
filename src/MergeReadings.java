import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import me.navigation.shared.ParseSensorReading;
import me.navigation.shared.SensorGPSData;
import me.navigation.shared.SensorUVData;
import me.navigation.shared.UVData;


public class MergeReadings {

	public static void main(String args[]) throws Exception
	{
		
		String gpsReadings = args[0];
		String uva1Readings = args[1];
		String uva2Readings = args[2];
		String uvb1Readings = args[3];
		String uvb2Readings = args[4];
		String outputFile = args[5];
		
		if(args.length<6)
		{
			System.out.println("Less number of argumnets");
			return;
		}
		
		// get GPS readings
		HashMap<Integer,SensorGPSData> h = new HashMap<Integer, SensorGPSData>();
		h=ParseSensorReading.getGPSData(gpsReadings);
		Iterator<Entry<Integer, SensorGPSData>> iter = h.entrySet().iterator();
		while(iter.hasNext())
		{	
			Entry<Integer, SensorGPSData> pairs = iter.next();
			System.out.println(pairs.getKey()+" "+ " "+pairs.getValue().getTime()+" "+pairs.getValue().getPosition().getLatitude());
		}
		
		// variables to join everything
		HashSet<UVData> joinedUVDataSet = new HashSet<UVData>();
		UVData joinedUVData;
		SensorGPSData gpsDataRaw;

		boolean isThereAUVReading = false;
		//System.out.println(h.containsKey(4));
		// get UVA1 Readings
		HashMap<Integer, SensorUVData> uva1ReadingsMap = new HashMap<Integer, SensorUVData>();
		uva1ReadingsMap= ParseSensorReading.getUVData(uva1Readings);
		
		//get UVA2 Readings 
		HashMap<Integer, SensorUVData> uva2ReadingsMap = new HashMap<Integer, SensorUVData>();
		uva2ReadingsMap= ParseSensorReading.getUVData(uva2Readings);
		
		//get UVB1 Readings 
		HashMap<Integer, SensorUVData> uvb1ReadingsMap = new HashMap<Integer, SensorUVData>();
		uvb1ReadingsMap= ParseSensorReading.getUVData(uvb1Readings);
				
		//get UVB2 Readings 
		HashMap<Integer, SensorUVData> uvb2ReadingsMap = new HashMap<Integer, SensorUVData>();
		uvb2ReadingsMap= ParseSensorReading.getUVData(uvb2Readings);
		
		System.out.println(uva1ReadingsMap);
		
		Iterator<Entry<Integer, SensorGPSData>> gpsReadingsIterator = h.entrySet().iterator();
		Integer seconds;
		
		while(gpsReadingsIterator.hasNext())
		{
			
			joinedUVData = new UVData();
			seconds = gpsReadingsIterator.next().getKey();
			if(h.containsKey(seconds))
			{
				isThereAUVReading = false;
				gpsDataRaw = h.get(seconds);
				joinedUVData.setPoint(gpsDataRaw.getPosition());
				joinedUVData.setTime(gpsDataRaw.getTime());
				if(uva1ReadingsMap.containsKey(seconds))
				{
					joinedUVData.setUva1(uva1ReadingsMap.get(seconds).getUva1());
					isThereAUVReading=true;
				}
				if(uva2ReadingsMap.containsKey(seconds))
				{
					joinedUVData.setUva2(uva2ReadingsMap.get(seconds).getUva2());
					isThereAUVReading=true;
				}
				if(uvb1ReadingsMap.containsKey(seconds))
				{
					joinedUVData.setUvb1(uvb1ReadingsMap.get(seconds).getUvb1());
					isThereAUVReading=true;
				}
				if(uvb2ReadingsMap.containsKey(seconds))
				{
					joinedUVData.setUvb2(uvb2ReadingsMap.get(seconds).getUvb2());
					isThereAUVReading=true;
				}
				if(isThereAUVReading)
					joinedUVDataSet.add(joinedUVData);
			}
		}
		FileWriter fstream = new FileWriter(outputFile,true);
		BufferedWriter out = new BufferedWriter(fstream);
		Iterator<UVData> joinedIter= joinedUVDataSet.iterator();
		while(joinedIter.hasNext())
		{
			out.write(joinedIter.next()+"\n");
			//System.out.println(joinedIter.next());
		}
		out.close();
		//System.out.println(joinedUVDataSet);
		
	}
}
