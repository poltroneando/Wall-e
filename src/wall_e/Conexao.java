/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wall_e;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Caio
 */
public class Conexao {
    
    public static Document conectar(String url) throws Exception{
    
    HttpURLConnection con = (HttpURLConnection) new URL(
                url)
                .openConnection();

        con.setRequestProperty("Request-Method", "GET");
        con.connect();

        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

        StringBuilder newData = new StringBuilder();
        String linha = "";
        while (null != ((linha = br.readLine()))) {
            newData.append(linha);
        }
        br.close();
        //System.out.println(newData.toString());
        String texto = newData.toString();

//        texto.indexOf(texto);
        
        return Jsoup.parse(texto);
    }    
}
