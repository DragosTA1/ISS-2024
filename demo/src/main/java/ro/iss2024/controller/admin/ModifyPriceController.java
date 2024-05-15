package ro.iss2024.controller.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ro.iss2024.domain.SeatCategory;
import ro.iss2024.observer.Observer;
import ro.iss2024.controller.utils.MessageAlert;
import ro.iss2024.service.Service;

import java.sql.SQLException;
import java.util.List;

public class ModifyPriceController implements Observer {

    private Service service;

    @FXML
    private TableView<SeatCategory> tblSeatCategory;
    @FXML
    private TableColumn<SeatCategory, String> lblLodge;
    @FXML
    private TableColumn<SeatCategory, Double> lblPrice;

    private ObservableList<SeatCategory> model = FXCollections.observableArrayList();

    @FXML
    private TextField txtPrice;

    public void setService(Service service) throws SQLException {
        this.service = service;
        service.registerObserver(this);
        initModel();
        initialize();
    }

    private void initModel() throws SQLException {
        List<SeatCategory> seats = service.getSeatsCategory();
        model.setAll(seats);
    }

    @FXML
    public void initialize() {
        lblLodge.setCellValueFactory(new PropertyValueFactory<>("name"));
        lblPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        tblSeatCategory.setItems(model);
    }

    public void handleSave(ActionEvent actionEvent) {
        SeatCategory seatCategory = tblSeatCategory.getSelectionModel().getSelectedItem();
        if (seatCategory == null) {
            MessageAlert.showErrorMessage(null, "You must select a seat category!");
            return;
        }
        try {
            double price = Double.parseDouble(txtPrice.getText());
            seatCategory.setPrice(price);
            service.updateSeatCategory(seatCategory);
        } catch (NumberFormatException e) {
            MessageAlert.showErrorMessage(null, "Price must be a number!");
        }
    }

    @Override
    public void update() throws SQLException {
        initModel();
    }
}
