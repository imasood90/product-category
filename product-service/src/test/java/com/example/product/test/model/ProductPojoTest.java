package com.example.product.test.model;

import com.example.product.model.Product;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.NoPublicFieldsExceptStaticFinalRule;
import com.openpojo.validation.rule.impl.NoStaticExceptFinalRule;
import com.openpojo.validation.rule.impl.SerializableMustHaveSerialVersionUIDRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;


public class ProductPojoTest {

    @Test
    public void validateProductPojo() {
        final Validator validator;
        validator = ValidatorBuilder.create()
                .with(new GetterMustExistRule())
                .with(new SetterMustExistRule())
                .with(new NoPublicFieldsExceptStaticFinalRule())
                .with(new NoStaticExceptFinalRule())
                .with(new SetterTester())
                .with(new SerializableMustHaveSerialVersionUIDRule())
                .with(new GetterTester())
                .build();
        validator.validate(PojoClassFactory.getPojoClass(Product.class));
    }


    @Test
    public void hasToString() {
        final Product product = new Product();
        product.setName("Mobile");
        product.setBrowsingName("Mobile");
        product.setCategoryId(UUID.randomUUID().toString());
        product.setDescription("This is for Mobile Product");
        product.setIsStockControlled(true);
        product.setSku("UGG-BB-PUR-06");
        product.setListPrice(120.0);
        product.setPrice(110.0);
        product.setImage("test.jpg");
        product.setQuantity(1);
        assertThat(ObjectUtils.identityToString(product), not(product.toString()));
    }
}
