enum LengthUnit {

    FEET(1.0),
    INCH(1.0 / 12.0),
    YARD(3.0),
    CM(0.393701 / 12.0);

    private final double toFeet;

    LengthUnit(double toFeet) {
        this.toFeet = toFeet;
    }

    public double convertToBase(double value) {
        return value * toFeet;
    }

    public double convertFromBase(double baseValue) {
        return baseValue / toFeet;
    }
}

class QuantityLength {

    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        if (!Double.isFinite(value) || unit == null) {
            throw new IllegalArgumentException("Invalid input");
        }
        this.value = value;
        this.unit = unit;
    }

    public double toBase() {
        return unit.convertToBase(value);
    }

    public QuantityLength convertTo(LengthUnit target) {
        double base = this.toBase();
        double result = target.convertFromBase(base);
        return new QuantityLength(result, target);
    }

    public QuantityLength add(QuantityLength other, LengthUnit target) {

        if (other == null || target == null) {
            throw new IllegalArgumentException("Invalid input");
        }

        double sumBase = this.toBase() + other.toBase();
        double result = target.convertFromBase(sumBase);

        return new QuantityLength(result, target);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        QuantityLength other = (QuantityLength) obj;

        return Double.compare(this.toBase(), other.toBase()) == 0;
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

        System.out.println("Convert: " + q1.convertTo(LengthUnit.INCH));
        System.out.println("Add (Feet): " + q1.add(q2, LengthUnit.FEET));
        System.out.println("Add (Yard): " + q1.add(q2, LengthUnit.YARD));
        System.out.println("Equals: " + q1.equals(q2));
    }
}