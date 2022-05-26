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
        String l;

        ArquivoTexto stopWords = new ArquivoTexto(); // objeto que gerencia o arquivo de stopwords
        ArquivoTexto arquivo = new ArquivoTexto(); // objeto que gerencia o arquivo a ser lido
        LinhaTexto linha = new LinhaTexto(); // objeto que gerencia uma linha
        ListaOrdenadaDePalavras listaPalavras = new ListaOrdenadaDePalavras();
        LinkedListOfString stopWordsList = new LinkedListOfString();

        //leitura do arquivo stopwors.txt
        stopWords.open("stopwords.txt");
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
        //leitura do arquivo java.txt
        arquivo.open("java.txt");
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

                if (!stopWordsList.contains(palavra)) {
                    if (listaPalavras.contains(palavra)) {
                        listaPalavras.addPagina(palavra, nLinha+1);
                    }else{
                        listaPalavras.add(palavra);
                        listaPalavras.addPagina(palavra, nLinha+1);
                    }
                }
            } while (true);

        } while (true);
        System.out.println(totalLinhas + " linhas lidas.");
        System.out.println("Gerando índice remissivo para " + totalLinhas + " linhas de texto...");
        System.out.println("Linhas por página: 40");
        System.out.println("Tamanho Minimo das Palavras Indexadas: 1");
        System.out.println("Ignorando " + stopWordsList.size() + " stopWords.");
        System.out.println("Índice remissivo gerado, contendo " + listaPalavras.size() + " palavras.");
        System.out.println("=== Índice Remissivo ===");

        System.out.println(listaPalavras.toString());

        arquivo.close();
    }
}
