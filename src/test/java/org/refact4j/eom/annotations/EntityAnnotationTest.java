package org.refact4j.eom.annotations;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.refact4j.eom.EOMContext;
import org.refact4j.eom.EntityFinder;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.EntitySetBuilder;
import org.refact4j.eom.impl.EOMContextImpl;
import org.refact4j.eom.impl.EntityListImpl;
import org.refact4j.eom.metamodel.EOMMetaModelRepository;
import org.refact4j.eom.model.EntityDescriptorRepository;
import org.refact4j.eom.model.impl.EntityDescriptorRepositoryImpl;
import org.refact4j.eom.xml.reader.EntityXmlReaderHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


public class EntityAnnotationTest {
    private EntityDescriptorRepository repository = new EntityDescriptorRepositoryImpl();

    private FooImpl foo;

    private BarImpl bar;

    @Test
    public void testTwoBeansWithToOneRelation() {
        AnnotedEntityBindableEntityConverter entityObjectTransformer = new AnnotedEntityBindableEntityConverter();
        entityObjectTransformer.setEntityDescriptorRepository(repository);
        EntityObject fooEntity = entityObjectTransformer.convert(foo);
        EntityObject barEntity = entityObjectTransformer.convert(bar);

        Assert.assertEquals("<Foo booleanFlag=\"true\" idFoo=\"1234\" name=\"fooo\" value=\"5.678\"/>", fooEntity
                .toXmlString());
        Assert.assertEquals("<Bar foo=\"Foo[idFoo=1234,name=fooo]\" idBar=\"99\" name=\"babar\"/>", barEntity.toXmlString());

        List beans = new ArrayList();
        beans.add(foo);
        beans.add(bar);

        List<EntityObject> entityObjects = (List) beans.stream().map(entityObjectTransformer::convert).collect(Collectors.toList());

        Assert.assertEquals(2, entityObjects.size());
        Assert.assertEquals(fooEntity.toXmlString(), entityObjects.get(0).toXmlString());
        Assert.assertEquals(barEntity.toXmlString(), entityObjects.get(1).toXmlString());

        AnnotedEntityBindableBeanConverter beanTransformer = new AnnotedEntityBindableBeanConverter();

        EntityFinder entityObjectFinder = EntitySetBuilder.init().addAll(entityObjects).getEntitySet();
        beanTransformer.setEntityFinder(entityObjectFinder);

        Foo actualFoo = (Foo) beanTransformer.convert(fooEntity);
        System.err.println(actualFoo.toString());
        Assert.assertEquals(foo.getId(), actualFoo.getId());
        Assert.assertEquals(foo.getName(), actualFoo.getName());
        Assert.assertEquals(foo.getValue(), actualFoo.getValue());

        Bar actualBar = (Bar) beanTransformer.convert(barEntity);
        Assert.assertEquals(bar.getId(), actualBar.getId());
        Assert.assertEquals(bar.getName(), actualBar.getName());
        Foo fooBar = actualBar.getFoo();
        Assert.assertEquals(foo.getId(), fooBar.getId());
        Assert.assertEquals(foo.getName(), fooBar.getName());
        Assert.assertEquals(foo.getValue(), fooBar.getValue());

        entityObjects = new EntityListImpl();
        entityObjects.add(fooEntity);
        entityObjects.add(barEntity);

        beans = entityObjects.stream().map((Function<EntityObject, Object>) beanTransformer::convert).collect(Collectors.toList());
        Assert.assertEquals(2, beans.size());
        Assert.assertEquals(foo.getId(), ((Foo) beans.get(0)).getId());
        Assert.assertEquals(foo.getName(), ((Foo) beans.get(0)).getName());
        Assert.assertEquals(foo.getValue(), ((Foo) beans.get(0)).getValue());
        Assert.assertEquals(bar.getId(), ((Bar) beans.get(1)).getId());
        Assert.assertEquals(bar.getName(), ((Bar) beans.get(1)).getName());

    }

    @Before
    public void setUp() {
        foo = new FooImpl();
        foo.id = 1234;
        foo.name = "fooo";
        foo.value = 5.678;
        foo.flag = true;

        bar = new BarImpl();
        bar.id = 99;
        bar.name = "babar";
        bar.foo = foo;

        repository = AnnotationEntityDescriptorRepoFactory.init(EOMMetaModelRepository.get(),
                new Class[]{Foo.class, Bar.class}).createEntityDescriptorRepository();
    }

    @Test
    public void testEntityBeanConverter() {
        EntityObject fooEntity = EntityXmlReaderHelper.parse(repository.getEntityDescriptor("Foo"),
                "<Foo value=\"5.678\" booleanFlag=\"true\" name=\"fooo\" idFoo=\"1234\"/>").get(0);
        EntityObject barEntity = EntityXmlReaderHelper.parse(repository.getEntityDescriptor("Bar"),
                "<Bar foo=\"Foo[idFoo=1234,name=fooo]\" idBar=\"99\" name=\"babar\"/>").get(0);
        EOMContext context = EOMContextImpl.newInstance(repository);

        AnnotedEntityBindableEntityConverter converter = new AnnotedEntityBindableEntityConverter();
        converter.setEntityDescriptorRepository(repository);
        Assert
                .assertEquals(fooEntity.toXmlString(), converter.convert(context.getBeanConverter().convert(fooEntity))
                        .toXmlString());
        Assert
                .assertEquals(barEntity.toXmlString(), converter.convert(context.getBeanConverter().convert(barEntity))
                        .toXmlString());
    }

    class FooImpl implements Foo {
        Integer id;

        String name;

        Double value;

        boolean flag;

        Collection<Bar> bars;

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Double getValue() {
            return value;
        }

        public boolean isFlag() {
            return flag;
        }

    }

    class BarImpl implements Bar {
        Integer id;

        String name;

        Foo foo;

        public Foo getFoo() {
            return foo;
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

}
