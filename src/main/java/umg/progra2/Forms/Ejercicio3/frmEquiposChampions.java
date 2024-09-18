package umg.progra2.Forms.Ejercicio3;

import umg.progra2.DataBase.Model.EquipoChampions;
import umg.progra2.DataBase.Service.EquipoChampionsService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.time.LocalDate;

public class frmEquiposChampions {
    private JPanel JPanelfrmEquiposChampions;
    private JTextField textFieldIdBuscar;
    private JButton ButtonBuscar;
    private JButton ButtonCrear;
    private JButton ButtonActualizar;
    private JButton ButtonEliminar;
    private JTextField textFieldNombre;
    private JTextField textFieldWebOficial;
    private JTextField textFieldId;
    private JSpinner spinnerAnioFundacion;
    private JTextField textFieldPais;
    private JTextField textFieldCiudad;
    private JTextField textFieldEstadio;
    private JTextField textFieldEntrenador;
    private JTextField textFieldFacebook;
    private JTextField textFieldTwitter;
    private JTextField textFieldInstagram;
    private JTextField textFieldPatrocinador;


    public void mostrar() {
        JFrame frame = new JFrame("Formulario de los Equipos de la Champions");
        frame.setContentPane(new frmEquiposChampions().JPanelfrmEquiposChampions);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setSize(870, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    public frmEquiposChampions() {

        int anioActual = LocalDate.now().getYear();
        SpinnerModel anioModel = new SpinnerNumberModel(2024, 1901, anioActual, 1);

        spinnerAnioFundacion.setModel(anioModel);
        textoSpinner("");

        EquipoChampionsService equiposc = new EquipoChampionsService();

        ButtonCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EquipoChampions equipoChampions = new EquipoChampions();

                equipoChampions.setNombre(textFieldNombre.getText());
                equipoChampions.setPais(textFieldPais.getText());
                equipoChampions.setCiudad(textFieldCiudad.getText());
                equipoChampions.setEstadio(textFieldEstadio.getText());
                equipoChampions.setFundacion((int) spinnerAnioFundacion.getValue());
                equipoChampions.setEntrenador(textFieldEntrenador.getText());
                equipoChampions.setWebOficial(textFieldWebOficial.getText());
                equipoChampions.setFacebook(textFieldFacebook.getText());
                equipoChampions.setTwitter(textFieldTwitter.getText());
                equipoChampions.setInstagram(textFieldInstagram.getText());
                equipoChampions.setPatrocinadorPrincipal(textFieldPatrocinador.getText());

                Timestamp timestampActual = new Timestamp(System.currentTimeMillis());
                equipoChampions.setCreadoEn(timestampActual);

                try {
                    equiposc.createEquipoChampions(equipoChampions);
                    JOptionPane.showMessageDialog(null, "Registro creado correctamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al crear el registro: " + ex.getMessage());
                }

                limpiarComponentes();
            }
        });


        ButtonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idBuscar = Integer.parseInt(textFieldIdBuscar.getText());

                try {
                    EquipoChampions equipoChampionsEncontrado = new EquipoChampions();
                    equipoChampionsEncontrado = equiposc.getEquipoChampionsById(idBuscar);

                    if (equipoChampionsEncontrado != null){
                        textFieldId.setText(Integer.toString(equipoChampionsEncontrado.getIdEquipo()));
                        textFieldNombre.setText(equipoChampionsEncontrado.getNombre());
                        textFieldPais.setText(equipoChampionsEncontrado.getPais());
                        textFieldCiudad.setText(equipoChampionsEncontrado.getCiudad());
                        textFieldEstadio.setText(equipoChampionsEncontrado.getEstadio());
                        textoSpinner(Integer.toString(equipoChampionsEncontrado.getFundacion()));
                        textFieldEntrenador.setText(equipoChampionsEncontrado.getEntrenador());
                        textFieldWebOficial.setText(equipoChampionsEncontrado.getWebOficial());
                        textFieldFacebook.setText(equipoChampionsEncontrado.getFacebook());
                        textFieldTwitter.setText(equipoChampionsEncontrado.getTwitter());
                        textFieldInstagram.setText(equipoChampionsEncontrado.getInstagram());
                        textFieldPatrocinador.setText(equipoChampionsEncontrado.getPatrocinadorPrincipal());
                    }else{
                        JOptionPane.showMessageDialog(null, "No se ha encontrado ningún equipo con este id.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al crear el registro: " + ex.getMessage());
                }

                textFieldIdBuscar.setText("");
            }
        });


        ButtonActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textFieldId.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Primero debe buscar un registro para poder actualizarlo.");
                }else{
                    try{
                        EquipoChampions equipoChampions = new EquipoChampions();

                        equipoChampions.setIdEquipo(Integer.parseInt(textFieldId.getText()));
                        equipoChampions.setNombre(textFieldNombre.getText());
                        equipoChampions.setPais(textFieldPais.getText());
                        equipoChampions.setCiudad(textFieldCiudad.getText());
                        equipoChampions.setEstadio(textFieldEstadio.getText());
                        equipoChampions.setFundacion((int) spinnerAnioFundacion.getValue());
                        equipoChampions.setEntrenador(textFieldEntrenador.getText());
                        equipoChampions.setWebOficial(textFieldWebOficial.getText());
                        equipoChampions.setFacebook(textFieldFacebook.getText());
                        equipoChampions.setTwitter(textFieldTwitter.getText());
                        equipoChampions.setInstagram(textFieldInstagram.getText());
                        equipoChampions.setPatrocinadorPrincipal(textFieldPatrocinador.getText());

                        int respuestaConfirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea actualizar de forma permanente este registro?", "Confirmación", JOptionPane.YES_NO_OPTION);

                        if (respuestaConfirmacion == JOptionPane.YES_OPTION) {
                            equiposc.updateEquipoChampions(equipoChampions);
                            JOptionPane.showMessageDialog(null, "Registro actualizado correctamente");
                            limpiarComponentes();
                        } else {
                            JOptionPane.showMessageDialog(null, "El registro no se actualizó.");
                            limpiarComponentes();
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error al actualizar el registro: " + ex.getMessage());
                    }
                }
            }
        });


        ButtonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textFieldId.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Primero debe buscar un registro para poder eliminarlo.");
                }else{
                    try{
                        int respuestaConfirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar de forma permanente este registro?", "Confirmación", JOptionPane.YES_NO_OPTION);

                        if (respuestaConfirmacion == JOptionPane.YES_OPTION) {
                            equiposc.deleteEquipoChampionsById(Integer.parseInt(textFieldId.getText()));
                            JOptionPane.showMessageDialog(null, "Registro eliminado correctamente");
                            limpiarComponentes();
                        } else {
                            JOptionPane.showMessageDialog(null, "El registro no se eliminó.");
                            limpiarComponentes();
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error al eliminar el registro: " + ex.getMessage());
                    }
                }
            }
        });
    }

    public void limpiarComponentes(){
        textFieldId.setText("");
        textFieldNombre.setText("");
        textFieldPais.setText("");
        textFieldCiudad.setText("");
        textFieldEstadio.setText("");
        textFieldEntrenador.setText("");
        textoSpinner("");
        textFieldWebOficial.setText("");
        textFieldFacebook.setText("");
        textFieldTwitter.setText("");
        textFieldInstagram.setText("");
        textFieldPatrocinador.setText("");
    }

    public void textoSpinner(String textoMostrar){
        JFormattedTextField spinnerEditor = ((JSpinner.DefaultEditor) spinnerAnioFundacion.getEditor()).getTextField();
        spinnerEditor.setText(textoMostrar);
    }
}
