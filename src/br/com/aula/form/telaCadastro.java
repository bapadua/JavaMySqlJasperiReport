/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aula.form;

import br.com.aula.dal.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author SolucaoLogica
 */
public class telaCadastro extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    private void Limpa() {
        txtId.setText(null);
        txtBusca.setText(null);
        txtCelular.setText(null);
        txtNome.setText(null);
        txtId.setText(null);
        txtSenha.setText(null);
        txtUser.setText(null);
    }

    private void DeletaUsuario(){
        try {
            String sql = "DELETE FROM tbusuarios WHERE iduser=?";
            if(txtId.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Faça uma busca pelo usuario");
            }else{
                int confirma = JOptionPane.showConfirmDialog(null, "Deseja excluir esse usuario?", "ATENÇÃO", JOptionPane.YES_NO_OPTION);
                if(confirma == JOptionPane.YES_OPTION){
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, txtId.getText());
                    pst.executeUpdate();
                    pst.close();
                    Limpa();                
                }else{
                    Limpa();
                }
            }
        } catch (Exception e) {
        }
    }
    
    private void UpdateUser() {
        try {
            String sql = "UPDATE tbusuarios SET usuario = ?, celular = ?, senha = ?, perfil = ? WHERE iduser = ?";
            if ((txtNome.getText().isEmpty()) || (txtCelular.getText().isEmpty()) || (txtSenha.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos Campos");
            } else {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtNome.getText());
                pst.setString(2, txtCelular.getText());
                pst.setString(3, txtSenha.getText());
                pst.setString(4, cbxPerfil.getSelectedItem().toString());
                pst.setString(5, txtId.getText());
                int update = pst.executeUpdate();
                if(update > 0)JOptionPane.showMessageDialog(null, "Cadastro atualizado com sucesso");
            }
        } catch (Exception e) {
            
        }
    }

    private void CreateUser() {
        try {
            String sqlInsere = "INSERT INTO tbusuarios (usuario, celular, login, senha, perfil)"
                    + "VALUES(?, ?, ?, ?, ?)";
            if ((txtUser.getText().isEmpty()) || (txtNome.getText().isEmpty()) || (txtSenha.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos Campos");
            } else {
                pst = conexao.prepareStatement(sqlInsere);
                pst.setString(1, txtNome.getText());
                pst.setString(2, txtCelular.getText());
                pst.setString(3, txtUser.getText());
                pst.setString(4, txtSenha.getText());
                pst.setString(5, cbxPerfil.getSelectedItem().toString());
                int add = pst.executeUpdate();
                if (add > 0) {
                    JOptionPane.showMessageDialog(null, txtUser.getText() + " adicionado");
                }
                Limpa();
                pst.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ReadUser() {

        String busca = txtBusca.getText();
        String sqlUsuario = "SELECT * FROM tbusuarios WHERE usuario = ?";
        String sqlCelular = "SELECT * FROM tbusuarios WHERE celular = ?";
        String sqlLogin = "SELECT * FROM tbusuarios WHERE login = ?";

        try {
            pst = conexao.prepareStatement(sqlUsuario);
            pst.setString(1, busca);
            rs = pst.executeQuery();
            if (!rs.next()) {
                pst = conexao.prepareStatement(sqlCelular);
                pst.setString(1, busca);
                rs = pst.executeQuery();
                if (!rs.next()) {
                    pst = conexao.prepareStatement(sqlLogin);
                    pst.setString(1, busca);
                    rs = pst.executeQuery();
                    if (!rs.next()) {
                        JOptionPane.showMessageDialog(null, "Não encontrado!", "Resultado da busca", JOptionPane.YES_OPTION);
                        Limpa();
                    }
                }
            }
            txtId.setText(rs.getString(1));
            txtNome.setText(rs.getString(2));
            txtCelular.setText(rs.getString(3));
            txtUser.setText(rs.getString(4));
            txtSenha.setText(rs.getString(5));
            cbxPerfil.setSelectedItem(rs.getString(6));

        } catch (Exception e) {
        }

    }

    public telaCadastro() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtUser = new javax.swing.JTextField();
        txtCelular = new javax.swing.JTextField();
        cbxPerfil = new javax.swing.JComboBox<>();
        txtSenha = new javax.swing.JPasswordField();
        txtId = new javax.swing.JTextField();
        btnBusca = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDeleta = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtBusca = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("::WebEmpresa");
        setPreferredSize(new java.awt.Dimension(597, 555));

        jLabel1.setText("NOME");

        jLabel2.setText("*USUARIO");

        jLabel3.setText("*CELULAR");

        jLabel4.setText("*SENHA");

        jLabel5.setText("ID");

        jLabel6.setText("*PERFIL");

        cbxPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "usuario", "admin" }));

        txtId.setEditable(false);

        btnBusca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/aula/icones/busca.png"))); // NOI18N
        btnBusca.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscaActionPerformed(evt);
            }
        });

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/aula/icones/cria.png"))); // NOI18N
        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/aula/icones/edita.png"))); // NOI18N
        btnEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDeleta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/aula/icones/deleta.png"))); // NOI18N
        btnDeleta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletaActionPerformed(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/aula/icones/icobusca.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(cbxPerfil, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtBusca, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSenha, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(355, 355, 355))))
            .addGroup(layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(btnBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(btnDeleta, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbxPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleta, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(128, Short.MAX_VALUE))
        );

        setBounds(0, 0, 600, 587);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaActionPerformed
        // TODO add your handling code here:
        ReadUser();
    }//GEN-LAST:event_btnBuscaActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        CreateUser();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // Atualiza os dados no banco
        UpdateUser();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletaActionPerformed
         // Exclui usuario
         DeletaUsuario();
    }//GEN-LAST:event_btnDeletaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBusca;
    private javax.swing.JButton btnDeleta;
    private javax.swing.JButton btnEdit;
    private javax.swing.JComboBox<String> cbxPerfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtBusca;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNome;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
