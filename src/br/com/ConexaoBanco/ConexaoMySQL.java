package br.com.ConexaoBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConexaoMySQL {
    public static String status = "Não conectou...";
    
    public ConexaoMySQL(){
        
    }   
    
    public static java.sql.Connection getConexaoMySQL(){
        Connection connection = null;
        try{
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);  
            String serverName = "localhost";
            String mydatabase = "poltroneando";
            String port = "3307";
            String url = "jdbc:mysql://" + serverName + ":" + port + "/" + mydatabase+"?useSSL=false";
            String username = "dbuser";
            String password = "killer.28";
            connection = DriverManager.getConnection(url, username, password);
            if (connection != null){
                status = ("STATUS--->Conectado com sucesso!");
            }else{
                status = ("STATUS--->Não foi possivel realizar a conexão");
            }
            return connection;
        }catch (ClassNotFoundException e){
            System.out.println("O driver não foi encontrado");
            return null;
        }catch (SQLException e){
            System.out.println("Não foi possivel conectar ao banco de dados.");
            return null;
        }
    }
    
    public static String statusConection(){
        return status;
    }
    
    public static boolean FecharConexao(){
        try{
            ConexaoMySQL.getConexaoMySQL().close();
            return true;
        } catch (SQLException e){
            return false;
        }
    }
    
    public static void Inserir_Link(int ano, String nome, String link){
        try{
            String query = "INSERT INTO links (ano,titulo,link) values (?, ?, ?)";
            PreparedStatement preparedstmt = ConexaoMySQL.getConexaoMySQL().prepareStatement(query);
            preparedstmt.setInt(1, ano);
            preparedstmt.setString(2, nome);
            preparedstmt.setString(3, link);
            preparedstmt.execute();
            ConexaoMySQL.FecharConexao();
            //System.out.println("1");
        } catch (Exception e){
            System.out.println("e - "+e);
            ConexaoMySQL.FecharConexao();
        }
    }
    public static void Inserir_Pagina(int ano, String link, int num_pag){
        try{
            String query = "INSERT INTO pagina (ano,link,num_pag) values (?, ?, ?)";
            PreparedStatement preparedstmt = ConexaoMySQL.getConexaoMySQL().prepareStatement(query);
            preparedstmt.setInt(1, ano);
            preparedstmt.setString(2, link);
            preparedstmt.setInt(3, num_pag);
            preparedstmt.execute();
            ConexaoMySQL.FecharConexao();
            //System.out.println("1");
        } catch (Exception e){
            System.out.println("e - "+e);
            ConexaoMySQL.FecharConexao();
        }
    }
    
    public static java.sql.Connection ReiniciarConexao() {
        FecharConexao();
        return ConexaoMySQL.getConexaoMySQL();
    }
}
