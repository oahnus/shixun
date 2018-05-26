package top.oahnus.common.config;

import lombok.extern.slf4j.Slf4j;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

/**
 * Created by oahnus on 2017/10/17
 * 21:33.
 */
@Slf4j
public class EventLogger implements CacheEventListener<Object, Object>{

    @Override
    public void onEvent(CacheEvent<?, ?> event) {
        log.debug("Event: " + event.getType() + " Key: " + event.getKey() + " old value: " + event.getOldValue()
                + " new value: " + event.getNewValue());

    }

}
