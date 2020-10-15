package vn.vmg.ptdv.hola.infra.jpa.servicepack.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import vn.vmg.ptdv.hola.infra.jpa.servicepack.entity.EServicePack;

@Repository
public interface ServicePackJPARepository extends PagingAndSortingRepository<EServicePack, Long> {
//    EServicePack findByIdAndUTimestamp(Long id, Timestamp uTimestamp);
//
//    EServicePack findByCode(String code);
}
