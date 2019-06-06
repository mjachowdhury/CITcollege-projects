package clock;

/**
 * Name - Mohammed Jahanigr Alom
 * Student No - R00144214
 * Lab No 3
 */
import java.text.DateFormat;
import java.util.Calendar;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/*
 * This class is main class where ClockPane class was called 
 */
public class DisplayClock extends Application {

	public static void main(String[] args) {
		Application.launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		BorderPane borderPane = new BorderPane();

		Label dateTime = new Label();
		DateFormat format = DateFormat.getInstance();

		final Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), event -> {
			// ClockPane object was created
			ClockPane clock = new ClockPane();
			// Setting the clock in the middle on the borderPane
			borderPane.setCenter(clock);

			String dt = "CURRENT DATE & TIME : ";

			Calendar cal = Calendar.getInstance();
			// Setting the date on the label
			dateTime.setText(dt + format.format(cal.getTime()));

		}));

		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		// Setting the local date and time bottom on the borderPane
		borderPane.setBottom(dateTime);

		BorderPane.setAlignment(dateTime, Pos.BOTTOM_CENTER);

		Scene scene = new Scene(borderPane, 350, 400);// Scene size

		primaryStage.setTitle("DISPLAY CLOCK");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

}
