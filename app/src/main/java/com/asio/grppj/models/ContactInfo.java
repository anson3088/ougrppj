package com.asio.grppj.models;

import java.util.ArrayList;
import java.util.HashMap;

public class ContactInfo {
    public static String NAME = "name";
    public static String EMAIL = "email";
    public static String ADDRESS = "address";
    public static String MOBILE = "mobile";

    public static ArrayList<HashMap<String, String>> contactList = new ArrayList<>();

    // Creates and add contact to contact list
    public static void addContact(String name, String email, String address, String mobile) {
        // Create contact
        HashMap<String, String> contact = new HashMap<>();
        contact.put(NAME, name);
        contact.put(EMAIL, email);
        contact.put(ADDRESS, address);
        contact.put(MOBILE, mobile);

        // Add contact to contact list
        contactList.add(contact);
    }
}
