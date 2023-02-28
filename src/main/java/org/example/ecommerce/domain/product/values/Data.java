package org.example.ecommerce.domain.product.values;

import org.example.ecommerce.generic.ValueObject;

import java.util.Objects;

public class Data implements ValueObject<Data.Props> {
    private final String name;
    private final String nit;
    private final String email;
    private final String about;

    public Data(String name, String nit, String email, String about) {
        this.name = name;
        this.nit = Objects.requireNonNull(nit);
        if (this.nit.isEmpty()) {
            throw new IllegalArgumentException("nit is not valid");
        }
        this.email = email;
        this.about = about;
    }

    @Override
    public Props value() {
        return new Props() {
            @Override
            public String name() {
                return name;
            }

            @Override
            public String nit() {
                return nit;
            }

            @Override
            public String email() {
                return email;
            }

            @Override
            public String about() {
                return about;
            }
        };
    }

    interface Props{
        String name();
        String nit();
        String email();
        String about();
    }
}
