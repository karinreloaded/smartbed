package at.fhv.uct.adaptaal.smartbed.util;

import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.LazyInitializer;

import java.io.Serializable;

public final class PersistenceUtil {

    private PersistenceUtil() {
    }

    /**
     * Returns id of the entity-proxy object without initializing it.
     *
     * @param proxy entity-proxy of type type <T> object id of which will be returned.
     * @return {@code Serializable} id of the proxy object.
     */
    public static <T> Serializable getProxyId(T proxy) {
        if (proxy instanceof HibernateProxy) {
            LazyInitializer lazyInitializer = ((HibernateProxy) proxy).getHibernateLazyInitializer();
            if (lazyInitializer.isUninitialized()) {
                return lazyInitializer.getIdentifier();
            }
        }
        return null;
    }
}
