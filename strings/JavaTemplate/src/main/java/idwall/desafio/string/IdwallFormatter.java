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
     * Should format as described in the challenge
     *
     * @param text
     * @return
     */
    @Override
    public String format(String text) {
        String[] lines = text.split("\n");

        int actualLen = 0;

        List<LinkedList<String>> resultLines = new ArrayList<>();

        for (int l = 0; l < lines.length; l++) {
            String[] words = lines[l].split(" ");
            LinkedList<String> lineRepresentantion = new LinkedList<>();

            if (words.length == 1 && words[0].isEmpty()) {
                lineRepresentantion.add("\n\n");
                resultLines.add(lineRepresentantion);
                actualLen = 0;
                continue;
            }

            for (int i = 0; i < words.length; i++) {
                if (actualLen + words[i].length() <= limit) {
                    lineRepresentantion.add(words[i]);
                    lineRepresentantion.add(" ");
                    actualLen += words[i].length()+1;
                } else {
                    lineRepresentantion.remove(lineRepresentantion.size()-1);
                    actualLen--;
                    lineRepresentantion.add("\n");
                    resultLines.add(lineRepresentantion);

                    lineRepresentantion = new LinkedList<>();
                    lineRepresentantion.add(words[i]);
                    lineRepresentantion.add(" ");

                    actualLen = words[i].length() + 1;
                }
            }
            resultLines.add(lineRepresentantion);
        }

        if(justify){
            justifyResultLines(resultLines);
        }

        return getStringResult(resultLines);
    }

    private List<LinkedList<String>> justifyResultLines(List<LinkedList<String>> resultLines) {
        resultLines.forEach(rl -> {
            List<Integer> spacesNodesReferences = new ArrayList<>();
            int lineLen = 0;

            for(int x =0; x < rl.size(); x ++){
                if(SPACE_CONSTANT.equals(rl.get(x))){
                    spacesNodesReferences.add(x);
                    lineLen++;
                } else if(!BREAK_LINE.equals(rl.get(x))) {
                    lineLen+= rl.get(x).length();
                }
            }

            int availableSpaces = limit - lineLen;

            if(availableSpaces > 0 && spacesNodesReferences.size() > 0) {
                while (availableSpaces > 0) {
                    Random randomSpace = new Random();
                    rl.add(spacesNodesReferences.get(randomSpace.nextInt(spacesNodesReferences.size())), SPACE_CONSTANT);
                    availableSpaces--;
                }
            }
        });

        return resultLines;
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
