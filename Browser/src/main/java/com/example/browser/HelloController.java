package com.example.browser;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;
public class HelloController implements Initializable {
    @FXML
    private WebView webView;
    @FXML
    private TextField textField;
    private WebHistory history;
    private WebEngine engine;
    private double zoom = 4;

    private String homePage;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        homePage = "www.google.com";
        textField.setText(homePage);
        engine = webView.getEngine();
        loadPage();


        textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    loadPage();
                }
            }
        });
    }

    public void loadPage() {
//        engine.load("http://www.google.com/");
        engine.load("https://"+textField.getText());
    }

    public void refreshPage() {
        engine.reload();
    }

    public void zoomIn() {
        webView.setZoom(zoom++/4);
    }
    public void zoomOut() {
        webView.setZoom(zoom--/4);
    }

    public void displayHistory() {
        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();

        for (WebHistory.Entry entry: entries) {
            System.out.println(entry);
        }
    }

    public void back() {
        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();
        history.go(-1);
        textField.setText(entries.get(history.getCurrentIndex()).getUrl());
    }
    public void forward() {
        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();
        history.go(1);
        textField.setText(entries.get(history.getCurrentIndex()).getUrl());
    }
}
