package com.dehssisfs2.LOTO.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

@Service
public class IpService {

    public String getIpAddress()
    {
        URL myIP;
        try {
            myIP = new URL("http://checkip.amazonaws.com/");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(myIP.openStream())
            );
            return in.readLine();
        } catch (Exception e)
        {
            try
            {
                myIP = new URL("http://myip.dnsomatic.com/");

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(myIP.openStream())
                );
                return in.readLine();
            } catch (Exception e1)
            {
                try {
                    myIP = new URL("http://icanhazip.com/");

                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(myIP.openStream())
                    );
                    return in.readLine();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }

        return null;
    }

//    https://ru.stackoverflow.com/questions/579709/%D0%9E%D0%B1%D1%8B%D1%87%D0%BD%D1%8B%D0%B9-get-%D0%B7%D0%B0%D0%BF%D1%80%D0%BE%D1%81-%D0%BD%D0%B0-java

}
