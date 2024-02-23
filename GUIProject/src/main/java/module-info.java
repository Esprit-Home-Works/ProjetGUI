module com.project.guiproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires resend.java;

    exports com.project.guiproject.test;
    opens com.project.guiproject.test to javafx.fxml;
    exports com.project.guiproject.models;
    opens com.project.guiproject.models to javafx.fxml;
    exports com.project.guiproject.controllers.matches;
    opens com.project.guiproject.controllers.matches;
    exports com.project.guiproject.controllers.tournament;
    opens com.project.guiproject.controllers.tournament;

}