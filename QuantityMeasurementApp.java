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

        public double fromFeet(double value) {
            return value / toFeet;
        }
    }

    static class QuantityLength {

        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (!Double.isFinite(value) || unit == null) {
                throw new IllegalArgumentException("Invalid input");
            }
            this.value = value;
            this.unit = unit;
        }

        public double toFeet() {
            return unit.toFeet(value);
        }

        public QuantityLength convertTo(LengthUnit target) {
            double feetValue = this.toFeet();
            double converted = target.fromFeet(feetValue);
            return new QuantityLength(converted, target);
        }

        public QuantityLength add(QuantityLength other) {
            return add(other, this.unit);
        }

        public QuantityLength add(QuantityLength other, LengthUnit target) {

            if (other == null || target == null) {
                throw new IllegalArgumentException("Invalid input");
            }

            double sumFeet = this.toFeet() + other.toFeet();
            double result = target.fromFeet(sumFeet);

            return new QuantityLength(result, target);
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;

            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    public static void main(String[] args) {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength q3 = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength q4 = new QuantityLength(3.0, LengthUnit.FEET);

        QuantityLength q5 = new QuantityLength(36.0, LengthUnit.INCH);
        QuantityLength q6 = new QuantityLength(1.0, LengthUnit.YARD);

        QuantityLength q7 = new QuantityLength(2.54, LengthUnit.CM);
        QuantityLength q8 = new QuantityLength(1.0, LengthUnit.INCH);

        System.out.println("Feet target: " + q1.add(q2, LengthUnit.FEET));
        System.out.println("Inch target: " + q1.add(q2, LengthUnit.INCH));
        System.out.println("Yard target: " + q1.add(q2, LengthUnit.YARD));
        System.out.println("Yard + Feet: " + q3.add(q4, LengthUnit.YARD));
        System.out.println("Inch + Yard → Feet: " + q5.add(q6, LengthUnit.FEET));
        System.out.println("CM + Inch: " + q7.add(q8, LengthUnit.CM));
    }
}