/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;

import static swing.windows.f;
import classe.DAO.MedicamentDAO;
import medecin.metier.Medicament;
import myconnections.DBConnection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author jerom
 */
public class Modifmedoc extends javax.swing.JPanel {

    /**
     * Creates new form Modifmedoc
     */
    
    String nom, description,code = "";
    Statement stmt;
    ResultSet rs = null;
    Medicament medoc;
    int id;
    public Modifmedoc() {
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

        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        NomRech = new javax.swing.JTextField();
        btRech = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNom = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCode = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtaDesc = new javax.swing.JTextArea();
        btValid = new javax.swing.JButton();
        btAnnul = new javax.swing.JButton();

        jTextField2.setText("jTextField2");

        setBackground(new java.awt.Color(153, 255, 153));
        setLayout(new java.awt.GridLayout(7, 2, 10, 10));

        jLabel2.setFont(new java.awt.Font("Tahoma", 2, 24)); // NOI18N
        jLabel2.setText("Entrez le nom :");
        add(jLabel2);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel1.setText("Modification de Medicament");
        add(jLabel1);
        add(NomRech);

        btRech.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btRech.setText("Rechercher");
        btRech.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRechActionPerformed(evt);
            }
        });
        add(btRech);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("ID");
        add(jLabel3);
        add(lblID);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Nom");
        add(jLabel4);

        txtNom.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        add(txtNom);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Code");
        add(jLabel5);

        txtCode.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        add(txtCode);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Description");
        add(jLabel6);

        txtaDesc.setColumns(20);
        txtaDesc.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtaDesc.setRows(5);
        jScrollPane1.setViewportView(txtaDesc);

        add(jScrollPane1);

        btValid.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btValid.setText("Valider");
        btValid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btValidActionPerformed(evt);
            }
        });
        add(btValid);

        btAnnul.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btAnnul.setText("Annuler");
        btAnnul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAnnulActionPerformed(evt);
            }
        });
        add(btAnnul);
    }// </editor-fold>//GEN-END:initComponents

    private void btAnnulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAnnulActionPerformed

        f.setContentPane(new menumedoc());
        f.repaint();
        f.revalidate();
    }//GEN-LAST:event_btAnnulActionPerformed

    private void btRechActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRechActionPerformed
        try {
            Connection dbConnect = DBConnection.getConnection();
            nom=NomRech.getText();
            stmt = dbConnect.createStatement();
            rs = stmt.executeQuery("select * from medicament");
            while(rs.next()){
                if(rs.getString("NOM").equals(nom)){
                    id=rs.getInt("IDMEDOC");
                    description = rs.getString("DESCRIPTION");
                    code = rs.getString("CODE");
                }
            }
            String sid=String.valueOf(id);
            lblID.setText(sid);
            txtNom.setText(nom);
            txtCode.setText(code);
            txtaDesc.setText(description);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,e,"Erreur",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btRechActionPerformed

    private void btValidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btValidActionPerformed
            MedicamentDAO mdao = new MedicamentDAO();        
            nom=txtNom.getText();
            code=txtCode.getText();
            description=txtaDesc.getText();
            medoc = new Medicament(id,nom,description,code);
            try{
                medoc=mdao.update(medoc);
                JOptionPane.showMessageDialog(this,"Médicament modifié avec succès","Succès",JOptionPane.INFORMATION_MESSAGE);
            }catch(SQLException e){
                JOptionPane.showMessageDialog(this,e,"Erreur",JOptionPane.INFORMATION_MESSAGE);
            }
            f.setContentPane(new menumedoc());
            f.repaint();
            f.revalidate();
    }//GEN-LAST:event_btValidActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField NomRech;
    private javax.swing.JButton btAnnul;
    private javax.swing.JButton btRech;
    private javax.swing.JButton btValid;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel lblID;
    private javax.swing.JTextField txtCode;
    private javax.swing.JTextField txtNom;
    private javax.swing.JTextArea txtaDesc;
    // End of variables declaration//GEN-END:variables
}
