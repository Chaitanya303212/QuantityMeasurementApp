enum WeightUnit {

    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double toKg;

    WeightUnit(double toKg) {
        this.toKg = toKg;
    }

    public double convertToBase(double value) {
        return value * toKg;
    }

    public double convertFromBase(double baseValue) {
        return baseValue / toKg;
    }
}

class QuantityWeight {

    private final double value;
    private final WeightUnit unit;

    public QuantityWeight(double value, WeightUnit unit) {
        if (!Double.isFinite(value) || unit == null) {
            throw new IllegalArgumentException("Invalid input");
        }
        this.value = value;
        this.unit = unit;
    }

    public double toBase() {
        return unit.convertToBase(value);
    }

    public QuantityWeight convertTo(WeightUnit target) {
        double base = this.toBase();
        double result = target.convertFromBase(base);
        return new QuantityWeight(result, target);
    }

    public QuantityWeight add(QuantityWeight other) {
        return add(other, this.unit);
    }

    public QuantityWeight add(QuantityWeight other, WeightUnit target) {

        if (other == null || target == null) {
            throw new IllegalArgumentException("Invalid input");
        }

        double sumBase = this.toBase() + other.toBase();
        double result = target.convertFromBase(sumBase);

        return new QuantityWeight(result, target);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        QuantityWeight other = (QuantityWeight) obj;

        return Double.compare(this.toBase(), other.toBase()) == 0;
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight w3 = new QuantityWeight(2.20462, WeightUnit.POUND);

        System.out.println("Equality (kg ↔ g): " + w1.equals(w2));
        System.out.println("Equality (kg ↔ lb): " + w1.equals(w3));

        System.out.println("Convert kg → g: " + w1.convertTo(WeightUnit.GRAM));
        System.out.println("Convert lb → kg: " + w3.convertTo(WeightUnit.KILOGRAM));

        System.out.println("Add (kg + g): " + w1.add(w2));
        System.out.println("Add (kg + g → g): " + w1.add(w2, WeightUnit.GRAM));
    }
}