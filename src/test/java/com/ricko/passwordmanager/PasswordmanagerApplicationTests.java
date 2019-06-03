package com.ricko.passwordmanager;

import com.ricko.passwordmanager.Service.EncryptionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

//import static org.springframework.test.util.AssertionErrors.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PasswordmanagerApplicationTests {

    @Test
    public void contextLoads() {
    }


    @Autowired
    EncryptionService encryptionService;

    @Test
    public void isDecryptionWorkingWhenGivenEncryptedString(){
        String str=encryptionService.encode("test");
        String str2=encryptionService.decode(str);

        assertEquals(str,str2);
    }

}
