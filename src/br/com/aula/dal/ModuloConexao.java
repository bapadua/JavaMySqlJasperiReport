/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aula.dal;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author SolucaoLogica
 */
public class ModuloConexao {
    
    public static Connection conector(){
        java.sql.Connection conexao = null;
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/nomedoseubanco?useSSL=false";
        String usr = "seu usuario";
        String pwd = "sua senha";
        
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, usr, pwd);
            return conexao;
        } catch (Exception e) {
            return null;
        }
        
    }
    
}
