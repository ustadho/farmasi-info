/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shpl.farmasi.info.service.impl;

import com.shpl.farmasi.info.dao.ItemDao;
import com.shpl.farmasi.info.service.ItemService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author faheem
 */
@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    ItemDao itemDao;
    
    @Override
    public List<Map<String, Object>> getAllItemFormularium() {
        return itemDao.getAllItemFormularium();
    }

    @Override
    public List<Map<String, Object>> getAllItemBelumMapping(String siteId) {
        return itemDao.getAllItemBelumMapping(siteId);
    }

    @Override
    public List<Map<String, Object>> getAllSite() {
        return itemDao.getAllSites();
    }

    @Override
    public Object getAllItemBelumOpname(String siteId) {
        return itemDao.getAllItemBelumOpname(siteId);
    }

    @Override
    public Object getAllItemDoubleInput() {
        return itemDao.getAllItemDoubleOpname();
    }
    
}
