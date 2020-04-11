package application;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
			Pane pane = (Pane) loader.load();
			MainController mainController = loader.getController();
			Scene scene = new Scene(pane, 800, 600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
				public void handle(KeyEvent ke) {
					mainController.hotKeys(ke);
				}
			});

			primaryStage.setScene(scene);
			primaryStage.setTitle("¯ó®Ñ¤j®v");
			primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("image/img.png")));
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		loadFont();
		launch(args);
	}
	
	public static void loadFont() {
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, Main.class.getClassLoader().getResourceAsStream("font/KouzanBrushFontSousyo.ttf")));
			for (String string : ge.getAvailableFontFamilyNames()) {
//				System.out.println(string);
			}
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
	}
	
}
