package com.huak.home.cost;

import com.huak.home.dao.cost.CostDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/27 0027.
 */
@Service
public class CostServiceImpl implements CostService{

    @Autowired
    private CostDao costDao;

    @Override
    @Transactional(readOnly = true)
    public String SourceEnergyCost(Map<String, Object> params) {

        return this.costDao.SourceEnergyCost(params);
    }

    @Override
    @Transactional(readOnly = true)
    public  List<Map<String, Object>> SourceDevicelCost(Map<String, Object> params) {
        System.out.println(".............");
        return this.costDao.SourceDevicelCost(params);
    }

    @Override
    public List<Map<String, Object>> SourceManagerCost(Map<String, Object> params) {

        return this.costDao.SourceManagerCost(params);
    }

    @Override
    public List<Map<String, Object>> SourceOtherCost(Map<String, Object> params) {
        return this.costDao.SourceOtherCost(params);
    }

    @Override
    public List<Map<String, Object>> SourceArtificialCost(Map<String, Object> params) {
        return this.costDao.SourceArtificialCost(params);
    }

    @Override
    public List<Map<String, Object>> NetworkOtherCost(Map<String, Object> params) {
        return this.costDao.NetworkOtherCost(params);
    }

    @Override
    public List<Map<String, Object>> NetworkManagerCost(Map<String, Object> params) {
        return this.costDao.NetworkManagerCost(params);
    }

    @Override
    public List<Map<String, Object>> NetworkArtificialCost(Map<String, Object> params) {
        return this.costDao.NetworkArtificialCost(params);
    }

    @Override
    public List<Map<String, Object>> NetworkDevicelCost(Map<String, Object> params) {
        return this.costDao.NetworkDevicelCost(params);
    }

    @Override
    public List<Map<String, Object>> StationOtherCost(Map<String, Object> params) {
        return this.costDao.StationOtherCost(params);
    }

    @Override
    public List<Map<String, Object>> StationManagerCost(Map<String, Object> params) {
        return this.costDao.StationManagerCost(params);
    }

    @Override
    public List<Map<String, Object>> StationArtificialCost(Map<String, Object> params) {
        return this.costDao.StationArtificialCost(params);
    }

    @Override
    public List<Map<String, Object>> StationDevicelCost(Map<String, Object> params) {
        return this.costDao.StationDevicelCost(params);
    }

    @Override
    public List<Map<String, Object>> LineOtherCost(Map<String, Object> params) {
        return this.costDao.LineOtherCost(params);
    }

    @Override
    public List<Map<String, Object>> LineManagerCost(Map<String, Object> params) {
        return this.costDao.LineManagerCost(params);
    }

    @Override
    public List<Map<String, Object>> LineArtificialCost(Map<String, Object> params) {
        return this.costDao.LineArtificialCost(params);
    }

    @Override
    public List<Map<String, Object>> LineDevicelCost(Map<String, Object> params) {
        return this.costDao.LineDevicelCost(params);
    }

    @Override
    public List<Map<String, Object>> RoomOtherCost(Map<String, Object> params) {
        return this.costDao.RoomOtherCost(params);
    }

    @Override
    public List<Map<String, Object>> RoomManagerCost(Map<String, Object> params) {
        return this.costDao.RoomManagerCost(params);
    }

    @Override
    public List<Map<String, Object>> RoomArtificialCost(Map<String, Object> params) {
        return this.costDao.RoomArtificialCost(params);
    }

    @Override
    public List<Map<String, Object>> RoomDevicelCost(Map<String, Object> params) {
        return this.costDao.RoomDevicelCost(params);
    }




    ////

//    @Override
//    public String SourceEnergyCostLastyear(Map<String, Object> params) {
//
//        return this.costDao.SourceEnergyCostLastyear(params);
//    }

    @Override
    public  List<Map<String, Object>> SourceDevicelCostLastyear(Map<String, Object> params) {
        System.out.println(".............");
        return this.costDao.SourceDevicelCostLastyear(params);
    }

    @Override
    public List<Map<String, Object>> SourceManagerCostLastyear(Map<String, Object> params) {

        return this.costDao.SourceManagerCostLastyear(params);
    }

    @Override
    public List<Map<String, Object>> SourceOtherCostLastyear(Map<String, Object> params) {
        return this.costDao.SourceOtherCostLastyear(params);
    }

    @Override
    public List<Map<String, Object>> SourceArtificialCostLastyear(Map<String, Object> params) {
        return this.costDao.SourceArtificialCostLastyear(params);
    }

    @Override
    public List<Map<String, Object>> NetworkOtherCostLastyear(Map<String, Object> params) {
        return this.costDao.NetworkOtherCostLastyear(params);
    }

    @Override
    public List<Map<String, Object>> NetworkManagerCostLastyear(Map<String, Object> params) {
        return this.costDao.NetworkManagerCostLastyear(params);
    }

    @Override
    public List<Map<String, Object>> NetworkArtificialCostLastyear(Map<String, Object> params) {
        return this.costDao.NetworkArtificialCostLastyear(params);
    }

    @Override
    public List<Map<String, Object>> NetworkDevicelCostLastyear(Map<String, Object> params) {
        return this.costDao.NetworkDevicelCostLastyear(params);
    }

    @Override
    public List<Map<String, Object>> StationOtherCostLastyear(Map<String, Object> params) {
        return this.costDao.StationOtherCostLastyear(params);
    }

    @Override
    public List<Map<String, Object>> StationManagerCostLastyear(Map<String, Object> params) {
        return this.costDao.StationManagerCostLastyear(params);
    }

    @Override
    public List<Map<String, Object>> StationArtificialCostLastyear(Map<String, Object> params) {
        return this.costDao.StationArtificialCostLastyear(params);
    }

    @Override
    public List<Map<String, Object>> StationDevicelCostLastyear(Map<String, Object> params) {
        return this.costDao.StationDevicelCostLastyear(params);
    }

    @Override
    public List<Map<String, Object>> LineOtherCostLastyear(Map<String, Object> params) {
        return this.costDao.LineOtherCostLastyear(params);
    }

    @Override
    public List<Map<String, Object>> LineManagerCostLastyear(Map<String, Object> params) {
        return this.costDao.LineManagerCostLastyear(params);
    }

    @Override
    public List<Map<String, Object>> LineArtificialCostLastyear(Map<String, Object> params) {
        return this.costDao.LineArtificialCostLastyear(params);
    }

    @Override
    public List<Map<String, Object>> LineDevicelCostLastyear(Map<String, Object> params) {
        return this.costDao.LineDevicelCostLastyear(params);
    }

    @Override
    public List<Map<String, Object>> RoomOtherCostLastyear(Map<String, Object> params) {
        return this.costDao.RoomOtherCostLastyear(params);
    }

    @Override
    public List<Map<String, Object>> RoomManagerCostLastyear(Map<String, Object> params) {
        return this.costDao.RoomManagerCostLastyear(params);
    }

    @Override
    public List<Map<String, Object>> RoomArtificialCostLastyear(Map<String, Object> params) {
        return this.costDao.RoomArtificialCostLastyear(params);
    }

    @Override
    public List<Map<String, Object>> RoomDevicelCostLastyear(Map<String, Object> params) {
        return this.costDao.RoomDevicelCostLastyear(params);
    }





}
