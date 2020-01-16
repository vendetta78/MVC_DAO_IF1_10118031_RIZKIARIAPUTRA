/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latihanmvcdao.controller;

import javax.swing.JOptionPane;
import latihanmvcdao.model.PelangganModel;
import latihanmvcdao.view.PelangganView;

/**
 * *Nama :Rizki Aria Putra
 * NIM : 10118031
 * Kelas : IF-1 2018
 *
 * @author Vedentta78
 */
public class PelangganController {
    private PelangganModel model;

    public void setModel(PelangganModel model) {
        this.model = model;
    }
    
    public void resetPelanggan(PelangganView view){
        model.resetPelanggan();
    }
    public void InsertPelanggan(PelangganView view){
        String nama = view.getTxtNAMA().getText();
        String alamat = view.getTxtALAMAT().getText();
        String telepon = view.getTxtTELEPON().getText();
        String email = view.getTxtEMAIL().getText();
        
        if(nama.trim().equals("")){
            JOptionPane.showMessageDialog(view,"Nama Tidak Boleh Kosong");
        }else  if(nama.length()>255){
            JOptionPane.showMessageDialog(view,"Nama Tidak Boleh Lebih dari 255 karakter");
        }else if(telepon.length()>12){
            JOptionPane.showMessageDialog(view,"Telepon Tidak Boleh Lebih dari 12 digit");
        }else  if(!email.contains("@") || !email.contains(".")){
            JOptionPane.showMessageDialog(view,"Email Tidak Valid");
        }else{
            model.setNama(nama);
            model.setAlamat(alamat);
            model.setTelepon(telepon);
            model.setEmail(email);
            
            try{
                model.InsertPelanggan();
                JOptionPane.showMessageDialog(view, "Pelanggan Berhasil Ditambahkan");
                model.resetPelanggan();
            }catch(Throwable throwabel){
                JOptionPane.showMessageDialog(view,new Object[]{
                    (" Terjadi Error di database dengan pesan"), throwabel.getMessage()});
            }
        }
        
        
    }
    public void UpdatePelanggan(PelangganView view){
        
        if(view.getTablePelanggan().getSelectedRowCount() == 0){
            JOptionPane.showMessageDialog(view, "Silahkan Seleksi data yang akan diubah");
            return;
        }
        
        Integer id = Integer.parseInt(view.getTxtID().getText());
        String nama = view.getTxtNAMA().getText();
        String alamat = view.getTxtALAMAT().getText();
        String telepon = view.getTxtTELEPON().getText();
        String email = view.getTxtEMAIL().getText();
        
        if(nama.trim().equals("")){
            JOptionPane.showMessageDialog(view,"Nama Tidak Boleh Kosong");
        }else  if(nama.length()>255){
            JOptionPane.showMessageDialog(view,"Nama Tidak Boleh Lebih dari 255 karakter");
        }else if(telepon.length()>12){
            JOptionPane.showMessageDialog(view,"Telepon Tidak Boleh Lebih dari 12 digit");
        }else  if(!email.contains("@") || !email.contains(".")){
            JOptionPane.showMessageDialog(view,"Email Tidak Valid");
        }else{
            model.setId(id);
            model.setNama(nama);
            model.setAlamat(alamat);
            model.setTelepon(telepon);
            model.setEmail(email);
            
            try{
                model.UpdatePelanggan();
                JOptionPane.showMessageDialog(view, "Pelanggan Berhasil Diubah");
                model.resetPelanggan();
            }catch(Throwable throwabel){
                JOptionPane.showMessageDialog(view,new Object[]{
                    (" Terjadi Error di database dengan pesan"), throwabel.getMessage()});
            }
        }
    }
    public void DeletePelanggan(PelangganView view){
         if(view.getTablePelanggan().getSelectedRowCount() == 0){
            JOptionPane.showMessageDialog(view, "Silahkan Seleksi data yang akan dihapus");
            return;
        }
         
        if(JOptionPane.showConfirmDialog(view, "Anda yakin akan Menghapus?")==JOptionPane.OK_OPTION){
            Integer id = Integer.parseInt(view.getTxtID().getText());
            model.setId(id);
            
            try{
                model.DeletePelanggan();
                JOptionPane.showMessageDialog(view, "Pelanggan Berhasil Di Hapus");
                model.resetPelanggan();
            }catch(Throwable throwabel){
                JOptionPane.showMessageDialog(view,new Object[]{
                    (" Terjadi Error di database dengan pesan"), throwabel.getMessage()});
            }
        }
    }

    
    
        
}
