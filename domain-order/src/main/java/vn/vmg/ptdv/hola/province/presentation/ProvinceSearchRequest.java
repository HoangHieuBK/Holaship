package vn.vmg.ptdv.hola.province.presentation;

import lombok.Data;
import vn.vmg.ptdv.hola.leadtime.factory.search.PagingSortFilter;
import vn.vmg.ptdv.hola.province.factory.search.ProvinceSearch;


@Data
public class ProvinceSearchRequest {

    private ProvinceSearch provinceSearch;
    private PagingSortFilter pagingSortFilter;

    public ProvinceSearchRequest() {

    }

    public ProvinceSearchRequest(ProvinceSearch provinceSearch) {
        this.provinceSearch = provinceSearch;
    }

}
