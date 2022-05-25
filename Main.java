/**
 * Classe que inicializa a execução da aplicacao.
 * 
 * @author Isabel H. Manssour
 */
public class Main {
    public static void main(String[] args) {

        int nLinha = 0;
        int nPagina = 0;

        ArquivoTexto stopWords = new ArquivoTexto(); // objeto que gerencia o arquivo de stopwords
        ArquivoTexto arquivo = new ArquivoTexto(); // objeto que gerencia o arquivo a ser lido
        LinhaTexto linha = new LinhaTexto(); // objeto que gerencia uma linha
        String l;

        stopWords.open("stopwords.txt");

        // LinkedList para armazenar todas as stopwords
        LinkedListOfString stopWordsList = new LinkedListOfString();

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

        // fecha objeto que gerencia o arquivo de stopwords
        // todas as stopwords já foram armazenadas na linkedlist
        stopWords.close();

        do // laco que passa em cada linha do arquivo
        {
            l = arquivo.getNextLine();
            if (l == null) // acabou o arquivo?
                break;
            nLinha++; // conta mais uma linha lida do arquivo
            if (nLinha == 40) // chegou ao fim da pagina?
            {
                nLinha = 0;
                nPagina++;
            }
            System.out.println("Linha " + nLinha + ":");

            linha.setLine(l); // define o texto da linha
            do // laco que passa em cada palavra de uma linha
            {
                String palavra = linha.getNextWord(); // obtem a proxima palavra da linha
                if (palavra == null)// acabou a linha
                {
                    break;
                }
                System.out.println("-" + palavra + "-");
            } while (true);

        } while (true);

        arquivo.close();
    }
}
