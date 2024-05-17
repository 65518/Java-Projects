package com.vj.texttospeechgui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



import java.io.IOException;

public class textTOspeechGUI extends Application {

    //Default app size.
    private static final int APP_WIDTH = 375;
    private static final int APP_HEIGHT = 475;

    private TextArea textarea;
    private ComboBox<String> voices, Rates, Volumes; // added to combo boxes.

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = CreateScene();
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm()); //Referencing CSS.
        stage.setTitle("T2S");

        //Adding Icon For Application
        Image image = new Image(
                        getClass().getResourceAsStream("user-voice-regular-24.png")
                    );
        stage.getIcons().add(image);

        stage.setScene(scene);
        stage.show();
    }

    private Scene CreateScene(){

        //Vbox is Vertical Box more ike a container to Store.
        VBox box = new VBox();
        box.getStyleClass().add("body");

        //Label
        Label TextToSpeechLabel = new Label("Text-To-Speech"); //Creating a Label.
        TextToSpeechLabel.getStyleClass().add("text-to-speech-label"); //adding CSS class.
        TextToSpeechLabel.setMaxWidth(Double.MAX_VALUE); //Set Max value for label.
        TextToSpeechLabel.setAlignment(Pos.CENTER); // Aligning the label to centre.
        box.getChildren().add(TextToSpeechLabel); // Adding the label to VBox.

        //TextArea
        textarea = new TextArea();
        box.getChildren().add(textarea); //adding to VBox
        textarea.setWrapText(true);//Move Text To Next Line If It's Overflowing The Current Width.
        textarea.getStyleClass().add("text-area"); //adding the CSS class.

        // Container for a TextArea.
        StackPane TextAreaPane = new StackPane();
        TextAreaPane.setPadding(new Insets(0,15,0,15)); //adding space for Left and Right of TextArea.
        TextAreaPane.getChildren().add(textarea); //adding textarea to a container.
        box.getChildren().add(TextAreaPane); //adding to box.

        //GridPane to View The Labels after the text area.
        GridPane SettingPane = createSettingComponents(); //method to set setting for GridPane which Controls the Voice Settings.
        box.getChildren().add(SettingPane); // adding the method instance to the VBox
        SettingPane.getStyleClass().add("SettingPane");

        //Speak Button
        Button speakButton = createImageButton();
        speakButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String msg = textarea.getText();
                String voice = voices.getValue();
                String rate = Rates.getValue();
                String volume = Volumes.getValue();

                textTOspeechController.speak(msg,voice,rate,volume);
            }
        });

        //adding a container inorder to give padding
        StackPane speakButtonPane = new StackPane();
        speakButtonPane.setPadding(new Insets(40,20,0,20));
        speakButtonPane.getChildren().add(speakButton);

        // adding the container to box
        box.getChildren().add(speakButtonPane);

        return new Scene(box,APP_WIDTH,APP_HEIGHT);
    }

    //Method for SpeakButton
    private Button createImageButton(){
        Button button = new Button("Speak");
        button.getStyleClass().add("speak-btn");
        button.setMaxWidth(Double.MAX_VALUE);
        button.setAlignment(Pos.CENTER);

        //Adding Image to Button.
        ImageView imageView = new ImageView(
                new Image(
                        getClass().getResourceAsStream("speak.png")
                )
        );

        //Adj size
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);

        //adding to button
        button.setGraphic(imageView);

        return button;
    }

    //Method to SettingPane.
    private GridPane createSettingComponents() {
        GridPane gridPane =  new GridPane();

        gridPane.setHgap(10); //gap between labels
        gridPane.setPadding(new Insets(10,0,0,0)); // padding

        //label to add into grid pane.
        Label VoiceLabel = new Label("Voice");
        Label RateLabel = new Label("Rate");
        Label VolumeLabel = new Label("Level");

        //adding the label to Grid Pane.
        gridPane.add(VoiceLabel,0,0); //col & row value.
        gridPane.add(RateLabel,1,0);
        gridPane.add(VolumeLabel,2,0);

        //Centering the Labels:
        GridPane.setHalignment(VoiceLabel, HPos.CENTER);
        GridPane.setHalignment(RateLabel, HPos.CENTER);
        GridPane.setHalignment(VolumeLabel, HPos.CENTER);

        //Declaring ComboBox
        voices = new ComboBox<>();
        voices.getStyleClass().add("setting-combo-boxes"); // adding the CSS to ComboBox.

        //Adding voices from controller
        voices.getItems().addAll(
                textTOspeechController.getVoices()
        );

        voices.setValue(voices.getItems().get(0)); //setting Default Value

        //Rates
        Rates = new ComboBox<>();
        Rates.getStyleClass().add("setting-combo-boxes");
        // Getting speed rates From the Controller
        Rates.getItems().addAll(
                textTOspeechController.getSpeedRates()
        );
        //Setting Default Value
        Rates.setValue(Rates.getItems().get(0));

        //Volumes
        Volumes = new ComboBox<>();
        Volumes.getStyleClass().add("setting-combo-boxes");
        //Getting the Items From COntroller
        Volumes.getItems().addAll(
                textTOspeechController.getVolumeLevels()
        );
        //Setting Default Value
        Volumes.setValue(Volumes.getItems().get(8));


        //adding CB to GP
        gridPane.add(voices,0,1);
        gridPane.add(Rates,1,1);
        gridPane.add(Volumes,2,1);

        gridPane.setAlignment(Pos.CENTER);
        return gridPane;
    }

    public static void main(String[] args) {
        launch();
    }
}