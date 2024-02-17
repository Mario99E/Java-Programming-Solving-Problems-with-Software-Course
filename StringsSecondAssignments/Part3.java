
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class Part3 {
    int findStopCodon (String dna,int startIndex , String stopCodon)
    {
        int n=0;
        int stopCodonIndex=startIndex;
        while(true)
        {
            //System.out.println("curent n: " + stopCodonIndex);
            stopCodonIndex=dna.indexOf(stopCodon,stopCodonIndex+1);
            if(stopCodonIndex == -1)
                {
                    n=-1;
                    break;
                }
            else if ( (startIndex - stopCodonIndex)%3 ==  0 )
                {
                    n=stopCodonIndex;
                    break;
                }
            
            
        }
        
        return n;
    }
    public String findGene(String dna ,int start  )
    {
        int startIndex= dna.indexOf("ATG",start);
        if( startIndex == -1)
            return "";
        int stopIndexTAA = findStopCodon(dna,startIndex,"TAA");
        int stopIndexTAG = findStopCodon(dna,startIndex,"TAG");
        int stopIndexTGA = findStopCodon(dna,startIndex,"TGA");
        int minIndex = 0;
        if(stopIndexTAA == -1 || (stopIndexTAG < stopIndexTAA && stopIndexTAG != -1) )
            minIndex=stopIndexTAG;
        else
            minIndex=stopIndexTAA;
        if(minIndex == -1 || (stopIndexTGA < minIndex && stopIndexTGA != -1) )
            minIndex=stopIndexTGA;
        if(minIndex == -1 )
        return "";
        else
        return dna.substring(startIndex,minIndex+3);
        
    }
    void printAllGenes(String dna)
    {
        int startIndex=0;
        String gene="";
        while (true)
        {
            gene= findGene(dna,startIndex);
            if( gene.isEmpty() )
                break;
            System.out.println("gene: " + gene);
            startIndex=dna.indexOf(gene,startIndex) + gene.length();
            
        }
        
    }
    int countGenes(String dna)
    {
        int startIndex=0;
        String gene="";
        int n=0;
        while (true)
        {
            gene= findGene(dna,startIndex);
            if( gene.isEmpty() )
                break;
            System.out.println("gene: " + gene);
            n++;
            startIndex=dna.indexOf(gene,startIndex) + gene.length();
            
        }
        return n;
    }
    void testCountGenes()
    {
        String dna="AATGCTAACTAGCTGACTAAT";
        System.out.println("num: " + countGenes(dna) );
    }
}
