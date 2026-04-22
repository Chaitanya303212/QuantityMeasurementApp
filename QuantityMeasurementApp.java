public class QuantityMeasurementApp {

    static class Feet {

        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null || getClass() != obj.getClass()) return false;

            Feet other = (Feet) obj;

            return Double.compare(this.value, other.value) == 0;
        }
    }

    static class Inches {

        private final double value;

        public Inches(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null || getClass() != obj.getClass()) return false;

            Inches other = (Inches) obj;

            return Double.compare(this.value, other.value) == 0;
        }
    }

    static boolean checkFeetEquality(double a, double b) {
        return new Feet(a).equals(new Feet(b));
    }

    static boolean checkInchesEquality(double a, double b) {
        return new Inches(a).equals(new Inches(b));
    }

    public static void main(String[] args) {

        System.out.println("Feet Same Value: " + checkFeetEquality(1.0, 1.0));
        System.out.println("Feet Different Value: " + checkFeetEquality(1.0, 2.0));

        System.out.println("Inches Same Value: " + checkInchesEquality(1.0, 1.0));
        System.out.println("Inches Different Value: " + checkInchesEquality(1.0, 2.0));

        System.out.println("Feet Null Comparison: " + new Feet(1.0).equals(null));
        System.out.println("Inches Null Comparison: " + new Inches(1.0).equals(null));

        System.out.println("Feet Same Reference: " + new Feet(1.0).equals(new Feet(1.0)));
        System.out.println("Inches Same Reference: " + new Inches(1.0).equals(new Inches(1.0)));

        System.out.println("Feet Different Type: " + new Feet(1.0).equals("test"));
        System.out.println("Inches Different Type: " + new Inches(1.0).equals("test"));
    }
}