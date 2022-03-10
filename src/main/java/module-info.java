module com.example.w22comp1008gcvideogame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.w22comp1008gcvideogame to javafx.fxml;
    exports com.example.w22comp1008gcvideogame;
}