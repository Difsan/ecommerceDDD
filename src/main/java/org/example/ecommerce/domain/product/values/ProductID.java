package org.example.ecommerce.domain.product.values;

import org.example.ecommerce.generic.Identity;

public class ProductID extends Identity {

        public ProductID(String ProductID) {
            super(ProductID);
        }

        public ProductID() {

        }

        public static ProductID of(String ProductID) {
            return new ProductID(ProductID);
        }
}
