/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class BabyBirths {
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                           " Gender " + rec.get(1) +
                           " Num Born " + rec.get(2));
            }
        }
    }

    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int numOfM=0;
        int numOfF=0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
                numOfM++;
            }
            else {
                totalGirls += numBorn;
                numOfF++;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("female girls = " + totalGirls);
        System.out.println("male boys = " + totalBoys);
        System.out.println("number of girls names = " + numOfF);
        System.out.println("number of boys names = " + numOfM);
        System.out.println("total names = " + (numOfF+numOfM) );
    }
    public int getRank(int year,String name, String gender)
    {
        int n=0;
        String yearS=String.valueOf(year);
        String myPath= "data/yob"+yearS+".csv";  // real data
        //String myPath= "testing/yob"+yearS+"short.csv"; //testing data
        FileResource fr = new FileResource(myPath);
        for (CSVRecord record : fr.getCSVParser(false) )
         {
             if( gender.equals(record.get(1) ))
                 {
                     n++;
                     if( name.equals(record.get(0) ))
                         {
                             return n;
                         }
                
                 }
             
         }
         return -1;
        
    }
     public String getName(int year,int rank, String gender)
    {
        String theName="NO NAME";
        int n=0;
        String yearS=String.valueOf(year);
        String myPath= "data/yob"+yearS+".csv";  // real data
        //String myPath= "testing/yob"+yearS+"short.csv"; //testing data
        FileResource fr = new FileResource(myPath);
        for (CSVRecord record : fr.getCSVParser(false) )
         {
             if( gender.equals(record.get(1) ))
                 {
                     n++;
                     if( n == rank)
                         {
                             return record.get(0);
                         }
                
                 }
             
         }
         return theName;
        
    }
    int whatIsNameInYear(String name,int year,int newYear,String gender)
    {
        int rank=getRank(year,name,gender);
        if(rank == -1)
            {
                System.out.println("Name not found");
                return 0;
            }
        String newName=getName(newYear,rank, gender);
        String gsps=(gender.equals("M") )? "he" : "she" ;
        System.out.println(name + " born in "
                            +year + " would be "
                            +newName+" if "+gsps
                            +" was born in "+newYear);
        return 0;
    }
    int yearOfHighestRank(String name,String gender)
    {
        int yearOfHighest=-1;
        int highestRank=0;
        DirectoryResource dr=new DirectoryResource();
        File highestRankFile = null;
        for (File f : dr.selectedFiles()) {
           //FileResource fr = new FileResource(f);
           String yearS= (f.getName()).substring(3,7);
           int currYear=Integer.parseInt(yearS);
           int currRank=getRank(currYear,name,gender);
           if( highestRankFile == null && currRank > 0)
               {
                   highestRankFile=f;
                   yearOfHighest=currYear;
                   highestRank=currRank;
               }
           else
           {
               if(currRank <= 0)
                   continue;
                if(highestRank>currRank)
                {
                   highestRankFile=f;
                   yearOfHighest=currYear;
                   highestRank=currRank;
                }
           }
           
           
        }
        if(highestRankFile == null)
            return -1;
        return yearOfHighest;
    }
    public double getAverageRank(String name,String gender)
    {
        double sum=0;
        double n=0;
        DirectoryResource dr=new DirectoryResource();
        for (File f : dr.selectedFiles())
        {
           String yearS= (f.getName()).substring(3,7);
           int currYear=Integer.parseInt(yearS);
           int currRank=getRank(currYear,name,gender);
           if(currRank>0)
           {
               
               sum+=currRank;
           }
           n++;
        }
        if(sum ==0)
        {
            return -1.0;
        }
        return sum/n;
    }
    public int getTotalBirthsRankedHigher(int year,String name,String gender)
    {
        int total=0;
        String yearS=String.valueOf(year);
        String myPath= "data/yob"+yearS+".csv";  // real data
        //String myPath= "testing/yob"+yearS+"short.csv"; //testing data
        FileResource fr = new FileResource(myPath);
        for (CSVRecord record : fr.getCSVParser(false) )
         {
             if( gender.equals(record.get(1) ))
                 {
                     if(name.equals(record.get(0)) )
                         break;
                     total+=   Integer.parseInt(record.get(2) ) ;
                         
                
                 }
             
         }
        return total;
    }
    public void testTotalBirths () {
        FileResource fr = new FileResource();
        //FileResource fr = new FileResource("data/yob2014.csv");
        totalBirths(fr);
    }
    public void testGetRank () {
        int year=2012;
        String name="Mason";
        String gender="F";
        int rank=getRank(year,name,gender);
        System.out.println("Rank of name \" "+name+"\" at year "
                            +year+ " and gender "+gender+" is "+rank );
    }
     public void testGetName() {
        int year=2012;
        String gender="M";
        int rank=2;
        String name=getName(year,rank,gender);
        System.out.println("Rank of name \" "+name+"\" at year "
                            +year+ " and gender "+gender+" is "+rank );
    }
    public void testWhatIsNameInYear()
    {
        int year=2012,newYear=2014;
        String name="Isabella", gender="F";
        int n=whatIsNameInYear(name,year,newYear,gender);
    }
}
