package jp.gr.java_conf.pesk.spreaddesigner.enums;

import java.util.ArrayList;
import java.util.List;

public enum Aligns {
    Blank(""),
    Top("top"),
    Right("right"),
    Left("left"),
    Bottom("bottom");
    
    private final String name;
    
    private Aligns(String name) {
        this.name = name;
    }
    
    public static String[] toArray() {
        List<String> valueList = new ArrayList<String>();
        
        Aligns[] values = Aligns.values();
        for (Aligns value : values) {
            valueList.add(value.name);
        }
        return (String[])valueList.toArray(new String[0]);
    }
    
    
}
