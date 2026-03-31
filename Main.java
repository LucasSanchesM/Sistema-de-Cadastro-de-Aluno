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
                    while(terminado == 0){
                        if(leitura == null){
                            //Quando leitura estiver vazia, ou seja quando ele tentou ler uma linha que nao existia (final do arquivo) ele sai do loop
                            break;
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
                                    contadorDeLinha = contadorDeLinha-4;
                                    buffReader.close();
                                    BufferedReader buffReader2 = new BufferedReader(new FileReader("/home/lucas/Arquivos/dados.txt"));
                                    leitura= "";
                                    //reinicio o buffer de leitur   a e deixo a leitura vazia
                                   
                                   //nessa variavel iremos  ver quantas infomrações (linhas)tem esse arquivo
                                   int tamanhoArray=0;
                                   while(true){
                                       leitura= buffReader2.readLine();
                                       if(leitura==null){
                                           break;
                                       }
                                       else{
                                           tamanhoArray++;
                                       }
                                   }//fecha loop que coleta quantas linhas tem o arquivo
                                   tamanhoArray = tamanhoArray - 5;
                                   //como será deletado um conjunto de informações, eu subtraio do tamanho do arquivo quantas linhas a informação excluida tem
                                   
                                   buffReader2.close();
                                   BufferedReader buffReader3 = new BufferedReader(new FileReader("/home/lucas/Arquivos/dados.txt"));
                                   leitura="";
                                   //novamente resenta buffer e leitura...
                                   
                                   //agora irei escrever todo o arquivo em um array, cada linha é uma posição nele, as linhas com as informações marcadas para exclusão seráo 
                                   //ignoradas durante a gravação
                                   String arquivosTemp[] = new String[tamanhoArray];
                                   int posicaoArray = 0;
                                   for(int i = 0; posicaoArray<tamanhoArray; i++){
                                       leitura = buffReader3.readLine();
                                       if(i==contadorDeLinha){
                                           buffReader3.readLine();
                                           buffReader3.readLine();
                                           buffReader3.readLine();
                                           buffReader3.readLine();
                                       }
                                       else{
                                           arquivosTemp[posicaoArray]=leitura;
                                           posicaoArray++;
                                       }
                                   }
                                   buffReader3.close();
                                   //agora o array está escrito com todo conteudo do arquivo, menos as linhas marcadas para exclusao
                                   
                                   //iremos sobreescreve-lo agora
                                   FileWriter arq = new FileWriter("/home/lucas/Arquivos/dados.txt");
                                   PrintWriter gravarArquivo = new PrintWriter(arq);
                                   for(String info : arquivosTemp){
                                       gravarArquivo.printf(info+"\n");
                                   }
                                   arq.close();
                                   terminado=1;
                                }//fechado a exclusao
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
// depois disso criar um array para colocar tudo o que está armazenado no arquivo, menos a linha marcada para exclusãoo, ou seja,
//toda vez que o programa for garvar uma informação no array ele irá verificar se aquele numero nao é o mesma da linha marcada para exclusao (veja na linha 112), caso seja o programa irá pular
// aquele conjunto de informações, detalhe: nesse processo haverá um contador (denominado i) para o arquivo e um contador (denominado posicaoArray) para marcar o array, isto é necessario visto
// o array no final vai sempre estar em um numero de posição menor que o numero de linhas.
//Depois de gravar toda informação no Array eu apenas sobreescrevo o arquivo original com a informação contida no array (que nao possui os dados que deseja excluir).]
//No meu sistema toda informação no arquivo txt, possui 5 linhas: 1 para nome, 1 para curso, 1 para serie, 1 para turma e 1 para um separador(*).
//Isso significa que na verdade o programa pulará um total de 5 linha quando encontrar a informação a ser deletada. Isso é uma regra essencial, pois me permite armazenar
//somente a linha inicial de exclusao e pular a mesma + 4 linhas seguintes quando necessario
      
        

