package jp.gr.java_conf.pesk.spreaddesigner.spread.utils;

import java.util.Optional;

import org.dom4j.Element;

import jp.gr.java_conf.pesk.spreaddesigner.spread.dto.ColumnDto;
import jp.gr.java_conf.pesk.spreaddesigner.spread.dto.ControlDto;
import jp.gr.java_conf.pesk.spreaddesigner.spread.elements.ColumnElement;
import jp.gr.java_conf.pesk.spreaddesigner.spread.elements.ControlElement;

public class ConvertUtils {
    
    public static void convertControlElementToDto(ControlElement element, ControlDto dto) {
        dto.setControlName(element.getName());
        dto.setControlIndex(element.getIndex());
    }
    
    public static void convertColumnElementToDto(ColumnElement element, ColumnDto dto) {
        Optional<Element> elLabel = Optional.ofNullable(element.getLabel());
        Optional<Element> elBgColor = Optional.ofNullable(element.getBgColor());
        Optional<Element> elName = Optional.ofNullable(element.getName());
        Optional<Element> elHeight = Optional.ofNullable(element.getHeight());
        Optional<Element> elWidth = Optional.ofNullable(element.getWidth());
        Optional<Element> elHidden = Optional.ofNullable(element.getHidden());
        Optional<Element> elLock = Optional.ofNullable(element.getLock());
        Optional<Element> elMaxSize = Optional.ofNullable(element.getMaxsize());
        Optional<Element> elAlign = Optional.ofNullable(element.getAlign());
        Optional<Element> elCellType = Optional.ofNullable(element.getCelltype());
        
        dto.setLabel(elLabel.map(o -> o.getStringValue()).orElse(""));
        dto.setBgColor(elBgColor.map(o -> o.getStringValue()).orElse(""));
        dto.setName(elName.map(o -> o.getStringValue()).orElse(""));
        dto.setHeight(elHeight.map(o -> o.getStringValue()).orElse(""));
        dto.setWidth(elWidth.map(o -> o.getStringValue()).orElse(""));
        dto.setHidden(Boolean.valueOf(elHidden.map(o -> o.getStringValue()).orElse("false")));
        dto.setLock(Boolean.valueOf(elLock.map(o -> o.getStringValue()).orElse("false")));
        dto.setMaxsize(elMaxSize.map(o -> o.getStringValue()).orElse(""));
        
        String vertical = elAlign.flatMap(o -> Optional.ofNullable(o.attributeValue("vertical"))).orElse("");
        dto.setAlignVertical(vertical);
        
        String horizontal = elAlign.flatMap(o -> Optional.ofNullable(o.attributeValue("horizontal"))).orElse("");
        dto.setAlignHorizontal(horizontal);
        
        String type = elCellType.flatMap(o -> Optional.ofNullable(o.attributeValue("type"))).orElse("");
        dto.setType(type);
        
        String delimiter = elCellType.flatMap(o -> Optional.ofNullable(o.attributeValue("delimiter"))).orElse("false");
        dto.setDelimiter(Boolean.valueOf(delimiter));
        
        String yen = elCellType.flatMap(o -> Optional.ofNullable(o.attributeValue("yen"))).orElse("false");
        dto.setYen(Boolean.valueOf(yen));
        
        String lower = elCellType.flatMap(o -> Optional.ofNullable(o.attributeValue("lower"))).orElse("");
        dto.setLower(lower);
        
        String upper = elCellType.flatMap(o -> Optional.ofNullable(o.attributeValue("upper"))).orElse("");
        dto.setUpper(upper);
        
        String decimal = elCellType.flatMap(o -> Optional.ofNullable(o.attributeValue("decimal"))).orElse("");
        dto.setDecimal(decimal);
    }
}
