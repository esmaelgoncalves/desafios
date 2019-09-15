package idwall.desafio.string;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 */
public class IdwallFormatter extends StringFormatter {

    public static final String SPACE_CONSTANT = " ";
    public static final String BREAK_LINE = "\n";

    public IdwallFormatter(Integer limit, boolean justify) {
        super(limit, justify);
    }

    /**
     * <p>O algoritmo de formatação abaixo realiza a formatação do texto recebido de acordo
     * com o limite de caracteres estipulado e se deve ou não justificar o texto.</p>
     *
     * <p>O texto recebido é valiado em relação ao seu tamanho, caso seja nulo ou tenha um
     * tamanho = 0 é lançada uma exception do tipo: {@link IllegalArgumentException}</p>
     *
     * <p>Em seguida é realizado um split do texto: {@code String[] lines = text.split("\n");}
     * com o intuito de separar o texto recebido em linhas, caso o texto tenha mais de uma linha.
     * O próximo passo é iterar sobre cada linha obtida no passo anterior e então realizar o split
     * do texto pelo caractere de espaço " " afim de separar todas as palavras do texto em um array:
     * {@code String[] words = lines[l].split(SPACE_CONSTANT);}, uma vez tendo um array com todas
     * palavras o próximo passo do algoritimo é iterar sobre esse array e adicionar em uma
     * {@link LinkedList}, que irá representar uma nova linha formatada cada palavra encontrada mais
     * uma representação de espaço controlando se o tamanho atual da linha é menor ou igual o limite
     * parametrizado ({@code  if (actualLen + words[i].length() <= limit)}).
     * Sempre que o tamanho atual da linha chegar ao limite um nova linha ({@link LinkedList}) é instanciado.</p>
     *
     * <p>O próximo passo é verificar se o texto deverá ou não ser justificado. Se sim, utilizamos uma implementação
     * baseada no Algoritmo de Markov. Armazenamos em um {@link ArrayList} as posições de referência de cada " "
     * (espaço) da linha e também calculamos a quantidade de espaços restantes para que o texto atinja o limite estabelecido:
     * ({@code int availableSpaces = limit - lineLen;}). Em seguida baseado nas posições do ArrayList de posições
     * vazias selecionamos de forma aleatória o index da LinkedList e preenchemos com espaços, até que o valor da variável
     * {@code availableSpaces} seja igual a 0. Com isso teremos o texto justificado a direita e a esquerda</p>
     *
     * <p>O último passo é retornar a String final com o texto formatado. Para isso iteramos sobre o ArrayList que
     * contém os LinkedList que representam cada linha e por fim iteramos cada uma das LinkedList e armazenamos
     * seu texto e um StringBuilder.</p>
     *
     * @param text
     * @return String
     */
    @Override
    public String format(String text) {

        if(text == null || text.length() <= 0){
            throw new IllegalArgumentException("Input de Texto Inválido");
        }

        String[] lines = text.split("\n");

        int actualLen = 0;

        List<LinkedList<String>> resultLines = new ArrayList<>();

        for (int l = 0; l < lines.length; l++) {
            String[] words = lines[l].split(SPACE_CONSTANT);
            LinkedList<String> lineRepresentantion = new LinkedList<>();

            if (words.length == 1 && words[0].isEmpty()) {
                lineRepresentantion.add("\n\n");
                resultLines.add(lineRepresentantion);
                actualLen = 0;
                continue;
            }

            for (int i = 0; i < words.length; i++) {
                if (actualLen + words[i].length() <= getLimit()) {
                    lineRepresentantion.add(words[i]);
                    lineRepresentantion.add(" ");
                    actualLen += words[i].length() + 1;
                } else {
                    lineRepresentantion.remove(lineRepresentantion.size() - 1);
                    actualLen--;
                    lineRepresentantion.add("\n");
                    resultLines.add(lineRepresentantion);

                    if (isJustify()) {
                        justifyResultLines(lineRepresentantion);
                    }

                    lineRepresentantion = new LinkedList<>();
                    lineRepresentantion.add(words[i]);
                    lineRepresentantion.add(" ");

                    actualLen = words[i].length() + 1;
                }
            }
            resultLines.add(lineRepresentantion);
        }

        return getStringResult(resultLines);
    }

    private void justifyResultLines(LinkedList<String> line) {
        List<Integer> spacesNodesReferences = new ArrayList<>();
        int lineLen = 0;

        for (int x = 0; x < line.size(); x++) {
            if (SPACE_CONSTANT.equals(line.get(x))) {
                spacesNodesReferences.add(x);
                lineLen++;
            } else if (!BREAK_LINE.equals(line.get(x))) {
                lineLen += line.get(x).length();
            }
        }

        int availableSpaces = getLimit() - lineLen;

        if (availableSpaces > 0 && spacesNodesReferences.size() > 0) {
            while (availableSpaces > 0) {
                Random randomSpace = new Random();
                line.add(spacesNodesReferences.get(randomSpace.nextInt(spacesNodesReferences.size())), SPACE_CONSTANT);
                availableSpaces--;
            }
        }
    }

    private String getStringResult(List<LinkedList<String>> resultLines) {
        StringBuilder result = new StringBuilder();

        resultLines.forEach(lp -> {
            lp.forEach(l -> {
                result.append(l);
            });
        });
        return result.toString();
    }
}
