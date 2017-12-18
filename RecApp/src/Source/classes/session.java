/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Source.classes;

import Source.MysqlConnect;
import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author shali
 */
public class session {
    private int sessionId;
    private String reason;
    private String court_id_check;
    private String nsbm_id;
    private String bookDate;  
    private String CheckDate;
    private String bookTime;
    private String checkTime;
    
    Connection conn = MysqlConnect.ConnectDB();;
    PreparedStatement pst;
    ResultSet rs;
    private int maxNo;
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCourt_id_check() {
        return court_id_check;
    }

    public void setCourt_id_check(String court_id_check) {
        this.court_id_check = court_id_check;
    }

    public String getNsbm_id() {
        return nsbm_id;
    }

    public void setNsbm_id(String nsbm_id) {
        this.nsbm_id = nsbm_id;
    }

    public String getBookDate() {
        return bookDate;
    }

    public void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }

    public String getCheckDate() {
        return CheckDate;
    }

    public void setCheckDate(String CheckDate) {
        this.CheckDate = CheckDate;
    }

    public String getBookTime() {
        return bookTime;
    }

    public void setBookTime(String bookTime) {
        this.bookTime = bookTime;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }
    
    public int setSessionId(){
        return this.sessionId = 100+(int)(Math.random()*99999999);
    }
    public void decreaseMaxNo(String x){
        String sql;
        sql = "UPDATE court_time_max SET max_no=max_no-1 WHERE court_id='"+x+"' AND start_time='"+this.bookTime+"'";
        try{
            pst = conn.prepareStatement(sql);
            int effectedRows = pst.executeUpdate();
            if(effectedRows>0){
                
                 JOptionPane.showMessageDialog(null, "Session decrease successful !");
                
            }
            else{
                
                JOptionPane.showMessageDialog(null, "session decrase unsuccessful !");
                
            }          
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void BookSession(String userNameGlob,String x){
        String sql;
        int sessionRandNo = setSessionId();
        sql = "INSERT INTO session(`session_id`, `date`, `start_time`, `end_time`, `reason`, `court_id`, `nsbm_id`)VALUES ('"+sessionRandNo+"', '" + this.bookDate + "', '" + this.bookTime + "', ADDTIME('"+ this.bookTime +":00','01:00:00'), '"+ this.reason +"', '" + x + "', '" + userNameGlob + "');";
        
        try{
            pst = conn.prepareStatement(sql);
            int effectedRows = pst.executeUpdate();
            if(effectedRows>0){
                
                 JOptionPane.showMessageDialog(null, "Booking successful ! \nSession ID to share: "+sessionRandNo);
                 
                
            }
            else{
                
                JOptionPane.showMessageDialog(null, "Booking unsuccessful !");
                
            }          
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public int getSessionId(){
        return this.sessionId;
    }
    
    public int CheckSession(){
        String sql = "SELECT * FROM session WHERE date ='" + this.CheckDate +"' AND start_time= '"+this.checkTime+":00"+"' AND court_id = '"+this.court_id_check+"'";
        int result = 0;
        try{
            pst = conn.prepareStatement(sql);   
            rs = pst.executeQuery();
            
            if(rs.next()){
                result = 1;
            }else{
                result = 0;
            }
            
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
        } 
        return result;
    }
    public int CheckSession(String CourtID){
        String sql = "SELECT * FROM session WHERE date ='" + this.CheckDate +"' AND start_time= '"+this.checkTime+":00"+"' AND court_id = '"+CourtID+"'";
        int result = 0;
        try{
            pst = conn.prepareStatement(sql);   
            rs = pst.executeQuery();
            
            if(rs.next()){
                result = 1;
            }else{
                result = 0;
            }
            
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
        } 
        return result;
    }
    
    
    public int GetMaxNo(){
        String maxNoSql = "SELECT * FROM court_time_max WHERE court_id ='"+this.court_id_check+"' AND start_time='"+this.checkTime+"'";
        
        try {
            pst = conn.prepareStatement(maxNoSql);
            rs = pst.executeQuery();
            if (rs.next()) {
                this.maxNo=rs.getInt("max_no");
            }
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e);
        }
        return maxNo;
        
    }

}
    
