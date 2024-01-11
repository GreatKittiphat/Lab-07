package ku.cs.lab03;

import javafx.application.Application;
import javafx.stage.Stage;
import ku.cs.lab03.services.FXRouter;

import java.io.IOException;

public class HelloApplication extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		final double WIDTH = 800;
		final double HEIGHT = 600;

		FXRouter.bind(this, stage, "6410401078 - Lab03", WIDTH, HEIGHT);
		configRoute();
		//FXRouter.goTo("hello");
//		FXRouter.goTo("layout");
		FXRouter.goTo("students-table");
	}

	private static void configRoute() {
		final String VIEW_PATH = "ku/cs/lab03/views/";

		FXRouter.when("hello", VIEW_PATH + "hello-view.fxml");
		FXRouter.when("student", VIEW_PATH + "student.fxml");
		FXRouter.when("student-list", VIEW_PATH + "student-list.fxml");
		FXRouter.when("students-table", VIEW_PATH + "students-table.fxml");
		FXRouter.when("student-score", VIEW_PATH + "student-score.fxml");
		FXRouter.when("layout", VIEW_PATH + "layout.fxml");
	}

	public static void main(String[] args) {
		launch();
	}
}