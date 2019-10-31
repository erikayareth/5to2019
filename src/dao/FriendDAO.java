
package dao;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import pojo.Friend;

public class FriendDAO {
    
    public int insertar(Friend friend) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        int id = 0;
        try{
            con = Conexion.getConnection(); 
            st = con.prepareStatement("call insert_friend(?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, friend.getName());
            st.setString(2, friend.getPhoneNumber());
            st.setString(3, friend.getAddress());
            st.setDate(4, friend.getBirthday());
            st.setBinaryStream(5, new FileInputStream(new File(friend.getImage())),(int)
                    new File(friend.getImage()).length());
            id = st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        }catch(Exception e){
            System.out.println("Eror al insertar friend" + e);
        }finally{
            Conexion.close(con);
            Conexion.close(st);
        }
        return id;
    }
    
    /*public static void exportFriend(String exportFile) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        
        try{
            con = Conexion.getConnection();
            st = con.prepareStatement("SELECT * FROM friend INTO OUTFILE'"+ exportFile + "' FIELDS TERMINNATED BY ';' OPTIONALLY ENCLOSED BY '\\\"' LINES TERMINATED BY '\\n\\r'");
            sucess = st.execute();
        }
    }*/
    
    public DefaultTableModel cargarModelo() throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        DefaultTableModel dt = null;
        String encabezados[] = {"ID", "Name", "Phone"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL select_all_friends");
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[3];
                Friend pojo = inflaPOJO(rs);
                ob[0] =  pojo.getIdfriend();
                ob[1] = pojo;
                ob[2] = pojo.getPhoneNumber();
                dt.addRow(ob);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar la tabla amigo " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
    
    public DefaultComboBoxModel loadCombo() throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        DefaultComboBoxModel dt = null;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL select_all_friends");
            dt = new DefaultComboBoxModel();
            ResultSet rs = st.executeQuery();
            dt.addElement("Seleccione a su amigo");
            while (rs.next()) {
                Friend pojo = inflaPOJO(rs);
                dt.addElement(pojo);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar el modelo amigos " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
    
    
    public Friend selectFriend(int i) {
        Connection con = null;
        PreparedStatement st = null;
        Friend friend = new Friend();
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL select_a_friend(?)");
            st.setInt(1, i);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                friend = inflaPOJO(rs);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al consultar amigo " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return friend;
    }
    
    
    private static Friend inflaPOJO(ResultSet rs){
        
        Friend pojo = new Friend();
        try {
            pojo.setIdfriend(rs.getInt("idFriend"));
            pojo.setName(rs.getString("name"));
            pojo.setPhoneNumber(rs.getString("phoneNumber"));
            pojo.setAddress(rs.getString("address"));
            pojo.setBirthday(rs.getDate("birthday"));
            //Friend setImage = pojo.setImage(rs.getImage("image"));
        } catch (SQLException ex) {
            System.out.println("Error al inflar pojo alumno" + ex);
        }
        return pojo;
    }
    
    public static boolean delete_friend (int id){
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL delete_friend(?)");
            st.setInt(1, id);
            int num = st.executeUpdate();
            if (num == 0) {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar " + e);
            return false;
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return true;
    }
    
    
    
}
