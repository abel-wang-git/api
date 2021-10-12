package com.wanghuiwen.address.controller;


import com.wanghuiwen.address.model.AddressCities;
import com.wanghuiwen.address.model.AddressCountries;
import com.wanghuiwen.address.model.AddressDistrict;
import com.wanghuiwen.address.model.AddressStates;
import com.wanghuiwen.address.service.AddressCitiesService;
import com.wanghuiwen.address.service.AddressCountriesService;
import com.wanghuiwen.address.service.AddressDistrictService;
import com.wanghuiwen.address.service.AddressStatesService;
import com.wanghuiwen.address.vo.CityVo;
import com.wanghuiwen.address.vo.StateVo;
import com.wanghuiwen.core.controller.Ctrl;
import com.wanghuiwen.core.response.Result;
import io.swagger.annotations.Api;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Api(value = "地址模块", tags = {"地址模块"})
@RestController
public class AddressController  extends Ctrl {

    @Resource
    private AddressCountriesService addressCountriesService;
    @Resource
    private AddressCitiesService addressCitiesService;
    @Resource
    private AddressDistrictService addressDistrictService;
    @Resource
    private AddressStatesService addressStatesService;


    @GetMapping(value = "countries",name = "获取全部国家")
    public Result countries(String nativeName) {
        Map<String,Object> params = new HashMap<>();
        params.put("nativeName",nativeName);
        List<AddressCountries> countries = addressCountriesService.list(params);
        return resultGenerator.genSuccessResult(countries);
    }

    @GetMapping(value = "states",name = "获取当前语音环境州")
    public Result states(String countryCode,String name) {
        if(countryCode==null){
            Locale locale = LocaleContextHolder.getLocale();
            countryCode = locale.getCountry();
        }
        List<StateVo> states = addressStatesService.listByCountryCode(countryCode,name);
        return resultGenerator.genSuccessResult(states);
    }

    @GetMapping(value = "cities",name = "根据省份获取城市")
    public Result cities(Integer stateId,String name) {
        List<CityVo> cities = addressCitiesService.listByStateId(stateId,name);
        return resultGenerator.genSuccessResult(cities);
    }

    @GetMapping(value = "district",name = "根据城市获取行政区")
    public Result district(Integer cityId) {
        List<AddressDistrict> districts = addressDistrictService.listByCityId(cityId);
        return resultGenerator.genSuccessResult(districts);
    }

    @GetMapping(value = "states/all",name = "获取当前国家省/市/区数据")
    public Result district() {
        Locale locale = LocaleContextHolder.getLocale();
        List<StateVo> districts = addressStatesService.listAllByCountryCode(locale.getCountry());
        return resultGenerator.genSuccessResult(districts);
    }

    @PutMapping(value = "country/save",name = "保存国家")
    public Result saveCountry(@RequestBody AddressCountries countries) {
        addressCountriesService.saveOrUpdate(countries);
        return resultGenerator.genSuccessResult();
    }

    @PutMapping(value = "city/save",name = "保存城市")
    public Result saveCity(@RequestBody AddressCities cities) {
        addressCitiesService.saveOrUpdate(cities);
        return resultGenerator.genSuccessResult();
    }

    @PutMapping(value = "state/save",name = "保存省")
    public Result saveCity(@RequestBody AddressStates states) {
        addressStatesService.saveOrUpdate(states);
        return resultGenerator.genSuccessResult();
    }

    @PutMapping(value = "district/save",name = "保存行政区")
    public Result saveCity(@RequestBody AddressDistrict district) {
        addressDistrictService.saveOrUpdate(district);
        return resultGenerator.genSuccessResult();
    }
}
