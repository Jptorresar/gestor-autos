package com.jtorres.prueba_autos;

public class BcryptGenerator {
    public static void main(String[] args) {
        String rawPassword = "1221";
        String hashed = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode(rawPassword);
        System.out.println(hashed);
    }
}