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
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import br.com.ConexaoBanco.ConexaoMySQL;

/**
 *
 * @author Caio
 */
public class Extracao {
    
    Document doc;
    
    public Extracao(Document doc){
        this.doc = doc;
    }

    public void verificarInformacoes(int ano) {

        //pegando todos os elementos que contém a classe "itens_curso"
        Elements e1 = doc.getElementsByClass("nomedofilme");

        for (Element element : e1) {
            //dentro de um elemento que contém itens_curso, pegar os que contém as tags span
//            Elements elements2 = element.getElementsByTag("span");
//            Element element1 = elements2.get(0);

            //caso o elemento não contenha o strong pule para o próxima caso
            if((element.getElementsByTag("a")).isEmpty()){
                continue;
            }
            Elements elements3 = element.getElementsByTag("a");
            Element element2 = elements3.get(0);
            
            //System.out.println(element2.text());
            //essa parte comentada é caso seja necessário extrair algum atributo de uma tag
            //por exemplo uma tag a, desejar pegar o link.
            Attributes ats = element2.attributes();
            for (Attribute at : ats) {
                if ("href".equals(at.getKey())){
                //System.out.println(at.getValue());
                ConexaoMySQL.Inserir_Link(ano, element2.text(), at.getValue());
                //System.out.println(at.getKey());
            }
            }
        }
    }
    
    public int extraiQtdePaginas(){
        Elements e1 = doc.getElementsByClass("n");
//  
        int quantidade;
        if (e1.isEmpty() == false) {
            quantidade = Integer.parseInt(e1.last().text());            
        } else {
            quantidade = 0;
        }
        return quantidade;

//        for (Element element : e1) {
//            
//            if((element.getElementsByTag("span")).isEmpty()){
//                continue;
//            }
//            
//            Elements elements = element.getElementsByTag("span");
//            Element element1 = elements.get(0);
//            
//            Elements elements2 = element1.getElementsByTag("p");
//            
//            for (Element element21 : elements2) {
//                System.out.println(element21.text());
//            }
//        }
    }
}
