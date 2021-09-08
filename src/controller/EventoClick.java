package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import access.AlmacenaDAO;
import model.AlmacenaModel;
import view.PanelControles;

public class EventoClick implements ActionListener {

    private PanelControles panelControles;
    int cantidad;

    public EventoClick(PanelControles panelControles) {
        this.panelControles = panelControles;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //BOTON ACTUALIZAR
        if (e.getSource() == this.panelControles.getActualizar_btn()) {
            AlmacenaDAO almacenaDAO = new AlmacenaDAO();
            PanelControles.setTablaResultados(almacenaDAO.getAllAlmacena());
        }
        //BOTON CERRAR
        if (e.getSource() == this.panelControles.getCerrar_btn()) {
            System.exit(0);
        }
    }
}
