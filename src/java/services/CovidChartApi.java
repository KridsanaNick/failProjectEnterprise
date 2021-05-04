/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author user
 */
@Path("covidchart")
public class CovidChartApi {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CovidChartApi
     */
    public CovidChartApi() {
    }

    /**
     * Retrieves representation of an instance of services.CovidChartApi
     *
     * @return an instance of java.lang.String
     * @throws java.net.MalformedURLException
     */
    @GET
    @Produces("application/json")
    public String getJson() throws MalformedURLException, IOException {
        String api = "https://covid19.th-stat.com/api/open/today";
        URL url = new URL(api);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.connect();

        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        if (con.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + con.getResponseCode());
        }
        StringBuffer response;
        BufferedReader in = new BufferedReader(new InputStreamReader(
                (con.getInputStream())));

        String inputLine;
        response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
//        in.close();

        return response.toString();

    }

    /**
     * PUT method for updating or creating an instance of CovidChartApi
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
