package com.lfx.demo.decorator.generic;

/**
 * @author <a href="mailto:idler41@163.con">linfuxin</a> created on 2022-11-21 15:49:52
 */
public abstract class BaseDomainDecorator<T extends BaseDomain, V, Q> implements BaseConvert<T, V, Q> {

    private final BaseConvert<T, V, Q> delegate;

    public BaseDomainDecorator(BaseConvert<T, V, Q> baseConvert) {
        this.delegate = baseConvert;
    }

    @Override
    public T voToEntity(V v) {
        T t = delegate.voToEntity(v);
        t.setUpdateBy(null);
        t.setUpdateTime(null);
        return t;
    }

    @Override
    public void voToEntityOverride(V v, T t) {
        delegate.voToEntityOverride(v, t);
        t.setUpdateBy(null);
        t.setUpdateTime(null);
    }

    @Override
    public T voToEntityIgnoreId(V v) {
        T t = delegate.voToEntityIgnoreId(v);
        t.setCreateBy(null);
        t.setCreateTime(null);
        return t;
    }
}
