package vn.vmg.ptdv.hola.shared;

public enum OrderDomainState {
    ACTIVE, DEACTIVATE;

    public static OrderDomainState fromBoolean(Boolean effectiveStatus) {
        return effectiveStatus ? ACTIVE : DEACTIVATE;
    }

    public Boolean toBoolean() {
        return ACTIVE.name().equals(this.name());
    }
}
