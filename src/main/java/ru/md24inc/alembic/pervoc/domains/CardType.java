//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.03.01 at 04:40:25 PM GMT+08:00 
//


package ru.md24inc.alembic.pervoc.domains;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for cardType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="cardType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="word" type="{}wordType"/>
 *         &lt;element name="transcript" type="{}transcriptType"/>
 *         &lt;element name="translation" type="{}translationType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="date" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cardType", propOrder = {
    "word",
    "transcript",
    "translation"
})
public class CardType {

    @XmlElement(required = true)
    protected WordType word;
    @XmlElement(required = true)
    protected TranscriptType transcript;
    @XmlElement(required = true)
    protected TranslationType translation;
    @XmlAttribute(required = true)
    protected String date;

    /**
     * Gets the value of the word property.
     * 
     * @return
     *     possible object is
     *     {@link WordType }
     *     
     */
    public WordType getWord() {
        return word;
    }

    /**
     * Sets the value of the word property.
     * 
     * @param value
     *     allowed object is
     *     {@link WordType }
     *     
     */
    public void setWord(WordType value) {
        this.word = value;
    }

    /**
     * Gets the value of the transcript property.
     * 
     * @return
     *     possible object is
     *     {@link TranscriptType }
     *     
     */
    public TranscriptType getTranscript() {
        return transcript;
    }

    /**
     * Sets the value of the transcript property.
     * 
     * @param value
     *     allowed object is
     *     {@link TranscriptType }
     *     
     */
    public void setTranscript(TranscriptType value) {
        this.transcript = value;
    }

    /**
     * Gets the value of the translation property.
     * 
     * @return
     *     possible object is
     *     {@link TranslationType }
     *     
     */
    public TranslationType getTranslation() {
        return translation;
    }

    /**
     * Sets the value of the translation property.
     * 
     * @param value
     *     allowed object is
     *     {@link TranslationType }
     *     
     */
    public void setTranslation(TranslationType value) {
        this.translation = value;
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDate(String value) {
        this.date = value;
    }
    
    public String toString(){
    	return "Card ["+word.getValue()+" "+ transcript.getValue() +" "+ translation.getValue() +" ] by date "+ date;
    }

}
