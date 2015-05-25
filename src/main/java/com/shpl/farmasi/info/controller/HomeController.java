/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shpl.farmasi.info.controller;

import com.shpl.farmasi.info.service.ItemService;
import com.shpl.farmasi.info.service.PurchasingService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author faheem
 */
@Controller
public class HomeController {
    @Autowired
    ItemService itemService;
    
    @Autowired
    PurchasingService purchasingService;
    
    @RequestMapping("/site/all")
    @ResponseBody
    private List<Map<String, Object>> showAllSites(){
        return itemService.getAllSite();
    }
    @RequestMapping("/item/formularium")
    @ResponseBody
    private List<Map<String, Object>> showItemFormularium(){
        return itemService.getAllItemFormularium();
    }
    
    @RequestMapping("/item/belum-mapping/{siteId}")
    @ResponseBody
    private List<Map<String, Object>> showItemBelumMapping(@PathVariable String siteId){
        return itemService.getAllItemBelumMapping(siteId);
    }
    @RequestMapping("/item/belum-opname/{siteId}")
    @ResponseBody
    private Object showItemBelumOpname(@PathVariable String siteId){
        return itemService.getAllItemBelumOpname(siteId);
    }
    @RequestMapping("/item/double-opname")
    @ResponseBody
    private Object showItemDoubleOpname(){
        return itemService.getAllItemDoubleInput();
    }
    
    @RequestMapping("/po/item-revisi/{from}/{to}")
    @ResponseBody
    private Object showPoItemRevisi(@PathVariable String from, @PathVariable String to){
        return purchasingService.getAllItemRevisi(from, to);
    }
    
    @RequestMapping(value="/item/belum-mapping-report*", method = RequestMethod.GET)
    public ModelMap laporanKehadiran(HttpServletRequest request) {
        ModelMap mm = new ModelMap();

        String uri = request.getRequestURI();
        String format = uri.substring(uri.lastIndexOf(".") + 1);
        String siteId = request.getParameter("siteId");
        
        mm.addAttribute("siteId", siteId);
        mm.addAttribute("format", format);
        mm.addAttribute("items", itemService.getAllItemBelumMapping(siteId));

        return mm;
    }
}
