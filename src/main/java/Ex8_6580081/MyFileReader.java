/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ex8_6580081;

import java.util.*;
import java.io.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MyFileReader 
{
    private String            path, fileName;
    private Scanner           keyboardScan;
    private ArrayList<String> allLines;
    
    public MyFileReader(String p, String fn)
    {
        path     = p;
        fileName = fn;
        keyboardScan = new Scanner(System.in);
        allLines = new ArrayList<String>();
    }
    
    public void readFile()
    {        
        boolean opensuccess = false;
        while (!opensuccess)
        {
            try (
                Scanner fileScan = new Scanner(new File(path + fileName));
            ){
                opensuccess = true;                
                while(fileScan.hasNextLine())  
                { 
                    allLines.add( fileScan.nextLine() );
                }
            }
            catch (FileNotFoundException e) 
            {
                System.out.print(e + " --> ");
                System.out.println("New file name = ");
                fileName = keyboardScan.next();
            }
        }        
    }
    
    public ArrayList<String> getLines()  
    {
        return allLines;
    }
}