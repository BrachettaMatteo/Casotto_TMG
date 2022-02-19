module Casotto.app.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens it.unicam.cs.IngegneriaDelSoftware.Casotto to javafx.fxml;
    exports it.unicam.cs.IngegneriaDelSoftware.Casotto;

    opens it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori to javafx.fxml;
    exports it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori;

    opens it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi to javafx.fxml;
    exports it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi;

    opens it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare to javafx.fxml;
    exports it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare;

    opens it.unicam.cs.IngegneriaDelSoftware.Casotto.Service to javafx.fxml;
    exports it.unicam.cs.IngegneriaDelSoftware.Casotto.Service;
    exports it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.ControllerGestione;
    opens it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.ControllerGestione to javafx.fxml;

}