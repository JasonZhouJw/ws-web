package com.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Set;
import java.util.TreeSet;

public class WsdlGetter {
    protected WsdlGetter() {

    }

    public static Set<String> getWsdl(String urlAddress) throws IOException {
        Set<String> wsdlSet = new TreeSet<String>();
        URL url = new URL(urlAddress);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        BufferedReader br = new BufferedReader(
            new InputStreamReader(urlConnection.getInputStream()));
        String line = br.readLine();
        String[] stringArray = line.split("<a href=\"");
        for (int i = 1; i < stringArray.length; ++i) {
            String wsdlUrl = stringArray[i].split("\">", 2)[0];
            if (wsdlUrl.endsWith(Constants.WSDL_SUFFIX)) {
                wsdlSet.add(wsdlUrl);
            }
        }
        return wsdlSet;
    }
}
