package com.example.colorchooser;

import javafx.fxml.FXML;


import javafx.event.ActionEvent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Controller {
    private enum PenSize {
        SMALL(2),
        MEDIUM(4),
        LARGE(6);
        private final int radius;
        PenSize(int radius) {this.radius = radius;}
        public int getRadius() {return radius;}
    }
    @FXML private Slider redSlider;
    @FXML private Slider greenSlider;
    @FXML private Slider blueSlider;
    @FXML private Rectangle colorRectangle;
    @FXML private Pane drawingAreaPane;
    @FXML private ToggleGroup sizeToggleGroup;
    @FXML private RadioButton smallRadioButton;
    @FXML private RadioButton mediumRadioButton;
    @FXML private RadioButton largeRadioButton;
    @FXML private TextField redText;
    @FXML private TextField greenText;
    @FXML private TextField blueText;
    private int red = 0;
    private int green = 0;
    private int blue = 0;
    private PenSize radius = PenSize.MEDIUM;
    private Paint brushColor = Color.BLACK;
    public void initialize() {
        smallRadioButton.setUserData(PenSize.SMALL);
        mediumRadioButton.setUserData(PenSize.MEDIUM);
        largeRadioButton.setUserData(PenSize.LARGE);
        redSlider.valueProperty().addListener(
                (ov, oldValue, newValue) -> {
                    red = newValue.intValue();
                    update();
                    redText.setText(String.valueOf(red));
                }
        );
        greenSlider.valueProperty().addListener(
                (ov, oldValue, newValue) -> {
                    green = newValue.intValue();
                    update();
                    greenText.setText(String.valueOf(green));
                }
        );
        blueSlider.valueProperty().addListener(
                (ov, oldValue, newValue) -> {
                    blue = newValue.intValue();
                    update();
                    blueText.setText(String.valueOf(blue));
                }
        );
        redText.textProperty().addListener((observable, oldValue, newValue) -> {
            int red;
            try {red = Integer.parseInt(newValue.trim());}
            catch (Exception ex) {red = 0;}
            if (red < 0 || red > 255) red = 0;
            this.red = red;
            redSlider.setValue(red);
            update();
        });
        greenText.textProperty().addListener((arg0, oldValue, newValue) -> {
            int green;
            try {green = Integer.parseInt(newValue.trim());}
            catch (Exception ex) {green = 0;}
            if (green < 0 || green > 255) green = 0;
            this.green = green;
            greenSlider.setValue(green);
            update();
        });
        blueText.textProperty().addListener((arg0, oldValue, newValue) -> {
            int blue;
            try {blue = Integer.parseInt(newValue.trim());}
            catch (Exception ex) {blue = 0;}
            if (blue < 0 || blue > 255) blue = 0;
            this.blue = blue;
            blueSlider.setValue(blue);
            update();
        });
    }
    @FXML
    private void drawingAreaMouseDragged(MouseEvent e) {
        Circle newCircle = new Circle(e.getX(), e.getY(), radius.getRadius(), brushColor);
        drawingAreaPane.getChildren().add(newCircle);
    }
    @FXML
    private void sizeRadioButtonSelected(ActionEvent e) {
        radius = (PenSize) sizeToggleGroup.getSelectedToggle().getUserData();
    }
    @FXML
    private void undoButtonPressed(ActionEvent event) {
        int count = drawingAreaPane.getChildren().size();
        if (count > 0) { drawingAreaPane.getChildren().remove(count - 1); }
    }
    @FXML
    private void clearButtonPressed(ActionEvent event) { drawingAreaPane.getChildren().clear(); }
    void update() {
        colorRectangle.setFill(Color.rgb(red, green, blue, 1));
        brushColor = Color.rgb(red, green, blue);
    }
}