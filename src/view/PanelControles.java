package view;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import java.awt.GridLayout;
import java.util.ArrayList;

import controller.DatosIniciales;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import model.AlmacenaModel;

public class PanelControles extends JPanel {

    JTable tlbResultados;
    JLabel titulo_lbl, insertar_lbl, nBodega_lbl, nProducto_lbl, cantidad_lbl, consultar_lbl, consultarBodega_lbl, consultarProducto_lbl;
    JComboBox nBodega_box, nProducto_box, buscarProducto_box, buscarBodega_box;
    JTextField cantidad_txt;
    JButton insertar_btn, eliminar_btn, consultar_btn, actualizar_btn;

    public PanelControles(PanelResultados panelResultados
            ) {
        this.tlbResultados = panelResultados.getTblResultados();
        add(panelResultados);
        initComponents();
    }

    private void initComponents() {
        setLayout(null);
        setSize(650, 700);
        DatosIniciales datosIniciales = new DatosIniciales();
        this.setTablaResultados(datosIniciales.getAlmacena());
        crearEtiquetas();
        crearComoBoxes();
        crearCajasTexto();
        crearBotones();
    }

    public void crearEtiquetas() {
        titulo_lbl = new JLabel("Te reto a programarlo S.A.S");
        titulo_lbl.setForeground(Color.BLACK);
        titulo_lbl.setFont(new Font("Courier new", Font.BOLD, 25));
        titulo_lbl.setBounds(25, 5, 500, 25);
        add(titulo_lbl).setVisible(true);

        insertar_lbl = new JLabel("Insertar un nuevo dato");
        insertar_lbl.setForeground(Color.BLACK);
        insertar_lbl.setFont(new Font("Courier new", Font.BOLD, 16));
        insertar_lbl.setBounds(25, 50, 300, 20);
        add(insertar_lbl).setVisible(true);

        nBodega_lbl = new JLabel("Seleccione una bodega:");
        nBodega_lbl.setForeground(Color.BLACK);
        nBodega_lbl.setFont(new Font("Courier new", Font.BOLD, 16));
        nBodega_lbl.setBounds(25, 75, 300, 20);
        add(nBodega_lbl).setVisible(true);

        nProducto_lbl = new JLabel("Seleccione un producto:");
        nProducto_lbl.setForeground(Color.BLACK);
        nProducto_lbl.setFont(new Font("Courier new", Font.BOLD, 16));
        nProducto_lbl.setBounds(25, 100, 300, 20);
        add(nProducto_lbl).setVisible(true);

        cantidad_lbl = new JLabel("Cantidad de datos:");
        cantidad_lbl.setForeground(Color.BLACK);
        cantidad_lbl.setFont(new Font("Courier new", Font.BOLD, 16));
        cantidad_lbl.setBounds(25, 125, 300, 20);
        add(cantidad_lbl).setVisible(true);

        consultar_lbl = new JLabel("Consultar un dato");
        consultar_lbl.setForeground(Color.BLACK);
        consultar_lbl.setFont(new Font("Courier new", Font.BOLD, 16));
        consultar_lbl.setBounds(25, 190, 300, 20);
        add(consultar_lbl).setVisible(true);

        consultarBodega_lbl = new JLabel("Consultar por bodega:");
        consultarBodega_lbl.setForeground(Color.BLACK);
        consultarBodega_lbl.setFont(new Font("Courier new", Font.BOLD, 16));
        consultarBodega_lbl.setBounds(25, 215, 300, 20);
        add(consultarBodega_lbl).setVisible(true);

        consultarProducto_lbl = new JLabel("Consultar por producto:");
        consultarProducto_lbl.setForeground(Color.BLACK);
        consultarProducto_lbl.setFont(new Font("Courier new", Font.BOLD, 16));
        consultarProducto_lbl.setBounds(25, 240, 300, 20);
        add(consultarProducto_lbl).setVisible(true);
    }

    public void crearComoBoxes() {
        nBodega_box = new JComboBox();
        nBodega_box.setBounds(330, 75, 50, 20);
        add(nBodega_box).setVisible(true);

        nProducto_box = new JComboBox();
        nProducto_box.setBounds(330, 100, 50, 20);
        add(nProducto_box).setVisible(true);

        buscarBodega_box = new JComboBox();
        buscarBodega_box.setBounds(330, 215, 50, 20);
        add(buscarBodega_box).setVisible(true);

        buscarProducto_box = new JComboBox();
        buscarProducto_box.setBounds(330, 240, 50, 20);
        add(buscarProducto_box).setVisible(true);
    }

    public void crearCajasTexto() {
        cantidad_txt = new JTextField();
        cantidad_txt.setBounds(330, 125, 80, 20);
        add(cantidad_txt).setVisible(true);
    }

    public void crearBotones() {
        insertar_btn = new JButton("INSERTAR");
        insertar_btn.setFont(new Font("Courier new", Font.BOLD, 14));
        insertar_btn.setBounds(420, 125, 100, 20);
        add(insertar_btn).setVisible(true);

        eliminar_btn = new JButton("ELIMINAR");
        eliminar_btn.setFont(new Font("Courier new", Font.BOLD, 14));
        eliminar_btn.setBounds(420, 150, 100, 20);
        eliminar_btn.setEnabled(false);
        add(eliminar_btn).setVisible(true);

        consultar_btn = new JButton("BUSCAR");
        consultar_btn.setFont(new Font("Courier new", Font.BOLD, 14));
        consultar_btn.setBounds(420, 240, 100, 20);
        add(consultar_btn).setVisible(true);
        
        actualizar_btn = new JButton("ACTUALIZAR");
        actualizar_btn.setFont(new Font("Courier new", Font.BOLD, 14));
        actualizar_btn.setBounds(420, 265, 100, 20);
        add(actualizar_btn).setVisible(true);
    }

    public void setTablaResultados(ArrayList<AlmacenaModel> almacena) {
        String[] cabeseras = {"Bodega", "Direccion", "Producto", "Precio unitario", "Cantidad"};
        this.tlbResultados.removeAll();

        DefaultTableModel tableModel = new DefaultTableModel();

        tableModel.setColumnIdentifiers(cabeseras);
        this.tlbResultados.setModel(tableModel);

        for (int i = 0; i < almacena.size(); i++) {
            tableModel.addRow(almacena.get(i).toArray());
        }
    }

}
