public class Main {
    public static void main(String[] args) {
        Boolean[] performanceMethods = new Boolean[]{false, false, false};
        FileParser file = new FileParser();

        for (String arg: args) {
            switch (arg) {
                case "-h":
                    performanceMethods[0] = true;
                    break;
                case "--si":
                    performanceMethods[1] = true;
                    break;
                case "-c":
                    performanceMethods[2] = true;
                    break;
                default:
                    file.addFile(arg.trim());
            }
        }

        if (performanceMethods[2]) {
            file.setCatalog();
        }

        if (performanceMethods[1]) {
            file.setNumberSystem();
        }

        if (performanceMethods[0]) {
            file.setNotation();
        }

        file.conclusionValues();
    }
}
