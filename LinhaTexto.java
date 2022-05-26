/**
 *
 * @author Isabel H. Manssour
 */
public class LinhaTexto {
    private String linha;
    private String palavras[];
    private int contPalavras;
    
    /**
     * Recebe a string da linha que sera armazenada.
     * @param string com a linha de texto
     */
    public void setLine(String lin) {
        linha = lin;
        linha = linha.replaceAll("\\t",""); // substitui tab por espaco em branco
        linha = linha.replaceAll(",",""); // para remover vírgulas
        linha = linha.replaceAll("\\.",""); // para remover ponto final
        linha = linha.replaceAll("\\?",""); // para remover ponto interrogacao
        linha = linha.replaceAll("\\!",""); // para remover ponto exclamacao
        linha = linha.replaceAll("\"",""); // para remover aspas duplas
        linha = linha.replaceAll("\'",""); // para remover aspas duplas
        linha = linha.replaceAll("\\(", ""); // para remover abre parenteses
        linha = linha.replaceAll("\\)", ""); // para remover fecha parenteses
        linha = linha.replaceAll("\\[", ""); // para remover abre colchetes
        linha = linha.replaceAll("\\]", ""); // para remover fecha colchetes
        linha = linha.replaceAll("\\{", ""); // para remover abre chaves
        linha = linha.replaceAll("\\}", ""); // para remover fecha chaves
        linha = linha.replaceAll("\\:", ""); // para remover dois pontos
        linha = linha.replaceAll("\\;", ""); // para remover dois pontos
        linha = linha.replaceAll("\\-", ""); // para remover hifen
        linha = linha.replaceAll("\\>", ""); // para remover sinal de maior
        linha = linha.replaceAll("\\<", ""); // para remover sinal de menor
        linha = linha.replaceAll("\\n",""); // para remover quebra de linha

        palavras = linha.split(" "); // divide a string pelo espaco em branco 
        contPalavras = 0;
    }
    
    /**
     * Retorna uma palavra da linha.
     * @return a palavra, ou null caso nao tenha mais palavras.
     */
    public String getNextWord() {
      String pal = null;
      if (contPalavras < palavras.length) {
          pal = palavras[contPalavras];
          contPalavras++;
      }
      return pal;
    }
}
