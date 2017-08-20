/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aula.form;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import br.com.aula.dal.ModuloConexao;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.DatasetFilter;
import net.sf.jasperreports.view.JasperViewer;

public class telaOS extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    private String tipo;

    private void imprimeOS() {
        int confirma = JOptionPane.showConfirmDialog(null, "Deseja Imprimir Ordem de Serviço?", "Ordem de Serviço", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            try {
                HashMap filtro = new HashMap();
                filtro.put("os",Integer.parseInt(txtOS.getText()));
                JasperPrint printOs = JasperFillManager.fillReport("C:/reports/aula/os.jasper", filtro, conexao);
                JasperViewer.viewReport(printOs, false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    

    private void pesquisaOS() {
        String num_os = JOptionPane.showInputDialog("Numero da OS");
        String sql = "SELECT * FROM tbos WHERE os =" + num_os;
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtOS.setText(rs.getString(1));
                txtData.setText(rs.getString(2));
                String tipo = rs.getString(3);
                if (tipo.equals("Orçamento")) {
                    rbOrcamento.setSelected(true);
                    tipo = "Orçamento";
                } else {
                    rbOs.setSelected(true);
                    tipo = "OS";
                }
                txtEquipamento.setText(rs.getString(4));
                txtDefeito.setText(rs.getString(5));
                txtServico.setText(rs.getString(6));
                txtTecnico.setText(rs.getString(7));
                txtValor.setText(rs.getString(8));
                cbxSituacao.setSelectedItem(rs.getString(9));

            } else {
                JOptionPane.showMessageDialog(null, "Os não cadastrada");
            }

        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException x) {
            JOptionPane.showMessageDialog(null, "OS inválida");

        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    private void apagaOS() {
        String sql = "DELETE FROM tbos WHERE os=" + txtOS.getText();
        try {
            pst = conexao.prepareStatement(sql);
            int confirma = JOptionPane.showConfirmDialog(null, "Deseja Excluir OS?", "Alerta", JOptionPane.YES_NO_OPTION);
            if (confirma == JOptionPane.YES_OPTION) {
                int apaga = pst.executeUpdate();
                if (apaga > 0) {
                    JOptionPane.showMessageDialog(null, "Registro Exlcluido");
                }
            }
        } catch (Exception e) {

        }
    }

    private void atualizaOS() {
        String sql = "UPDATE tbos SET tipo=?, equipamento=?, "
                + "defeito=?, servico=?, tecnico=?, valor=?, "
                + "situacao=? WHERE os=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tipo);
            pst.setString(2, txtEquipamento.getText());
            pst.setString(3, txtDefeito.getText());
            pst.setString(4, txtServico.getText());
            pst.setString(5, txtTecnico.getText());
            pst.setString(6, txtValor.getText());
            pst.setString(7, cbxSituacao.getSelectedItem().toString());
            pst.setString(8, txtOS.getText());
            if ((txtEquipamento.getText().isEmpty()) || (txtDefeito.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha os campos");
            } else {
                int confirma = pst.executeUpdate();

                if (confirma > 0) {
                    JOptionPane.showMessageDialog(null, "Registro atualizado");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void escolheCliente() {
        int coluna = tblClientes.getSelectedRow();
        txtId.setText(tblClientes.getModel().getValueAt(coluna, 0).toString());
        btnCria.setEnabled(true);
    }

    private void limpa() {
        txtData.setText(null);
        txtDefeito.setText(null);
        txtEquipamento.setText(null);
        txtId.setText(null);
        txtOS.setText(null);
        txtPesquisa.setText(null);
        txtServico.setText(null);
        txtTecnico.setText(null);
        txtValor.setText(null);
        rbOrcamento.setSelected(true);
    }

    private void novaOS() {
        String sql = "INSERT INTO tbos (tipo, equipamento, defeito, servico, tecnico, valor, situacao, idcli)VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tipo);
            pst.setString(2, txtEquipamento.getText());
            pst.setString(3, txtDefeito.getText());
            pst.setString(4, txtServico.getText());
            pst.setString(5, txtTecnico.getText());
            pst.setString(6, txtValor.getText().replace(",", "."));
            pst.setString(7, cbxSituacao.getSelectedItem().toString());
            pst.setString(8, txtId.getText());
            if ((txtId.getText().isEmpty()) || (txtEquipamento.getText().isEmpty()) || (txtDefeito.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha os campos");
            } else {
                int novo = pst.executeUpdate();

                if (novo > 0) {
                    JOptionPane.showMessageDialog(null, "OS cadastrado");
                    limpa();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    private void pesquisaCliente() {
        String sql = "SELECT idcli as ID, nomecli as NOME, contato as TELEFONE FROM tbclientes WHERE nomecli LIKE ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtPesquisa.getText() + "%");
            rs = pst.executeQuery();
            tblClientes.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public telaOS() {
        initComponents();
        conexao = ModuloConexao.conector();
        if (conexao == null) {
            JOptionPane.showMessageDialog(null, "Erro no servidor");
            telaLogin login = new telaLogin();
            login.setVisible(true);
            this.dispose();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupobotoes1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtOS = new javax.swing.JTextField();
        txtData = new javax.swing.JTextField();
        rbOrcamento = new javax.swing.JRadioButton();
        rbOs = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        cbxSituacao = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        txtId = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtEquipamento = new javax.swing.JTextField();
        txtDefeito = new javax.swing.JTextField();
        txtServico = new javax.swing.JTextField();
        txtTecnico = new javax.swing.JTextField();
        txtValor = new javax.swing.JTextField();
        btnCria = new javax.swing.JButton();
        btnBusca = new javax.swing.JButton();
        btnEdita = new javax.swing.JButton();
        btnDeleta = new javax.swing.JButton();
        btnImprime = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("::WebEmpresa");
        setPreferredSize(new java.awt.Dimension(600, 587));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setToolTipText("");

        jLabel1.setText("NºOS");

        jLabel2.setText("Data");

        txtOS.setEnabled(false);

        txtData.setEnabled(false);

        grupobotoes1.add(rbOrcamento);
        rbOrcamento.setText("Orçamento");
        rbOrcamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbOrcamentoActionPerformed(evt);
            }
        });

        grupobotoes1.add(rbOs);
        rbOs.setText("Ordem de Serviço");
        rbOs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbOsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtOS, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbOrcamento))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbOs)
                    .addComponent(jLabel2)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbOrcamento)
                    .addComponent(rbOs))
                .addContainerGap())
        );

        jLabel3.setText("Situação:");

        cbxSituacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Entrega Ok", "Orçamento Reprovado", "Aguardando peças", "Na bancada", "Cliente Abandonou", "Retornou", " " }));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/aula/icones/icobusca.png"))); // NOI18N

        txtPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisaKeyReleased(evt);
            }
        });

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        txtId.setEnabled(false);

        jLabel10.setText("ID:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 37, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel5.setText("*EQUIPAMENTO");

        jLabel6.setText("*DEFEITO");

        jLabel7.setText("SERVIÇO");

        jLabel8.setText("*TECNICO");

        jLabel9.setText("VALOR TOTAL");

        txtValor.setText("0");

        btnCria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/aula/icones/cria.png"))); // NOI18N
        btnCria.setEnabled(false);
        btnCria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCriaActionPerformed(evt);
            }
        });

        btnBusca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/aula/icones/busca.png"))); // NOI18N
        btnBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscaActionPerformed(evt);
            }
        });

        btnEdita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/aula/icones/edita.png"))); // NOI18N
        btnEdita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditaActionPerformed(evt);
            }
        });

        btnDeleta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/aula/icones/deleta.png"))); // NOI18N
        btnDeleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletaActionPerformed(evt);
            }
        });

        btnImprime.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/aula/icones/imprime.png"))); // NOI18N
        btnImprime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCria, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEdita, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDeleta, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnImprime, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtServico, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                                    .addComponent(txtDefeito, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtEquipamento, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addComponent(txtValor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxSituacao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(10, 10, 10))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cbxSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEquipamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtDefeito, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtServico, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8))
                .addGap(94, 94, 94)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCria, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdita, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleta, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImprime, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(97, Short.MAX_VALUE))
        );

        setBounds(0, 0, 600, 587);
    }// </editor-fold>//GEN-END:initComponents

    private void txtPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaKeyReleased
        // TODO add your handling code here:
        pesquisaCliente();
    }//GEN-LAST:event_txtPesquisaKeyReleased

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        // TODO add your handling code here:
        escolheCliente();
    }//GEN-LAST:event_tblClientesMouseClicked

    private void rbOrcamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbOrcamentoActionPerformed
        // TODO add your handling code here:
        tipo = "Orçamento";
    }//GEN-LAST:event_rbOrcamentoActionPerformed

    private void rbOsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbOsActionPerformed
        // TODO add your handling code here:
        tipo = "OS";
    }//GEN-LAST:event_rbOsActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        rbOrcamento.setSelected(true);
        tipo = "Orçamento";

    }//GEN-LAST:event_formInternalFrameOpened

    private void btnCriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCriaActionPerformed
        // TODO add your handling code here:
        novaOS();
    }//GEN-LAST:event_btnCriaActionPerformed

    private void btnBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaActionPerformed
        // TODO add your handling code here:
        pesquisaOS();
    }//GEN-LAST:event_btnBuscaActionPerformed

    private void btnEditaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditaActionPerformed
        // TODO add your handling code here:
        atualizaOS();
    }//GEN-LAST:event_btnEditaActionPerformed

    private void btnDeletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletaActionPerformed
        // TODO add your handling code here:
        apagaOS();
        limpa();
    }//GEN-LAST:event_btnDeletaActionPerformed

    private void btnImprimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimeActionPerformed
         // TODO add your handling code here:
         imprimeOS();
    }//GEN-LAST:event_btnImprimeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBusca;
    private javax.swing.JButton btnCria;
    private javax.swing.JButton btnDeleta;
    private javax.swing.JButton btnEdita;
    private javax.swing.JButton btnImprime;
    private javax.swing.JComboBox<String> cbxSituacao;
    private javax.swing.ButtonGroup grupobotoes1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbOrcamento;
    private javax.swing.JRadioButton rbOs;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtDefeito;
    private javax.swing.JTextField txtEquipamento;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtOS;
    private javax.swing.JTextField txtPesquisa;
    private javax.swing.JTextField txtServico;
    private javax.swing.JTextField txtTecnico;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
