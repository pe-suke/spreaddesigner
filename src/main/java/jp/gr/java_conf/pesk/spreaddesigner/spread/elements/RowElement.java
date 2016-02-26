package jp.gr.java_conf.pesk.spreaddesigner.spread.elements;

import java.util.Iterator;

import org.dom4j.Element;

import javafx.beans.property.SimpleStringProperty;

public class RowElement {
    private Element row;
    
    private Element name;
    private Element bgColor;
    private Element height;
    
    private SimpleStringProperty dummy = new SimpleStringProperty("");
    
    @SuppressWarnings("rawtypes")
    public RowElement(Element row) {
        setRow(row);
        
        Iterator elementIterator = row.elementIterator();
        while(elementIterator.hasNext()) {
            Element child = (Element)elementIterator.next();
            String localName = child.getQName().getName();
            
            switch(localName) {
                case "name":
                    setName(child);
                    break;
                case "bgColor":
                    setBgColor(child);
                    break;
                case "height":
                    setHeight(child);
                    break;
            }
        }
        
    }
    
    public Element getRow() {
        return row;
    }
    public void setRow(Element row) {
        this.row = row;
    }
    public Element getName() {
        return name;
    }
    public void setName(Element name) {
        this.name = name;
    }
    public Element getBgColor() {
        return bgColor;
    }
    public void setBgColor(Element bgColor) {
        this.bgColor = bgColor;
    }
    public Element getHeight() {
        return height;
    }
    public void setHeight(Element height) {
        this.height = height;
    }
    public SimpleStringProperty dummyProperty() {
        return dummy;
    }
    public void setDummy(String dummy) {
        this.dummy.set(dummy);
    }
    public String getDummy() {
        return this.dummy.get();
    }
    
}
