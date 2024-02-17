/**
 * Reads a chosen CSV file of country exports and prints each country that exports coffee.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
    public void listExporters(CSVParser parser, String exportOfInterest) {
        //for each row in the CSV File
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String export = record.get("Exports");
            //Check if it contains exportOfInterest
            if (export.contains(exportOfInterest)) {
                //If so, write down the "Country" from that row
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }

    public void whoExportsCoffee() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "coffee");
    }
    String countryInfo(CSVParser parser,String country)
    {
        for(CSVRecord record: parser)
        {
            if( country.equals(record.get("Country") ) )
            {
                String s=country + ":" + record.get("Exports") +":"+record.get("Value (dollars)");
                return s;
            }
        }
        return "NOT FOUND";
    }
    void listExportersTwoProducts(CSVParser parser,String exportItem1,String exportItem2)
    {
        for(CSVRecord record : parser)
        {
            String exports= record.get("Exports");
            if( exports.contains(exportItem1) && exports.contains(exportItem2) )
            {
                 System.out.println(record.get("Country"));
            }
        }
    }
    int numberOfExporters(CSVParser parser,String exportItem)
    {
        int n=0;
        for(CSVRecord record : parser)
        {
            String exports= record.get("Exports");
            if( exports.contains(exportItem)  )
            {
                 n++;
            }
        }
        return n;
    }
    void bigExporters (CSVParser parser,String amount)
    {
         for(CSVRecord record : parser)
        {
            String value= record.get("Value (dollars)");
            if( value.length() > amount.length() )
            {
                 System.out.println(record.get("Country") + " " + value);
            }
        }
    }
    public void tester()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        System.out.println(countryInfo(parser,"Nauru") );
        
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser,"gold","diamonds");
        
        parser = fr.getCSVParser();
        System.out.println("Number of countries Export \"sugar\" :" +numberOfExporters(parser,"sugar") );
        
        parser = fr.getCSVParser();
        bigExporters (parser,"$999,999,999,999");        
    }
}
