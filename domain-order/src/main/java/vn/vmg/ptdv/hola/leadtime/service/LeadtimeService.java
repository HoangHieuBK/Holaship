package vn.vmg.ptdv.hola.leadtime.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import vn.vmg.ptdv.hola.district.presentation.DistrictListResponse;
import vn.vmg.ptdv.hola.district.presentation.DistrictSearchRequest;
import vn.vmg.ptdv.hola.district.repository.DistrictRepository;
import vn.vmg.ptdv.hola.exception.EntityInsertException;
import vn.vmg.ptdv.hola.exception.EntityNotFoundException;
import vn.vmg.ptdv.hola.exception.EntityUpdateException;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeListResponse;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeRequest;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeResponse;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeSearchRequest;
import vn.vmg.ptdv.hola.leadtime.repository.LeadtimeRepository;
import vn.vmg.ptdv.hola.leadtime.repository.LeadtimeRouteRepository;
import vn.vmg.ptdv.hola.leadtime.service.impl.LeadtimeCreateUCImpl;
import vn.vmg.ptdv.hola.leadtime.service.impl.LeadtimeGetUCImpl;
import vn.vmg.ptdv.hola.leadtime.service.impl.LeadtimeUpdateUCImpl;
import vn.vmg.ptdv.hola.leadtime.service.usecase.LeadtimeCreateUC;
import vn.vmg.ptdv.hola.leadtime.service.usecase.LeadtimeGetUC;
import vn.vmg.ptdv.hola.leadtime.service.usecase.LeadtimeUpdateUC;
import vn.vmg.ptdv.hola.province.presentation.ProvinceListResponse;
import vn.vmg.ptdv.hola.province.presentation.ProvinceSearchRequest;
import vn.vmg.ptdv.hola.province.repository.ProvinceRepository;

public class LeadtimeService {

    private final LeadtimeRepository leadtimeRepository;
    private final LeadtimeRouteRepository leadtimeRouteRepository;
    private final ProvinceRepository provinceRepository;
    private final DistrictRepository districtRepository;

    public LeadtimeService(LeadtimeRepository leadtimeRepository, LeadtimeRouteRepository leadtimeRouteRepository,
                           ProvinceRepository provinceRepository, DistrictRepository districtRepository) {
        this.leadtimeRepository = leadtimeRepository;
        this.leadtimeRouteRepository = leadtimeRouteRepository;
        this.provinceRepository = provinceRepository;
        this.districtRepository = districtRepository;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public LeadtimeListResponse getAll(LeadtimeSearchRequest request) {
        LeadtimeGetUC useCase = new LeadtimeGetUCImpl(leadtimeRepository);
        LeadtimeListResponse response = useCase
                .getAll(request.getLeadtimeSearch(), request.getPagingSortFilter())
                .endGetAll();
        return response;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ProvinceListResponse getProvinces(ProvinceSearchRequest request) {
        LeadtimeGetUC useCase = new LeadtimeGetUCImpl(provinceRepository);
        ProvinceListResponse response = useCase
                .getProvinces(request.getProvinceSearch())
                .endGetProvinces();
        return response;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public DistrictListResponse getDistricts(DistrictSearchRequest request) {
        LeadtimeGetUC useCase = new LeadtimeGetUCImpl(districtRepository);
        DistrictListResponse response = useCase
                .getDistricts(request.getDistrictSearch())
                .endGetDistricts();
        return response;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public LeadtimeResponse getById(LeadtimeSearchRequest request) throws EntityNotFoundException {
        LeadtimeGetUC useCase = new LeadtimeGetUCImpl(leadtimeRepository);
        LeadtimeResponse leadtimeResponse = useCase
                .getById(request.getLeadtimeSearch().getLeadtimeId())
                .leadtimeNotFound()
                .endGetById();
        return leadtimeResponse;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public LeadtimeResponse create(LeadtimeRequest request) throws EntityInsertException {
        LeadtimeCreateUC useCase = new LeadtimeCreateUCImpl(leadtimeRepository);
        LeadtimeResponse response = useCase
                .create(request.getLeadTimeId(), request.getName(),
                        request.getStatus(), request.getNote(), request.getAuditLog(), request.getEffectiveAt())
                .fail()
                .end(request.getLeadTimeId(), request.getName(),
                        request.getStatus(), request.getNote(), request.getAuditLog(), request.getEffectiveAt());
        return response;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public LeadtimeResponse update(LeadtimeRequest request) throws EntityUpdateException {
        LeadtimeUpdateUC useCase = new LeadtimeUpdateUCImpl(leadtimeRepository);
        LeadtimeResponse response = useCase
                .update(request.getLeadTimeId(), request.getName(),
                        request.getStatus(), request.getNote(), request.getAuditLog(), request.getEffectiveAt())
                .fail()
                .endUpdate(request.getLeadTimeId(), request.getName(), request.getStatus(),
                        request.getNote(), request.getAuditLog(), request.getEffectiveAt());
        return response;
    }

    public LeadtimeListResponse getBySuggest(LeadtimeSearchRequest request) {
        LeadtimeGetUC useCase = new LeadtimeGetUCImpl(leadtimeRepository);
        LeadtimeListResponse response = useCase
                .getBySuggest(request.getLeadtimeSearch(), request.getPagingSortFilter())
                .endGetBySuggest();
        return response;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public LeadtimeResponse changeStatus(LeadtimeRequest request) throws EntityUpdateException {
        LeadtimeUpdateUC useCase = new LeadtimeUpdateUCImpl(leadtimeRepository);
        LeadtimeResponse response = useCase
                .changeStatus(request.getLeadTimeId(), request.getStatus(), request.getAuditLog())
                .fail()
                .endChangeStatus(request.getLeadTimeId(), request.getStatus(), request.getAuditLog());
        return response;
    }

}
