/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Source.classes;

import Source.MysqlConnect;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author shali
 */
public class on_going {
    int session_live_id;
    int session_id;
    String nsbm_id;
    
    Connection conn = MysqlConnect.ConnectDB();;
    PreparedStatement pst;
    ResultSet rs;

    public int getSession_live_id() {
        return session_live_id;
    }

    public void setSession_live_id(int session_live_id) {
        this.session_live_id = session_live_id;
    }

    public int getSession_id() {
        return session_id;
    }

    public void setSession_id(int session_id) {
        this.session_id = session_id;
    }

    public String getNsbm_id() {
        return nsbm_id;
    }

    public void setNsbm_id(String nsbm_id) {
        this.nsbm_id = nsbm_id;
    }
    
    public void OnGoingBook(){
        String sql;
        sql = "INSERT INTO on_going(`session_live_id`, `session_id`, `nsbm_id`, `random_id`)VALUES (NULL, '" + this.session_id + "', '" + this.nsbm_id + "', '"+ this.session_id +"')";

        try{
            pst = conn.prepareStatement(sql);
            int effectedRows = pst.executeUpdate();
            if(effectedRows>0){
                
                 JOptionPane.showMessageDialog(null, "Ongonig successful !");
                
            }
            else{
                
                JOptionPane.showMessageDialog(null, "Ongoing unsuccessful !");
                
            }          
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        
       
    }
    
    public void codeSubmit(String x){
        String sql;
        sql = "INSERT INTO on_going(`session_live_id`, `session_id`, `nsbm_id`, `random_id`)VALUES (NULL, '" + x + "', '" + this.nsbm_id + "', '"+ x +"')";

        try{
            pst = conn.prepareStatement(sql);
            int effectedRows = pst.executeUpdate();
            if(effectedRows>0){
                
                 JOptionPane.showMessageDialog(null, "registerd to session successful !");
                
            }
            else{
                
                JOptionPane.showMessageDialog(null, "registerd to session unsuccessful !");
                
            }          
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
}
