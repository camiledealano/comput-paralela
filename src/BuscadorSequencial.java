import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BuscadorSequencial {

    final String DIRETORIO = "caminho/do/diretorio";

    public void buscarSequencial(String nomeBusca) {
        long startTime = System.currentTimeMillis();

        List<ResultadoBusca> resultados = buscarEmArquivosTxt(diretorio, nomeBusca);

        for (ResultadoBusca resultado : resultados) {
            System.out.println("Arquivo: " + resultado.getArquivo());
            for (Ocorrencia oc : resultado.getOcorrencias()) {
                System.out.println("  Linha " + oc.getLinha() + ": " + oc.getTexto());
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Tempo total (sequencial): " + (endTime - startTime) + "ms");
    }

    public static List<ResultadoBusca> buscarEmArquivosTxt(String diretorio, String nomeBusca) {
        List<ResultadoBusca> resultados = new ArrayList<>();
        File dir = new File(diretorio);

        File[] arquivos = dir.listFiles((d, nome) -> nome.endsWith(".txt"));
        if (arquivos != null) {
            for (File arquivo : arquivos) {
                ResultadoBusca resultado = buscarNoArquivo(arquivo, nomeBusca);
                if (!resultado.getOcorrencias().isEmpty()) {
                    resultados.add(resultado);
                }
            }
        }

        return resultados;
    }

    private static ResultadoBusca buscarNoArquivo(File arquivo, String nomeBusca) {
        List<Ocorrencia> ocorrencias = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            int numLinha = 0;

            while ((linha = br.readLine()) != null) {
                numLinha++;
                if (linha.contains(nomeBusca)) {
                    ocorrencias.add(new Ocorrencia(numLinha, linha));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResultadoBusca(arquivo.getAbsolutePath(), ocorrencias);
    }
}

class ResultadoBusca {
    private final String arquivo;
    private final List<Ocorrencia> ocorrencias;

    // Construtor, getters...
}

class Ocorrencia {
    private final int linha;
    private final String texto;

    // Construtor, getters...
}