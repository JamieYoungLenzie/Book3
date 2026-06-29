package com.sas.memex.book3.test.service;

import com.sas.memex.book3.model.Subsector;
import com.sas.memex.book3.helper.Marshal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MarshalServiceTest {

    public MarshalServiceTest() {
    }

    @Test
    public void testUnmarshallA() throws Exception {
        Subsector result = Marshal.unmarshal("C:\\book3-app\\data\\Asgard.xml");
        assertEquals(3, result.getWorlds().size());
        assertEquals("Asgard", result.getName());
    }

    @Test
    public void testUnmarshallB() throws Exception {
        Subsector result = Marshal.unmarshal(Subsector.class, "C:\\book3-app\\data\\Asgard.xml");
        assertEquals(3, result.getWorlds().size());
        assertEquals("Asgard", result.getName());
    }
}
