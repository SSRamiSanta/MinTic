package controller;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.MouseInputListener;

import access.AlmacenaDAO;
import model.AlmacenaModel;
import model.BodegaModel;
import model.ProductoModel;
import view.PanelControles;

public class EventoMouse implements MouseInputListener {

    private PanelControles panelControles;

    String bodega;
    String producto;
    int cantidad;

    public EventoMouse(PanelControles panelControles) {
        this.panelControles = panelControles;
    }

    public EventoMouse() {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TABLA
        if (e.getSource() == this.panelControles.getTlbResultados()) {
            JTable tablaResultados = this.panelControles.getTlbResultados();
            this.panelControles.getEliminar_btn().setEnabled(true);
            this.bodega = String.valueOf(tablaResultados.getValueAt(tablaResultados.getSelectedRow(), 0));
            this.producto = String.valueOf(tablaResultados.getValueAt(tablaResultados.getSelectedRow(), 2));
        }
        // BOTON ELIMINAR
        if (e.getSource() == this.panelControles.getEliminar_btn()) {
            this.panelControles.getEliminar_btn().setEnabled(false);
            int decision = JOptionPane.showConfirmDialog(this.panelControles.getEliminar_btn(), "Esta seguro que quiere eliminar el registro seleccionado",
                    "¿Quiere borrar el registro?", JOptionPane.YES_NO_OPTION);
            if (decision == 0) {
                //System.out.println(almacena.getNombreBodega() + " nombre de la bodegaS");
                eliminarDatos(this.bodega, this.producto);
            }
        }
        //BOTON BUSCAR
        if (e.getSource() == this.panelControles.getConsultar_btn()) {
            int idBodega = ((BodegaModel) panelControles.getBuscarBodega_box().getSelectedItem()).getIdBodega();
            int idProducto = ((ProductoModel) panelControles.getBuscarProducto_box().getSelectedItem()).getIdProducto();
            BuscarDatos(idBodega, idProducto);
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Auto-generated method stub

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Auto-generated method stub

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //  Auto-generated method stub

    }

    public void eliminarDatos(String bodega, String producto) {
        DatosIniciales datosIniciales = new DatosIniciales(bodega, producto);
        //System.out.println("bodega en eliminar " + this.bodega);
        ArrayList<Integer> ids = new ArrayList<Integer>();
        ids.add(datosIniciales.getAlmacenaEliminar().get(0).getIdBodegaFK());
        ids.add(datosIniciales.getAlmacenaEliminar().get(0).getIdProductoFK());
        System.out.println("Los ides que escogio son: " + ids);
        AlmacenaDAO almacenaDAO = new AlmacenaDAO();
        almacenaDAO.deleteAlmacena(ids.get(0), ids.get(1));
    }

    public void BuscarDatos(int idBodega, int idProducto) {
        idBodega = ((BodegaModel) panelControles.getBuscarBodega_box().getSelectedItem()).getIdBodega();
        idProducto = ((ProductoModel) panelControles.getBuscarProducto_box().getSelectedItem()).getIdProducto();
        if (idBodega == -1 && idProducto == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione al menos un ítem", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else {
            AlmacenaDAO almacenaDAO = new AlmacenaDAO();
            PanelControles.setTablaResultados(almacenaDAO.getAlmacenaSearch(idBodega, idProducto));
        }
    }

}
