package slotmachine.model;

public enum SymbolEnum {

        CHERRY("cherry.png", 5, 50),
        SEVEN("seven.png", 15, 10),
        BELL("bell.png", 20, 15),
        DIAMOND("diamond.png", 50, 5),
        HORSESHOE("horseshoe.png", 10, 30);

        private final String iconPath;
        private final int value;
        private final int baseWeight;

        SymbolEnum(String iconFile, int value, int baseWeight) {
            this.iconPath = "/icons/" + iconFile;
            this.value = value;
            this.baseWeight = baseWeight;
        }

        public String getIconPath() {
            return iconPath;
        }

        public int getValue() {
            return value;
        }
        public int getBaseWeight() {
            return baseWeight;
        }


}
