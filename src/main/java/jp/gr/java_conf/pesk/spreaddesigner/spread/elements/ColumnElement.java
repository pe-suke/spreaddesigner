package jp.gr.java_conf.pesk.spreaddesigner.spread.elements;

import java.util.Iterator;

import org.dom4j.Element;

public class ColumnElement {
    
    private Element column;
    private Element name;
    private Element label;
    private Element bgColor;
    private Element width;
    private Element height;
    private Element hidden;
    private Element lock;
    private Element maxsize;
    private Element align;
    private Element celltype;
    
    @SuppressWarnings("rawtypes")
    public ColumnElement(Element column) {
        setColumn(column);
        
        Iterator elementIterator = column.elementIterator();
        while(elementIterator.hasNext()) {
            Element child = (Element)elementIterator.next();
            String localName = child.getQName().getName();
            
            switch(localName) {
                case "name":
                    setName(child);
                    break;
                case "label":
                    setLabel(child);
                    break;
                case "bgcolor":
                    setBgColor(child);
                    break;
                case "width":
                    setWidth(child);
                    break;
                case "height":
                    setHeight(child);
                    break;
                case "hidden":
                    setHidden(child);
                    break;
                case "lock":
                    setLock(child);
                    break;
                case "maxsize":
                    setMaxsize(child);
                    break;
                case "align":
                    setAlign(child);
                    break;
                case "celltype":
                    setCelltype(child);
                    break;
            }
            
        }
    }
    
    
    public Element getColumn() {
        return column;
    }
    public void setColumn(Element column) {
        this.column = column;
    }
    public Element getName() {
        return name;
    }
    public void setName(Element name) {
        this.name = name;
    }
    public void setNameValue(String name) {
        this.name.setText(name);
    }
    public Element getLabel() {
        return label;
    }
    public void setLabel(Element label) {
        this.label = label;
    }
    public void setLabelValue(String label) {
        this.label.setText(label);
    }
    public Element getBgColor() {
        return bgColor;
    }
    public void setBgColor(Element bgColor) {
        this.bgColor = bgColor;
    }
    public void setBgColorValue(String bgColor) {
        this.bgColor.setText(bgColor);
    }
    public Element getWidth() {
        return width;
    }
    public void setWidth(Element width) {
        this.width = width;
    }
    public Element getHeight() {
        return height;
    }
    public void setHeight(Element height) {
        this.height = height;
    }
    public Element getHidden() {
        return hidden;
    }
    public void setHidden(Element hidden) {
        this.hidden = hidden;
    }
    public Element getLock() {
        return lock;
    }
    public void setLock(Element lock) {
        this.lock = lock;
    }
    public Element getMaxsize() {
        return maxsize;
    }
    public void setMaxsize(Element maxsize) {
        this.maxsize = maxsize;
    }
    public Element getCelltype() {
        return celltype;
    }
    public void setCelltype(Element celltype) {
        this.celltype = celltype;
    }
    public Element getAlign() {
        return align;
    }
    public void setAlign(Element align) {
        this.align = align;
    }
}
    