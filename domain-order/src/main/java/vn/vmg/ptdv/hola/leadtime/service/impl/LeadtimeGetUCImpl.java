package vn.vmg.ptdv.hola.leadtime.service.impl;

import vn.vmg.ptdv.hola.district.factory.District;
import vn.vmg.ptdv.hola.district.factory.search.DistrictSearch;
import vn.vmg.ptdv.hola.district.presentation.DistrictListResponse;
import vn.vmg.ptdv.hola.district.repository.DistrictRepository;
import vn.vmg.ptdv.hola.exception.EntityNotFoundException;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeId;
import vn.vmg.ptdv.hola.leadtime.factory.Leadtime;
import vn.vmg.ptdv.hola.leadtime.factory.search.LeadtimeSearch;
import vn.vmg.ptdv.hola.leadtime.factory.search.PagingSortFilter;
import vn.vmg.ptdv.hola.leadtime.mapper.LeadtimeMapper;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeListResponse;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeResponse;
import vn.vmg.ptdv.hola.leadtime.repository.LeadtimeRepository;
import vn.vmg.ptdv.hola.leadtime.service.usecase.LeadtimeGetUC;
import vn.vmg.ptdv.hola.province.factory.Province;
import vn.vmg.ptdv.hola.province.factory.search.ProvinceSearch;
import vn.vmg.ptdv.hola.province.presentation.ProvinceListResponse;
import vn.vmg.ptdv.hola.province.repository.ProvinceRepository;

import java.util.List;

public class LeadtimeGetUCImpl implements LeadtimeGetUC {

    private final LeadtimeRepository leadtimeRepository;
    private final ProvinceRepository provinceRepository;
    private final DistrictRepository districtRepository;

    private PagingSortFilter pagingSortFilter;
    private List<Leadtime> leadtimes;
    private List<Province> provinces;
    private List<District> districts;
    private Leadtime leadtime;
    private Integer totalRecords;

    public LeadtimeGetUCImpl(LeadtimeRepository leadtimeRepository) {
        this.leadtimeRepository = leadtimeRepository;
        this.provinceRepository = null;
        this.districtRepository = null;
    }

    public LeadtimeGetUCImpl(ProvinceRepository provinceRepository) {
        this.leadtimeRepository = null;
        this.provinceRepository = provinceRepository;
        this.districtRepository = null;
    }

    public LeadtimeGetUCImpl(DistrictRepository districtRepository) {
        this.leadtimeRepository = null;
        this.provinceRepository = null;
        this.districtRepository = districtRepository;
    }

    public LeadtimeGetUCImpl(LeadtimeRepository leadtimeRepository, ProvinceRepository provinceRepository, DistrictRepository districtRepository) {
        this.leadtimeRepository = leadtimeRepository;
        this.provinceRepository = provinceRepository;
        this.districtRepository = districtRepository;
    }

    @Override
    public LeadtimeGetUC getAll(LeadtimeSearch leadtimeSearch, PagingSortFilter pagingSortFilter) {
        this.pagingSortFilter = pagingSortFilter;
        leadtimes = leadtimeRepository.findAll(leadtimeSearch, pagingSortFilter);
        totalRecords = leadtimeRepository.countTotal(leadtimeSearch);
        return this;
    }

    @Override
    public LeadtimeGetUC getProvinces(ProvinceSearch provinceSearch) {
        provinces = provinceRepository.findAll(provinceSearch);
        return this;
    }

    @Override
    public LeadtimeGetUC getDistricts(DistrictSearch districtSearch) {
        districts = districtRepository.findAll(districtSearch);
        return this;
    }

    @Override
    public LeadtimeGetUC getBySuggest(LeadtimeSearch leadtimeSearch, PagingSortFilter pagingSortFilter) {
        this.pagingSortFilter = pagingSortFilter;
        leadtimes = leadtimeRepository.findBySuggest(leadtimeSearch, pagingSortFilter);
        return this;
    }

    @Override
    public LeadtimeGetUC getById(LeadtimeId leadtimeId) {
        leadtime = leadtimeRepository.findById(leadtimeId.getId());
        return this;
    }

    @Override
    public LeadtimeGetUC leadtimeNotFound() throws EntityNotFoundException {
        if (leadtime == null) {
            throw new EntityNotFoundException(404, "Không tìm thấy leadtime");
        }
        return this;
    }

    @Override
    public LeadtimeListResponse endGetAll() {
        LeadtimeListResponse response = new LeadtimeListResponse();
        response.setLeadtimes(leadtimes);
        response.setPageIndex(pagingSortFilter.getPageIndex());
        response.setPageSize(pagingSortFilter.getPageSize());
        response.setTotalCount(totalRecords);
        return response;
    }

    @Override
    public ProvinceListResponse endGetProvinces() {
        ProvinceListResponse response = new ProvinceListResponse();
        response.setProvinces(provinces);
        response.setTotalCount(totalRecords);
        return response;
    }

    @Override
    public DistrictListResponse endGetDistricts() {
        DistrictListResponse response = new DistrictListResponse();
        response.setDistricts(districts);
        response.setTotalCount(totalRecords);
        return response;
    }

    @Override
    public LeadtimeListResponse endGetBySuggest() {
        LeadtimeListResponse response = new LeadtimeListResponse();
        response.setLeadtimes(leadtimes);
        return response;
    }

    @Override
    public LeadtimeResponse endGetById() {
        LeadtimeResponse response = LeadtimeMapper.getInstance().fromLeadtime(leadtime);
        return response;
    }

}
