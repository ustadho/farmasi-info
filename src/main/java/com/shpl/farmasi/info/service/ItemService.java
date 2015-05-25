/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shpl.farmasi.info.service;

import java.util.List;
import java.util.Map;

/**
 *
 * @author faheem
 */
public interface ItemService {
    public List<Map<String, Object>> getAllItemFormularium();
    public List<Map<String, Object>> getAllItemBelumMapping(String siteId);
    public Object getAllItemBelumOpname(String siteId);
    public Object getAllItemDoubleInput();

    public List<Map<String, Object>> getAllSite();
}
