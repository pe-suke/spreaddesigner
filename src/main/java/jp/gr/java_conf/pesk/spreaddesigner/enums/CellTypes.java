package jp.gr.java_conf.pesk.spreaddesigner.enums;

import java.util.ArrayList;
import java.util.List;

public enum CellTypes {
    Blank(""),
    Text("text"),
    CheckBox("checkbox"),
    ComboBox("combobox"),
    Currency("currency"),
    Date("date"),
    Number("number"),
    Label("label");
    
    private final String name;
    
    private CellTypes(String name) {
        this.name = name;
    }
    
    public static String[] toArray() {
        List<String> valueList = new ArrayList<String>();
        
        CellTypes[] values = CellTypes.values();
        for (CellTypes value : values) {
            valueList.add(value.name);
        }
        return (String[])valueList.toArray(new String[0]);
    }
    
}
