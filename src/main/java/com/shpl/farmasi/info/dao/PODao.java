/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shpl.farmasi.info.dao;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author faheem
 */
@Repository
public class PODao {
    @Autowired
    DataSource dataSource;
    
    @Autowired
    ResultSetToListMap resultSetToListMap;
    
    public Object getAllItemRevisi(String from, String to) {
        String sQry = "select * from fn_phar_po_rpt_item_revisi('"+from+"', '"+to+"') as (no_po varchar, kode_supplier varchar, nama_supplier varchar, \n" +
        "tanggal timestamp without time zone, kode_barang varchar, nama_barang varchar, jumlah numeric, harga numeric, discount double precision, \n" +
        "ppn real, jumlah_awal numeric, harga_awal numeric, discount_awal double precision, ppn_awal real, tgl_buat_po text, \n" +
        "tgl_upd_po text, no_pr varchar)";
            
        return resultSetToListMap.cast(sQry);
    }
}
