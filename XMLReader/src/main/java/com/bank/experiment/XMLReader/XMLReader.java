package com.bank.experiment.XMLReader;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;


/**
 * Hello world!
 *
 */
public class XMLReader 
{
    public static void main( String[] args ) throws ParserConfigurationException, SAXException, IOException
    {
        System.out.println( "Parse XML" );
        SAX sax = new SAX();
        sax.parseXML(new File("file.xml"), "SALARY");
        System.out.println( "\n================\n" );
        sax.parseXML(new File("file.xml"), "NICKNAME");
    }
}
