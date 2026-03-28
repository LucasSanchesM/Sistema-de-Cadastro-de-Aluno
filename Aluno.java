/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import java.io.FileWriter;
import java .io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;


public class Aluno {
    private String nome;
    private String curso;
    private String serie;
    private String turma;
    
    //construtor com valores necessarios para instanciar
    public Aluno(){
        this.nome = "";
        this.curso = "";
        this.serie = "";
        this.turma = "";
    }
    
    //getters para obtenção de valores
    public String getNome(){
        return this.nome;
    }
    public String getCurso(){
        return this.curso;
    }
    public String getSerie(){
        return this.serie;
    }
    public String getTurma(){
        return this.turma;
    }
    
    //setters para definir atributos, eles seráo usados apenas para a persistencia de arquivos deste metodo, por isso estao privados
    public void setNome(String entraNome){
        this.nome = entraNome;
    }
    public void setCurso(String entraCurso){
        this.curso= entraCurso;
    }
    public void setSerie(String entraSerie){
        this.serie = entraSerie;
    }
    public void setTurma(String entraTurma){
        this.turma = entraTurma;
    }
    
    public void Cadastrar(){
        Scanner s = new Scanner(System.in);
        
    }
    
    //metodo para gravar cadastro
    public void gravarCadastro() throws IOException{
            //Fiquei um bom tempoo nesta, pensando que o java por padrao sempre iria sobreescrever o que estava presente no arquivo,
            //porem descobri que precisava apenas colocar um true na frente, desta forma ele apenas adiciona conteudo
            FileWriter arq = new FileWriter("/home/lucas/Arquivos/dados.txt", true);
            PrintWriter gravarArq = new PrintWriter(arq);
            gravarArq.printf(nome +"\n" +curso + "\n" + serie + "\n" + turma + "\n*\n");
            arq.close();
        }
    
    
}
