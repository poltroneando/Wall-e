/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wall_e;

import org.jsoup.nodes.Document;
import br.com.ConexaoBanco.ConexaoMySQL;
/**
 *
 * @author Caio
 */
public class Wall_e {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            int qpag;
            String url;
            Document doc;
            Extracao ext;
            for (int i = 2011; i < 2018; i++) {
                if (i != 2011){
                url = "http://interfilmes.com/listaporano_"+ i +"_1_td.html";
                doc = Conexao.conectar(url);
                ConexaoMySQL.Inserir_Pagina(i, url, 1);
    //            System.out.println(doc.html());

                ext = new Extracao(doc);
                System.out.println("Ano: "+i);
                qpag = ext.extraiQtdePaginas();
                ext.verificarInformacoes(i);
                } else {
                    qpag = 106;
                }
                for (int j = 2; j <= qpag; j++) {
                    url = "http://interfilmes.com/listaporano_"+ i +"_"+ j +"_td.html";
                    doc = Conexao.conectar(url);
                    ext = new Extracao(doc);
                    ConexaoMySQL.Inserir_Pagina(i, url, j);
                    ext.verificarInformacoes(i);  
                    if (j == qpag){
                        int aux = ext.extraiQtdePaginas();
                        if (aux != qpag) {
                            qpag = aux;
                        }
                    }
                }
                //ext.verificarInformacoes();
            }
            //ConexaoMySQL.getConexaoMySQL();
            //System.out.println(ConexaoMySQL.statusConection());
            //ConexaoMySQL.FecharConexao();
            //ext.extraiSobreCurso();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
