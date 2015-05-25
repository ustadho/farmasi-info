/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shpl.farmasi.info.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author faheem
 */
@Repository
public class ResultSetToListMap {
    @Autowired
    private DataSource dataSource;
    
    public List<Map<String, Object>> cast(ResultSet rs) {
        List<Map<String, Object>> hasil=new ArrayList<Map<String, Object>>();
        try {
            ResultSetMetaData rm=rs.getMetaData();
            while (rs.next()) {
                Map<String, Object> map = new HashMap<String, Object>();

                for (int i = 1; i <= rm.getColumnCount(); i++) {
                    map.put(rm.getColumnName(i), rs.getObject(i));
//                    System.out.println("Nama Kolom : " + rm.getColumnName(i));
//                    System.out.println("Isi Kolom  : " + rs.getObject(i));
                }
                hasil.add(map);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ResultSetToListMap.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasil;
    }
    public Object cast(String sql) {
        List<Map<String, Object>> hasil=new ArrayList<Map<String, Object>>();
        try {
            System.out.println(sql);
            ResultSet rs=dataSource.getConnection().createStatement().executeQuery(sql);
            ResultSetMetaData rm=rs.getMetaData();
            while (rs.next()) {
                Map<String, Object> map = new HashMap<String, Object>();

                for (int i = 1; i <= rm.getColumnCount(); i++) {
                    map.put(rm.getColumnName(i), rs.getObject(i));
//                    System.out.println("Nama Kolom : " + rm.getColumnName(i));
//                    System.out.println("Isi Kolom  : " + rs.getObject(i));
                }
                hasil.add(map);
            }
        } catch (SQLException ex) {
            System.out.println("ERror : "+ex.getMessage());
            Logger.getLogger(ResultSetToListMap.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasil;
    }
}
