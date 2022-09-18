package com.hackerearth.hackerearth;

import com.google.gson.Gson;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

@SpringBootApplication
public class HackerearthApplication {

    public static void main(String[] args) throws IOException {
        //  SpringApplication.run(HackerearthApplication.class, args);
        HackerearthApplication h = new HackerearthApplication();
        List<Integer> value = h.getDetails(Arrays.asList("company.basename", "EQUALS", "e-support to middle retailers"));
        System.out.println(value);
    }

    public List<Integer> getDetails(List<String> details) throws IOException {
        List<Integer> l = new ArrayList<>();
        URL url = new URL("https://raw.githubusercontent.com/arcjsonapi/ApiSampleData/master/api/users");
        InputStreamReader reader = new InputStreamReader(url.openStream());
        User[] dto = new Gson().fromJson(reader, User[].class);
        Map<String, User> m = new HashMap<>();
        for (User u : dto) {
            m.put(u.username, u);
        }
        if (details.get(0).equals("username") && details.get(1).equals("EQUALS")) {
            if (m.containsKey(details.get(2))) {
                l.add(m.get(details.get(2)).id);
            }
        }

        if (details.get(0).contains("address.")) {
            if (details.get(0).contains("city")) {
                for (Map.Entry<String, User> e : m.entrySet()) {
                    if (e.getValue().address.city.equals(details.get(2))) {
                        l.add(e.getValue().id);
                    }
                }
            } else if (details.get(0).contains("street")) {
                for (Map.Entry<String, User> e : m.entrySet()) {
                    if (e.getValue().address.street.equals(details.get(2))) {
                        l.add(e.getValue().id);
                    }
                }
            } else if (details.get(0).contains("zipcode")) {
                for (Map.Entry<String, User> e : m.entrySet()) {
                    if (e.getValue().address.zipcode.equals(details.get(2))) {
                        l.add(e.getValue().id);
                    }
                }
            }

            if (m.containsKey(details.get(2))) {
                l.add(m.get(details.get(2)).id);
            }
        }

        if (details.get(0).contains("company.")) {
            if (details.get(0).contains("name")) {
                for (Map.Entry<String, User> e : m.entrySet()) {
                    if (e.getValue().company.name.equals(details.get(2))) {
                        l.add(e.getValue().id);
                    }
                }
            } else if (details.get(0).contains("basename")) {
                for (Map.Entry<String, User> e : m.entrySet()) {
                    if (e.getValue().company.basename.equals(details.get(2))) {
                        l.add(e.getValue().id);
                    }
                }
            }

            if (m.containsKey(details.get(2))) {
                l.add(m.get(details.get(2)).id);
            }
        }

        if (l.size() == 0) {
            l.add(-1);
        }

        return l;
    }

    private class User {
        int id;
        String name;
        String username;
        String email;
        Address address;
        String website;
        Company company;
    }

    private class Address {
        String street;
        String suite;
        String city;
        String zipcode;
        Geo geo;
    }

    private class Geo {
        String geo;
        String lat;
    }

    private class Company {
        String name;
        String basename;
    }
}
