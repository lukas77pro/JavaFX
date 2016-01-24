package application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class Main extends Application {

	private double size;

	@Override
	public void start(Stage primaryStage) {
		try {
			HBox bottombar = new HBox();

			TextField clicks = new TextField();
			clicks.setText(String.valueOf(0));
			clicks.setEditable(false);
			clicks.setFocusTraversable(false);
			clicks.setPrefSize(100.0, 50.0);

			Label label = new Label("Kliknięcia:");
			label.setPrefSize(100.0, 50.0);
			label.setTextAlignment(TextAlignment.CENTER);

			Reset reset = new Reset();
			reset.setText("RESET");
			reset.setPrefSize(200.0, 50.0);

			bottombar.getChildren().add(label);
			bottombar.getChildren().add(clicks);
			bottombar.getChildren().add(reset);

			BorderPane root = new BorderPane();
			GridPane grid = new GridPane();
			Board board = new Board(grid, clicks);

			reset.setDad(board);
			reset.addEventHandler(MouseEvent.MOUSE_CLICKED,
					new EventHandler<MouseEvent>() {
			     @Override
			     public void handle(MouseEvent event) {
			         Reset reset = (Reset) event.getTarget();
			         reset.getDad().reset();
			         event.consume();
			     }
			});

			grid.setVgap(2.0);
			grid.setHgap(2.0);

			Image img = new Image("obrazek2.jpg");

			for (int i=0; i<15; i++) {
				int x = i%4;
				int y = i/4;

				Partial imageView = new Partial(img, i, x, y);

				Rectangle2D rect = new Rectangle2D(
						x*100.0, y*100.0, 100.0, 100.0);


				imageView.setViewport(rect);

				imageView.addEventHandler(MouseEvent.MOUSE_CLICKED,
						new EventHandler<MouseEvent>() {
				     @Override
				     public void handle(MouseEvent event) {
				         Partial part = (Partial) event.getTarget();
				         part.getDad().change(part);
				         if (part.getDad().checkVicotory()) {
				        	 System.out.println("Wiktory!");
				         }
				         event.consume();
				     }
				});

				board.add(imageView);
				grid.add(imageView, x, y);

			}

			root.setBottom(bottombar);
			root.setCenter(grid);

			Scene scene = new Scene(root);
			primaryStage.setTitle("Gierka:)");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();


		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
