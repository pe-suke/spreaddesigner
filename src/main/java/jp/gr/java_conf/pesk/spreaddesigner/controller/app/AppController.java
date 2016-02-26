package jp.gr.java_conf.pesk.spreaddesigner.controller.app;


import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.WeakListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import jp.gr.java_conf.pesk.spreaddesigner.App;
import jp.gr.java_conf.pesk.spreaddesigner.enums.Aligns;
import jp.gr.java_conf.pesk.spreaddesigner.enums.CellTypes;
import jp.gr.java_conf.pesk.spreaddesigner.spread.dto.ColumnDto;
import jp.gr.java_conf.pesk.spreaddesigner.spread.dto.ControllDto;
import jp.gr.java_conf.pesk.spreaddesigner.spread.elements.ColumnElement;
import jp.gr.java_conf.pesk.spreaddesigner.spread.elements.ControlElement;
import jp.gr.java_conf.pesk.spreaddesigner.spread.elements.FormElement;
import jp.gr.java_conf.pesk.spreaddesigner.spread.elements.RowElement;
import jp.gr.java_conf.pesk.spreaddesigner.spread.utils.ConvertUtils;

public class AppController implements Initializable {

    @FXML
    private ComboBox<String> cellTypeComboBox;
    
    @FXML
    private ComboBox<String> alignVerticalComboBox;
    
    @FXML
    private ComboBox<String> alignHorizontalComboBox;
    
    @FXML
    private TextField bgColorTextField;
    
    @FXML
    private ColorPicker bgColorPicker;
    
    @FXML
    private ComboBox<String> formComboBox;
    
    @FXML
    private ComboBox<String> controlComboBox;
    
    @FXML
    private TableView<RowElement> spreadSheet;
    
    private Map<String ,FormElement> formMap;
    
    @FXML
    private TextField label;
    
    @FXML
    private TextField name;
    
    @FXML
    private TextField height;
    
    @FXML
    private TextField width;
    
    @FXML
    private CheckBox hidden;
    
    @FXML
    private CheckBox lock;
    
    @FXML
    private TextField maxsize;
    
    @FXML
    private CheckBox delimiter;
    
    @FXML
    private CheckBox yen;
    
    @FXML
    private TextField lower;
    
    @FXML
    private TextField upper;
    
    @FXML
    private TextField decimal;
    
    @FXML
    private TextField controlName;
    
    @FXML
    private TextField controlIndex;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // set items to the align vertical combobox
        alignVerticalComboBox.getItems().setAll(Aligns.toArray());
        
        // set items to the align horizontal combobox.
        alignHorizontalComboBox.getItems().setAll(Aligns.toArray());
        
        // set items to the cell type combobox.
        cellTypeComboBox.getItems().setAll(CellTypes.toArray());
        
        // add focus listener to bgColorTextField
        bgColorTextField.focusedProperty().addListener((o, old, newval) -> {if(!newval) onBgColorTextFieldFocusOut();});
        
        // set cell selection mode
        spreadSheet.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        spreadSheet.getSelectionModel().setCellSelectionEnabled(true);
        
        // set event hander that call onColumnClicked method
        spreadSheet.setOnMouseClicked(e -> onColumnClicked(e));
        
        formComboBox.getItems().add("");
        controlComboBox.getItems().add("");
    }
    
    /**
     * Exit Application by Pressing MenuItems on File menu.
     * @param event
     * 
     * */
    @FXML
    public void exitApplication(ActionEvent event) {
        Platform.exit();
    }
    
    
    /**
     * Open Dialog for selecting SpreadConfig.xml
     * 
     * */
    @SuppressWarnings("unchecked")
    @FXML
    public void openSpreadConfig(ActionEvent event) {
        // Remove instances from SpreadConfig and instantiate new form map.
        cleanUpSpreadConfig();
        
        // clear formComboBox
        formComboBox.getItems().clear();
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select your SpreadConfig.xml");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().add(new ExtensionFilter("XML","*.xml"));
        
        File selectedFile = fileChooser.showOpenDialog(App.getApplicationStage());
        
        if (selectedFile != null) {
            SAXReader saxReader = new SAXReader();
            try{
                Document documentRoot = saxReader.read(selectedFile);
                Element rootElement = documentRoot.getRootElement();
                List<Element> formElements = rootElement.elements();
                
                for (Element form : formElements) {
                    FormElement formElement = new FormElement(form);
                    
                    String formName = formElement.getName();
                    formMap.put(formName, formElement);
                    
                    formComboBox.getItems().add(formName);
                    
                }
                
            } catch(DocumentException e) {
                e.printStackTrace();
            }
            
            System.out.println("selected!!");
        } else {
            System.out.println("cancelled!!");
        }
        
    }
    
    /**
     * Auto set the value to the bgColorTextField when selecting color from ColorPicker.
     * @param event
     * */
    @FXML
    public void onBgColorPickerHidden(ActionEvent event) {
        Optional.ofNullable(bgColorPicker.getValue()).ifPresent(selectedColor -> {
            String color = selectedColor.toString();
            color = StringUtils.replace(color, "0x", "#");
            color = StringUtils.left(color, 7);
            color = StringUtils.upperCase(color);
            
            bgColorTextField.setText(color);
        });
    }
    
    /**
     * Auto set the values to bgColorPicker when the focus removed from bgColorTextField.
     * 
     * */
    @FXML
    public void onBgColorTextFieldFocusOut() {
        Optional.ofNullable(bgColorTextField.getText()).ifPresent(color -> {
            Color webColor = Color.web(color);
            bgColorPicker.setValue(webColor);
        });
    }
    
    /**
     * set control value when change the value in formComboBox
     * 
     * */
    @FXML
    public void onChangeForm() {
        String selectedForm = formComboBox.getValue();
        
        Optional.ofNullable(formMap.get(selectedForm)).ifPresent(form -> {
            Map<String, ControlElement> controlMap = form.getControlMap();
            
            Stream<String> keyStream = controlMap.keySet().stream();
            
            // remove and set again
            controlComboBox.getItems().clear();
            controlComboBox.getItems().add("");
            controlComboBox.getItems().addAll(keyStream.sorted((o1, o2) -> o1.compareTo(o2)).toArray(o -> new String[o]));
        });
    }
    
    /**
     * clean up spreadConfig information
     * 
     * */
    private void cleanUpSpreadConfig() {
        //FIXME may be a memory leak.
        formMap = new HashMap<String, FormElement>();
    }
    
    /**
     * set values to treeTableView
     * @param event
     * */
    public void setSpreadSheet(ActionEvent event) {
        // clear spread.
        clearSpreadSheet();
        
        // create column list that will be shown on the screen.
        List<TableColumn<RowElement, String>> spreadColumnList = new LinkedList<>();
        
        // retrieve spread config
        String selectedForm = formComboBox.getValue();
        FormElement formElement = formMap.get(selectedForm);
        Map<String, ControlElement> controlMap = formElement.getControlMap();
        String selectedControl = controlComboBox.getValue();
        ControlElement spread = controlMap.get(selectedControl);
        
        if (spread != null) {
            // set column text to all columns that will be shown on the screen.
            Collection<ColumnElement> columnMapCollection = spread.getColumnMap().values();
            columnMapCollection.stream()
                               .sorted((o1, o2) -> o1.getName().getStringValue().compareTo(o2.getName().getStringValue()))
                               .forEach(o -> {
                                   TableColumn<RowElement, String> spreadColumn = new TableColumn<>();
                                   spreadColumn.setId(o.getName().getStringValue());
                                   spreadColumn.setText(o.getLabel().getStringValue());
                                   spreadColumn.setCellValueFactory(param -> param.getValue().dummyProperty());
                                   spreadColumn.setSortable(false);
                                   spreadColumnList.add(spreadColumn);
                               });
            
            // set columns
            spreadSheet.getColumns().setAll(spreadColumnList);
            
            // set dummy rows
            ObservableList<RowElement> rowList = FXCollections.observableArrayList(new RowElement(), new RowElement());
            spreadSheet.setItems(rowList);
            
            // set values to all fields as clicked first column.
            setValuesToAllFields("column000");
        }
    }
    
    private void clearSpreadSheet() {
        // clear spread.
        spreadSheet.getColumns().clear();
        spreadSheet.getItems().clear();
    }
    
    /**
     * capture click event on spread sheet
     * 
     * */
    private void onColumnClicked(MouseEvent event) {
        PickResult pickResult = event.getPickResult();
        Node node = pickResult.getIntersectedNode();
        String clickedColumnId = node.getId();
        
        setValuesToAllFields(clickedColumnId);
    }
    
    private void setValuesToAllFields(String clickedColumnId) {
        // initialize all fields
        spreadSheet.getSelectionModel().clearSelection();
        initializeAllFields();
        
        // get column info
        FormElement formElement = formMap.get(formComboBox.getValue());
        Map<String, ControlElement> controlMap = formElement.getControlMap();
        ControlElement controlElement = controlMap.get(controlComboBox.getValue());
        
        if (controlElement != null && StringUtils.isNotEmpty(clickedColumnId)) {
            Map<String, ColumnElement> columnMap = controlElement.getColumnMap();
            ColumnElement columnElement = columnMap.get(clickedColumnId);
            
            // set values to all fields from selected control.
            ControllDto controllDto = new ControllDto();
            ConvertUtils.convertControlElementToDto(controlElement, controllDto);
            setValuesToControlFields(controllDto);
            
            // set values to all fields from selected column.
            ColumnDto columnDto = new ColumnDto();
            ConvertUtils.convertColumnElementToDto(columnElement, columnDto);
            setValuesToColumnFields(columnDto);
            onBgColorTextFieldFocusOut();
            
            // all rows of selected column will be changed color.
            changeSpreadSheetSelection(clickedColumnId);
        }
    }
    
    private void changeSpreadSheetSelection(String clickedColumnId) {
        //FIXME investigate a way to retrieve columnIndex.
        int columnIndex = 0;
        String columnIndexStr = StringUtils.replace(clickedColumnId, "column", "");
        while(true) {
            try {
                columnIndex = Integer.parseInt(columnIndexStr);
                break;
            } catch(NumberFormatException e) {
                columnIndexStr = StringUtils.replaceOnce(columnIndexStr, "0", "");
            }
        }
        spreadSheet.getSelectionModel().selectRange(0, spreadSheet.getColumns().get(columnIndex), 1, spreadSheet.getColumns().get(columnIndex));
    }
    
    private void setValuesToControlFields(ControllDto dto) {
        controlName.setText(dto.getControlName());
        controlIndex.setText(dto.getControlIndex());
    }
    
    private void setValuesToColumnFields(ColumnDto dto) {
        label.setText(dto.getLabel());
        bgColorTextField.setText(dto.getBgColor());
        name.setText(dto.getName());
        height.setText(dto.getHeight());
        width.setText(dto.getWidth());
        hidden.setSelected(dto.isHidden());
        lock.setSelected(dto.isLock());
        maxsize.setText(dto.getMaxsize());
        alignVerticalComboBox.setValue(dto.getAlignVertical());
        alignHorizontalComboBox.setValue(dto.getAlignHorizontal());
        cellTypeComboBox.setValue(dto.getType());
        delimiter.setSelected(dto.isDelimiter());
        lower.setText(dto.getLower());
        upper.setText(dto.getUpper());
        decimal.setText(dto.getDecimal());
    }
    
    private void initializeAllFields() {
        controlName.setText("");
        controlIndex.setText("");
        label.setText("");
        bgColorTextField.setText("");
        name.setText("");
        height.setText("");
        width.setText("");
        hidden.setSelected(false);
        lock.setSelected(false);
        maxsize.setText("");
        alignVerticalComboBox.setValue("");
        alignHorizontalComboBox.setValue("");
        cellTypeComboBox.setValue("");
        delimiter.setSelected(false);
        lower.setText("");
        upper.setText("");
        decimal.setText("");
    }

}
