package hotel;

import java.net.URL;
import java.util.ResourceBundle;

import hotel.SQLite;
import hotel.Vendeg;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

public class NezetController implements Initializable {

	private final String MENU_Menü = "Menü";

	private final String MENU_Szobák = "Szobák";

	private final String MENU_Konvertálás = "Konvertálás";

	@FXML
	private Label label;
	@FXML
	private TextField addVezeteknev;
	@FXML
	private TextField addKeresztnev;
	@FXML
	private TextField addCim;
	@FXML
	private TextField addTelszam;
	@FXML
	private TextField addBejelentkezes;
	@FXML
	private TextField addKijelentkezes;
	@FXML
	private ComboBox<String> addSzobaszam;
	@FXML
	private ComboBox<String> addSzobatipus;
	@FXML
	private ComboBox<String> addFo;
	@FXML
	private TextField addPdf;
	@FXML
	private Button exportButton;
	@FXML
	private Pane exportPane;
	@FXML
	private Pane tablePane;
	@FXML
	private Pane menuPane;
	@FXML
	private Pane singlePane;
	@FXML
	private Pane doublePane;
	@FXML
	private Pane apartmentPane;

	@FXML
	private TextField editVezeteknev;
	@FXML
	private TextField editKeresztnev;
	@FXML
	private TextField editCim;
	@FXML
	private TextField editTelszam;
	@FXML
	private TextField editBejelentkezes;
	@FXML
	private TextField editKijelentkezes;
	@FXML
	private ComboBox<String> editSzobaszam;
	@FXML
	private ComboBox<String> editSzobatipus;
	@FXML
	private ComboBox<String> editFo;
	@FXML
	private TextField search;

	@FXML
	private TableView<Vendeg> table;

	@FXML
	private TableColumn<Vendeg, String> tID;
	@FXML
	private TableColumn<Vendeg, String> tVezeteknev;
	@FXML
	private TableColumn<Vendeg, String> tKeresztnev;
	@FXML
	private TableColumn<Vendeg, String> tCim;
	@FXML
	private TableColumn<Vendeg, Number> tTelszam;
	@FXML
	private TableColumn<Vendeg, String> tBejelentkezes;
	@FXML
	private TableColumn<Vendeg, String> tKijelentkezes;
	@FXML
	private TableColumn<Vendeg, String> tSzobaszam;
	@FXML
	private TableColumn<Vendeg, String> tSzobatipus;
	@FXML
	private TableColumn<Vendeg, String> tFo;

	@FXML
	public void addGuest(ActionEvent event) {
		database.addVendeg(

				addVezeteknev.getText(), addKeresztnev.getText(), addCim.getText(),
				Integer.parseInt(addTelszam.getText()), addBejelentkezes.getText(), addKijelentkezes.getText(),
				addSzobaszam.getValue(), addSzobatipus.getValue(), addFo.getValue());

		tableRefresh();

	}

	@FXML
	public void editSzerkeszt(ActionEvent event) {

		database.updateVendeg(selectedID, editVezeteknev.getText(), editKeresztnev.getText(), editCim.getText(),
				Integer.parseInt(editTelszam.getText()), editBejelentkezes.getText(), editKijelentkezes.getText(),
				editSzobaszam.getValue(), editSzobatipus.getValue(), editFo.getValue());

		tableRefresh();
	}

	@FXML
	public void searchTableData() {
		search.setOnKeyPressed(event -> {
			FilteredList<Vendeg> filteredData = new FilteredList<>(data, p -> true);
			search.textProperty().addListener((observable, oldValue, newValue) -> {
				filteredData.setPredicate(myObject -> {
					if (newValue == null || newValue.isEmpty()) {
						return true;
					}
					String lowerCaseFilter = newValue.toLowerCase();

					if (String.valueOf(myObject.getVezeteknev()).toLowerCase().contains(lowerCaseFilter)) {
						return true;
					}
					return false;
				});
			});
			SortedList<Vendeg> sortedData = new SortedList<>(filteredData);
			sortedData.comparatorProperty().bind(table.comparatorProperty());
			table.setItems(sortedData);
		});
	}

	@FXML
	public void resetButton(ActionEvent event) {
		addVezeteknev.clear();
		addKeresztnev.clear();
		addCim.clear();
		addTelszam.clear();
		addBejelentkezes.clear();
		addKijelentkezes.clear();
		addSzobaszam.getSelectionModel().clearSelection();
		addSzobatipus.getSelectionModel().clearSelection();
		addFo.getSelectionModel().clearSelection();
	}

	private void setMenu() {

		Menu menu1 = new Menu(MENU_Menü);
		Menu menu2 = new Menu(MENU_Szobák);
		Menu menu3 = new Menu(MENU_Konvertálás);

		MenuBar menuBar = new MenuBar();

		menuBar.setId("menuBar");

		menuBar.getMenus().add(menu1);
		menuBar.getMenus().add(menu2);
		menuBar.getMenus().add(menu3);

		MenuItem menuItem1 = new MenuItem("Táblanézet");
		menu1.getItems().add(menuItem1);

		MenuItem menuItem2 = new MenuItem("Kilépés"); 
		menu1.getItems().add(menuItem2);

		MenuItem menuItem3 = new MenuItem("Egyágyas szobák"); 
		menu2.getItems().add(menuItem3);

		MenuItem menuItem3a = new MenuItem("Kétágyas szobák"); 
		menu2.getItems().add(menuItem3a);

		MenuItem menuItem3b = new MenuItem("Apartman"); 
		menu2.getItems().add(menuItem3b);

		MenuItem menuItem4 = new MenuItem("PDF konvertálás"); 
		menu3.getItems().add(menuItem4);

		HBox hBox = new HBox(menuBar);
		menuPane.getChildren().add(hBox);

		menuItem1.setOnAction(new EventHandler<ActionEvent>() { 

			@Override
			public void handle(ActionEvent arg0) {

				exportPane.setVisible(false);
				tablePane.setVisible(true);
				singlePane.setVisible(false);
				doublePane.setVisible(false);
				apartmentPane.setVisible(false);

			}

		});

		menuItem4.setOnAction(new EventHandler<ActionEvent>() { 

			@Override
			public void handle(ActionEvent arg0) {

				exportPane.setVisible(true);
				tablePane.setVisible(false);
				singlePane.setVisible(false);
				doublePane.setVisible(false);
				apartmentPane.setVisible(false);
			}

		});

		menuItem2.setOnAction(new EventHandler<ActionEvent>() { // kilépés

			@Override
			public void handle(ActionEvent arg0) {

				System.exit(0);
			}
		});

		menuItem3.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				singlePane.setVisible(true);
				exportPane.setVisible(false);
				tablePane.setVisible(false);
				doublePane.setVisible(false);
				apartmentPane.setVisible(false);
			}

		});

		menuItem3a.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				doublePane.setVisible(true);
				exportPane.setVisible(false);
				tablePane.setVisible(false);
				singlePane.setVisible(false);
				apartmentPane.setVisible(false);
			}

		});

		menuItem3b.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				apartmentPane.setVisible(true);
				exportPane.setVisible(false);
				tablePane.setVisible(false);
				singlePane.setVisible(false);
				doublePane.setVisible(false);
			}

		});
	}

	private final SQLite database = new SQLite();
	private String selectedID;

	private ObservableList<Vendeg> data = database.getAllGuest();

	private void tableRefresh() {
		data = database.getAllGuest();
		table.setItems(data);
	}

	private void setTableData() {
		tID.setCellValueFactory(cellData -> cellData.getValue().idProperty());
		tVezeteknev.setCellValueFactory(cellData -> cellData.getValue().vezeteknevProperty());
		tKeresztnev.setCellValueFactory(cellData -> cellData.getValue().keresztnevProperty());
		tCim.setCellValueFactory(cellData -> cellData.getValue().cimProperty());
		tTelszam.setCellValueFactory(cellData -> cellData.getValue().telszamProperty());
		tBejelentkezes.setCellValueFactory(cellData -> cellData.getValue().bejelentkezesProperty());
		tKijelentkezes.setCellValueFactory(cellData -> cellData.getValue().kijelentkezesProperty());
		tSzobaszam.setCellValueFactory(cellData -> cellData.getValue().szobaszamProperty());
		tSzobatipus.setCellValueFactory(cellData -> cellData.getValue().szobatipusProperty());
		tFo.setCellValueFactory(cellData -> cellData.getValue().foProperty());
		table.setItems(data);

	}

	private void getValueViaClick() {
		table.setOnMouseClicked(e -> {
			if (!table.getItems().isEmpty()) {
				if (table.getSelectionModel().getSelectedItem() != null) {
					Vendeg t = table.getItems().get(table.getSelectionModel().getSelectedIndex());
					editVezeteknev.setText(t.getVezeteknev());
					editKeresztnev.setText(t.getKeresztnev());
					editCim.setText(t.getCim());
					editTelszam.setText(String.valueOf(t.getTelszam()));
					editBejelentkezes.setText(t.getBejelentkezes());
					editKijelentkezes.setText(t.getKijelentkezes());
					editSzobaszam.setValue(t.getSzobaszam());
					editSzobatipus.setValue(t.getSzobatipus());
					editFo.setValue(t.getFo());
					selectedID = t.getId();
				}
			}
		});

	}

	@FXML
	private void exportLista(ActionEvent event) {

		String fileName = addPdf.getText();
		fileName = fileName.replaceAll("\\s+", "");
		if (fileName != null && !fileName.equals("")) {
			PDFGeneration pdf = new PDFGeneration();
			pdf.pdfGeneration(fileName, data);

		} else {
			System.out.println("hiba");
		}
	}

	@FXML
	private void handleButtonAction(ActionEvent event) {

		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Kapcsolat.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root1));
			stage.setResizable(false);
			stage.show();
		} catch (Exception e) {
			System.out.println("Nem betölthető");
		}

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		setTableData();
		rightClick();
		setMenu();
		getValueViaClick();
		searchTableData();
		addVezeteknev.addEventHandler(KeyEvent.KEY_TYPED, onlyLetter());
		addKeresztnev.addEventHandler(KeyEvent.KEY_TYPED, onlyLetter());
		addCim.addEventHandler(KeyEvent.KEY_TYPED, onlyLetter());
		addTelszam.addEventHandler(KeyEvent.KEY_TYPED, onlyNumber());
		

		addSzobatipus.getItems().add("Egyágyas");
		addSzobatipus.getItems().add("Kétágyas");
		addSzobatipus.getItems().add("Apartman");
		addSzobaszam.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
		addFo.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");

		editSzobatipus.getItems().add("Egyágyas");
		editSzobatipus.getItems().add("Kétágyas");
		editSzobatipus.getItems().add("Apartman");
		editSzobaszam.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
		editFo.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
	}

	private void rightClick() {
		MenuItem torol = new MenuItem("Törlés");
		torol.setOnAction((ActionEvent event) -> {
			Vendeg t = table.getSelectionModel().getSelectedItem();
			String id = t.getId();

			database.removeGuest(id);
			tableRefresh();

		});

		ContextMenu menu = new ContextMenu();
		menu.getItems().add(torol);
		table.setContextMenu(menu);

	}

	public EventHandler<KeyEvent> onlyLetter() {
		return new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent fieldKeyEvent) {
				if (!"abcdeéfghijklmnopqrstuvwxyzáéúőóüöíABCDEFGHIJKLMNOPQRSTUVWXYZÁÉÚŐÓÜÖÍ"
						.contains(fieldKeyEvent.getCharacter())) {
					fieldKeyEvent.consume();
				}
			}

		};

	}
	
	public EventHandler<KeyEvent> onlyNumber() {
		return new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent fieldKeyEvent) {
				if (!"1234567890"
						.contains(fieldKeyEvent.getCharacter())) {
					fieldKeyEvent.consume();
				}
			}

		};

	}

}
