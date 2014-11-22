package enums;

public enum Day {

    HÉTFŐ, KEDD, SZERDA, CSÜTÖRTÖK, PÉNTEK;

    /**
     *
     * @param day
     * @return
     */
    public static String fromString(String day) {

        String first = day.substring(0, 1);
        first = first.toLowerCase();

        switch (first) {
            case "h": {
                return "Hétfő";
            }
            case "k": {
                return "Kedd";
            }
            case "s": {
                return "Szerda";
            }
            case "c": {
                return "Csütörtök";
            }
            case "p": {
                return "Péntek";
            }
        }

        return null;
    }
    
    public static String getEnum(int day) {       

        switch (day) {
            case 1: {
                return "Hétfő";
            }
            case 2: {
                return "Kedd";
            }
            case 3: {
                return "Szerda";
            }
            case 4: {
                return "Csütörtök";
            }
            case 5: {
                return "Péntek";
            }
        }

        return null;
    }
        
}
