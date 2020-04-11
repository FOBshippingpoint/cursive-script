package application;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;

public class MainController {

	@FXML
	private TextArea textArea;

	@FXML
	private ComboBox<Integer> sizeCombo;

	@FXML
	private TextField sizeTextField;

	@FXML
	private CheckBox sousyoCheck;

	String sousyoFontName = Font
			.loadFont(MainController.class.getClassLoader().getResourceAsStream("font/KouzanBrushFontSousyo.ttf"), 48)
			.getName();
	String defaultFontName = Font.getDefault().getName();

	public void initialize() {
		ObservableList<Integer> options = FXCollections.observableArrayList(12, 14, 16, 18, 20, 24, 28, 32, 48,
				64, 72, 96, 144);
		sizeCombo.setItems(options);
		sizeCombo.getSelectionModel().select(10);
		sizeTextField.setText(sizeCombo.getValue() + "");
		changeStyle((sousyoCheck.isSelected() ? sousyoFontName : defaultFontName), sizeCombo.getValue());
		Platform.runLater(new Runnable() {
			public void run() {
				textArea.requestFocus();
			}
		});
	}

	public void hotKeys(KeyEvent ke) {
		final KeyCombination kcSwitchSousyo = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
		final KeyCode kTextBigger = KeyCode.PAGE_UP;
		final KeyCode kTextSmaller = KeyCode.PAGE_DOWN;
		final KeyCode kHelp = KeyCode.F1;

		if (kcSwitchSousyo.match(ke)) {
			sousyoCheck.setSelected(!sousyoCheck.isSelected());
			changeSousyo(new ActionEvent());
		}  else if (kTextBigger == ke.getCode()) {
			sizeCombo.getSelectionModel().selectNext();;
		} else if (kTextSmaller == ke.getCode()) {
			sizeCombo.getSelectionModel().selectPrevious();;
		} else if (kHelp == ke.getCode()) {
			System.out.println("hello");
			Alert alert = new Alert(AlertType.NONE, "Ctrl + S 切換字型\nPageUp: 放大字體\nPageDown: 縮小字體",
					ButtonType.OK);
			alert.setTitle("說明");
			alert.show();
		}
	}

	@FXML
	void changeSizeCombo(ActionEvent event) {
		sizeTextField.setText(String.valueOf(sizeCombo.getValue()));
		changeStyle((sousyoCheck.isSelected() ? sousyoFontName : defaultFontName), sizeCombo.getValue());
	}

	@FXML
	void changeSizeText(ActionEvent event) {
		changeStyle((sousyoCheck.isSelected() ? sousyoFontName : defaultFontName),
				Integer.parseInt(sizeTextField.getText()));
	}

	@FXML
	void changeSousyo(ActionEvent event) {
		changeStyle((sousyoCheck.isSelected() ? sousyoFontName : defaultFontName),
				Integer.parseInt(sizeTextField.getText()));
	}

	void changeStyle(String fontName, int fontSize) {
		textArea.setStyle(" -fx-font-family: " + fontName + ";" + "-fx-font-size: " + fontSize / 12.0 + "em;");
	}

}