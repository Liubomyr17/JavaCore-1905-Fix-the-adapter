package com.company;

/*

1905 Fix the adapter
Adapt Customer and Contact to RowItem.
The adapter class is the DataAdapter.
Initialize countries before starting the program. Matching country code and name:
UA Ukraine
RU Russia
CA Canada

Requirements:
1. The Solution class must contain a public static field of countries of type Map.
2. In the static block of the Solution class, initialize the countries field with test data according to the task.
3. The Solution class must contain the RowItem interface.
4. The Solution class must contain the Contact interface.
5. The Solution class must contain the Customer interface.
6. The DataAdapter class must implement the RowItem interface.
7. The DataAdapter class must contain two private fields: customer of type Customer and contact Contact.
8. The DataAdapter class must contain a constructor with parameters (Customer customer, Contact contact), which initializes the contact and customer fields.
9. In the DataAdapter class, implement the RowItem interface methods using prompts in the form of comments in the interfaces.

*/

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Map;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;



public class Solution {

    public static Map<String,String> countries = new HashMap<String,String>();
    static {
        countries.put("UA", "Ukraine");
        countries.put("RU", "Russia");
        countries.put("CA", "Canada");
    }


    public static void main(String[] args) {

    }

    public static class DataAdapter implements RowItem {
        private Customer customer;
        private Contact contact;

        public DataAdapter(Customer customer, Contact contact) {
            this.contact = contact;
            this.customer = customer;
        }

        @Override
        public String getCountryCode() {
            String res = null;
            for(Map.Entry<String, String> pair : countries.entrySet()) {
                if(pair.getValue().equals(customer.getCountryName())) {
                    res = pair.getKey();
                    break;
                }
            }
            return res;
        }

        @Override
        public String getCompany() {
            return customer.getCompanyName();
        }

        @Override
        public String getContactFirstName() {
            return contact.getName().split(",")[1];
        }

        @Override
        public String getContactLastName() {
            return contact.getName().split(",")[0];
        }

        @Override
        public String getDialString() {
            String tel = "callto://" + contact.getPhoneNumber().replaceAll("[()-]","");
            return tel;
        }
    }

    public static interface RowItem {
        String getCountryCode();        //example UA
        String getCompany();            //example JavaRush Ltd.
        String getContactFirstName();   //example Ivan
        String getContactLastName();    //example Ivanov
        String getDialString();         //example callto://+380501234567
    }

    public static interface Customer {
        String getCompanyName();        //example JavaRush Ltd.
        String getCountryName();        //example Ukraine
    }

    public static interface Contact {
        String getName();               //example Ivanov, Ivan
        String getPhoneNumber();        //example +38(050)123-45-67

    }
}




