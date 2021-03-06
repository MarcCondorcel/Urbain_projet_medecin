/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;
import medecin.metier.*;
import classe.DAO.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import myconnections.DBConnection;
import static swing.windows.f;

/**
 *
 * @author jerom
 */
public class Presmed extends javax.swing.JPanel {

    /**
     * Creates new form Presmed
     */
    String nommed,prenommed,nommedoc,description,telmed,unite = "";
    Statement stmt;
    ResultSet rs = null;
    Patient pat;
    Infos info;
    Medicament medoc;
    Medecin med;
    Prescription pres;
    int quantite,idmed;
    public Presmed() {
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
        txtRech = new javax.swing.JTextField();
        btRech = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btAnnuler = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 204, 102));
        setLayout(new java.awt.GridLayout(3, 2, 20, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 2, 24)); // NOI18N
        jLabel1.setText("Entrez le nom :");
        add(jLabel1);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Prescriptions d'un medecin");
        add(jLabel2);

        txtRech.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        add(txtRech);

        btRech.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btRech.setText("Rechercher");
        btRech.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRechActionPerformed(evt);
            }
        });
        add(btRech);
        add(jLabel3);

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
            nommed=txtRech.getText();
            stmt = dbConnect.createStatement();
            rs = stmt.executeQuery("select * from medecin");            
            while(rs.next()){
                if(rs.getString("NOM").equals(nommed)){
                    idmed=rs.getInt("IDMED");
                    prenommed = rs.getString("PRENOM");
                    telmed = rs.getString("TEL");
                }
            }
            String affiche="Médicaments prescrits par "+nommed +" "+prenommed +" Tel : "+telmed;
            //stmt = dbConnect.createStatement();
            String req = "select * from INFO_PRESCRIPTION WHERE IDPRES IN (SELECT idpres FROM PRESCRIPTION WHERE idmed=?)";
            try (PreparedStatement pstm = dbConnect.prepareStatement(req)){
                pstm.setInt(1, idmed);
            //rs = stmt.executeQuery("select * from INFO_PRESCRIPTION WHERE IDPRES IN (SELECT idpres FROM PRESCRIPTION WHERE idpat=?)");
                try (ResultSet rs = pstm.executeQuery()){
                    while(rs.next()){
                        nommedoc=rs.getString("NOM");
                        description=rs.getString("DESCRIPTION");
                        unite=rs.getString("UNITE");
                        quantite=rs.getInt("QUANTITE");
                        affiche += "\n" +"  "+ quantite +"\t  "+ nommedoc +"\t  "+ unite +"\t  "+ description;
                    }
                }
            }
            JOptionPane.showMessageDialog(this,affiche,"Résultat",JOptionPane.INFORMATION_MESSAGE);
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(this,e,"Erreur",JOptionPane.INFORMATION_MESSAGE);
        }
        f.setContentPane(new menupres());
        f.repaint();
        f.revalidate();
    }//GEN-LAST:event_btRechActionPerformed

    private void btAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAnnulerActionPerformed
        f.setContentPane(new menupres());
        f.repaint();
        f.revalidate();
    }//GEN-LAST:event_btAnnulerActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAnnuler;
    private javax.swing.JButton btRech;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtRech;
    // End of variables declaration//GEN-END:variables
}
