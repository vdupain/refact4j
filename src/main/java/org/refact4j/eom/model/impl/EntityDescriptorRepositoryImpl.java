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
        setKeyifier(new Keyifier<String, EntityDescriptor>() {

            public String keyify(EntityDescriptor object) {
                return object.getName();
            }

        });
        this.context = EOMContextImpl.newInstance(this);
    }

    @Override
    public void add(String key, EntityDescriptor object) {
        super.add(key, object);
        ((EntityDescriptorImpl) object).setContext(context);
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
