package stopWatch;

/**
 * Name - Mohammed Jahanigr Alom
 * Student No - R00144214
 * Lab No 3
 */

import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class StopWatch extends Application implements EventHandler<ActionEvent> {

	private Label label = new Label("00 : 00 : 00"); // global scope!!!
	public Timer timer = new Timer(10);

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		try {

			final VBox vbox = new VBox(50);
			label.setFont(new Font("Arial", 60));
			vbox.getChildren().addAll(label);
			vbox.setAlignment(Pos.CENTER);
			root.setCenter(vbox);

			// Set up the buttons
			final HBox hbox = new HBox(80);
			final Button b1 = new Button("START");
			b1.setFont(new Font("Verdana", 10));

			/*
			 * binded Threading
			 */
			b1.setOnAction((event) -> {
				Platform.runLater(() -> {
					timer.attach(this);
					timer.start();
				});
			});

			final Button b2 = new Button("STOP");
			b2.setFont(new Font("Verdana", 10));

			b2.setOnAction((event) -> {
				Platform.runLater(() -> {
					timer.attach(this);
					timer.stop();
				});
			});

			final Button b3 = new Button("RESET");
			b3.setFont(new Font("Verdana", 10));

			b3.setOnAction((event) -> {
				Platform.runLater(() -> {
					timer.attach(this);
					timer.reset();
				});
			});

			// final Button b4 = new Button("Change");
			final RadioButton rb1 = new RadioButton();
			rb1.setFont(new Font("Verdana", 10));
			rb1.setStyle("-fx-background-color: RED");
			final RadioButton rb2 = new RadioButton();
			rb2.setFont(new Font("Verdana", 10));
			rb2.setStyle("-fx-background-color: BLUE");

			/*
			 * anonymous class
			 */

			rb1.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					root.setStyle("-fx-background-color: red");
				}
			});
			rb2.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					root.setStyle("-fx-background-color: blue");
				}
			});

			/**
			 * shorter version with lambda expressions lambda expressions
			 */
			// b4.addEventHandler(ActionEvent.ACTION,
			// event -> root.setStyle("-fx-background-color: blue"));

			final ArrayList<Node> node = new ArrayList<>();
			node.add(b1);
			node.add(b2);
			node.add(b3);
			node.add(rb1);
			node.add(rb2);
			hbox.getChildren().addAll(node);
			hbox.setAlignment(Pos.CENTER);
			hbox.setPrefSize(30, 30);
			root.setBottom(hbox);

			Scene scene = new Scene(root, 500, 500);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("STOPWATCH");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void handle(ActionEvent arg0) {
		this.label.setText("H");

	}

	public void update() {
		Platform.runLater(() -> {
			this.label.setText(this.timer.getTimeString());
		});
	}
}
