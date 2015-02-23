package com.bank.experiment.XMLReader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.lang3.StringUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAX {

	private List<Double> values = new ArrayList<Double>();
	
	public void parseXML(File file, final String lookingNode) throws ParserConfigurationException,  SAXException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		DefaultHandler handler = new DefaultHandler() {
			boolean found = false;
			public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
				System.out.println("Start Element :" + qName);
				if (qName.equalsIgnoreCase(lookingNode)) {
					found = true;
				}
			}

			public void endElement(String uri, String localName, String qName) throws SAXException {
				System.out.println("End Element :" + qName);
			}

			public void characters(char ch[], int start, int length)
					throws SAXException {
				if (found) {
					System.out.println(lookingNode + " " + new String(ch, start, length));
					values.add(stringToDouble(new String(ch, start, length)));
					found = false;
				}
			}
		};
		
		saxParser.parse(file.getPath(), handler);
		
		System.out.println("total = " + sumValues(values));
	}
	
	private Double sumValues(List<Double> values){
		Double total = 0.0;
		for(Double value : values)
			total = total + value;
		
		return total;
	}
	
	public static double stringToDouble(String str) {
		str = str.replaceAll(",", "");
		if(org.apache.commons.lang3.math.NumberUtils.isNumber(str)) {
			return org.apache.commons.lang3.math.NumberUtils.createDouble(StringUtils.defaultIfEmpty(str, "0"));
		}
		return 0;
	}
}
