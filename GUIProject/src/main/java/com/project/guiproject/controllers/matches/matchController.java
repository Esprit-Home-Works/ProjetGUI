package com.project.guiproject.controllers.matches;

import com.project.guiproject.helpers.NavigationHelpers;
import com.project.guiproject.models.Match;
import com.project.guiproject.services.MatchService;
import com.project.guiproject.test.Storage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class matchController implements Initializable {
    MatchService matchService = new MatchService();

    Match selectedItem;

    TableView.TableViewSelectionModel<Match> selectionModel;

    @FXML
    private TableView<Match> MatchesList;

    @FXML
    public TableColumn<Match, String> name;

    @FXML
    public TableColumn<Match, String> description;
    @FXML
    public TableColumn<Match, String> code;
    @FXML
    public TableColumn<Match, String> duration;
    @FXML
    public TableColumn<Match, String> startDate;

    @FXML
    public TableColumn<Match, String> endDate;

    @FXML
    public Button deleteButton;

    @FXML
    public Button updateButton;

    @FXML
    public Button addButton;

    /*@FXML
    public Button homeButton;*/

    @FXML
    public TextField idFilter;

    @FXML
    public TextField nomFilter;

    @FXML
    public ImageView background;

    NavigationHelpers nh = new NavigationHelpers();

    private ArrayList<Match> data;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            loadMatches();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        initButtons();
        initFilters();
        Image image1 = new Image("file:C:\\Users\\RaedCHARRAD\\Desktop\\esprit\\ProjetGUI\\GUIProject\\src\\main\\java\\com\\project\\guiproject\\controllers\\matches\\back.jpg");
        background.setImage(image1);
    }

    private void initFilters() {
        // add listener to the filter fields
        idFilter.textProperty().addListener((observable, oldValue, newValue) -> filter());
        nomFilter.textProperty().addListener((observable, oldValue, newValue) -> filter());
    }

    public void filter() {
        data = matchService.filter(nomFilter.getText() , idFilter.getText());
        MatchesList.setItems(FXCollections.observableArrayList(data));
    }
    @FXML
    public void clearIdFilter() {
        idFilter.clear();
    }
    @FXML
    public void clearNomFilter() {
        nomFilter.clear();
    }

    private void initButtons() {
        deleteButton.setVisible(false);
        updateButton.setVisible(false);
    }

    private void loadMatches() throws SQLException {
        data = (ArrayList<Match>) matchService.get();
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        code.setCellValueFactory(new PropertyValueFactory<>("code"));
        duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        MatchesList.setItems(FXCollections.observableArrayList(data));
        selectionModel = MatchesList.getSelectionModel();
    }

    @FXML
    public void clickItem(MouseEvent event) {
        event.consume();
        Match temp = selectionModel.getSelectedItem();
        if (temp != null) {
            selectedItem = temp;
            deleteButton.setVisible(true);
            updateButton.setVisible(true);
        }
    }
    @FXML
    public void deleteButtonClicked(ActionEvent ignoredEv) {
        if (selectedItem != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Supprimer classe");
            alert.setContentText("Êtes-vous sûr de vouloir supprimer cette classe ("+ selectedItem.getName() + ") ?");
            alert.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response -> {
                try {
                    matchService.delete(selectedItem.getId());
                    loadMatches();
                    selectionModel.clearSelection();
                    deleteButton.setVisible(false);
                    updateButton.setVisible(false);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        }
    }
    @FXML
    public void updateButtonClicked(ActionEvent ignoredEv) {
        if (selectedItem != null) {
            Pane ctrl;
            try {
                Storage.Match.code = selectedItem.getCode();
                ctrl = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/project/guiproject/UpsertMatches.fxml")));
                nh.navigate(addButton, "Modifier classe", ctrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    public void addButtonClicked(ActionEvent ignoredEv) {
        Pane ctrl;
        try {
            ctrl = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/project/guiproject/UpsertMatches.fxml")));
            nh.navigate(addButton, "Ajouter classe", ctrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
