package com.crossent.monitoring.portal.group.mon.test;

import com.crossent.monitoring.portal.common.test.AbstractMockTest;
import org.junit.Test;

public class MonGroupTest extends AbstractMockTest {

    @Test
    public void listMonGroup() throws Exception {
        String userId = "monuser1";
        logger.debug("Int userId :" +userId);
        super.get("/monitoring-groups/" + userId);
    }
}
