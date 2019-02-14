
// "BuilderPattern" can be swapped with the desired object.
public class BuilderPattern {
    private final String bread;
    private final String condiments;
    private final String dressing;
    private final String meat;

    // Take in a Builder object and use its fields to construct the desired object.
    private BuilderPattern(Builder builder) {
        this.bread = builder.bread;
        this.condiments = builder.condiments;
        this.dressing = builder.dressing;
        this.meat = builder.meat;
    }

    // Getters... (but no setters, because we use the Builder for that).
    public String getBread() {
        return bread;
    }
    public String getCondiments() {
        return condiments;
    }
    public String getDressing() {
        return dressing;
    }
    public String getMeat() {
        return meat;
    }

    // ************************************************************************************************
    // This is the static inner class that is used to construct the Builder, which is used to contruct
    // the desired object.
    public static class Builder {
        private String bread;
        private String condiments;
        private String dressing;
        private String meat;

        // The Builder takes no args, but in it's next method, performs its real work...
        public Builder() {}

        // This build() method returns a new instance of the desired object, which it constructs
        // using the private-constructor above (that takes in the Builder and uses its fields
        // to construct),
        public BuilderPattern build() {
            return new BuilderPattern(this);
        }

        // These methods are used to change the Builder fields from null to whatever-value.
        // So to build a desired-object, you use these, then call build() to
        // return a constructed desired-object.
        public Builder bread(String bread) {
            this.bread = bread;
            return this;
        }
        public Builder condiments(String condiments) {
            this.condiments = condiments;
            return this;
        }
        public Builder dressing(String dressing) {
            this.dressing = dressing;
            return this;
        }
        public Builder meat(String meat) {
            this.meat = meat;
            return this;
        }
    }
    // ************************************************************************************************



    // Example:
    public static void main(String[] args) {

        // Instantiate the Builder.
        BuilderPattern.Builder myBuilder = new BuilderPattern.Builder();

        // Use the Builder's methods to set it's fields.
        myBuilder.bread("Wheat").condiments("Lettuce").dressing("Mayo").meat("Turkey");

        // Instantiate a BuilderPattern using the constructor that takes a Builder object.
        // The Builder returns itself on the call to build(),
        // then the BuilderPattern constructor uses the Builder's pre-set fields to construct itself.
        BuilderPattern myBuilderPattern = myBuilder.build();
        System.out.println(myBuilderPattern.getBread());
        System.out.println(myBuilderPattern.getCondiments());
        System.out.println(myBuilderPattern.getDressing());
        System.out.println(myBuilderPattern.getMeat());
    }
}