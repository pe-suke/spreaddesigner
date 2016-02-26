package jp.gr.java_conf.pesk.spreaddesigner.spread.elements;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

import org.dom4j.Element;

public class FormElement {
    
    private Element form;
    private Map<String, ControlElement> controlMap = new HashMap<>();
    
    @SuppressWarnings("rawtypes")
    public FormElement(Element form) {
        this.form = form;
        
        Iterator elementIterator = form.elementIterator();
        while(elementIterator.hasNext()) {
            Element child = (Element)elementIterator.next();
            
            ControlElement controlElement = new ControlElement(child);
            Optional<String> controlIndex = Optional.ofNullable(controlElement.getIndex());
            String keyName = controlElement.getName() + controlIndex.map(o -> "(index:[" + o + "])").orElse("");
            
            controlMap.put(keyName, controlElement);
        }
    }
    
    public String getName() {
        return form.attributeValue("name");
    }
    
    public Map<String, ControlElement> getControlMap() {
        return controlMap;
    }
    
}
