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
    exports it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Dipendete.ControllerGestione;
    opens it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Dipendete.ControllerGestione to javafx.fxml;
    exports it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Dipendete.Cassiere;
    opens it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Dipendete.Cassiere to javafx.fxml;
    exports it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Dipendete.Bagnino;
    opens it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Dipendete.Bagnino to javafx.fxml;
    exports it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Cliente;
    opens it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Cliente to javafx.fxml;
    exports it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Dipendete;
    opens it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Dipendete to javafx.fxml;
    exports it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.Attivita;
    opens it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.Attivita to javafx.fxml;
    exports it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.Ristorazione;
    opens it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.Ristorazione to javafx.fxml;
    exports it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.Balneare;
    opens it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.Balneare to javafx.fxml;

}