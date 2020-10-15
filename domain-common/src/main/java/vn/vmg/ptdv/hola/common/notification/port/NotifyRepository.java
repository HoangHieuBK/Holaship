package vn.vmg.ptdv.hola.common.notification.port;


import vn.vmg.ptdv.hola.common.notification.factory.Notify;

public interface NotifyRepository {
    void send(Notify notify);
}
