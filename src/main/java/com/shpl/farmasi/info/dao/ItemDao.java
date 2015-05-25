/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shpl.farmasi.info.dao;

import java.sql.Connection;
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
public class ItemDao {
    @Autowired
    DataSource dataSource;
    
    @Autowired
    ResultSetToListMap resultSetToListMap;
    
    private Connection conn;

    private Connection getConn(){
        try {
            conn= conn==null? dataSource.getConnection(): conn;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    public List<Map<String, Object>> getAllItemFormularium() {
        List<Map<String, Object>> hasil = new ArrayList<Map<String, Object>>();

        try {
            System.out.println("Koneksi: "
                    + getConn().getMetaData().getURL());
            String sQry = "select * from fn_phar_info_item_formularium() as(kode_barang varchar, nama_barang varchar, base_price_type text, \n" +
                            "jenis_barang varchar, group_name varchar, consignment boolean , bentuk_name varchar)";
            System.out.println(sQry);
            ResultSet rs = getConn().createStatement()
                    .executeQuery(sQry);
            ResultSetMetaData rm = rs.getMetaData();
            while (rs.next()) {
                Map<String, Object> map = new HashMap<String, Object>();

                for (int i = 1; i <= rm.getColumnCount(); i++) {
                    map.put(rm.getColumnName(i), rs.getObject(i));
                }
                hasil.add(map);
            }
            
            rs.close();
        } catch (SQLException se) {
        }
        return hasil;
    }
    public List<Map<String, Object>> getAllItemBelumMapping(String siteId) {
        List<Map<String, Object>> hasil = new ArrayList<Map<String, Object>>();

        try {
            String sQry = "select * from fn_phar_opname_belum_mapping_lokasi('"+siteId+"') as (kode_barang varchar, nama_barang varchar, kons text, group_name varchar, \n" +
                        "stock numeric, uom_kecil varchar)";
            System.out.println(sQry);
            ResultSet rs = getConn().createStatement()
                    .executeQuery(sQry);
            ResultSetMetaData rm = rs.getMetaData();
            while (rs.next()) {
                Map<String, Object> map = new HashMap<String, Object>();

                for (int i = 1; i <= rm.getColumnCount(); i++) {
                    map.put(rm.getColumnName(i), rs.getObject(i));
                }
                hasil.add(map);
            }
            
            rs.close();
        } catch (SQLException se) {
        }
        return hasil;
    }
    public Object getAllItemBelumOpname(String siteId) {
        String sQry = "select * from fn_phar_opname_item_loss('"+siteId+"') as (site_id varchar, kode_barang varchar, nama_barang varchar, \n" +
                    "saldo_site double precision, jumlah_real double precision, hpp double precision, no_opname varchar, locations varchar)";
            
        return resultSetToListMap.cast(sQry);
    }
    public Object getAllItemDoubleOpname() {
        String sQry = "select o.site_id, d.kode_barang, i.nama_barang, count(d.kode_barang) as jml, \n" +
                    "gabung(o.no_opname) as no_opname\n" +
                    "from phar_opname o\n" +
                    "inner join phar_opname_detail d on d.no_opname=o.no_opname\n" +
                    "inner join phar_item i on i.kode_barang=d.kode_barang\n" +
                    "where o.tanggal=current_date\n" +
                    "group by o.site_id, d.kode_barang, i.nama_barang\n" +
                    "having count(d.kode_barang)>1";
            
        return resultSetToListMap.cast(sQry);
    }

    public List<Map<String, Object>> getAllSites() {
        List<Map<String, Object>> hasil = new ArrayList<Map<String, Object>>();

        try {
            String sQry = "select site_id as \"siteId\", site_name as \"siteName\" from phar_site order by 1";
            System.out.println(sQry);
            ResultSet rs = getConn().createStatement()
                    .executeQuery(sQry);
            ResultSetMetaData rm = rs.getMetaData();
            while (rs.next()) {
                Map<String, Object> map = new HashMap<String, Object>();

                for (int i = 1; i <= rm.getColumnCount(); i++) {
                    map.put(rm.getColumnName(i), rs.getObject(i));
                }
                hasil.add(map);
            }
            
            rs.close();
        } catch (SQLException se) {
        }
        return hasil;
    }
    
    
}
