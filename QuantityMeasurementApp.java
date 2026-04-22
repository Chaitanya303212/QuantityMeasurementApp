public class QuantityMeasurementApp {

    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CM(0.393701 / 12.0);

        private final double toFeet;

        LengthUnit(double toFeet) {
            this.toFeet = toFeet;
        }

        public double toFeet(double value) {
            return value * toFeet;
        }
    }

    static class QuantityLength {

        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            this.value = value;
            this.unit = unit;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;

            double thisInFeet = this.unit.toFeet(this.value);
            double otherInFeet = other.unit.toFeet(other.value);

            return Double.compare(thisInFeet, otherInFeet) == 0;
        }
    }

    public static void main(String[] args) {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength q2 = new QuantityLength(3.0, LengthUnit.FEET);

        QuantityLength q3 = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength q4 = new QuantityLength(36.0, LengthUnit.INCH);

        QuantityLength q5 = new QuantityLength(1.0, LengthUnit.CM);
        QuantityLength q6 = new QuantityLength(0.393701, LengthUnit.INCH);

        QuantityLength q7 = new QuantityLength(2.0, LengthUnit.YARD);
        QuantityLength q8 = new QuantityLength(2.0, LengthUnit.YARD);

        System.out.println("Yard to Feet: " + q1.equals(q2));
        System.out.println("Yard to Inch: " + q3.equals(q4));
        System.out.println("CM to Inch: " + q5.equals(q6));
        System.out.println("Same Yard Value: " + q7.equals(q8));
        System.out.println("Null Comparison: " + q1.equals(null));
        System.out.println("Same Reference: " + q1.equals(q1));
    }
}