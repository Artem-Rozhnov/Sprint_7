package org.example.courier;
import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator {
    public static Courier generic(){

        return new Courier("Nikita", "12345", "Joly");
    }
    public static Courier random(){
        return new Courier("Nikita" + RandomStringUtils.randomAlphanumeric(5,10),"12345", "Joly");
    }
}