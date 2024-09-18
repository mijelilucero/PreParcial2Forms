package umg.progra2.Forms.Ejercicio1;

import umg.progra2.DataBase.Model.Dato;
import umg.progra2.DataBase.Service.DatoService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Calendar;


public class frmDatos {
    private JPanel jPanelFrmDatos;
    private JTextField textFieldCodigo;
    private JTextField textFieldNombre;
    private JTextField textFieldApellido;
    private JComboBox comboBoxDepartamento;
    private JTextField textFieldIdBuscar;
    private JButton buscarButton;
    private JButton crearRegistroButton;
    private JButton actualizarRegistroButton;
    private JButton eliminarRegistroButton;
    private JTextField textFieldFechaNacimiento;
    private JButton ButtonCrearRegistro;


    public void mostrar() {
        JFrame frame = new JFrame("Formulario de Datos Personales");
        frame.setContentPane(new frmDatos().jPanelFrmDatos);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setSize(750, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    public frmDatos() {

        comboBoxDepartamento.addItem("Recursos Humanos");
        comboBoxDepartamento.addItem("Finanzas");
        comboBoxDepartamento.addItem("Marketing");
        comboBoxDepartamento.addItem("Ventas");
        comboBoxDepartamento.addItem("Atención al Cliente");
        comboBoxDepartamento.setSelectedIndex(-1);

        DatoService datosc = new DatoService();

        crearRegistroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(convertirFechaNacimiento(textFieldFechaNacimiento.getText()) != null) {
                    Dato dato = new Dato();
                    dato.setNombre(textFieldNombre.getText());
                    dato.setApellido(textFieldApellido.getText());
                    dato.setDepartamento(comboBoxDepartamento.getSelectedItem().toString());
                    dato.setFechaNacimiento(convertirFechaNacimiento(textFieldFechaNacimiento.getText()));

                    try{
                        datosc.createDato(dato);
                        JOptionPane.showMessageDialog(null, "Registro creado correctamente.");
                    }catch(Exception ex){
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error al crear el registro: " + ex.getMessage());
                    }

                    LimpiarComponentes();
                }
            }
        });


        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textFieldIdBuscar.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Ingrese el codigo para buscar.");
                }else{
                    int id = Integer.parseInt(textFieldIdBuscar.getText());

                    try {
                        Dato datoEncontrado = new Dato();
                        datoEncontrado = datosc.getDatoByCodigo(id);

                        if(datoEncontrado != null){
                            System.out.println(datoEncontrado.getCodigo());
                            System.out.println(datoEncontrado.getNombre());
                            System.out.println(datoEncontrado.getApellido());
                            System.out.println(datoEncontrado.getDepartamento());
                            System.out.println(datoEncontrado.getFechaNacimiento());

                            textFieldCodigo.setText(Integer.toString(datoEncontrado.getCodigo()));
                            textFieldNombre.setText(datoEncontrado.getNombre());
                            textFieldApellido.setText(datoEncontrado.getApellido());
                            comboBoxDepartamento.setSelectedItem(datoEncontrado.getDepartamento());
                            textFieldFechaNacimiento.setText(datoEncontrado.getFechaNacimiento().toString());
                        }else{
                            JOptionPane.showMessageDialog(null, "No existe registro para el codigo ingresado.");
                        }
                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error al buscar el registro: " + ex.getMessage());
                    }

                    textFieldIdBuscar.setText("");
                }
            }
        });


        actualizarRegistroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textFieldCodigo.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Primero debe buscar un registro para poder actualizarlo.");
                }else {
                    if (convertirFechaNacimiento(textFieldFechaNacimiento.getText()) != null) {
                        Dato dato = new Dato();
                        dato.setCodigo(Integer.parseInt(textFieldCodigo.getText()));
                        dato.setNombre(textFieldNombre.getText());
                        dato.setApellido(textFieldApellido.getText());
                        dato.setDepartamento(comboBoxDepartamento.getSelectedItem().toString());
                        dato.setFechaNacimiento(convertirFechaNacimiento(textFieldFechaNacimiento.getText()));

                        System.out.println(dato.getCodigo());
                        System.out.println(dato.getNombre());
                        System.out.println(dato.getApellido());
                        System.out.println(dato.getDepartamento());
                        System.out.println(dato.getFechaNacimiento());

                        try {
                            int respuestaConfirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea actualizar de forma permanente este registro?", "Confirmación", JOptionPane.YES_NO_OPTION);

                            if (respuestaConfirmacion == JOptionPane.YES_OPTION) {
                                datosc.updateDato(dato);
                                JOptionPane.showMessageDialog(null, "Registro actualizado correctamente");
                            } else {
                                JOptionPane.showMessageDialog(null, "El registro no se actualizó.");
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al actualizar el registro: " + ex.getMessage());
                        }

                        LimpiarComponentes();
                    }
                }
            }
        });


        eliminarRegistroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textFieldCodigo.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Primero debe buscar un registro para poder eliminarlo.");
                }else {
                    int id = Integer.parseInt(textFieldCodigo.getText());

                    try {
                        int respuestaConfirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar de forma permanente este registro?", "Confirmación", JOptionPane.YES_NO_OPTION);

                        if (respuestaConfirmacion == JOptionPane.YES_OPTION) {
                            datosc.deleteDatoByCodigo(id);
                            JOptionPane.showMessageDialog(null, "Registro eliminado correctamente");
                        } else {
                            JOptionPane.showMessageDialog(null, "El registro no se eliminó.");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error al eliminar el registro: " + ex.getMessage());
                    }

                    LimpiarComponentes();
                }
            }
        });
    }


    public void LimpiarComponentes(){
        textFieldCodigo.setText("");
        textFieldNombre.setText("");
        textFieldApellido.setText("");
        comboBoxDepartamento.setSelectedIndex(-1);
        textFieldFechaNacimiento.setText("");
    }


    public Date convertirFechaNacimiento(String fechaTexto) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        java.util.Date utilDate = null;
        Date sqlDate = null;

        try {
            utilDate = dateFormat.parse(fechaTexto);

            if(validarFechaNacimiento(utilDate)) {
                sqlDate = new Date(utilDate.getTime());
                return sqlDate;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "La fecha ingresada es inválida.");
        }

        return null;
    }


    public boolean validarFechaNacimiento (java.util.Date utilDate){
            Calendar fechaIngresada = Calendar.getInstance();
            fechaIngresada.setTime(utilDate);

            Calendar hoy = Calendar.getInstance();

            if (fechaIngresada.after(hoy)) {
                JOptionPane.showMessageDialog(null, "La fecha no puede ser futura.");
                return false;
            }

            int edad = hoy.get(Calendar.YEAR) - fechaIngresada.get(Calendar.YEAR);

            if (hoy.get(Calendar.MONTH) < fechaIngresada.get(Calendar.MONTH) ||
                    (hoy.get(Calendar.MONTH) == fechaIngresada.get(Calendar.MONTH) && hoy.get(Calendar.DAY_OF_MONTH) < fechaIngresada.get(Calendar.DAY_OF_MONTH))) {
                edad--;
            }

            if (edad < 18) {
                JOptionPane.showMessageDialog(null, "Debe tener más de 18 años.");
                return false;
            }

            return true;
    }

}
