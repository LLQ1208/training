package com.acsm.training.manage;/**
 * Created by lq on 2018/2/26.
 */

import com.acsm.training.model.TecentAreaInfo;
import com.acsm.training.service.TecentAreaInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author lianglinqiang
 * @create 2018-02-26
 */
@Component
public class AreaManage {
    private static final Logger log = LoggerFactory.getLogger(AreaManage.class);

    @Autowired
    private static TecentAreaInfoService tecentAreaInfoService;

    public static List<TecentAreaInfo> proviceList;
    public static Map<Integer,List<TecentAreaInfo>> cityMap;
    public static Map<Integer,List<TecentAreaInfo>> areaMap;

    void init(){
        long startTime = System.currentTimeMillis()/1000;
        //初始化省级列表
        getProviceList();

        //初始化市
        if(cityMap == null || cityMap.isEmpty()){
            cityMap = new HashMap<>();
            for(TecentAreaInfo provice : proviceList){
                List<TecentAreaInfo> cityList = tecentAreaInfoService.queryCityList(provice.getAreaId());
                cityMap.put(provice.getAreaId(),cityList);
                if(areaMap == null || areaMap.isEmpty()){
                    areaMap = new HashMap<>();
                    for(TecentAreaInfo city : cityList){
                        List<TecentAreaInfo> areaList = tecentAreaInfoService.queryAreaList(city.getAreaId());
                        areaMap.put(city.getAreaId(),areaList);
                    }
                }
            }
        }

        if(areaMap == null || areaMap.isEmpty()){
            areaMap = new HashMap<>();
            for(TecentAreaInfo provice : proviceList){
                for(TecentAreaInfo city : cityMap.get(provice.getAreaId())){
                    List<TecentAreaInfo> areaList = tecentAreaInfoService.queryAreaList(city.getAreaId());
                    areaMap.put(city.getAreaId(),areaList);
                }
            }
        }
        System.out.print("AreaManage use time ----" + (System.currentTimeMillis()/1000 - startTime));
        log.info("AreaManage use time ----" + (System.currentTimeMillis()/1000 - startTime));
    }


    public static List<TecentAreaInfo> getProviceList(){
        if(proviceList == null || proviceList.isEmpty()){
            proviceList = tecentAreaInfoService.queryProvinceList();
            return proviceList;
        }else{
            return proviceList;
        }
    }

    public static Map<Integer,List<TecentAreaInfo>> getCityMap(){
        if(cityMap == null || cityMap.isEmpty()){
            cityMap = new HashMap<>();
            for(TecentAreaInfo provice : getProviceList()){
                List<TecentAreaInfo> cityList = tecentAreaInfoService.queryCityList(provice.getAreaId());
                cityMap.put(provice.getAreaId(),cityList);
            }
        }
        return cityMap;
    }

    public static Map<Integer,List<TecentAreaInfo>> getAreaMap(){
        if(areaMap == null || areaMap.isEmpty()){
            areaMap = new HashMap<>();
            for(TecentAreaInfo provice : getProviceList()){
                for(TecentAreaInfo city : getCityMap().get(provice.getAreaId())){
                    List<TecentAreaInfo> areaList = tecentAreaInfoService.queryAreaList(city.getAreaId());
                    areaMap.put(city.getAreaId(),areaList);
                }
            }
        }
        return areaMap;
    }

    public static TecentAreaInfoService getTecentAreaInfoService() {
        return tecentAreaInfoService;
    }

    public static void setTecentAreaInfoService(TecentAreaInfoService tecentAreaInfoService) {
        AreaManage.tecentAreaInfoService = tecentAreaInfoService;
    }
}
