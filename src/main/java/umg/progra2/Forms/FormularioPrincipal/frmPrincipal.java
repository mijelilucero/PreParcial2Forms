package umg.progra2.Forms.FormularioPrincipal;

import umg.progra2.Forms.Ejercicio1.frmDatos;
import umg.progra2.Forms.Ejercicio2.frmUsuarios;
import umg.progra2.Forms.Ejercicio3.frmEquiposChampions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmPrincipal {
    private JPanel frmPrincipal;
    private JButton ejercicio1Button;
    private JButton ejercicio2Button;
    private JButton ejercicio3Button;

    public static void main(String[] args) {
        JFrame frame = new JFrame("frmPrincipal");
        frame.setContentPane(new frmPrincipal().frmPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public frmPrincipal() {
        ejercicio1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmDatos form1 = new frmDatos();
                form1.mostrar();
            }
        });


        ejercicio2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmUsuarios form2 = new frmUsuarios();
                form2.mostrar();
            }
        });


        ejercicio3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmEquiposChampions form3 = new frmEquiposChampions();
                form3.mostrar();
            }
        });
    }
}
