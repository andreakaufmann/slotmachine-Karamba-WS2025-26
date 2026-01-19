package slotmachine.model;

public enum SymbolEnum {

        CHERRY("cherry.png", 5),
        SEVEN("seven.png", 15),
        BELL("bell.png", 20),
        DIAMOND("diamond.png", 50),
        HORSESHOE("horseshoe.png", 10);

        private final String iconPath;
        private final int value;

        SymbolEnum(String iconFile, int value) {
            this.iconPath = "/icons/" + iconFile;
            this.value = value;
        }

        public String getIconPath() {
            return iconPath;
        }

        public int getValue() {
            return value;
        }


}
