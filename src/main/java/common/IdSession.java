package common;

import common.SocketModel;

public interface IdSession {

    static final String ID = "ID";

    void sendPacket(SocketModel packet);

    long getOwnerId();

    /**
     * 更新属性值
     * @param key
     * @param value
     * @return
     */
    Object setAttribute(String key, Object value);

    /**
     * 修改属性值
     * @param key
     * @return
     */
    Object getAttribute(String key);

}