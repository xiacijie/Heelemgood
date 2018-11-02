package com.example.jerry.healemgood;

import android.support.test.runner.AndroidJUnit4;

import com.example.jerry.healemgood.model.photo.Photo;
import com.example.jerry.healemgood.model.record.CareProviderRecord;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * CareProviderRecord Test
 * 1. CareProviderRecordConstructorTest: The class constructors and getters and setters.
 * @author tw
 * @version 1.0.2
 */
@RunWith(AndroidJUnit4.class)
public class CareProviderRecordTest {
    @Test
    public void careProviderRecordConstructorTest() {
        int rId = 1;
        String title = "Test";

        CareProviderRecord record = new CareProviderRecord(rId, title, false);

        assertEquals(record.getTitle(), title);
        assertEquals(record.getrId(), rId);

        int rId2 = 2;
        String title2 = "Test2";
        record.setrId(rId2);
        record.setTitle(title2);
        assertEquals(record.getTitle(), title2);
        assertEquals(record.getrId(), rId2);

        String description = "Test care provider record";
        record.setDescription(description);
        assertEquals(record.getDescription(), description);

        Photo photo1 = new Photo("/tmp/1.png",200,320);
        Photo photo2 = new Photo("/tmp/2.png",400,520);

        record.addPhoto(photo1);
        record.addPhoto(photo2);

        ArrayList<Photo> photos = record.getPhotos();

        assertTrue(photos.contains(photo1));
        assertTrue(photos.contains(photo2));
        assertEquals(photo1, record.getPhotoById(0));
        assertEquals(photo2, record.getPhotoById(1));


        Date date = new Date();
        record.setCreatedDate(date);
        assertEquals(record.getCreatedDate(), date);

        assertFalse(record.isPatientRecord());
    }

}
