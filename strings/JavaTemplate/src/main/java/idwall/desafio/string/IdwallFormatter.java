package idwall.desafio.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 */
public class IdwallFormatter extends StringFormatter {

    public IdwallFormatter(Integer limit) {
        super(limit);
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

        List<String> resultLines = new ArrayList<>();

        for (int l = 0; l < lines.length; l++) {
            String[] words = lines[l].split(" ");

            if (words.length == 1 && words[0].isEmpty()) {
                resultLines.add("\n\n");
                actualLen = 0;
                continue;
            }

            StringBuilder resultSb = new StringBuilder();

            for (int i = 0; i < words.length; i++) {
                if (actualLen + words[i].length() <= limit) {
                    resultSb.append(words[i]);
                    actualLen += words[i].length();
                    if (actualLen + 1 < limit) {
                        resultSb.append(" ");
                        actualLen++;
                    }
                } else {
                    resultLines.add(resultSb.toString());
                    resultLines.add("\n");
                    resultSb = new StringBuilder();
                    resultSb.append(words[i]);
                    resultSb.append(" ");
                    actualLen = words[i].length() + 1;
                }
            }
            resultLines.add(resultSb.toString());
        }

        StringBuilder result = new StringBuilder();

        resultLines.forEach(l -> {
            result.append(l);
        });

        return result.toString();
    }
}
