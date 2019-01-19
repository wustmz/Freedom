package org.mz;

import org.decampo.xirr.Transaction;
import org.decampo.xirr.Xirr;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {

    @Test
    public void test() {
        double xirr = new Xirr(Arrays.asList(
                new Transaction(-300, "2018-01-15"),
                new Transaction(600, "2018-02-23")))
                .xirr();
        System.out.println(xirr);
    }

}

