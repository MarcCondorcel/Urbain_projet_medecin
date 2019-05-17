/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;
import static swing.windows.f;
import java.sql.*;
import medecin.metier.Prescription;
import medecin.metier.Medecin;
import medecin.metier.Patient;
import classe.DAO.PrescriptionDAO;
import classe.DAO.MedecinDAO;
import classe.DAO.PatientDAO;
import javax.swing.JOptionPane;
import myconnections.DBConnection;

/**
 *
 * @author jerom
 */
public class Supprpres extends javax.swing.JPanel {

    /**
     * Creates new form Supprpres
     */
    String datepres,nompat,nommed;
    Statement stmt;
    ResultSet rs = null;
    int id,idmed,idpat,count;
    Prescription pres;
    PrescriptionDAO pdao = new PrescriptionDAO();
    public Supprpres() {
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
        IDRech = new javax.swing.JTextField();
        btRech = new javax.swing.JButton();
        txtMed = new javax.swing.JLabel();
        txtPat = new javax.swing.JLabel();
        txtDatepres = new javax.swing.JLabel();
        txtCount = new javax.swing.JLabel();
        btValider = new javax.swing.JButton();
        btAnnuler = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 204, 102));
        setLayout(new java.awt.GridLayout(5, 2, 10, 20));

        jLabel1.setFont(new java.awt.Font("Tahoma", 2, 24)); // NOI18N
        jLabel1.setText("Entrez l'ID :");
        add(jLabel1);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Suppression prescription");
        add(jLabel2);

        IDRech.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        add(IDRech);

        btRech.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btRech.setText("Rechercher");
        btRech.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRechActionPerformed(evt);
            }
        });
        add(btRech);

        txtMed.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        add(txtMed);

        txtPat.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        add(txtPat);

        txtDatepres.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        add(txtDatepres);

        txtCount.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        add(txtCount);

        btValider.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btValider.setText("Valider");
        btValider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btValiderActionPerformed(evt);
            }
        });
        add(btValider);

        btAnnuler.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btAnnuler.setText("Annuler");
        btAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAnnulerActionPerformed(evt);
            }
        });
        add(btAnnuler);
    }// </editor-fold>//GEN-END:initComponents

    private void btRechActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRechActionPerformed
        try{
            Connection dbConnect = DBConnection.getConnection();
            String ids=IDRech.getText();
            id=Integer.parseInt(ids);
            System.out.println(id);
                stmt = dbConnect.createStatement();
                rs = stmt.executeQuery("select * from prescription");
                while(rs.next()){
                    if(rs.getInt("IDPRES")==(id)){
                        datepres=rs.getString("DATEPRES");
                        idmed=rs.getInt("IDMED");
                        idpat=rs.getInt("IDPAT");
                    }                
                }
            txtDatepres.setText("Date : "+datepres);
                stmt = dbConnect.createStatement();
                rs = stmt.executeQuery("select * from patient");
                while(rs.next()){
                    if(rs.getInt("IDPAT")==(idpat)){
                        nompat=rs.getString("NOM");
                    }
                }
            txtPat.setText("Patient : "+nompat);
                stmt = dbConnect.createStatement();
               rs = stmt.executeQuery("select * from medecin");
               while(rs.next()){
                   if(rs.getInt("IDMED")==(idmed)){
                       nommed=rs.getString("NOM");
                   }
               }
            txtMed.setText("Medecin : "+nommed);
            String req = "Select count(*) AS COUNT from infos where idpres=? ";
            try (PreparedStatement pstm = dbConnect.prepareStatement(req)){
                pstm.setInt(1, id);
            //rs = stmt.executeQuery("select * from INFO_PRESCRIPTION WHERE IDPRES IN (SELECT idpres FROM PRESCRIPTION WHERE idpat=?)");
                try (ResultSet rs = pstm.executeQuery()){
                    while(rs.next()){
                        count=rs.getInt("COUNT");
                    }
                }
            }                        
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(this,e,"Erreur",JOptionPane.INFORMATION_MESSAGE);
        }
        String scount=String.valueOf(count);
        if(count==1){
            txtCount.setText(scount+" médicament");
        }else{
            txtCount.setText(scount+" médicaments");
        }    
    }//GEN-LAST:event_btRechActionPerformed

    private void btAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAnnulerActionPerformed
        f.setContentPane(new menupres());
        f.repaint();
        f.revalidate();
    }//GEN-LAST:event_btAnnulerActionPerformed

    private void btValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btValiderActionPerformed
        try{
            pres= new Prescription(id,datepres,idmed,idpat);
            pdao.delete(pres);
            JOptionPane.showMessageDialog(this,"Suppression effectuée avec succès","Succés",JOptionPane.INFORMATION_MESSAGE);
            f.setContentPane(new menupres());
            f.repaint();
            f.revalidate();
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(this,e,"Erreur",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btValiderActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField IDRech;
    private javax.swing.JButton btAnnuler;
    private javax.swing.JButton btRech;
    private javax.swing.JButton btValider;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel txtCount;
    private javax.swing.JLabel txtDatepres;
    private javax.swing.JLabel txtMed;
    private javax.swing.JLabel txtPat;
    // End of variables declaration//GEN-END:variables
}
