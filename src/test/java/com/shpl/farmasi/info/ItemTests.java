/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shpl.farmasi.info;

import com.shpl.farmasi.info.service.ItemService;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author faheem
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:com/shpl/**/applicationContext.xml")
public class ItemTests {
    @Autowired
    private ItemService service;
    
    @Test
    public void cariSemua(){
        List<Map<String, Object>> hasilQuery = service.getAllItemFormularium();
        System.out.println("Jumlah Record : "+hasilQuery.size());
        for (int i=0; i<hasilQuery.size(); i++) {
            System.out.println("ID : "+hasilQuery.get(i));
        }
    }
}
