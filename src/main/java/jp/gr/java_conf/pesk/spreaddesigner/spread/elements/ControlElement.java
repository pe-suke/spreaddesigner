package jp.gr.java_conf.pesk.spreaddesigner.spread.elements;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.dom4j.Attribute;
import org.dom4j.Element;

public class ControlElement {
    
    private Element control;
    
    private String index;
    private Map<String, ColumnElement> columnMap = new HashMap<>();
    private List<RowElement> rowList = new LinkedList<>();
    
    @SuppressWarnings("rawtypes")
    public ControlElement(Element control) {
        this.control = control;

        Optional<Attribute> attributeIndex = Optional.ofNullable(control.attribute("index"));
        attributeIndex.ifPresent(o -> index = o.getValue());
        
        Iterator elementIterator = control.elementIterator();
        while(elementIterator.hasNext()) {
            Element child = (Element)elementIterator.next();
            
            String localName = child.getQName().getName();
            
            switch(localName) {
                case "column":
                    ColumnElement columnElement = new ColumnElement(child);
                    String columnId = columnElement.getName().getStringValue();
                    columnMap.put(columnId, columnElement);
                    break;
                case "row":
                    RowElement rowElement = new RowElement(child);
                    rowList.add(rowElement);
                    break;
            }
            
        }
        
        // sort row order by name.
        rowList.sort((o1, o2) -> o1.getName().getStringValue().compareTo(o2.getName().getStringValue()));
    }
    
    public String getName() {
        return control.attributeValue("name");
    }
    
    public String getIndex() {
        return index;
    }
    
    public Map<String, ColumnElement> getColumnMap() {
        return columnMap;
    }
    
    public List<RowElement> getRowList() {
        return rowList;
    }
}
