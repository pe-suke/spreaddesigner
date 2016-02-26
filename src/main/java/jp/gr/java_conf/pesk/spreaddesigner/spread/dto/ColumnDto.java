package jp.gr.java_conf.pesk.spreaddesigner.spread.dto;

public class ColumnDto {
    
    private String label;
    private String bgColor;
    private String name;
    private String height;
    private String width;
    private boolean hidden;
    private boolean lock;
    private String maxsize;
    private String alignVertical;
    private String alignHorizontal;
    private String type;
    private boolean delimiter;
    private boolean yen;
    private String lower;
    private String upper;
    private String decimal;
    
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public String getBgColor() {
        return bgColor;
    }
    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getHeight() {
        return height;
    }
    public void setHeight(String height) {
        this.height = height;
    }
    public String getWidth() {
        return width;
    }
    public void setWidth(String width) {
        this.width = width;
    }
    public boolean isHidden() {
        return hidden;
    }
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
    public boolean isLock() {
        return lock;
    }
    public void setLock(boolean lock) {
        this.lock = lock;
    }
    public String getMaxsize() {
        return maxsize;
    }
    public void setMaxsize(String maxsize) {
        this.maxsize = maxsize;
    }
    public String getAlignVertical() {
        return alignVertical;
    }
    public void setAlignVertical(String alignVertical) {
        this.alignVertical = alignVertical;
    }
    public String getAlignHorizontal() {
        return alignHorizontal;
    }
    public void setAlignHorizontal(String alignHorizontal) {
        this.alignHorizontal = alignHorizontal;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public boolean isDelimiter() {
        return delimiter;
    }
    public void setDelimiter(boolean delimiter) {
        this.delimiter = delimiter;
    }
    public boolean isYen() {
        return yen;
    }
    public void setYen(boolean yen) {
        this.yen = yen;
    }
    public String getLower() {
        return lower;
    }
    public void setLower(String lower) {
        this.lower = lower;
    }
    public String getUpper() {
        return upper;
    }
    public void setUpper(String upper) {
        this.upper = upper;
    }
    public String getDecimal() {
        return decimal;
    }
    public void setDecimal(String decimal) {
        this.decimal = decimal;
    }
}
