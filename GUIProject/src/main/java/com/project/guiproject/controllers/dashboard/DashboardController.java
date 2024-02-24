package com.project.guiproject.controllers.dashboard;

import com.project.guiproject.helpers.NavigationHelpers;
import com.project.guiproject.services.MatchService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DashboardController implements Initializable{

    @FXML
    public Label MatchesCount;

    @FXML
    public ImageView background;

    @FXML
    public LineChart<Number, Number> chart;

    private final MatchService MService = new MatchService();
    public NavigationHelpers nh = new NavigationHelpers();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        MatchesCount.setText(String.valueOf(MService.count()));

        Image image1 = new Image("file:C:/Users/RaedCHARRAD/Desktop/esprit/ProjetGUI/GUIProject/src/main/java/com/project/guiproject/controllers/back.jpg");
        background.setImage(image1);

        chart.getData().add(MService.getChartData());


    }

    public void goToPage(String pageName, String pagePath) {

        Pane ctrl;
        try {
            ctrl = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(pagePath)));
            nh.navigate(MatchesCount, pageName, ctrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToMatches() {
        goToPage("Gérer les Matches", "/com/project/guiproject/ManageMatches.fxml");
    }


}
