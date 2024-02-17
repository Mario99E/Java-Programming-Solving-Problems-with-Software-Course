
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class Part1 {
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
    void testFindStopCodon()
    {
        String s="dsATGTGAxiy";
        int x=findStopCodon(s,2,"TGA");
        System.out.println("index: " + x);
    }
    void testFindGene ()
    {
        String S1="AGTTTGAGGGTTTAAAAGGTGTGGGGT"; //NO ATG
        String S2="AAEERATGXYZXYZTAAXY"; //ONE VALID
        String S3="FEWWATGXYZZTGAXXYZTAGZXYZTAA"; //MUL VALID
        String S4="FEWWATGXYZGAAGAAATGGTGAAA"; //NO VALID
        
        System.out.println("no START CODON: " + findGene(S1,0) );
        System.out.println("one valid: " + findGene(S2,0) );
        System.out.println("mul valid: " + findGene(S3,0) );
        System.out.println("no valid stop: " + findGene(S4,0) );
    }
}
