package com.example.category.test.model;

import com.example.product.model.Fixer;
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
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

public class FixerPojoTest {

    @Test
    public void validateFixerPojo() {
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
        validator.validate(PojoClassFactory.getPojoClass(Fixer.class));
    }

    @Test
    public void shouldFollowEqualsContract() {
        EqualsVerifier.forClass(Fixer.class)
                .suppress(Warning.ALL_FIELDS_SHOULD_BE_USED)
                .verify();
    }

    @Test
    public void hasToString() {
        Fixer fixer = new Fixer(true, 123123L, "EUR", new Date(), MapUtils.putAll(new HashMap<>(), new Object[][]{
                {"USD", 120.00}}));
        assertThat(ObjectUtils.identityToString(fixer), not(fixer.toString()));
    }
}
