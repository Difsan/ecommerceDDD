package org.example.ecommerce.domain.delivery.values;

import org.example.ecommerce.generic.ValueObject;

import java.time.LocalDate;
import java.util.Objects;

public class Data implements ValueObject<Data.Props> {

    private final String name;
    private final String phone;

    private final String webPage;



    public Data(LocalDate createDate, Double total, String name, String phone, String webPage) {
        this.name = Objects.requireNonNull(name);
        if (this.name.isEmpty()) {
            throw new IllegalArgumentException("name is not valid");
        }
        this.webPage = Objects.requireNonNull(webPage);
        this.phone = Objects.requireNonNull(phone);
        if (this.phone.isEmpty()) {
            throw new IllegalArgumentException("phone is not valid");
        }
    }

    @Override
    public Props value() {
        return new Props() {
            @Override
            public String name() {
                return null;
            }

            @Override
            public String webPage() {
                return null;
            }

            @Override
            public String phone() {
                return null;
            }
        };
    }

    interface Props{
        String name();
        String webPage();

        String phone();
    }
}
