import java.util.ArrayList;
import java.util.List;


public class ResultadoBusca {
    private final String arquivo;          // Caminho completo do arquivo
    private final List<Ocorrencia> ocorrencias; // Lista de ocorrências encontradas
    private int totalOcorrencias;         // Contagem total de ocorrências

    public ResultadoBusca(String arquivo, List<Ocorrencia> ocorrencias) {
        this.arquivo = arquivo;
        this.ocorrencias = new ArrayList<>(ocorrencias);
        this.totalOcorrencias = ocorrencias.size();
    }

    /**
     * @return Caminho completo do arquivo
     */
    public String getArquivo() {
        return arquivo;
    }

    /**
     * @return Lista imutável de ocorrências encontradas
     */
    public List<Ocorrencia> getOcorrencias() {
        return new ArrayList<>(ocorrencias);
    }

    /**
     * @return Número total de ocorrências encontradas no arquivo
     */
    public int getTotalOcorrencias() {
        return totalOcorrencias;
    }

    /**
     * Adiciona uma nova ocorrência ao resultado
     * @param ocorrencia Ocorrência a ser adicionada
     */
    public void adicionarOcorrencia(Ocorrencia ocorrencia) {
        this.ocorrencias.add(ocorrencia);
        this.totalOcorrencias++;
    }

    /**
     * Verifica se existem ocorrências neste resultado
     * @return true se há ocorrências, false caso contrário
     */
    public boolean possuiOcorrencias() {
        return !ocorrencias.isEmpty();
    }

    /**
     * Retorna uma representação em string formatada do resultado
     * @return String formatada com as informações do resultado
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Arquivo: ").append(arquivo).append("\n");
        sb.append("Total de ocorrências: ").append(totalOcorrencias).append("\n");

        for (Ocorrencia oc : ocorrencias) {
            sb.append("  Linha ").append(oc.getLinha())
                    .append(": ").append(oc.getTexto()).append("\n");
        }

        return sb.toString();
    }
}