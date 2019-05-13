/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;

import java.sql.ResultSet;
import java.sql.Statement;
import medecin.metier.Medecin;
import static swing.windows.f;
import classe.DAO.MedecinDAO;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import static swing.windows.f;

/**
 *
 * @author jerom
 */
public class Creamed extends javax.swing.JPanel {

    /**
     * Creates new form Creamed
     */
    String matricule,nom,prenom,tel = "";
    Statement stmt;
    ResultSet rs = null;
    Medecin med;
    int id;
    public Creamed() {
        initComponents();
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
        txtMat = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNom = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtPrenom = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTel = new javax.swing.JTextField();
        btValider = new javax.swing.JButton();
        btAnnuler = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 204, 204));
        setLayout(new java.awt.GridLayout(6, 2, 10, 10));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Création de médecin");
        add(jLabel1);
        add(jLabel2);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Matricule");
        add(jLabel3);

        txtMat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        add(txtMat);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Nom");
        add(jLabel4);

        txtNom.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        add(txtNom);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Prenom");
        add(jLabel5);

        txtPrenom.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        add(txtPrenom);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("N° de téléphone");
        add(jLabel6);

        txtTel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        add(txtTel);

        btValider.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btValider.setText("Valider");
        btValider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btValiderActionPerformed(evt);
            }
        });
        add(btValider);

        btAnnuler.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btAnnuler.setText("Annuler");
        btAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAnnulerActionPerformed(evt);
            }
        });
        add(btAnnuler);
    }// </editor-fold>//GEN-END:initComponents

    private void btAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAnnulerActionPerformed
        f.setContentPane(new menumed());
        f.repaint();
        f.revalidate();
    }//GEN-LAST:event_btAnnulerActionPerformed

    private void btValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btValiderActionPerformed
        MedecinDAO medao = new MedecinDAO();
        id=0;
        matricule=txtMat.getText();
        nom=txtNom.getText();
        prenom=txtPrenom.getText();
        tel=txtTel.getText();
        med = new Medecin(id,matricule,nom,prenom,tel);
        try{
                med=medao.create(med);
                JOptionPane.showMessageDialog(this,"Medecin créé avec succès","Succès",JOptionPane.INFORMATION_MESSAGE);
            }catch(SQLException e){
                JOptionPane.showMessageDialog(this,e,"Erreur",JOptionPane.INFORMATION_MESSAGE);
            }
        f.setContentPane(new menumed());
        f.repaint();
        f.revalidate();
    }//GEN-LAST:event_btValiderActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAnnuler;
    private javax.swing.JButton btValider;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField txtMat;
    private javax.swing.JTextField txtNom;
    private javax.swing.JTextField txtPrenom;
    private javax.swing.JTextField txtTel;
    // End of variables declaration//GEN-END:variables
}
