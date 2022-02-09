module Casotto.app.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens it.unicam.cs.IngegneriaDelSoftware.Casotto to javafx.fxml;
    exports it.unicam.cs.IngegneriaDelSoftware.Casotto;

    exports it.unicam.cs.IngegneriaDelSoftware.Casotto.View.Gestore;
    opens it.unicam.cs.IngegneriaDelSoftware.Casotto.View.Gestore to javafx.fxml;

    opens it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori to javafx.fxml;
    exports it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori;

    opens it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi to javafx.fxml;
    exports it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi;

    opens it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare to javafx.fxml;
    exports it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare;

    opens it.unicam.cs.IngegneriaDelSoftware.Casotto.View.Bagnino to javafx.fxml;
    exports it.unicam.cs.IngegneriaDelSoftware.Casotto.View.Bagnino;

    opens it.unicam.cs.IngegneriaDelSoftware.Casotto.View.Cliente to javafx.fxml;
    exports it.unicam.cs.IngegneriaDelSoftware.Casotto.View.Cliente;
}