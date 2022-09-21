module Casotto.app.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG to javafx.fxml;
    exports it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG;

    opens it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Attori to javafx.fxml;
    exports it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Attori;

    opens it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Servizi to javafx.fxml;
    exports it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Servizi;

    opens it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Balneare to javafx.fxml;
    exports it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Balneare;

    opens it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Service to javafx.fxml;
    exports it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Service;
    exports it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Attori.Dipendete.ControllerGestione;
    opens it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Attori.Dipendete.ControllerGestione to javafx.fxml;
    exports it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Attori.Dipendete.Cassiere;
    opens it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Attori.Dipendete.Cassiere to javafx.fxml;
    exports it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Attori.Dipendete.Bagnino;
    opens it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Attori.Dipendete.Bagnino to javafx.fxml;
    exports it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Attori.Cliente;
    opens it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Attori.Cliente to javafx.fxml;
    exports it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Attori.Dipendete;
    opens it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Attori.Dipendete to javafx.fxml;
    exports it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Servizi.Attivita;
    opens it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Servizi.Attivita to javafx.fxml;
    exports it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Servizi.Ristorazione;
    opens it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Servizi.Ristorazione to javafx.fxml;
    exports it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Servizi.Balneare;
    opens it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Servizi.Balneare to javafx.fxml;

}