

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import java.util.*;



import java.io.*;

public class OldFile {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		//Time to run read CSV File
	    System.gc();
	   
	    long size=0;
	    long seconds;
	    Scanner sc = null;
	    FileInputStream inputStream = null;
	    
		// Read Csv from Local
	  
	    List<List<String>> records = new ArrayList<>();
	    List<Records> ethereumRecords = new ArrayList<Records>() ;
	    File directory = new File("C:\\Users\\rshagan\\Documents\\DS");
	    File[] files = directory.listFiles();
	    for(int i = 0 ; i < files.length ; i++)
	    {
	    	if(files[i].isDirectory())
	    		continue;
		    try {
		    	
		    	inputStream = new FileInputStream(files[i]);
		    	sc = new Scanner(inputStream, "UTF-8");
		    	if(sc.hasNextLine())
		    		sc.nextLine();
		    	while(sc.hasNextLine()) {
		    		String line = sc.nextLine();
		    		String[] values = line.split(",");
		    		Records record= new
							  Records(values[0],Integer.parseInt(values[3]),values[5],Integer.parseInt(
							 values[7])); 
		    		ethereumRecords.add(record);
		    		
		    		records.add(Arrays.asList(values));
					
					/*
					 * Date date; DateFormat dateFormat = new
					 * SimpleDateFormat("yyyy-mm-dd HH:mm:ss"); date = dateFormat.parse(values[5]);
					 * long time = date.getTime(); Timestamp timeStamp = new Timestamp(time);
					 */
						/*
						 * System.out.println(values[0]+" " + values[3]); Records record= new
						 * Records(values[0],Integer.parseInt(values[3]),values[5],Integer.parseInt(
						 * values[7])); ethereumRecords.add(record); size++;
						 */
		    		
					
					/*
					 * String line; line = br.readLine(); while ((line = br.readLine()) != null) {
					 * String[] values = line.split(","); records.add(Arrays.asList(values));
					 * DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss"); Date
					 * date; //System.out.println(values[5]); date = dateFormat.parse(values[5]);
					 * long time = date.getTime(); Timestamp timeStamp = new Timestamp(time);
					 * Records record= new
					 * Records(values[0],Integer.parseInt(values[3]),timeStamp,Integer.parseInt(
					 * values[7])); ethereumRecords.add(record); size++;
					 */
					 
		        }
		    }
		    catch (OutOfMemoryError oome) {
	            //Log the info
	            System.err.println("Array size too large");
	            System.err.println("Max JVM memory: " + Runtime.getRuntime().maxMemory());
	        }
		    catch(Exception e) {
		    	System.out.println(e);
		    }
		    
		    finally {
		    	if(inputStream != null)
		    		inputStream.close();
		    	if(sc != null)
		    		sc.close();
		    }
		    System.out.println("Read file of "+size+" size and name is " + files[i]);
		    
	    }
		//String fileName = "/Users/ranjith/Desktop/Ranjith/Java/test.csv";
	   // String fileName = "/Users/ranjith/Desktop/Ranjith/Java/testData.csv";
	    
	    System.out.println("Reading of "+ size + " is completed!!!");
			
		        
					
					HashMap<Integer,Long> blockGas = new HashMap<Integer,Long>();
			        HashMap<Integer,Timestamp> blockTime = new HashMap<Integer,Timestamp>();
			        //ArrayList<String> txHash = new ArrayList<String>();
			        HashSet<String> txHash = new HashSet<String>();
			        HashSet<Integer> blocks = new HashSet<Integer>();
			        //ArrayList<Integer> blocks = new ArrayList<Integer>();
			        for(int i=0;i<ethereumRecords.size();i++)
			        {
			        		long sum=0;
			        		if(txHash.contains(ethereumRecords.get(i).getTx_hash()))
			        		{
			        			continue;
			        		}
			        		
			        		else if(!blocks.contains(ethereumRecords.get(i).getBlock_number()))
			        		{
			        			txHash.add(ethereumRecords.get(i).getTx_hash());
			        			blocks.add(ethereumRecords.get(i).getBlock_number());
			        			blockTime.put(ethereumRecords.get(i).getBlock_number(),ethereumRecords.get(i).getBlock_time());
			        			//System.out.println("Else outside block");
			        			sum= sum + ethereumRecords.get(i).getGas();
			        			
			        		}
			        		else
			        		{
			        			sum=blockGas.get(ethereumRecords.get(i).getBlock_number()) + ethereumRecords.get(i).getGas();		
			        			blockGas.put(ethereumRecords.get(i).getBlock_number(), sum);
			        		}
			        			//System.out.println(ethereumRecords.get(i).getBlock_number()+" - "+sum);
			        }
			        
			        long startTime = System.nanoTime();
			        System.out.println("Completed inserting records by removing duplicates " );
			        
			        Map<Integer, Long> sorted = blockGas
			                .entrySet()
			                .stream()
			                .sorted(comparingByValue())
			                .collect(
			                    toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,
			                        LinkedHashMap::new));
			        
			        sorted = blockGas
			                .entrySet()
			                .stream()
			                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
			                .collect(
			                    toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
			                        LinkedHashMap::new));
			        
			        
			        long end = System.nanoTime();
					   System.out.println("Number of records-"+size);
					  // long minutes = TimeUnit.NANOSECONDS.toMinutes(startTime-end);
					    seconds= TimeUnit.NANOSECONDS.toSeconds(startTime-end);
					    System.out.println("Time Taken to read all the data in Milli seconds--"+seconds+" seconds" );
				        Set<Integer> block = sorted.keySet();

 
				        File file = new File("/Users/ranjith/Desktop/Ranjith/Java/question_1_output_1_"+size+".csv");
				     
				            // create FileWriter object with file as parameter
				          //  FileWriter outputfile = new FileWriter(file);
				      
				            // create CSVWriter object filewriter object as parameter
				           // CSVWriter writer = new //(outputfile);
				      
				            // adding header to csv
				            //String[] header = { "Block_Number", "Block_Time", "Block_Gas" };
				            //writer.writeNext(header);
				      				            
				            
				            System.out.println("Block_Number"+" , "+ "Block_Time"+" , "+"Block_Gas");
				            for(Integer key : block)
					        {	
				            	String blockNumber = key.toString();
				            	Timestamp timeStamp = blockTime.get(key);
				            	Date date2 = new Date();
				            	date2.setTime(timeStamp.getTime());
				            	String blockTim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date2);
				            	String blocksGas = Long.toString(sorted.get(key));
				            	//String[] data = {blockNumber,blockTim,blocksGas};
				            	//writer.writeNext(data);
				            	System.out.println(blockNumber+" , "+blockTim+" , "+blocksGas);
					        }
				            
				            
				            System.out.println("Number of records-"+size);
				            //writer.close();
				        }
				       
				       
	
	
}