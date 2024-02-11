package com.project.guiproject.controllers.matches;

import com.project.guiproject.helpers.NavigationHelpers;
import com.project.guiproject.models.Match;
import com.project.guiproject.services.MatchService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class matchController implements Initializable {
    MatchService matchService = new MatchService() {
        @Override
        public Match createMatch(int duration, String name, String description, String code) {
            return null;
        }

        @Override
        public Match getMatchById(int matchId) {
            return null;
        }

        @Override
        public List<Match> getAllMatches() {
            return null;
        }
    };

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

    @FXML
    public Button homeButton;

    @FXML
    public TextField idFilter;

    @FXML
    public TextField nomFilter;

    @FXML
    public ImageView background;

    NavigationHelpers nh = new NavigationHelpers();

    private ObservableList<Match> data;

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
     //   idFilter.textProperty().addListener((observable, oldValue, newValue) -> filter());
       // nomFilter.textProperty().addListener((observable, oldValue, newValue) -> filter());
    }

    public void filter() {
        data = matchService.filter(idFilter.getText(), nomFilter.getText());
        MatchesList.setItems(data);
    }

    private void initButtons() {
        //deleteButton.setVisible(false);
        //updateButton.setVisible(false);
    }

    private void loadMatches() throws SQLException {
        data = FXCollections.observableArrayList(matchService.get());
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        code.setCellValueFactory(new PropertyValueFactory<>("code"));
        duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        MatchesList.setItems(data);
        selectionModel = MatchesList.getSelectionModel();
    }

    @FXML
    public void clickItem(MouseEvent event) {
        event.consume();
        Match temp = selectionModel.getSelectedItem();
        if (temp != null) {
            selectedItem = temp;
            //deleteButton.setVisible(true);
            //updateButton.setVisible(true);
        }
    }



}
