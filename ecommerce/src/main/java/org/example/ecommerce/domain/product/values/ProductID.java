package org.example.ecommerce.domain.product.values;

import org.example.ecommerce.generic.Identity;

public class ProductID extends Identity {

        public ProductID(String productID) {
            super(productID);
        }

        public ProductID() {

        }

        public static ProductID of(String productID) {
            return new ProductID(productID);
        }
}
