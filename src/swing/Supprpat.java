/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import myconnections.DBConnection;
import classe.DAO.PatientDAO;
import java.sql.ResultSet;
import java.sql.Statement;
import medecin.metier.Patient;
import static swing.windows.f;

/**
 *
 * @author jerom
 */
public class Supprpat extends javax.swing.JPanel {

    /**
     * Creates new form Supprpat
     */
    String nom,prenom,tel = "";
    Statement stmt;
    ResultSet rs = null;
    Patient pat;
    int id;
    public Supprpat() {
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
        nomRech = new javax.swing.JTextField();
        btRech = new javax.swing.JButton();
        lblID = new javax.swing.JLabel();
        lblTel = new javax.swing.JLabel();
        lblNom = new javax.swing.JLabel();
        lblPrenom = new javax.swing.JLabel();
        btValider = new javax.swing.JButton();
        btAnnuler = new javax.swing.JButton();

        setBackground(new java.awt.Color(153, 204, 255));
        setLayout(new java.awt.GridLayout(5, 2, 20, 20));

        jLabel1.setFont(new java.awt.Font("Tahoma", 2, 24)); // NOI18N
        jLabel1.setText("Entrez le nom :");
        add(jLabel1);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("Suppression de patient");
        add(jLabel2);
        add(nomRech);

        btRech.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btRech.setText("Rechercher");
        btRech.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRechActionPerformed(evt);
            }
        });
        add(btRech);

        lblID.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        add(lblID);

        lblTel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        add(lblTel);

        lblNom.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        add(lblNom);

        lblPrenom.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        add(lblPrenom);

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
        f.setContentPane(new menupat());
        f.repaint();
        f.revalidate();
    }//GEN-LAST:event_btAnnulerActionPerformed

    private void btRechActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRechActionPerformed
        try{
            Connection dbConnect = DBConnection.getConnection();
            nom=nomRech.getText();
            stmt = dbConnect.createStatement();
            rs = stmt.executeQuery("select * from patient");
            while(rs.next()){
                if(rs.getString("NOM").equals(nom)){
                    id=rs.getInt("IDPAT");
                    prenom = rs.getString("PRENOM");
                    tel = rs.getString("TEL");
                }
            }
            String sid=String.valueOf(id);
            lblID.setText("ID : "+sid);
            lblNom.setText("Nom : "+nom);
            lblPrenom.setText("Prenom : "+prenom);
            lblTel.setText("N° de téléphone : "+tel);
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(this,e,"Erreur",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btRechActionPerformed

    private void btValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btValiderActionPerformed
        PatientDAO pdao = new PatientDAO();
        pat = new Patient(id,nom,prenom,tel);
        try{
            pdao.delete(pat);
            JOptionPane.showMessageDialog(this,"Patient supprimé avec succès","Succès",JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this,e,"Erreur",JOptionPane.INFORMATION_MESSAGE);
        }
        f.setContentPane(new menupat());
        f.repaint();
        f.revalidate();
    }//GEN-LAST:event_btValiderActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAnnuler;
    private javax.swing.JButton btRech;
    private javax.swing.JButton btValider;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblNom;
    private javax.swing.JLabel lblPrenom;
    private javax.swing.JLabel lblTel;
    private javax.swing.JTextField nomRech;
    // End of variables declaration//GEN-END:variables
}