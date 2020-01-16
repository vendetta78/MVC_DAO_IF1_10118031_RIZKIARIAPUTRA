/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latihanmvcdao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import latihanmvcdao.entity.pelanggan;
import latihanmvcdao.error.PelangganException;
import latihanmvcdao.service.PelangganDAO;

/**
 * *Nama :Rizki Aria Putra
 * NIM : 10118031
 * Kelas : IF-1 2018
 *
 * @author Vedentta78
 */
public class pelangganDAOIMPL implements PelangganDAO{
    
    private Connection connection;
    
    private final String InsertPelanggan = "INSERT INTO  pelanggan (NAMA,ALAMAT,TELEPON,EMAIL) VALUES(?,?,?,?)";
    private final String UpdatePelanggan = "UPDATE pelanggan SET NAMA=?, ALAMAT=?, TELEPON=?, EMAIL=? WHERE ID=?";
    private final String deletePelanggan = "DELETE FROM pelanggan WHERE ID=?";    
    private final String getById = "SELECT * FROM pelanggan WHERE ID=?";   
    private final String getByEmail = "SELECT * FROM pelanggan WHERE EMAIL=?";    
    private final String selectAll = "SELECT * FROM pelanggan";
    
    public pelangganDAOIMPL(Connection connection) {
        this.connection = connection;
    }
    
    

    @Override
    public void insertPelanggan(pelanggan pelanggan) throws PelangganException {
        PreparedStatement statement = null;
        try{
            
            connection.setAutoCommit(false);
            
            statement = connection.prepareStatement(InsertPelanggan, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, pelanggan.getNama());
            statement.setString(2, pelanggan.getAlamat());
            statement.setString(3, pelanggan.getTelepon());
            statement.setString(4, pelanggan.getEmail());
            statement.executeUpdate();
            
            ResultSet result = statement.getGeneratedKeys();
            if(result.next()){
                pelanggan.setId(result.getInt(1));
            }
            connection.commit();
            
           
        }catch (SQLException e){
            try{
                connection.rollback();
            }catch(SQLException f){            
                throw new PelangganException(f.getMessage());
                
            }
        } finally{
            try{
                connection.setAutoCommit(true);
            }catch(Exception e){
            }
            
            if(statement!=null){
                 try{
                statement.close();
            }catch(SQLException e){
              }    
           }
           
            
        }
    }

    @Override
    public void updatePelanggan(pelanggan pelanggan) throws PelangganException {
          PreparedStatement statement = null;
          
           try{
            connection.setAutoCommit(false);   
               
            statement = connection.prepareStatement(UpdatePelanggan);
            statement.setString(1, pelanggan.getNama());
            statement.setString(2, pelanggan.getAlamat());
            statement.setString(3, pelanggan.getTelepon());
            statement.setString(4, pelanggan.getEmail());
            statement.setInt(5, pelanggan.getId());
            statement.executeUpdate();
            connection.commit();
        }catch (SQLException e){
            try{
                connection.rollback();
            }catch(SQLException f){            
                throw new PelangganException(f.getMessage());
                
            }
        } finally{
            try{
                connection.setAutoCommit(true);
            }catch(Exception e){
            }
            
            if(statement!=null){
                 try{
                statement.close();
            }catch(SQLException e){
              }    
           }
           
            
        }
           
          
    }

    @Override
    public void deletePelanggan(Integer id) throws PelangganException {
          PreparedStatement statement = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(deletePelanggan);
            statement.setInt(1, id);
            statement.executeUpdate();
            connection.commit();
        }catch (SQLException e){
            try{
                connection.rollback();
            }catch(SQLException f){            
                throw new PelangganException(f.getMessage());
                
            }
        } finally{
            try{
                connection.setAutoCommit(true);
            }catch(Exception e){
            }
            
            if(statement!=null){
                 try{
                statement.close();
            }catch(SQLException e){
              }    
           }
           
            
        }
    }

    @Override
    public pelanggan getPelanggan(Integer id) throws PelangganException {
        PreparedStatement statement = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getById);
            statement.setInt(1, id);
            
            ResultSet result = statement.executeQuery();
            pelanggan pelanggan = null;
           
            
            if(result.next()){
                pelanggan = new pelanggan();
                pelanggan.setId(result.getInt("ID"));
                pelanggan.setNama(result.getString("NAMA"));
                pelanggan.setAlamat(result.getString("ALAMAT"));
                pelanggan.setTelepon(result.getString("TELEPON"));
                pelanggan.setEmail(result.getString("EMAIL"));  
            }else{
                throw new PelangganException("Pelanggan dengan id " + id+ " tidak ditemukan");
            }
          connection.commit();  
          return pelanggan;   
        }catch (SQLException e){
            try{
                connection.rollback();
            }catch(SQLException f){            
                throw new PelangganException(f.getMessage());
                
            }
        } finally{
            try{
                connection.setAutoCommit(true);
            }catch(Exception e){
            }
            
            if(statement!=null){
                 try{
                statement.close();
            }catch(SQLException e){
              }    
           }
           
            
        }
        return null;
    }

    @Override
    public pelanggan getPelanggan(String email) throws PelangganException {
        PreparedStatement statement = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getByEmail);
            statement.setString(1, email);          
            ResultSet result = statement.executeQuery();
            pelanggan pelanggan = null;
            if(result.next()){
                pelanggan = new pelanggan();
                pelanggan.setId(result.getInt("ID"));
                pelanggan.setNama(result.getString("NAMA"));
                pelanggan.setAlamat(result.getString("ALAMAT"));
                pelanggan.setTelepon(result.getString("TELEPON"));
                pelanggan.setEmail(result.getString("EMAIL"));  
            }else{
                throw new PelangganException("Pelanggan dengan email " + email+ " tidak ditemukan");
            }
          connection.commit(); 
          return pelanggan;   
        }catch (SQLException e){
            try{
                connection.rollback();
            }catch(SQLException f){            
                throw new PelangganException(f.getMessage());
                
            }
        } finally{
            try{
                connection.setAutoCommit(true);
            }catch(Exception e){
            }
            
            if(statement!=null){
                 try{
                statement.close();
            }catch(SQLException e){
              }    
           }
           
            
        }
        return null;
    }

    @Override
    public List<pelanggan> selectAllPelanggan() throws PelangganException {
          Statement statement = null;
          List<pelanggan> list = new ArrayList<pelanggan>();
          
          try{
           connection.setAutoCommit(false);
            statement = connection.createStatement();
            
            ResultSet result = statement.executeQuery(selectAll);
            pelanggan pelanggan = null;
            
            while(result.next()){
                pelanggan = new pelanggan();
                pelanggan.setId(result.getInt("ID"));
                pelanggan.setNama(result.getString("NAMA"));
                pelanggan.setAlamat(result.getString("ALAMAT"));
                pelanggan.setTelepon(result.getString("TELEPON"));
                pelanggan.setEmail(result.getString("EMAIL"));  
                list.add(pelanggan);
            }
          connection.commit();  
          return list;   
        }catch (SQLException e){
            try{
                connection.rollback();
            }catch(SQLException f){            
                throw new PelangganException(f.getMessage());
                
            }
        } finally{
            try{
                connection.setAutoCommit(true);
            }catch(Exception e){
            }
            
            if(statement!=null){
                 try{
                statement.close();
            }catch(SQLException e){
              }    
           }
           
            
        }
        return null;
    }
    
}
