package com.sas.memex.book3.test.service;

import com.sas.memex.book3.model.SubsectorList;
import com.sas.memex.book3.model.SubsectorListItem;
import com.sas.memex.book3.service.ListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ListServiceTest {

    public ListServiceTest() {    }

    @Autowired
    ListService listService;
        
    @Test    
    public void listTest() {
        SubsectorList list = listService.getList();
        for (SubsectorListItem item : list.getItems()) {
            System.out.println(item.getName());
        }
    }
}
