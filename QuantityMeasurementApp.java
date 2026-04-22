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

    static double convert(double value, LengthUnit from, LengthUnit to) {
        return new QuantityLength(value, from).convertTo(to).value;
    }

    public static void main(String[] args) {

        System.out.println("Feet → Inches: " + convert(1.0, LengthUnit.FEET, LengthUnit.INCH));
        System.out.println("Yard → Feet: " + convert(3.0, LengthUnit.YARD, LengthUnit.FEET));
        System.out.println("Inch → Yard: " + convert(36.0, LengthUnit.INCH, LengthUnit.YARD));
        System.out.println("CM → Inch: " + convert(1.0, LengthUnit.CM, LengthUnit.INCH));
        System.out.println("Zero Conversion: " + convert(0.0, LengthUnit.FEET, LengthUnit.INCH));
        System.out.println("Negative Conversion: " + convert(-1.0, LengthUnit.FEET, LengthUnit.INCH));
    }
}