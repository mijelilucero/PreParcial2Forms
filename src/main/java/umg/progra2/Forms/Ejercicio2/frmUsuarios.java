package umg.progra2.Forms.Ejercicio2;

import umg.progra2.DataBase.Model.Usuario;
import umg.progra2.DataBase.Service.UsuarioService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmUsuarios {
    private JPanel JPanelfrmUsuarios;
    private JButton ButtonCrear;
    private JButton ButtonActualizar;
    private JButton ButtonEliminar;
    private JTextField textFieldBuscarCarnet;
    private JButton ButtonBuscar;
    private JTextField textFieldId;
    private JTextField textFieldCarnet;
    private JTextField textFieldNombre;
    private JTextField textFieldCorreo;
    private JTextField textFieldTelegramId;
    private JRadioButton RadioButtonActivo;
    private JComboBox comboBoxSeccion;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Formulario para Gestión de Datos de Estudiantes");
        frame.setContentPane(new frmUsuarios().JPanelfrmUsuarios);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    public frmUsuarios() {

        comboBoxSeccion.addItem("A");
        comboBoxSeccion.addItem("B");
        comboBoxSeccion.setSelectedIndex(-1);
        UsuarioService usuariosc = new UsuarioService();


        ButtonCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Usuario usuarioPruebaCorreo = usuariosc.getUserByEmail(textFieldCorreo.getText().trim());
                    Usuario usuarioPruebaCarnet = usuariosc.getUserByCarne(textFieldCarnet.getText().trim());

                    if(usuarioPruebaCorreo == null){
                        if(usuarioPruebaCarnet == null) {
                            Usuario usuario = new Usuario();
                            usuario.setCarne(textFieldCarnet.getText());
                            usuario.setNombre(textFieldNombre.getText());
                            usuario.setCorreo(textFieldCorreo.getText());
                            usuario.setSeccion(comboBoxSeccion.getSelectedItem().toString());
                            usuario.setTelegramid(Long.parseLong(textFieldTelegramId.getText()));
                            if (RadioButtonActivo.isSelected()) {
                                usuario.setActivo(1);
                            } else {
                                usuario.setActivo(0);
                            }

                            try {
                                usuariosc.createUser(usuario);
                                JOptionPane.showMessageDialog(null, "Registro creado correctamente.");
                                LimpiarComponentes();
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, "Ha ocurrido un error al crear el registro: " + ex.getMessage());
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "El carnet ya se encuentra registrado. Por favor registrese con uno nuevo.");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "El correo electrónico ya se encuentra registrado. Por favor registrese con uno nuevo.");
                    }
                }catch(Exception ex) {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al validar el correo electrónico: " + ex.getMessage());
                }
            }
        });


        ButtonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textFieldBuscarCarnet.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Ingrese el carnet que desea buscar.");
                }else{
                    try{
                        Usuario usuario=new Usuario();
                        usuario=usuariosc.getUserByCarne(textFieldBuscarCarnet.getText().trim());

                        if (usuario != null){
                            textFieldId.setText(Integer.toString(usuario.getId()));
                            textFieldCarnet.setText(usuario.getCarne());
                            textFieldNombre.setText(usuario.getNombre());
                            textFieldCorreo.setText(usuario.getCorreo());
                            comboBoxSeccion.setSelectedItem(usuario.getSeccion());
                            textFieldTelegramId.setText(Long.toString(usuario.getTelegramid()));
                            RadioButtonActivo.setSelected(usuario.getActivo() == 1);
                        }else{
                            JOptionPane.showMessageDialog(null, "El carnet no existe");
                        }
                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error al buscar el registro: " + ex.getMessage());
                    }

                    textFieldBuscarCarnet.setText("");
                }
            }
        });


        ButtonActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textFieldId.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Primero debe buscar un registro para poder actualizarlo.");
                }else{
                    try{
                        Usuario usuarioPruebaCorreo = usuariosc.getUserByEmail(textFieldCorreo.getText().trim());
                        Usuario usuarioPruebaCarnet = usuariosc.getUserByCarne(textFieldCarnet.getText().trim());

                            if (usuarioPruebaCorreo == null || Integer.parseInt(textFieldId.getText()) == usuarioPruebaCorreo.getId()) {
                                if (usuarioPruebaCarnet == null || Integer.parseInt(textFieldId.getText()) == usuarioPruebaCarnet.getId()) {
                                    Usuario usuario = new Usuario();
                                    usuario.setId(Integer.parseInt(textFieldId.getText()));
                                    usuario.setCarne(textFieldCarnet.getText());
                                    usuario.setNombre(textFieldNombre.getText());
                                    usuario.setCorreo(textFieldCorreo.getText());
                                    usuario.setSeccion(comboBoxSeccion.getSelectedItem().toString());
                                    usuario.setTelegramid(Long.parseLong(textFieldTelegramId.getText()));

                                    if (RadioButtonActivo.isSelected()) {
                                        usuario.setActivo(1);
                                    } else {
                                        usuario.setActivo(0);
                                    }

                                    int respuestaConfirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea actualizar de forma permanente este registro?", "Confirmación", JOptionPane.YES_NO_OPTION);

                                    if (respuestaConfirmacion == JOptionPane.YES_OPTION) {
                                        usuariosc.updateUser(usuario);
                                        JOptionPane.showMessageDialog(null, "Registro actualizado correctamente");
                                        LimpiarComponentes();
                                    } else {
                                        JOptionPane.showMessageDialog(null, "El registro no se actualizó.");
                                        LimpiarComponentes();
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "El carnet ya está siendo utilizado por otra persona.");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "El correo electronico ya está siendo utilizado por otra persona.");
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
                    try {
                        int respuestaConfirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar de forma permanente este registro?", "Confirmación", JOptionPane.YES_NO_OPTION);

                        if (respuestaConfirmacion == JOptionPane.YES_OPTION) {
                            usuariosc.deleteUserByEmail(textFieldCorreo.getText());
                            JOptionPane.showMessageDialog(null, "Registro eliminado correctamente");
                            LimpiarComponentes();
                        } else {
                            JOptionPane.showMessageDialog(null, "El registro no se eliminó.");
                            LimpiarComponentes();
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error al eliminar el registro: " + ex.getMessage());
                    }
                }
            }
        });
    }

    public void LimpiarComponentes(){
        textFieldId.setText("");
        textFieldCarnet.setText("");
        textFieldNombre.setText("");
        textFieldCorreo.setText("");
        comboBoxSeccion.setSelectedIndex(-1);
        textFieldTelegramId.setText("");
        RadioButtonActivo.setSelected(false);
    }

}
