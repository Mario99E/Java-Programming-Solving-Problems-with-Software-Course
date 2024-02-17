
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class Part2 {

    public int howMany(String stringa,String stringb)
    {
        int n=0,start=0,myindex=0;
        while(true)
        {
            // System.out.println(start);
            myindex=stringb.indexOf(stringa,start);
            // System.out.println("index: "+ myindex);
            if( myindex == -1)
                break;
            n++;
            start=myindex + stringa.length();
            
        }
        
        return n;
    }
    public void testHowMany()
    {
        int n=0;
        String s1="ATGAACGAATTGAATC";
        String s2="GAA";
        n=howMany(s2,s1);
        System.out.println(s2 + " >>reapeated in >>" + s1 + "\n" 
                            + n + " times");
                            
        s1="AAGDAAFDAFAAAAFDAA";
        s2="AA";
        n=howMany(s2,s1);
        System.out.println(s2 + " >>reapeated in >>" + s1 + "\n" 
                            + n + " times");    
        s1="";
        s2="AA";
        n=howMany(s2,s1);
        System.out.println(s2 + " >>reapeated in >>" + s1 + "\n" 
                            + n + " times"); 
    }
}
