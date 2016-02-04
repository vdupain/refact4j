package org.refact4j.eom;

import org.junit.Test;
import org.refact4j.eom.model.FieldFactory;
import org.refact4j.eom.model.IntegerField;
import org.refact4j.eom.model.NumericField;
import org.refact4j.expr.ExpressionBuilder;


public class IntegerFieldTest extends AbstractFieldTestCase {

    @Test
    public void testCheckValues() throws Exception {

        IntegerField field = FieldFactory.init(entityDescriptorBuilder, "id")
                .createIntegerField();
        entityDescriptorBuilder.setConstraint(ExpressionBuilder.initEO(field)
                .greaterOrEqual(10).getExpression());

        checkError(field, 8, "Constraint (type.id>=10) failed: id=8");
    }

    @Test
    public void testNullableField() {
        IntegerField field = FieldFactory.init(entityDescriptorBuilder, "id")
                .setNullable(false).createIntegerField();

        checkFieldValue(field, null, null);
    }

    // TODO ???
//    public void testFieldConstraint() {
//        String fieldName = "id";
//        IntegerField field = FieldFactory.init(entityDescriptorBuilder, fieldName)
//                .setConstraintExpression(
//                        ExpressionBuilder.init(fieldName)
//                                .greaterOrEqual(10).getExpression()).createIntegerField();
//
//        checkFieldValue(field, 8, "(id>=10)");
//
//        try {
//            field.toXmlString();
//            fail();
//        } catch (NotImplementedException e) {
//        }
//    }

    @Test
    public void testMinMaxValueFieldConstraint() {
        NumericField field = FieldFactory.init(entityDescriptorBuilder, "id")
                .setMaxValue(10.).createDoubleField();
        checkFieldValue(field, 11.3, "(id<=10.0)");

        field = FieldFactory.init(entityDescriptorBuilder, "id").setMinValue(1)
                .createIntegerField();
        checkFieldValue(field, 0, "(id>=1)");

        field = FieldFactory.init(entityDescriptorBuilder, "id").setMinValue(20)
                .setMaxValue(30).createIntegerField();
        field.checkValue(25);
        checkFieldValue(field, 19, "((id>=20) AND (id<=30))");
        checkFieldValue(field, 31, "((id>=20) AND (id<=30))");

    }

}
