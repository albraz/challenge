package br.com.itausegdev.backend.challenge.domain.builder;

public class ProductBuilder {

    private String id;
    private String name;
    private String category;
    private String base;
    private String fee;

    public static class Builder {
        private String id;
        private String name;
        private String category;
        private String base;
        private String fee;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder category(String category) {
            this.category = category;
            return this;
        }

        public Builder base(String base) {
            this.base = base;
            return this;
        }

        public Builder fee(String fee) {
            this.fee = fee;
            return this;
        }

        public ProductBuilder build() {
            return new ProductBuilder(this);
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getBase() {
        return base;
    }

    public String getFee() {
        return fee;
    }

    public ProductBuilder(Builder builder) {
        id = builder.id;
        name = builder.name;
        category = builder.category;
        base = builder.base;
        fee = builder.fee;
    }
}
