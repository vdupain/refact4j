package org.refact4j.eom.model;

import org.refact4j.eom.EOMContext;
import org.refact4j.eom.impl.DefaultMetaModelVisitor;
import org.refact4j.eom.impl.EOMContextImpl;
import org.refact4j.eom.model.impl.EntityDescriptorImpl;
import org.refact4j.util.StringHelper;
import org.refact4j.visitor.Visitable;
import org.refact4j.visitor.Visitor;
import org.refact4j.xml.ToXmlString;

import java.util.HashMap;

public class EntityDescriptorRepository extends HashMap<String, EntityDescriptor> implements Visitable, ToXmlString {

    private final EOMContext context;

    public EntityDescriptorRepository() {
        this.context = EOMContextImpl.newInstance(this);
    }

    public EntityDescriptor put(String key, EntityDescriptor object) {
        ((EntityDescriptorImpl) object).setContext(context);
        return super.put(key, object);
    }

    public EntityDescriptor getEntityDescriptor(String name) {
        return this.get(name);
    }

    public void accept(Visitor visitor) {
        if (visitor instanceof EntityDescriptorRepositoryVisitor) {
            ((EntityDescriptorRepositoryVisitor) visitor).visitEntityDescriptorRepository(this);
        }
    }

    public String toXmlString() {
        DefaultMetaModelVisitor visitor = new DefaultMetaModelVisitor();
        this.accept(visitor);
        String xml = visitor.toXmlString();
        return xml.replaceAll("[ ]+<", "<").replaceAll(StringHelper.LINE_SEPARATOR, "").replaceAll("><",
                ">" + StringHelper.LINE_SEPARATOR + "<").replace('\'', '"');
    }

    public EOMContext getContext() {
        return this.context;
    }


    interface EntityDescriptorRepositoryVisitor extends Visitor {
        void visitEntityDescriptorRepository(EntityDescriptorRepository entityDescriptorRepository);
    }

}
