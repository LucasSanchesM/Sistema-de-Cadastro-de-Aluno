/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;
import java .io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in); 
        int escolha= 0;
        while(true){
            System.out.println("**Sistema de Cadastro de Alunos**\nInforme a opcao desejada");
            System.out.println("1-Cadastrar\n2-Remover\n3-Listar Alunos\n");
            escolha = s.nextInt();
            s.nextLine();
            //nextLine para limpar buffer
            switch (escolha){
                case 1:
                    Aluno novoAluno = new Aluno();
                    System.out.println("Digite o nome do aluno: ");
                    novoAluno.setNome(s.nextLine());
                    System.out.println("Digite o curso do aluno: ");
                    novoAluno.setCurso(s.nextLine());
                    System.out.println("DIgite a serie do aluno: ");
                    novoAluno.setSerie(s.nextLine());
                    System.out.println("Digite a turma do aluno: ");
                    novoAluno.setTurma(s.nextLine());
                    System.out.printf("Nome: %s\nCurso: %s\nSerie: %s\nTurma: %s\n", novoAluno.getNome(),novoAluno.getCurso(), novoAluno.getSerie(), novoAluno.getTurma());

                    System.out.println("Digite 1 caso queira salvar essas informacoes, caso contrario digite 2");
                    if(s.nextInt()==1){
                            novoAluno.gravarCadastro();
                    }
                    break;
                case 2:
                    int terminado = 0;
                    //antes de tudo, para entender esse codigo pode ser necessario visitar as ultimas linhas desse programa, lá explicará o que foi feito aqui
                    System.out.println("Informe o nome cadastrado do aluno a ser excluido:");
                    String excluirAluno = s.nextLine();
                    BufferedReader buffReader = new BufferedReader(new FileReader("/home/lucas/Arquivos/dados.txt"));
                    //ja inicio o loop com a leitura (variavel que armazena o conteudo das linhas) com valor preenchido
                    String leitura = "";
                    int contadorDeLinha= -1;
                    //este contador irá dizer qual a primeira linha do conteudo a ser excluido, ele inicia em -1 pois a primeira interação nunca vai ser verdadeira, devido a leitura ser inicialmente "ada", 
                    //ele contará a primeira linha como 0 apos o primeiro loop terminar, sincronizando corretamente
                    while(terminado == 0){
                        if(leitura == null){
                            //Quando leitura estiver vazia, ou seja quando ele tentou ler uma linha que nao existia (final do arquivo) ele sai do loop
                            break;
                          //vale lembrar que leitura== null e leitura == "" sao diferentes
                        }
                        else if(leitura.equals(excluirAluno)){
                            //caso tenha dois alunos com o mesmo nome, ele irá verificar primeiro com o usuario se o aluno a ser excluido é o que possui as seguintes informações...
                            //vale lembrar que no momento que a pergunta de exclusao é feita o contadorDeLinha já está com a linha inicial (a do nome), armazenada
                            System.out.println("Este eh o aluno que deseja excluir?");
                            System.out.println("Nome: "+leitura);
                            //atualiza contador de linha e leitura para exibir todos os dados, pois caso nao seja o aluno certo, o programa 
                            //consegue seguir sem o problema da leitura e contador não estarem sincronizados. ELES NAO PODEM ESTAR DESSINCRONIZADOS SE O ALUNO FOR OUTRO
                            contadorDeLinha++;                            
                            leitura= buffReader.readLine();
                            System.out.println("Curso: "+leitura);
                            contadorDeLinha++;
                            leitura= buffReader.readLine();
                            System.out.println("Serie: " + leitura);
                            contadorDeLinha++;
                            leitura = buffReader.readLine();
                            System.out.println("Turma: " + leitura);
                            contadorDeLinha++;
                            leitura = buffReader.readLine();   
                            System.out.println("Digite 1 para sim e 2 para nao");
                            int escolhaExclusao = 0;
                            //Armazenará a confirmação se é ou não o aluno correto;
                            while(escolhaExclusao>2 || escolhaExclusao<1){
                                escolhaExclusao = s.nextInt();
                                //Coleta de escolha
                                if(escolhaExclusao==1){
                                    //Se for o aluno correto irá iniciar a exclusão
                                    contadorDeLinha= contadorDeLinha-4;
                                    //serve para guardar a linha inicial já que antes disso ele estava com o numero de linha correspondente ao final dos dados (veja o final do 
                                    //programa caso queira entender melhor o porquê do -4);
                                    FileWriter arqtemp = new FileWriter("/home/lucas/Arquivos/dadostemp.txt", true);
                                    PrintWriter gravarArqTemp = new PrintWriter(arqtemp);
                                    //Para escrever o arquivo temporario
                                    buffReader.close();
                                    BufferedReader buffReader2 = new BufferedReader(new FileReader("/home/lucas/Arquivos/dados.txt"));
                                    //reseta o buffer de leitura do arquivo original
                                   leitura = "";
                                  
                                    while(true){
                                        if (contadorDeLinha==0){
                                            for(int i = 0; i < 5; i++){
                                                buffReader2.readLine();
                                            }   
                                        }
                                        if(leitura== null){
                                            terminado = 1 ;
                                            arqtemp.close();
                                            break;
                                        }
                                        else if(!leitura.equals("")){
                                            gravarArqTemp.printf(leitura+"\n");
                                        }
                                        leitura = buffReader2.readLine();
                                        contadorDeLinha--;
                                    }
                                    
                            }
                                else if(escolhaExclusao == 2){
                                    //continua procura
                                    break;
                                }
                                else{
                                    System.out.println("Entrada invalida, escolha 1 para apagar ou 2 para acessar outro aluno: ");
                                }       
                            }//fecha while de escolha
                            
                        }
                        else{
                            contadorDeLinha++;
                            leitura = buffReader.readLine();
                        }
                    }
                    break;//termina case 2
                case 3:
                    BufferedReader buffReadered = new BufferedReader(new FileReader("/home/lucas/Arquivos/dados.txt"));
                    String linha = "";
                    while(true){
                         if(linha == null){
                            break;
                         }
                    else{
                        System.out.println(linha);  
                    }
                    linha = buffReadered.readLine();
                    }
                    break;
                default:
                    System.out.println("Erro");
                    break;
            }//fecha switch
                
        }//fecha while

    }//fecha main
    
 }//Fecha classe

 //Antes de tudo vale explicar o seguinte: aparentemente em java nao existe uma função nativa para apenas deletar uma linha
 //o que isso significa? Que o jeito facil nao existe... O metodo para passar por esse obstaculo é armazenar a linha a ser excluida em uma varivael,
 // depois disso criar um outro arquivo temporario para colocar tudo o que está armazenado no original, menos a linha marcada para exclusãoo, ou seja,
 //a cada nova linha copiada para o arquivo temporario o contador para chegar a linha de exclusao diminui em 1, quando ele chegar a 0 o programa pula
 //a informação, ignorando aquele dado. No meu sistema toda informação no arquivo txt, possui 5 linhas: 1 para nome, 1 para curso, 1 para serie, 1 para turma e 1 para um separador(*).
 //Isso significa que na verdade o programa pulara um total de 5 linha quando encontrar a informação a ser deletada. Isso é uma regra essencial, pois me permite armazenar
  //somente a linha inicial de exclusao.
      
        

