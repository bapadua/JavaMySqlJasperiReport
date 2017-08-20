/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aula.form;

import java.awt.Desktop;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import java.sql.*;
import br.com.aula.dal.ModuloConexao;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author SolucaoLogica
 */
public class telaPrincipal extends javax.swing.JFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public telaPrincipal() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Desktop = new javax.swing.JDesktopPane();
        lblUser = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuCad = new javax.swing.JMenu();
        menuCadCliente = new javax.swing.JMenuItem();
        menuCadOs = new javax.swing.JMenuItem();
        menuCadUser = new javax.swing.JMenuItem();
        menuRel = new javax.swing.JMenu();
        menuRelOs = new javax.swing.JMenuItem();
        relClientes = new javax.swing.JMenuItem();
        menuOpc = new javax.swing.JMenu();
        menuOpcSair = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuSobre = new javax.swing.JMenu();
        menuSobreAjuda = new javax.swing.JMenuItem();
        menuSobreShow = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("::WebEmpresa");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        javax.swing.GroupLayout DesktopLayout = new javax.swing.GroupLayout(Desktop);
        Desktop.setLayout(DesktopLayout);
        DesktopLayout.setHorizontalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        DesktopLayout.setVerticalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        lblUser.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblUser.setText("USUARIO");

        lblData.setText("Data");

        menuCad.setText("Cadastros");

        menuCadCliente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        menuCadCliente.setText("Cliente");
        menuCadCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCadClienteActionPerformed(evt);
            }
        });
        menuCad.add(menuCadCliente);

        menuCadOs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        menuCadOs.setText("OS");
        menuCadOs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCadOsActionPerformed(evt);
            }
        });
        menuCad.add(menuCadOs);

        menuCadUser.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        menuCadUser.setText("Usuario");
        menuCadUser.setEnabled(false);
        menuCadUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCadUserActionPerformed(evt);
            }
        });
        menuCad.add(menuCadUser);

        jMenuBar1.add(menuCad);

        menuRel.setText("Relatórios");

        menuRelOs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        menuRelOs.setText("OS");
        menuRelOs.setEnabled(false);
        menuRelOs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRelOsActionPerformed(evt);
            }
        });
        menuRel.add(menuRelOs);

        relClientes.setText("Clientes");
        relClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relClientesActionPerformed(evt);
            }
        });
        menuRel.add(relClientes);

        jMenuBar1.add(menuRel);

        menuOpc.setText("Opções");

        menuOpcSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        menuOpcSair.setText("Sair");
        menuOpcSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOpcSairActionPerformed(evt);
            }
        });
        menuOpc.add(menuOpcSair);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Logoff");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuOpc.add(jMenuItem1);

        jMenuBar1.add(menuOpc);

        menuSobre.setText("Sobre");

        menuSobreAjuda.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        menuSobreAjuda.setText("Ajuda");
        menuSobre.add(menuSobreAjuda);

        menuSobreShow.setText("Sobre");
        menuSobreShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSobreShowActionPerformed(evt);
            }
        });
        menuSobre.add(menuSobreShow);

        jMenuBar1.add(menuSobre);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Desktop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblData)
                    .addComponent(lblUser))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblData)
                .addContainerGap(512, Short.MAX_VALUE))
            .addComponent(Desktop)
        );

        setSize(new java.awt.Dimension(816, 638));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        Date data = new Date();
        DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
        lblData.setText(formatador.format(data));
    }//GEN-LAST:event_formWindowActivated

    private void menuOpcSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOpcSairActionPerformed
        int sair = JOptionPane.showConfirmDialog(null, "Deseja Sair?", "ATENÇÃO", JOptionPane.YES_NO_OPTION);
        if (sair == JOptionPane.YES_OPTION) {
            this.dispose();
        }
    }//GEN-LAST:event_menuOpcSairActionPerformed

    private void menuSobreShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSobreShowActionPerformed
        telaSobre sobre = new telaSobre();
        sobre.setVisible(true);
        Desktop.add(sobre);
    }//GEN-LAST:event_menuSobreShowActionPerformed

    private void menuCadUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCadUserActionPerformed
        // TODO add your handling code here:
        telaCadastro cadastro = new telaCadastro();
        cadastro.setVisible(true);
        Desktop.add(cadastro);
    }//GEN-LAST:event_menuCadUserActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // Efetua Logout 
        int sair = JOptionPane.showConfirmDialog(null, "DESEJA SAIR?", "LOGOFF", JOptionPane.YES_NO_OPTION);
        if (sair == JOptionPane.YES_OPTION) {
            telaLogin login = new telaLogin();
            login.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void menuCadClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCadClienteActionPerformed
        telaClientes cliente = new telaClientes();
        cliente.setVisible(true);
        Desktop.add(cliente);
    }//GEN-LAST:event_menuCadClienteActionPerformed

    private void menuCadOsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCadOsActionPerformed
        // TODO add your handling code here:
        telaOS os = new telaOS();
        os.setVisible(true);
        Desktop.add(os);

    }//GEN-LAST:event_menuCadOsActionPerformed

    private void relClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relClientesActionPerformed
        // TODO add your handling code here:
        int confirma = JOptionPane.showConfirmDialog(null, "Deseja Imprimir Relatorio?", "Clientes", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            try {
                JasperPrint imprimecliente = JasperFillManager.fillReport("C:/reports/aula/clientes.jasper", null, conexao);
                JasperViewer.viewReport(imprimecliente, false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }//GEN-LAST:event_relClientesActionPerformed

    private void menuRelOsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRelOsActionPerformed
        int confirma = JOptionPane.showConfirmDialog(null, "Deseja Imprimir Relatorio?", "Orde de Serviço", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            try {
                JasperPrint imprimeservico = JasperFillManager.fillReport("C:/reports/aula/servicos.jasper", null, conexao);
                JasperViewer.viewReport(imprimeservico, false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_menuRelOsActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(telaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(telaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(telaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(telaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new telaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane Desktop;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JLabel lblData;
    public static javax.swing.JLabel lblUser;
    private javax.swing.JMenu menuCad;
    private javax.swing.JMenuItem menuCadCliente;
    private javax.swing.JMenuItem menuCadOs;
    public static javax.swing.JMenuItem menuCadUser;
    private javax.swing.JMenu menuOpc;
    private javax.swing.JMenuItem menuOpcSair;
    private javax.swing.JMenu menuRel;
    public static javax.swing.JMenuItem menuRelOs;
    private javax.swing.JMenu menuSobre;
    private javax.swing.JMenuItem menuSobreAjuda;
    private javax.swing.JMenuItem menuSobreShow;
    private javax.swing.JMenuItem relClientes;
    // End of variables declaration//GEN-END:variables
}
