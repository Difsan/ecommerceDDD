package org.example.ecommerce.domain.delivery.values;

import org.example.ecommerce.generic.ValueObject;

import java.time.LocalDate;
import java.util.Objects;

public class PersonalInfo implements ValueObject<PersonalInfo.Props> {

    private final String name;
    private final Integer age;

    private final String phone;



    public PersonalInfo(LocalDate createDate, Double total, String name, Integer age, String phone) {
        this.name = Objects.requireNonNull(name);
        if (this.name.isEmpty()) {
            throw new IllegalArgumentException("name is not valid");
        }
        this.age = Objects.requireNonNull(age);
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
            public Integer age() {
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
        Integer age();

        String phone();
    }
}
