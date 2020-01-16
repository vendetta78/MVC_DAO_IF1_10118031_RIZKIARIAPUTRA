/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latihanmvcdao.main;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import latihanmvcdao.database.kingsbarbershopDatabase;
import latihanmvcdao.entity.pelanggan;
import latihanmvcdao.error.PelangganException;
import latihanmvcdao.service.PelangganDAO;
import latihanmvcdao.view.MainViewPelanggan;

/**
 * *Nama :Rizki Aria Putra
 * NIM : 10118031
 * Kelas : IF-1 2018
 *
 * @author Vedentta78
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, PelangganException {
        // TODO code application logic here
       SwingUtilities.invokeLater(new Runnable() {

           @Override
           public void run() {       
               try{
                   MainViewPelanggan pelanggan = new MainViewPelanggan();
                   pelanggan.loadDatabase();
                   pelanggan.setVisible(true);
               }catch(SQLException e){
               
               
               }
             catch (PelangganException ex) {
                   Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
               }
       }
        
       });    
        
        
       }  
        
        
        
    }
    

