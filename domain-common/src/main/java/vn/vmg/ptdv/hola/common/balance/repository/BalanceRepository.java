package vn.vmg.ptdv.hola.common.balance.repository;


import vn.vmg.ptdv.hola.common.balance.factory.Balance;

public interface BalanceRepository {
    void calculate(Balance balance);
}
