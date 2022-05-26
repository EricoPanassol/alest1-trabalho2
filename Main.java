
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

import src.ArquivoTexto;
import src.LinhaTexto;
import src.LinkedListOfString;
import src.ListaOrdenadaDePalavras;

/**
 * Classe que inicializa a execução da aplicacao.
 * 
 * @author Isabel H. Manssour
 */
public class Main {
    public static void main(String[] args) {

        int nLinha = 0;
        int nPagina = 0;
        int totalLinhas = 0;

        double totalPalavras = 0;
        double totalStopWords = 0;
        String l;

        ArquivoTexto stopWords = new ArquivoTexto(); // objeto que gerencia o arquivo de stopwords
        ArquivoTexto arquivo = new ArquivoTexto(); // objeto que gerencia o arquivo a ser lido
        LinhaTexto linha = new LinhaTexto(); // objeto que gerencia uma linha
        ListaOrdenadaDePalavras listaPalavras = new ListaOrdenadaDePalavras();
        LinkedListOfString stopWordsList = new LinkedListOfString();

        // leitura do arquivo stopwors.txt
        stopWords.open("arquivos/stopwords.txt");
        do {
            // verifica se tem próxima linha
            l = stopWords.getNextLine();
            if (l == null) {
                break;
            }
            linha.setLine(l);

            do {
                // verifica se tem próxima palavra
                String word = linha.getNextWord();
                if (word == null) {
                    break;
                }
                stopWordsList.add(word);
            } while (true);
        } while (true);
        stopWords.close();

        System.out.println("Carregando arquivo \'java.txt\'...");
        // leitura do arquivo java.txt
        arquivo.open("arquivos/java.txt");
        do // laco que passa em cada linha do arquivo
        {
            l = arquivo.getNextLine();
            if (l == null) // acabou o arquivo?
                break;
            nLinha++; // conta mais uma linha lida do arquivo
            totalLinhas++;
            if (nLinha == 40) // chegou ao fim da pagina?
            {
                nLinha = 0;
                nPagina++;
            }

            linha.setLine(l); // define o texto da linha
            do // laco que passa em cada palavra de uma linha
            {
                String palavra = linha.getNextWord(); // obtem a proxima palavra da linha
                if (palavra == null)// acabou a linha
                {
                    break;
                }

                palavra = palavra.toLowerCase();

                if (!palavra.equals("")) {
                    if (!stopWordsList.contains(palavra)) {

                        if (listaPalavras.contains(palavra)) {
                            listaPalavras.addPagina(palavra, nPagina + 1);
                        } else {
                            listaPalavras.add(palavra);
                            listaPalavras.addPagina(palavra, nPagina + 1);
                        }
                    } else {
                        totalStopWords += 1;
                    }
                    totalPalavras += 1;
                }

            } while (true);

        } while (true);

        double percentualStopWords = (totalStopWords * 100) / totalPalavras;

        // metodo para pagina com mais palavras indexadas
        // Página com mais palavras indexadas = 1
        System.out.println("Gerando índice remissivo para " + totalLinhas + " linhas de texto...");
        System.out.println("Ignorando " + stopWordsList.size() + " stopWords.");
        System.out.println("Índice remissivo gerado, contendo " + listaPalavras.size() + " palavras.");

        System.out.println("\n=== Índice Remissivo ===");

        try {
            BufferedWriter saida = new BufferedWriter(new FileWriter("arquivos/saida.txt"));

            saida.write(totalLinhas + " linhas lidas. \n");
            saida.write("Gerando índice remissivo para " + totalLinhas + " linhas de texto... \n");
            saida.write("Linhas por página: 40 \n");
            saida.write("Tamanho Minimo das Palavras Indexadas: 1 \n");
            saida.write("Ignorando " + stopWordsList.size() + " stopWords. \n");
            saida.write("Índice remissivo gerado, contendo " + listaPalavras.size() + " palavras. \n");
            saida.write("\n=== Índice Remissivo ===\n\n");

            saida.write(listaPalavras.toString());
            saida.close();
            System.out.println("Verifique a saída no caminho \'arquivos/saida.txt\'\n");

        } catch (Exception e) {
            System.out.println("Não foi possível salvar arquivo!\n");
        }


        arquivo.close();

        Scanner sc = new Scanner(System.in);
        int opUser = -1;

        try{
            while (opUser != 5) {
                System.out.println("\nO que você deseja fazer?");
                System.out.println("1 - Exibir todo índice remissivo (ordem alfabética)");
                System.out.println("2 - Exibir percentual de stopwords do texto (quanto % do texto é formado por stopwords)");
                System.out.println("3 - Encontrar a palavra mais frequente (maior número de ocorrências)");
                System.out.println("4 - Pesquisar palavras (lista o número das páginas onde ela se encontra)");
                System.out.println("5 - Encerrar o programa");
    
                opUser = sc.nextInt();
                switch (opUser) {
                    case 1:
                        System.out.println(listaPalavras.toString());
                        break;
                    case 2:
                        System.out.printf("Total de palavras no texto: %.0f\n", totalPalavras);
                        System.out.printf("Total de stopwords no texto: %.0f\n", totalStopWords);
                        System.out.printf("Percentual de stopwords: %.2f%%\n", percentualStopWords);
                        break;
                    case 3:
                        System.out.println("Palavra(s) mais frequente(s): ");
                        ListaOrdenadaDePalavras maisFrequentes = listaPalavras.palavraMaisFrequente();
                        for (int i = 0; i < maisFrequentes.size(); i++) {
                            System.out.println(maisFrequentes.get(i));
                        }
                        break;
                    case 4:
                        System.out.println("Palavra a ser buscada: ");
                        String word = sc.next();
                        word = word.toLowerCase();
                        while(!listaPalavras.contains(word)){
                            System.out.println("Palavra não encontrada\n Informe uma nova palavra");
                            word = sc.next();
                            word = word.toLowerCase();
                        }
                        System.out.println(word);
                        System.out.println(listaPalavras.buscaPalavra(word));
                        break;
                    case 5:
                        System.out.println("Tchauzinho :)");
                        break;
                    default:
                        System.out.println("Opção inválida, tente novamente!");
                        break;
                }
            }
        } catch(Exception e){
            System.out.println("A entrada foi inválida\n[*] Encerrando o programa...");
        }

        sc.close();
        
    }
}
