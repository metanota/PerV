package ru.md24inc.alembic.pervoc.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

/**
 * Class Vocabulary for making XML to object
 *
 * @author Alexander Panov
 *
 * @version 0.2
 */
public class Vocabulary {

    protected List<Card> voc;

    /**
     * Class constructor.
     */
    public Vocabulary() {
        System.out.println("Initial Vocab");
        voc = new ArrayList<Card>();
    }

    /**
     * Adding a Word Card to list of Cards
     *
     * @param card
     */
    public void addCard(Card card) {
        voc.add(card);
    }

    /**
     * Printing to console the content of vocabulary in tabs columns
     */
    public void printVocabular() {
        System.out.println("Printing All Cards");
        for (int i = 0; i < voc.size(); i++) {
            System.out.println("\t" + voc.get(i).getWord() + " \t["
                    + voc.get(i).getTranscript() + "] \t"
                    + voc.get(i).getTranslation());
        }

    }

    /**
     * Opens XML file of Vocabulary and fills up Vocabulary
     *
     * @param fileName String for source XML file
     */
    //TODO Add some kind of Error Exceptions
    public void openXMLFile(String fileName) {
        Card card;

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fileName);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("card");

            for (int i = 0; i < nList.getLength(); i++) {
                card = new Card();

                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    card.setWord(getTagValue(("word"), eElement));
                    card.setTranscript(getTagValue(("transcript"), eElement));
                    card.setTranslation(getTagValue(("translation"), eElement));
                }

                voc.add(card);

            }// end for

        }// end try
        catch (Exception e) {
            System.out.println(e);
        }

    }

    /**
     * Saves Vocabulary to XML file
     *
     * @param fileName String for output XML file
     */
    //TODO Add some kind of Error Exceptions
    public void saveXMLFile(String fileName) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            //root element
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("vocabulary");
            doc.appendChild(rootElement);

            //Cycle for adding cards elements goes here
            //One itteration
            for (int i = 0; i < voc.size(); i++) {

                //card element
                Element card = doc.createElement("card");
                rootElement.appendChild(card);
                //card.setAttribute("count", Integer.toString(i));
                Attr attr = doc.createAttribute("count");
                attr.setValue(Integer.toString(i+1));
                card.setAttributeNode(attr);
                //word element
                Element word = doc.createElement("word");
                word.appendChild(doc.createTextNode(voc.get(i).getWord()));
                card.appendChild(word);
                //transcript
                Element transcript = doc.createElement("transcript");
                transcript.appendChild(doc.createTextNode(voc.get(i).getTranscript()));
                card.appendChild(transcript);
                //translation
                Element translation = doc.createElement("translation");
                translation.appendChild(doc.createTextNode(voc.get(i).getTranslation()));
                card.appendChild(translation);

            }
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(fileName));
            transformer.transform(source, result);

            System.out.println("File saved!");
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    /**
     * Inner Method for getting Value of Node Chiled by it's Name
     *
     * @param sTag
     * @param eElement
     * @return the Value of Node
     */
    private static String getTagValue(String sTag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();

        Node nValue = (Node) nlList.item(0);

        return nValue.getNodeValue();
    }
}