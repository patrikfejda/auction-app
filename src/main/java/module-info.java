module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;


    opens com.example.controller to javafx.fxml;
    exports com.example.controller;
}