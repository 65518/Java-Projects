module com.vj.texttospeechgui {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jfr;
    requires freetts;
    requires java.desktop;

    opens com.vj.texttospeechgui to javafx.fxml;
    exports com.vj.texttospeechgui;
}