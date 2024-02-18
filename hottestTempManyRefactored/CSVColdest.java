
/**
 * Write a description of CSVColdest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class CSVColdest {
     public CSVRecord coldestHourInFile(CSVParser parser) {
        //start with largestSoFar as nothing
        CSVRecord smallestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar,"TemperatureF");
        }
        //The largestSoFar is the answer
        return smallestSoFar;
    }
    public double averageTemperatureInFile(CSVParser parser) {
        //start with largestSoFar as nothing
        int n=0;
        double sum=0;
        String s;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            n++;
            s=currentRow.get("TemperatureF");
            sum += Double.parseDouble(s);
        }
        //The largestSoFar is the answer
        return sum/n;
    }
    public double averageTemperatureWithHighHumidityInFile (CSVParser parser,int value) {
        //start with largestSoFar as nothing
        int n=0;
        double sum=0;
        double humidity=0;
        String s;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            humidity=Double.parseDouble(currentRow.get("Humidity"));
            if(humidity>= value)
            {
                n++;
            s=currentRow.get("TemperatureF");
            sum += Double.parseDouble(s);
            }
        }
        //The largestSoFar is the answer
        if(n!=0)
        return sum/n;
        else
        {
            System.out.println("No temperatures with that humidity: " + value);
              return -1;
        }
      
    }
    public CSVRecord lowestHumidityInFile(CSVParser parser)
    {
        //start with largestSoFar as nothing
        CSVRecord smallestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar,"Humidity");
        }
        //The largestSoFar is the answer
        return smallestSoFar;
    }
    public File fileWithColdestTemperature()
    {
        DirectoryResource dr = new DirectoryResource();
        File myF = null;
        CSVRecord coldestSoFar = null;
        // iterate over files
        for (File f : dr.selectedFiles()) {
           
            FileResource fr = new FileResource(f);
            // use method to get largest in file.
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            // use method to compare two records
            coldestSoFar = getSmallestOfTwo(currentRow, coldestSoFar,"TemperatureF");
             if(coldestSoFar == currentRow)
            {
                myF=f;
            }
        }
        return myF;
    }
    public CSVRecord lowestHumidityInManyFiles()
    {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldestSoFar = null;
        // iterate over files
        for (File f : dr.selectedFiles()) {
           
            FileResource fr = new FileResource(f);
            // use method to get largest in file.
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            // use method to compare two records
            coldestSoFar = getSmallestOfTwo(currentRow, coldestSoFar,"Humidity");
        }
        //The largestSoFar is the answer
        return coldestSoFar;
    }
    void testlowestHumidityInManyFiles()
    {
        CSVRecord csv=lowestHumidityInManyFiles();
         System.out.println("Lowest Humidity was " + csv.get("Humidity") +
                   " at " + csv.get("DateUTC"));
    }
    void testFileWithColdestTemperature()
    {
        File myfile = fileWithColdestTemperature();
        System.out.println("Coldest day was in file" + myfile.getName() );
        FileResource fr= new FileResource(myfile);
        
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature on that day was " + coldest.get("TemperatureF"));
        
    }
    
    
    public void testColdestHourInFile () {
        FileResource fr = new FileResource("data/2014/weather-2014-05-01.csv");
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + coldest.get("TemperatureF") +
                   " at " + coldest.get("DateUTC"));
    }
    public void testLowestHumidityInFile()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") +
                   " at " + csv.get("DateUTC"));
        
    }
    public void testAverageTemperatureInFile()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg = averageTemperatureInFile (parser);
        System.out.println("Average temperature in file is " + avg);
        
    }
    public void testAverageTemperatureWithHighHumidityInFile()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg = averageTemperatureWithHighHumidityInFile  (parser,80);
        if(avg != -1)
        System.out.println("Average Temp when high Humidity is " + avg);
        
    }
     public CSVRecord getSmallestOfTwo (CSVRecord currentRow, CSVRecord smallestSoFar,String field) {
        //If largestSoFar is nothing
        if (smallestSoFar == null) {
            smallestSoFar = currentRow;
        }
        //Otherwise
        else {
            String currentRowS=currentRow.get(field);
            String smallestSoFarS=smallestSoFar.get(field);
            double currentTemp;
            if( currentRowS.equals("N/A") )
            {
                 currentTemp = -9999;
            }
            else
            {
                 currentTemp = Double.parseDouble(currentRow.get(field));
            }
            double smallestTemp;
            if( smallestSoFarS.equals("N/A") )
            {
                 smallestTemp = -9999;
            }
            else
            {
                 smallestTemp = Double.parseDouble(smallestSoFar.get(field));
            }
            
            
            //Check if currentRow’s temperature > largestSoFar’s
            if ( (currentTemp < smallestTemp && (currentTemp!= -9999 ) ) || (smallestTemp==-9999) ) {
                //If so update largestSoFar to currentRow
                smallestSoFar = currentRow;
            }
        }
        return smallestSoFar;
    }

}
