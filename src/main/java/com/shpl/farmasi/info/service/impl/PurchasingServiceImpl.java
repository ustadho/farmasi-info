/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shpl.farmasi.info.service.impl;

import com.shpl.farmasi.info.dao.PODao;
import com.shpl.farmasi.info.service.PurchasingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author faheem
 */
@Service
public class PurchasingServiceImpl implements PurchasingService{
    @Autowired
    PODao pODao;
    
    @Override
    public Object getAllItemRevisi(String from, String to) {
        return pODao.getAllItemRevisi(from, to);
    }
    
}
