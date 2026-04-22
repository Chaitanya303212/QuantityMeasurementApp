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

    public static void main(String[] args) {

        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);
        Feet f3 = new Feet(2.0);

        System.out.println("Same Value (1.0 == 1.0): " + f1.equals(f2));
        System.out.println("Different Value (1.0 != 2.0): " + f1.equals(f3));
        System.out.println("Null Comparison: " + f1.equals(null));
        System.out.println("Same Reference: " + f1.equals(f1));
        System.out.println("Different Type: " + f1.equals("test"));
    }
}