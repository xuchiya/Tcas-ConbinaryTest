package com.xuchiya.version1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class findfaultcase
{
  public static void main(String[] args)
    throws IOException
  {
    for (int i = 1; i <= 41; i++)
    {
      FileReader readerTestcase = new FileReader(
        "D://testcase//tcas//testadd.txt");
      BufferedReader brTestCase = new BufferedReader(readerTestcase);
      FileReader readerNumber = new FileReader(
        "D://cygwin//home//root//sir//SIR//tcas_2.0//outputs//outputs//v0//diff" + 
        i);
      
      BufferedReader brNumber = new BufferedReader(readerNumber);
      FileWriter writer = new FileWriter(
        "D://cygwin//home//root//sir//SIR//tcas_2.0//outputs//outputs//v0//difffaultcase" + 
        i);
      BufferedWriter bw = new BufferedWriter(writer);
      
      FileWriter writer2 = new FileWriter(
        "D://cygwin//home//root//sir//SIR//tcas_2.0//outputs//outputs//v0//diffcorrectcase" + 
        i);
      BufferedWriter bw2 = new BufferedWriter(writer2);
      String str1 = null;
      String str2 = null;
      while ((str1 = brNumber.readLine()) != null)
      {
        String[] number = str1.split(" ");
        while ((str2 = brTestCase.readLine()) != null)
        {
          String[] numbercase = str2.split(" ");
          if (number[0].compareTo(numbercase[0]) == 0)
          {
            bw.write(numbercase[1] + " " + numbercase[2] + " " + 
              numbercase[3] + " " + numbercase[4] + " " + 
              numbercase[5] + " " + numbercase[6] + " " + 
              numbercase[7] + " " + numbercase[8] + " " + 
              numbercase[9] + " " + numbercase[10] + " " + 
              numbercase[11] + " " + numbercase[12]);
            
            bw.write("\n");
            break;
          }
          bw2.write(numbercase[1] + " " + numbercase[2] + " " + 
            numbercase[3] + " " + numbercase[4] + " " + 
            numbercase[5] + " " + numbercase[6] + " " + 
            numbercase[7] + " " + numbercase[8] + " " + 
            numbercase[9] + " " + numbercase[10] + " " + 
            numbercase[11] + " " + numbercase[12]);
          
          bw2.write("\n");
        }
      }
      while ((str2 = brTestCase.readLine()) != null)
      {
        String[] numbercase = str2.split(" ");
        bw2.write(numbercase[1] + " " + numbercase[2] + " " + 
          numbercase[3] + " " + numbercase[4] + " " + 
          numbercase[5] + " " + numbercase[6] + " " + 
          numbercase[7] + " " + numbercase[8] + " " + 
          numbercase[9] + " " + numbercase[10] + " " + 
          numbercase[11] + " " + numbercase[12]);
        
        bw2.write("\n");
      }
      bw.close();
      writer.close();
      bw2.close();
      writer2.close();
      brNumber.close();
      readerNumber.close();
      System.out.print(i + " completed" + "\n");
      brTestCase.close();
      readerTestcase.close();
    }
  }
}
