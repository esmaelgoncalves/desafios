package idwall.desafio.string;

/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 */
public abstract class StringFormatter {

    private Integer limit;
    private boolean justify;

    public StringFormatter(Integer limit, boolean justify) {
        this.limit = limit;
        this.justify = justify;
    }

    public Integer getLimit() {
        return limit;
    }

    public boolean isJustify() {
        return justify;
    }

    /**
     * It receives a text and should return it formatted
     *
     * @param text
     * @return
     */
    public abstract String format(String text);
}
