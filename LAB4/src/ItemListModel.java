public class ItemListModel {
    private String name;
    private String value;
    private boolean braces;
    public ItemListModel(String name, String value, boolean braces) {
        this.name = name;
        this.value = value;
        this.braces = braces;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public boolean hasBraces() {
        return braces;
    }
}
