

//package hadwritingprocess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class HadwritingProcess {

    
    public static void main(String[] args) throws FileNotFoundException, IOException 
    {
        
        String line = "", line2 = "", line3;
        int reducedPix, id;
        int sampleMatrix[][] = new int [32][32];
        String charArray[] = new String[32];
        String idNumber[] = new String[32];
        BufferedReader br = new BufferedReader(new FileReader("Z:\\ai4\\optdigits-orig.windep"));
        
        File file1 = new File("Z:\\ai4\\input.txt");
        BufferedWriter input = new BufferedWriter(new FileWriter(file1));
        
        File file2 = new File("Z:\\ai4\\target.txt");
        BufferedWriter target = new BufferedWriter(new FileWriter(file2));
        
        for(int p = 0; p < 1797; p++)
        {
            for(int i = 0; i< 32; i++)
            {
                line = br.readLine();
                charArray = line.split("");
                for(int j = 0; j < 32; j++)
                {
                    sampleMatrix[i][j] = Integer.parseInt(charArray[j]);
                    
                }
                
            }
            line2 = br.readLine();
            line2 = line2.trim();
            
            id = Integer.parseInt(line2);
            
            for (int m = 0; m < 32; m+=2)
            {
                for (int n = 0; n < 32; n+=2)
                {
                    reducedPix = sampleMatrix[m][n] + sampleMatrix[m+1][n] + sampleMatrix[m][n+1] + sampleMatrix[m+1][n+1];
                    if(reducedPix == 0)
                    {
                        input.write("0.00 ");
                    }
                    else if(reducedPix == 1)
                    {
                        input.write("0.25 ");
                    }
                    else if(reducedPix == 2)
                    {
                        input.write("0.50 ");
                    }
                    else if(reducedPix == 3)
                    {
                        input.write("0.75 ");
                    }
                    else if(reducedPix == 4)
                    {
                        input.write("1.00 ");
                    }
                    else
                    {}
                }
            }
            input.write("\n");
            System.out.println(p+"   "+id);
            if (id == 0)
            {
                target.write("1 0 0 0 0 0 0 0 0 0");
                target.write("\n");
                System.out.println("0");
            }
            else if (id == 1)
            {
                target.write("0 1 0 0 0 0 0 0 0 0");
                target.write("\n");
                System.out.println("1");
            }
            else if (id == 2)
            {
                target.write("0 0 1 0 0 0 0 0 0 0");
                target.write("\n");
                System.out.println("2");
            }
            else if (id == 3)
            {
                target.write("0 0 0 1 0 0 0 0 0 0");
                target.write("\n");
                System.out.println("3");
            }
            else if (id == 4)
            {
                target.write("0 0 0 0 1 0 0 0 0 0");
                target.write("\n");
                System.out.println("4");
            }
            else if (id == 5)
            {
                target.write("0 0 0 0 0 1 0 0 0 0");
                target.write("\n");
                System.out.println("5");
            }
            else if (id == 6)
            {
                target.write("0 0 0 0 0 0 1 0 0 0");
                target.write("\n");
                System.out.println("6");
            }
            else if (id == 7)
            {
                target.write("0 0 0 0 0 0 0 1 0 0");
                target.write("\n");
                System.out.println("7");
            }
            else if (id == 8)
            {
                target.write("0 0 0 0 0 0 0 0 1 0");
                target.write("\n");
                System.out.println("8");
            }
            else if (id == 9)
            {
                target.write("0 0 0 0 0 0 0 0 0 1");
                target.write("\n");
                System.out.println("9");
            }
            else
            {
                System.out.println("////");
            } 
        }
        input.close();
        target.close();
        
    }
    
}
