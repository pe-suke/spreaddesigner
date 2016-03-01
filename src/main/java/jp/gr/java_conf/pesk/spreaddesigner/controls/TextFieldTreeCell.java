package jp.gr.java_conf.pesk.spreaddesigner.controls;

import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import jp.gr.java_conf.pesk.spreaddesigner.controller.app.AppController;

public class TextFieldTreeCell extends TreeCell<String>{

    private TextField textField;
    private final AppController controller;
    
    public TextFieldTreeCell(AppController controller) {
        this.controller = controller;
    }
    
    @Override
    public void startEdit() {
        super.startEdit();

        if (textField == null) {
            createTextField();
        }
        setText(null);
//        setGraphic(textField);
        textField.selectAll();
        
        controller.setSpreadSheet(getTreeItem().getParent().getValue(), getTreeItem().getValue());
        
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText((String) getItem());
//        setGraphic(getTreeItem().getGraphic());
    }

    @Override
    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
//            setGraphic(null);
        } else {
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getString());
                }
                setText(null);
//                setGraphic(textField);
            } else {
                setText(getString());
//                setGraphic(getTreeItem().getGraphic());
            }
        }
    }

    private void createTextField() {
        textField = new TextField(getString());
        textField.setOnKeyReleased((KeyEvent t) -> {
            if (t.getCode() == KeyCode.ENTER) {
                commitEdit(textField.getText());
            } else if (t.getCode() == KeyCode.ESCAPE) {
                cancelEdit();
            }
        });
    }

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }
    
}
