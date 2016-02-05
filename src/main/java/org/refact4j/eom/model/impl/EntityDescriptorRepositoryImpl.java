package org.refact4j.eom.model.impl;

import org.refact4j.eom.EOMContext;
import org.refact4j.eom.impl.DefaultMetaModelVisitor;
import org.refact4j.eom.impl.EOMContextImpl;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.EntityDescriptorRepository;
import org.refact4j.util.RepositoryImpl;
import org.refact4j.util.StringHelper;
import org.refact4j.visitor.Visitor;

public class EntityDescriptorRepositoryImpl extends RepositoryImpl<String, EntityDescriptor> implements
        EntityDescriptorRepository {

    private final EOMContext context;

    public EntityDescriptorRepositoryImpl() {
        this.context = EOMContextImpl.newInstance(this);
    }

    public EntityDescriptor put(String key, EntityDescriptor object) {
        ((EntityDescriptorImpl) object).setContext(context);
        return super.put(key, object);
    }

    public EntityDescriptor getEntityDescriptor(String name) {
        try {
            return this.get(name);
        } catch (Exception e) {
            throw new RuntimeException("Missing EntityDescriptor '" + name + "'");
        }
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

}
