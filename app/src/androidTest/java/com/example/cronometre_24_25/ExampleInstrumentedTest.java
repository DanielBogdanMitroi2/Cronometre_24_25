package com.example.cronometre_24_25;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        assertEquals("com.example.cronometre_24_25", InstrumentationRegistry.getInstrumentation().getTargetContext().getPackageName());
    }
}
