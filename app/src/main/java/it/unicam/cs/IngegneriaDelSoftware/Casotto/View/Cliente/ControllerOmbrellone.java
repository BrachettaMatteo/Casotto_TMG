package it.unicam.cs.IngegneriaDelSoftware.Casotto.View.Cliente;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Ombrellone;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ControllerOmbrellone {
    @FXML
    Label costo;

    void setPrezzo(Float c) {
        costo.setText(String.valueOf(c));
    }
    
}
