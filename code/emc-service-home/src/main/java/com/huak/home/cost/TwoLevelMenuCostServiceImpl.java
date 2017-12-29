package com.huak.home.cost;

import com.huak.common.utils.DateUtils;
import com.huak.home.dao.cost.TwoLevelMenuCostDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/11/30 0030.
 */
@Service
public class TwoLevelMenuCostServiceImpl implements TwoLevelMenuCostService {
    @Autowired
    private TwoLevelMenuCostDao twoLevelMenuCostDao;


    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> groubCostAn(Map<String, Object> params) {
        Map<String,Object> result = new HashMap<String,Object>();
        List<String> last = new ArrayList<String>();
        List<String> cur = new ArrayList<String>();
        List<Map<String, Object>> efbListMap = twoLevelMenuCostDao.groubCostAn(params);
        for(int i=1;i<=5;i++){
            if(null!=efbListMap&&efbListMap.size()>0){
                for(Map<String, Object> map:efbListMap){
                    if((i+"").equals(map.get("unittype")+"")){
                        last.add(map.get("last")+"");
                        cur.add(map.get("cur")+"");
                    }
                }
            }else{
                last.add("0");
                cur.add("0");
            }
        }
        result.put("last", last);
        result.put("cur", cur);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> TwoEnergyCostTrend(Map<String, Object> params) throws Exception{
//        Map<String,Object> result = new HashMap<String,Object>();
//        String sDate = (String) params.get("startTime");//查询条件的开始时间
//        String eDate = (String) params.get("endTime");//查询条件的结束时间
//        sDate = (null==sDate||"".equals(sDate))? DateUtils.getYearDate(null, Calendar.DATE, -5):sDate.substring(0,10);//如果查询条件的开始时间为空，设置默认的开始时间
//        eDate = (null==eDate||"".equals(eDate))?DateUtils.getYearDate(null,Calendar.DATE, 0):eDate.substring(0,10);//如果查询条件的结束时间为空，设置默认的结束时间
//        //查询时间list
//        List<String> yearList = new ArrayList<String>();
//        //无查询结果时，设为默认值0
//        List<String> defaultValue = new ArrayList<String>();
//        while(!sDate.equals(eDate)){
//            yearList.add(sDate);
//            sDate = DateUtils.getYearDate(sDate,Calendar.DATE,1);
//            defaultValue.add("0");
//        }
//        yearList.add(eDate);
//        defaultValue.add("0");
//        //处理查询结果，为折线图做准备
//        List<Map<String, Object>> eflListMap = twoLevelMenuCostDao.TwoEnergyCostTrend(params);
//        String[] unitType = null;
//        int toolorgtype = Integer.valueOf("".equals((params.get("orgType")+""))?"-1":(params.get("orgType")+""));
//        switch(toolorgtype){
//            case 1:unitType = new String[]{"供热源"};break;
//            case 2:unitType = new String[]{"一次网"};break;
//            case 3:unitType = new String[]{"换热站"};break;
//            case 4:unitType = new String[]{"二次线"};break;
//            case 5:unitType = new String[]{"民户"};break;
//            default:unitType = new String[]{"供热源","一次网","换热站","二次线","民户"};
//        }
//        List<Map<String,Object>> series = new ArrayList<Map<String,Object>>();
//        for(String type:unitType){
//            Map<String, Object> serie = new HashMap<String,Object>();
//            serie.put("name",type);
//            serie.put("type","line");
//            serie.put("symbol","circle");
//            serie.put("smooth",false);
//            List<String> lineValue = new ArrayList<String>();
//            if(null!=eflListMap&&eflListMap.size()>0){
//                for(String d:yearList){
//                    boolean isHas = false;
//                    for(Map<String, Object> map:eflListMap){
//                        if(type.equals(map.get("unittype")+"")&&d.equals(map.get("yeardate")+"")){
//                            lineValue.add(map.get("total")+"");
//                            isHas = true;
//                        }
//                    }
//                    if(!isHas){
//                        lineValue.add("0");
//                    }
//                }
//                serie.put("data",lineValue);
//            }else{
//                serie.put("data",defaultValue);
//            }
//            series.add(serie);
//        }
//        result.put("xDate", yearList);
//        result.put("series", series);
//        result.put("legends", unitType);
//        return result;
        return this.twoLevelMenuCostDao.TwoEnergyCostTrend(params);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> BranchEnergyCons(Map<String, Object> params) {
       List<Map<String, Object>> subOrg=this.twoLevelMenuCostDao.subOrg(params);
        List<Map<String, Object>> groupEnergyCost=this.twoLevelMenuCostDao.BranchCostProportionCostEnergy(params);
////        List<Map<String, Object>> groupOtheCost=this.twoLevelMenuCostDao.BranchCostProportionCost(params);
//        //如果单位成本跟组织id相等，就把他放在totalcostList里面
//        List<Map<String, Object>> totalcostList=new ArrayList<>();
//        //计算总成本费
//        //将能源费和其他 的费用整合在一起去
//        Map groupMap=null;
//        Double totalcost=0.0;
//
//        for (Map<String, Object>  groupEnergy: groupEnergyCost) {
//            groupMap=new HashMap();
//            for (Map<String, Object> groupOther:groupOtheCost) {
//                String groupEnergyId= groupEnergy.get("id").toString();
//                String groupOtherId= groupOther.get("id").toString();
//                if(groupEnergyId.equals(groupOtherId)){
//                    String subCostenergyTotal= groupEnergy.get("totalCost").toString()==null?"0.0":groupEnergy.get("totalCost").toString();
//                    String subCostenergyTqTotal= groupOther.get("totalCost").toString()==null?"0.0":groupOther.get("totalCost").toString();
//                    groupMap=new HashMap();
//                    totalcost=Double.valueOf(subCostenergyTotal.toString())+Double.valueOf(subCostenergyTqTotal.toString());
//                    groupMap.put("ID",groupEnergyId);
//                    groupMap.put("NAME",groupOther.get("orgname"));
//                    groupMap.put("VALUE",totalcost);
//                    break;
//                }
//            }
//            totalcostList.add(groupMap);
//        }
        return groupEnergyCost;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> BranchEnergyConsqn(Map<String, Object> params) {

        List<Map<String, Object>> subOrg=this.twoLevelMenuCostDao.subOrg(params);
        List<Map<String, Object>> groupEnergyCost=this.twoLevelMenuCostDao.BranchCostProportionCostEnergyTq(params);
        List<Map<String, Object>> groupOtheCost=this.twoLevelMenuCostDao.BranchCostProportionCostTq(params);
        //如果单位成本跟组织id相等，就把他放在totalcostList里面
        List<Map<String, Object>> totalcostList=new ArrayList<>();
        //计算总成本费
        //将能源费和其他 的费用整合在一起去
        Map groupMap=null;
        Double totalcost=0.0;
        String id=null;
        String orgname=null;
        if(groupEnergyCost.size()==0 && groupOtheCost.size()!=0){
            for (Map<String, Object> groupOthe:groupOtheCost) {
                groupMap=new HashMap();
                groupMap.put("id",groupOthe.get("id").toString());
                groupMap.put("name",groupOthe.get("name"));
                groupMap.put("value",groupOthe.get("value").toString()==null?"0.0":groupOthe.get("totalCost").toString());
                totalcostList.add(groupMap);
            }

        }else if(groupOtheCost.size()==0&&groupEnergyCost.size()!=0){
            for (Map<String, Object> groupEnergy:groupEnergyCost) {
                if(null==groupEnergy.get("totalCost")){
                    totalcost=0.0;
                }
                if(null==groupEnergy.get("id").toString()){
                    id=null;
                }
                if(null==groupEnergy.get("orgname").toString()){
                    orgname=null;
                }
                if(null!=groupEnergy.get("id").toString()){
                    id=groupEnergy.get("id").toString();
                }
                if(null!=groupEnergy.get("name").toString()){
                    orgname=groupEnergy.get("name").toString();
                }
                groupMap.put("id",id);
                groupMap.put("name",orgname);
                groupMap.put("value",totalcost);
                totalcostList.add(groupMap);
            }
        }else if (groupOtheCost.size()==0&&groupEnergyCost.size()==0){
            totalcostList=null;
        }else {
            for (Map<String, Object> groupEnergy : groupEnergyCost) {
                groupMap = new HashMap();
                for (Map<String, Object> groupOther : groupOtheCost) {
                    String groupEnergyId = groupEnergy.get("id").toString();
                    String groupOtherId = groupOther.get("id").toString();
                    if (groupEnergyId.equals(groupOtherId)) {
                        String subCostenergyTotal = groupEnergy.get("value").toString() == null ? "0.0" : groupEnergy.get("totalCost").toString();
                        String subCostenergyTqTotal = groupOther.get("value").toString() == null ? "0.0" : groupOther.get("totalCost").toString();
                        groupMap = new HashMap();
                        totalcost = Double.valueOf(subCostenergyTotal.toString()) + Double.valueOf(subCostenergyTqTotal.toString());
                        groupMap.put("id", groupEnergyId);
                        groupMap.put("name", groupOther.get("orgname"));
                        groupMap.put("value", totalcost);
                        break;
                    }
                }
                totalcostList.add(groupMap);
            }
        }
        return totalcostList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> TwoEnergyFlowPie(Map<String, Object> params) {
        return this.twoLevelMenuCostDao.TwoEnergyFlowPie(params);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> TwoEnergyFlowPieAn(Map<String, Object> params) {
        return this.twoLevelMenuCostDao.TwoEnergyFlowPieAn(params);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> TwoEnergyCost(Map<String, Object> params) {
        return this.twoLevelMenuCostDao.TwoEnergyCost(params);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> TwoEnergyCostLastYear(Map<String, Object> params) {
        return this.twoLevelMenuCostDao.TwoEnergyCostLastYear(params);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String,Object>> TwoDeviceCost(Map<String, Object> params) {
        return this.twoLevelMenuCostDao.TwoDeviceCost(params);
    }

    @Override
    @Transactional(readOnly = true)
    public    List<Map<String,Object>>  TwoDeviceCostLastYear(Map<String, Object> params) {
        return this.twoLevelMenuCostDao.TwoDeviceCostLastYear(params);
    }

    @Override
    @Transactional(readOnly = true)
    public    List<Map<String,Object>>  TwoManagerCost(Map<String, Object> params) {
        return this.twoLevelMenuCostDao.TwoManagerCost(params);
    }

    @Override
    @Transactional(readOnly = true)
    public    List<Map<String,Object>>  TwoManagerCostLastYear(Map<String, Object> params) {
        return this.twoLevelMenuCostDao.TwoManagerCostLastYear(params);
    }

    @Override
    @Transactional(readOnly = true)
    public    List<Map<String,Object>>  TwoOtherCost(Map<String, Object> params) {
        return this.twoLevelMenuCostDao.TwoOtherCost(params);
    }

    @Override
    @Transactional(readOnly = true)
    public    List<Map<String,Object>>  TwoOtherCostLastYear(Map<String, Object> params) {
        return this.twoLevelMenuCostDao.TwoOtherCostLastYear(params);
    }


    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> BranchCostTrend(Map<String, Object> params) {

        return this.twoLevelMenuCostDao.BranchCostTrend(params
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> BranchCostProportion(Map<String, Object> params) {
        List<Map<String, Object>> subOrg=this.twoLevelMenuCostDao.subOrg(params);
        List<Map<String, Object>> groupEnergyCost=this.twoLevelMenuCostDao.BranchCostProportionCostEnergy(params);
//        List<Map<String, Object>> groupOtheCost=this.twoLevelMenuCostDao.BranchCostProportionCost(params);
//        //如果单位成本跟组织id相等，就把他放在totalcostList里面
//        List<Map<String, Object>> totalcostList=new ArrayList<>();
//        //计算总成本费
//        //将能源费和其他 的费用整合在一起去
//       Map groupMap=null;
//       Double totalcost=0.0;
//
//           for (Map<String, Object>  groupEnergy: groupEnergyCost) {
//               groupMap=new HashMap();
//               for (Map<String, Object> groupOther:groupOtheCost) {
//                   String groupEnergyId= groupEnergy.get("id").toString();
//                   String groupOtherId= groupOther.get("id").toString();
//                   if(groupEnergyId.equals(groupOtherId)){
//                       String subCostenergyTotal= groupEnergy.get("value").toString()==null?"0.0":groupEnergy.get("totalCost").toString();
//                       String subCostenergyTqTotal= groupOther.get("value").toString()==null?"0.0":groupOther.get("totalCost").toString();
//                       groupMap=new HashMap();
//                       totalcost=Double.valueOf(subCostenergyTotal.toString())+Double.valueOf(subCostenergyTqTotal.toString());
//                       groupMap.put("id",groupEnergyId);
//                       groupMap.put("name",groupOther.get("name"));
//                       groupMap.put("value",totalcost);
//                       break;
//                   }
//               }
//               totalcostList.add(groupMap);
//           }
           return groupEnergyCost;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> selectBmTqLine(Map<String, Object> params) {
//        List<Map<String, Object>> groupEnergyCost=this.twoLevelMenuCostDao.groupEnergyCostLastYear(params);
//        List<Map<String, Object>> groupOtheCost=this.twoLevelMenuCostDao.groupCostLastYear(params);
//        List<Map<String, Object>> totalcostList=new ArrayList<>();
//
//        Double totalcost=0.0;
//        for (Map<String, Object>groupenergy: groupEnergyCost) {
//            for (Map<String, Object>groupcost:groupOtheCost  ) {
//                String EnergyUnitid=  groupenergy.get("unitid").toString();
//                String EnergyDaydata=  groupenergy.get("daydata").toString();
//                Double EnergyTotal=  Double.parseDouble(groupenergy.get("total").toString());
//                Double otherTotal= Double.parseDouble(groupcost.get("total").toString()) ;
//                String otherUnitid=  groupcost.get("unitid").toString();
//                String otherDaydata=  groupcost.get("dayDateas").toString();
//                if(EnergyUnitid.equals(otherUnitid)&& EnergyDaydata.equals(otherDaydata)){
//                    totalcost=EnergyTotal+otherTotal;
//                    groupenergy.remove("total");
//                    groupenergy.put("total",totalcost);
//                    break;
//
//                }else{
//                    System.out.println("暂时没有数据");
//                }
//            }
//        }
//
//
//        List<Map<String, Object>> groupOrg=this.twoLevelMenuCostDao.groupOrg(params);
//        for (Map<String, Object>energyCost:groupEnergyCost) {
//            for (Map<String, Object> group: groupOrg ) {
//                String otherUnitid=  energyCost.get("unitid").toString();
//                String groupUnitid=  group.get("unitid").toString();
//                if(otherUnitid.equals(groupUnitid)){
//                    totalcostList.add(energyCost);
//                    break;
//                }
//            }
//        }
        return this.twoLevelMenuCostDao.selectBmTqLine(params);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> selectBmBqLine(Map<String, Object> params) {
//        List<Map<String, Object>> groupEnergyCost=this.twoLevelMenuCostDao.groupEnergyCost(params);
//        List<Map<String, Object>> groupOtheCost=this.twoLevelMenuCostDao.groupCost(params);
//        List<Map<String, Object>> totalcostList=new ArrayList<>();
//
//        Double totalcost=0.0;
//        for (Map<String, Object>groupenergy: groupEnergyCost) {
//            for (Map<String, Object>groupcost:groupOtheCost  ) {
//                String EnergyUnitid=  groupenergy.get("unitid").toString();
//                String EnergyDaydata=  groupenergy.get("daydata").toString();
//                Double EnergyTotal=  Double.parseDouble(groupenergy.get("total").toString());
//                Double otherTotal= Double.parseDouble(groupcost.get("total").toString()) ;
//                String otherUnitid=  groupcost.get("unitid").toString();
//                String otherDaydata=  groupcost.get("dayDateas").toString();
//                if(EnergyUnitid.equals(otherUnitid)&& EnergyDaydata.equals(otherDaydata)){
//                    totalcost=EnergyTotal+otherTotal;
//                    groupenergy.remove("total");
//                    groupenergy.put("total",totalcost);
//                   break;
//                }else{
//                    System.out.println("暂时没有数据");
//                }
//            }
//        }
//
//
//        List<Map<String, Object>> groupOrg=this.twoLevelMenuCostDao.groupOrg(params);
//        for (Map<String, Object>energyCost:groupEnergyCost) {
//            for (Map<String, Object> group: groupOrg ) {
//                String otherUnitid=  energyCost.get("unitid").toString();
//                String groupUnitid=  group.get("unitid").toString();
//                if(otherUnitid.equals(groupUnitid)){
//                    totalcostList.add(energyCost);
//                    break;
//                }
//            }
//        }
        List<Map<String, Object>>list=this.twoLevelMenuCostDao.selectBmBqLine(params);
        return list;

    }

    @Override
    @Transactional(readOnly = true)
    public Double selectBmBqTotal(Map<String, Object> params) {
//        List<Map<String, Object>> groupEnergyCost=this.twoLevelMenuCostDao.groupEnergyCost(params);
//        List<Map<String, Object>> groupOtheCost=this.twoLevelMenuCostDao.groupCost(params);
//        List<Map<String, Object>> totalcostList=new ArrayList<>();
//        Double sumTotalCost=0.0;
//        Double totalcost=0.0;
//        for (Map<String, Object>groupenergy: groupEnergyCost) {
//            for (Map<String, Object>groupcost:groupOtheCost  ) {
//                String EnergyUnitid=  groupenergy.get("unitid").toString();
//                String EnergyDaydata=  groupenergy.get("daydata").toString();
//                Double EnergyTotal=  Double.parseDouble(groupenergy.get("total").toString());
//                Double otherTotal= Double.parseDouble(groupcost.get("total").toString()) ;
//                String otherUnitid=  groupcost.get("unitid").toString();
//                String otherDaydata=  groupcost.get("dayDateas").toString();
//                if(EnergyUnitid.equals(otherUnitid)&& EnergyDaydata.equals(otherDaydata)){
//                    totalcost=EnergyTotal+otherTotal;
//                    groupenergy.remove("total");
//                    groupenergy.put("total",totalcost);
//
//                }else{
//                    System.out.println("暂时没有数据");
//                }
//            }
//        }
//
//
//        List<Map<String, Object>> groupOrg=this.twoLevelMenuCostDao.groupOrg(params);
//        for (Map<String, Object>energyCost:groupEnergyCost) {
//            for (Map<String, Object> group: groupOrg ) {
//                String otherUnitid=  energyCost.get("unitid").toString();
//                String groupUnitid=  group.get("unitid").toString();
//                if(otherUnitid.equals(groupUnitid)){
//                    totalcostList.add(energyCost);
//                    break;
//                }
//            }
//        }
//        for (Map<String, Object>totalcosts: totalcostList  ) {
//            sumTotalCost+= Double.parseDouble(totalcosts.get("total").toString()==null?"0":totalcosts.get("total").toString());
//        }
        return this.twoLevelMenuCostDao.selectBmBqTotal(params);
    }

    @Override
    @Transactional(readOnly = true)
    public Double selectBmTqTotal(Map<String, Object> params) {
//        List<Map<String, Object>> groupEnergyCost=this.twoLevelMenuCostDao.groupEnergyCostLastYear(params);
//        List<Map<String, Object>> groupOtheCost=this.twoLevelMenuCostDao.groupCostLastYear(params);
//        List<Map<String, Object>> totalcostList=new ArrayList<>();
//        Double sumTotalCost=0.0;
//        Double totalcost=0.0;
//        for (Map<String, Object>groupenergy: groupEnergyCost) {
//            for (Map<String, Object>groupcost:groupOtheCost  ) {
//                String EnergyUnitid=  groupenergy.get("unitid").toString();
//                String EnergyDaydata=  groupenergy.get("daydata").toString();
//                Double EnergyTotal=  Double.parseDouble(groupenergy.get("total").toString());
//                Double otherTotal= Double.parseDouble(groupcost.get("total").toString()) ;
//                String otherUnitid=  groupcost.get("unitid").toString();
//                String otherDaydata=  groupcost.get("dayDateas").toString();
//                if(EnergyUnitid.equals(otherUnitid)&& EnergyDaydata.equals(otherDaydata)){
//                    totalcost=EnergyTotal+otherTotal;
//                    groupenergy.remove("total");
//                    groupenergy.put("total",totalcost);
//
//                }else{
//                    System.out.println("暂时没有数据");
//                }
//            }
//        }
//
//
//        List<Map<String, Object>> groupOrg=this.twoLevelMenuCostDao.groupOrg(params);
//        for (Map<String, Object>energyCost:groupEnergyCost) {
//            for (Map<String, Object> group: groupOrg ) {
//                String otherUnitid=  energyCost.get("unitid").toString();
//                String groupUnitid=  group.get("unitid").toString();
//                if(otherUnitid.equals(groupUnitid)){
//                    totalcostList.add(energyCost);
//                    break;
//                }
//            }
//        }
//        for (Map<String, Object>totalcosts: totalcostList  ) {
//            sumTotalCost+= Double.parseDouble(totalcosts.get("total").toString()==null?"0":totalcosts.get("total").toString());
//        }
        return this.twoLevelMenuCostDao.selectBmTqTotal(params);

    }


    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> selectSubcostLine(Map<String, Object> params) {
        return this.twoLevelMenuCostDao.selectSubcostLine(params);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> selectSubcostLineLastYear(Map<String, Object> params) {
        return this.twoLevelMenuCostDao.selectSubcostLineLastYear(params);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> selectSubcostTotal(Map<String, Object> params) {
        return this.twoLevelMenuCostDao.selectSubcostTotal(params);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> selectSubcostTotalLastyear(Map<String, Object> params) {
        return this.twoLevelMenuCostDao.selectSubcostTotalLastyear(params);
    }
    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> selectSubcostEnergyLine(Map<String, Object> params) {
        List<Map<String, Object>> subCostEnergy=this.twoLevelMenuCostDao.selectSubcostEnergyLine(params);
        List<Map<String, Object>> subCostEnergyOrg=this.twoLevelMenuCostDao.selectSubcostEnergyOrg(params);
        Map groupMap=null;
        List<Map<String, Object>> costOrgUnitid=new ArrayList<>();
        for (Map<String, Object>costEnergy:subCostEnergy) {
            for (Map<String, Object>energyOrg:subCostEnergyOrg ) {
                String costEnergyUnitid=  costEnergy.get("UNITID").toString();
                String orgUnitid=  energyOrg.get("UNITID").toString();
                if(orgUnitid.equals(costEnergyUnitid)){
                    groupMap=new HashMap();
                    String total=  costEnergy.get("total").toString()==null ?"0.0": costEnergy.get("total").toString();
                    String dayDate=costEnergy.get("dayDate").toString()==null ?"0.0": costEnergy.get("dayDate").toString();
                    groupMap.put("total",total);
                    groupMap.put("unitid",orgUnitid);
                    groupMap.put("dayDate",dayDate);
                    break;
                }

            }
            costOrgUnitid.add(groupMap);
    }

       //以时间进行分组
        System.out.println("...........fdfd");
        System.out.println("...........fdfd");
        System.out.println("...........fdfd");
        System.out.println("...........fdfd");
        System.out.println("...........fdfd");

        Map<String,List<Map<String,Object>>>  map  = new HashMap<>();
        for(Map<String, Object> totalcost : costOrgUnitid){
            List<Map<String,Object>>  list  = new ArrayList<>();
            String  key  = totalcost.get("dayDate").toString();
            List<Map<String,Object>>  temp =  map.get(key);
            if(temp == null){
               list.add(totalcost);
               map.put(key,list);
            }else {
                temp.add(totalcost);
            }
        }

        Set <String> set=map.keySet();
        List<Map<String,Object>> returnCost=new ArrayList<>();
        Double totalcost=0.0;
        Map addmap=null;
                   for(String DayDatekey:set) {
                       Map<String, Object> retumap = new HashMap<>();
                       List<Map<String, Object>> DataList = map.get(DayDatekey);
                       Double amount = 0d;
                       for (Map<String, Object> DataListValue : DataList) {
                           amount += Double.valueOf(DataListValue.get("total").toString() == null ? "0.0" : DataListValue.get("total").toString());
                       }
                       retumap.put("dayDate", DayDatekey);
                       retumap.put("total", amount);
                       returnCost.add(retumap);
                   }
        Double sumtoal=0.0;
        for (Map<String, Object> sumtotal:returnCost) {
            sumtoal+=Double.valueOf(sumtotal.get("total").toString());
        }
        System.out.println(sumtoal);
        System.out.println(sumtoal);
        return returnCost;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> selectSubcostEnergyLineLastYear(Map<String, Object> params) {
        List<Map<String, Object>> subCostEnergy = this.twoLevelMenuCostDao.selectSubcostEnergyLineLastYear(params);
        List<Map<String, Object>> subCostEnergyOrg = this.twoLevelMenuCostDao.selectSubcostEnergyOrg(params);
        Map groupMap=null;
        List<Map<String, Object>> costOrgUnitid=new ArrayList<>();
        for (Map<String, Object>costEnergy:subCostEnergy) {
            for (Map<String, Object>energyOrg:subCostEnergyOrg ) {
                String costEnergyUnitid=  costEnergy.get("UNITID").toString();
                String orgUnitid=  energyOrg.get("UNITID").toString();
                if(orgUnitid.equals(costEnergyUnitid)){
                    groupMap=new HashMap();
                    String total=  costEnergy.get("total").toString()==null ?"0.0": costEnergy.get("total").toString();
                    String dayDate=costEnergy.get("dayDate").toString()==null ?"0.0": costEnergy.get("dayDate").toString();
                    groupMap.put("total",total);
                    groupMap.put("unitid",orgUnitid);
                    groupMap.put("dayDate",dayDate);
                    break;
                }
            }
            costOrgUnitid.add(groupMap);
        }

        //以时间进行分组
        Map<String,List<Map<String,Object>>>  map  = new HashMap<>();
        for(Map<String, Object> totalcost : costOrgUnitid){
            List<Map<String,Object>>  list  = new ArrayList<>();
            String  key  = totalcost.get("dayDate").toString();
            List<Map<String,Object>>  temp =  map.get(key);
            if(temp == null){
                list.add(totalcost);
                map.put(key,list);
            }else {
                temp.add(totalcost);
            }
        }

        Set <String> set=map.keySet();
        List<Map<String, Object>> returnCost=new ArrayList<>();
        Double totalcost=0.0;
        Map addmap=null;
        for(String DayDatekey:set) {
            Map<String, Object> retumap = new HashMap<>();
            List<Map<String, Object>> DataList = map.get(DayDatekey);
            Double amount = 0d;
            for (Map<String, Object> DataListValue : DataList) {
                amount += Double.valueOf(DataListValue.get("total").toString() == null ? "0.0" : DataListValue.get("total").toString());
                retumap.put("dayDate", DayDatekey);
                retumap.put("total", amount);
            }
            returnCost.add(retumap);
        }
        return returnCost;
    }

    @Override
    @Transactional(readOnly = true)
    public   Map<String, Object> selectSubcostTotalEnergy(Map<String, Object> params) {
        List<Map<String, Object>> subCostEnergy=this.twoLevelMenuCostDao.selectSubcostEnergyLine(params);
        List<Map<String, Object>> subCostEnergyOrg=this.twoLevelMenuCostDao.selectSubcostEnergyOrg(params);
        Map groupMap=null;
        List<Map<String, Object>> costOrgUnitid=new ArrayList<>();
        for (Map<String, Object>costEnergy:subCostEnergy) {
            for (Map<String, Object>energyOrg:subCostEnergyOrg ) {
                String costEnergyUnitid=  costEnergy.get("UNITID").toString();
                String orgUnitid=  energyOrg.get("UNITID").toString();
                if(orgUnitid.equals(costEnergyUnitid)){
                    groupMap=new HashMap();
                    String total=  costEnergy.get("total").toString()==null ?"0.0": costEnergy.get("total").toString();
                    String dayDate=costEnergy.get("dayDate").toString()==null ?"0.0": costEnergy.get("dayDate").toString();
                    groupMap.put("total",total);
                    groupMap.put("unitid",orgUnitid);
                    groupMap.put("dayDate",dayDate);
                    break;
                }
            }
            costOrgUnitid.add(groupMap);
        }

        //以时间进行分组
        Map<String,List<Map<String,Object>>>  map  = new HashMap<>();
        for(Map<String, Object> totalcost : costOrgUnitid){
            List<Map<String,Object>>  list  = new ArrayList<>();
            String  key  = totalcost.get("dayDate").toString();
            List<Map<String,Object>>  temp =  map.get(key);
            if(temp == null){
                list.add(totalcost);
                map.put(key,list);
            }else {
                temp.add(totalcost);
            }
        }

        Set <String> set=map.keySet();
        List<Map<String, Object>> returnCost=new ArrayList<>();
        Double totalcost=0.0;
        Map addmap=null;
        for(String DayDatekey:set) {
            Map<String, Object> retumap = new HashMap<>();
            List<Map<String, Object>> DataList = map.get(DayDatekey);
            Double amount = 0d;
            for (Map<String, Object> DataListValue : DataList) {
                amount += Double.valueOf(DataListValue.get("total").toString() == null ? "0.0" : DataListValue.get("total").toString());
                retumap.put("dayDate", DayDatekey);
                retumap.put("total", amount);
            }
            returnCost.add(retumap);
        }
        Double sumtoal=0.0;
        for (Map<String, Object> sumtotal:returnCost) {
            sumtoal+=Double.valueOf(sumtotal.get("total").toString());
        }
        System.out.println(sumtoal);
        System.out.println(sumtoal);
        Map<String, Object> tbMap = new HashMap<>();
        tbMap.put("energy",sumtoal);
        return tbMap;
    }

    @Override
    @Transactional(readOnly = true)
    public   Map<String, Object> selectSubcostTotalEnergyLastYear(Map<String, Object> params) {
        List<Map<String, Object>> subCostEnergy=this.twoLevelMenuCostDao.selectSubcostEnergyLineLastYear(params);
        List<Map<String, Object>> subCostEnergyOrg=this.twoLevelMenuCostDao.selectSubcostEnergyOrg(params);
        Map groupMap=null;
        List<Map<String, Object>> costOrgUnitid=new ArrayList<>();
        for (Map<String, Object>costEnergy:subCostEnergy) {
            for (Map<String, Object>energyOrg:subCostEnergyOrg ) {
                String costEnergyUnitid=  costEnergy.get("UNITID").toString();
                String orgUnitid=  energyOrg.get("UNITID").toString();
                if(orgUnitid.equals(costEnergyUnitid)){
                    groupMap=new HashMap();
                    String total=  costEnergy.get("total").toString()==null ?"0.0": costEnergy.get("total").toString();
                    String dayDate=costEnergy.get("dayDate").toString()==null ?"0.0": costEnergy.get("dayDate").toString();
                    groupMap.put("total",total);
                    groupMap.put("unitid",orgUnitid);
                    groupMap.put("dayDate",dayDate);
                    break;
                }
            }
            costOrgUnitid.add(groupMap);
        }

        //以时间进行分组
        Map<String,List<Map<String,Object>>>  map  = new HashMap<>();
        for(Map<String, Object> totalcost : costOrgUnitid){
            List<Map<String,Object>>  list  = new ArrayList<>();
            String  key  = totalcost.get("dayDate").toString();
            List<Map<String,Object>>  temp =  map.get(key);
            if(temp == null){
                list.add(totalcost);
                map.put(key,list);
            }else {
                temp.add(totalcost);
            }
        }

        Set <String> set=map.keySet();
        List<Map<String, Object>> returnCost=new ArrayList<>();
        Double totalcost=0.0;
        Map addmap=null;
        for(String DayDatekey:set) {
            Map<String, Object> retumap = new HashMap<>();
            List<Map<String, Object>> DataList = map.get(DayDatekey);
            Double amount = 0d;
            for (Map<String, Object> DataListValue : DataList) {
                amount += Double.valueOf(DataListValue.get("total").toString() == null ? "0.0" : DataListValue.get("total").toString());
                retumap.put("dayDate", DayDatekey);
                retumap.put("total", amount);
            }
            returnCost.add(retumap);
        }
        Double sumtoal=0.0;
        for (Map<String, Object> sumtotal:returnCost) {
            sumtoal+=Double.valueOf(sumtotal.get("total").toString());
        }
        System.out.println(sumtoal);
        System.out.println(sumtoal);

        Map<String, Object> tbMap = new HashMap<>();
        tbMap.put("energy",sumtoal);
        return tbMap;

    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> SubCostDetailed(Map<String, Object> params) {

        return this.twoLevelMenuCostDao.SubCostDetailed(params);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> SubCostDetailedEnergy(Map<String, Object> params) {
        return this.twoLevelMenuCostDao.SubCostDetailedEnergy(params);
    }

    @Override
    @Transactional(readOnly = true)
    public  Map<String, Object> selectOrg(Map<String, Object> params) {
        return this.twoLevelMenuCostDao.selectOrg(params);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> SubCostDetailedEnergyLastYear(Map<String, Object> params) {
        return this.twoLevelMenuCostDao.SubCostDetailedEnergyLastYear(params);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> groupTotalCost(Map<String, Object> params) {
        List<Map<String, Object>> groupEnergyCost=this.twoLevelMenuCostDao.groupEnergyCost(params);
        List<Map<String, Object>> groupOtheCost=this.twoLevelMenuCostDao.groupCost(params);
        List<Map<String, Object>> totalcostList=new ArrayList<>();
        Double totalcost=0.0;
        for (Map<String, Object>groupenergy: groupEnergyCost) {
            for (Map<String, Object>groupcost:groupOtheCost  ) {
                String EnergyUnitid=  groupenergy.get("unitid").toString();
                String EnergyDaydata=  groupenergy.get("daydata").toString();
                Double EnergyTotal=  Double.parseDouble(groupenergy.get("total").toString());
                Double otherTotal= Double.parseDouble(groupcost.get("total").toString()) ;
                String otherUnitid=  groupcost.get("unitid").toString();
                String otherDaydata=  groupcost.get("dayDateas").toString();
                if(EnergyUnitid.equals(otherUnitid)&& EnergyDaydata.equals(otherDaydata)){
                    totalcost=EnergyTotal+otherTotal;
                    groupenergy.remove("total");
                    groupenergy.put("total",totalcost);
                }else{
                    System.out.println("暂时没有数据");
                }
            }
        }


        List<Map<String, Object>> groupOrg=this.twoLevelMenuCostDao.groupOrg(params);
        for (Map<String, Object>energyCost:groupEnergyCost) {
                 for (Map<String, Object> group: groupOrg ) {
                     String otherUnitid=  energyCost.get("unitid").toString();
                     String groupUnitid=  group.get("unitid").toString();
                     if(otherUnitid.equals(groupUnitid)){
                         totalcostList.add(energyCost);
                         break;
                     }
           }
        }
        return totalcostList;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> twolEvelEnergyFlowLine(Map<String, String> params) throws Exception {
        Map<String,Object> result = new HashMap<String,Object>();
        String sDate = params.get("startTime");//查询条件的开始时间
        String eDate = params.get("endTime");//查询条件的结束时间
        sDate = (null==sDate||"".equals(sDate))?DateUtils.getYearDate(null,Calendar.DATE, -5):sDate.substring(0,10);//如果查询条件的开始时间为空，设置默认的开始时间
        eDate = (null==eDate||"".equals(eDate))?DateUtils.getYearDate(null,Calendar.DATE, 0):eDate.substring(0,10);//如果查询条件的结束时间为空，设置默认的结束时间
        //查询时间list
        List<String> yearList = new ArrayList<String>();
        //无查询结果时，设为默认值0
        List<String> defaultValue = new ArrayList<String>();
        while(!sDate.equals(eDate)){
            yearList.add(sDate);
            sDate = DateUtils.getYearDate(sDate,Calendar.DATE,1);
            defaultValue.add("0");
        }
        yearList.add(eDate);
        defaultValue.add("0");
        //处理查询结果，为折线图做准备
        List<Map<String, Object>> eflListMap = twoLevelMenuCostDao.twolEvelEnergyFlowLine(params);
        String[] unitType = null;
        int toolorgtype = Integer.valueOf("".equals((params.get("orgType")+""))?"-1":(params.get("orgType")+""));
        switch(toolorgtype){
            case 1:unitType = new String[]{"供热源"};break;
            case 2:unitType = new String[]{"一次网"};break;
            case 3:unitType = new String[]{"换热站"};break;
            case 4:unitType = new String[]{"二次线"};break;
            case 5:unitType = new String[]{"民户"};break;
            default:unitType = new String[]{"供热源","一次网","换热站","二次线","民户"};
        }
        List<Map<String,Object>> series = new ArrayList<Map<String,Object>>();
        for(String type:unitType){
            Map<String, Object> serie = new HashMap<String,Object>();
            serie.put("name",type);
            serie.put("type","line");
            serie.put("symbol","circle");
            serie.put("smooth",false);
            List<String> lineValue = new ArrayList<String>();
            if(null!=eflListMap&&eflListMap.size()>0){
                for(String d:yearList){
                    boolean isHas = false;
                    for(Map<String, Object> map:eflListMap){
                        if(type.equals(map.get("unittype")+"")&&d.equals(map.get("yeardate")+"")){
                            lineValue.add(map.get("total")+"");
                            isHas = true;
                        }
                    }
                    if(!isHas){
                        lineValue.add("0");
                    }
                }
                serie.put("data",lineValue);
            }else{
                serie.put("data",defaultValue);
            }
            series.add(serie);
        }
        result.put("xDate", yearList);
        result.put("series", series);
        result.put("legends", unitType);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> twolEvelSubFlowLine(Map<String, String> params) throws Exception {
//        Map<String,Object> result = new HashMap<String,Object>();
//        String sDate = params.get("startTime");//查询条件的开始时间
//        String eDate = params.get("endTime");//查询条件的结束时间
//        sDate = (null==sDate||"".equals(sDate))?DateUtils.getYearDate(null,Calendar.DATE, -5):sDate.substring(0,10);//如果查询条件的开始时间为空，设置默认的开始时间
//        eDate = (null==eDate||"".equals(eDate))?DateUtils.getYearDate(null,Calendar.DATE, 0):eDate.substring(0,10);//如果查询条件的结束时间为空，设置默认的结束时间
//        //查询时间list
//        List<String> yearList = new ArrayList<String>();
//        //无查询结果时，设为默认值0
//        List<String> defaultValue = new ArrayList<String>();
//        while(!sDate.equals(eDate)){
//            yearList.add(sDate);
//            sDate = DateUtils.getYearDate(sDate,Calendar.DATE,1);
//            defaultValue.add("0");
//        }
//        yearList.add(eDate);
//        defaultValue.add("0");
//        //处理查询结果，为折线图做准备
      // List<Map<String, Object>> eflListMap = twoLevelMenuCostDao.twolEvelEnergyFlowLine(params);
      List<Map<String, Object>> SubFlowLine = twoLevelMenuCostDao.twolEvelSubFlowLine(params);
//        List<Map<String, Object>> hash=new ArrayList<>();
//        Map addMap=null;
//        Double subTotal=0.0;
//        for (Map<String, Object> energyFlowLine:eflListMap) {
//            addMap=new HashMap();
//            for (Map<String, Object>subflowline:SubFlowLine) {
//                String energyUnittype=  energyFlowLine.get("unittype").toString();
//                String subUnittype=  subflowline.get("unittype").toString();
//                String energyYeardate=  energyFlowLine.get("yeardate").toString();
//                String subYeardate=  subflowline.get("yeardate").toString();
//                if(energyUnittype.equals(subUnittype)&&energyYeardate.equals(subYeardate)){
//                    String subCostenergyTotal = energyFlowLine.get("total").toString() == null ? "0.0" : energyFlowLine.get("total").toString();
//                    String subCostenergyTqTotal = subflowline.get("total").toString() == null ? "0.0" : subflowline.get("total").toString();
//                    subTotal=Double.valueOf(subCostenergyTotal)+Double.valueOf(subCostenergyTqTotal);
//                    addMap.put("unittype",energyUnittype);
//                    addMap.put("yeardate",energyYeardate);
//                    addMap.put("total",subTotal);
//                    hash.add(addMap);
//                    break;
//                }
//            }
//
//        }
//        String[] unitType = null;
//        int toolorgtype = Integer.valueOf("".equals((params.get("orgType")+""))?"-1":(params.get("orgType")+""));
//        switch(toolorgtype){
//            case 1:unitType = new String[]{"供热源"};break;
//            case 2:unitType = new String[]{"一次网"};break;
//            case 3:unitType = new String[]{"换热站"};break;
//            case 4:unitType = new String[]{"二次线"};break;
//            case 5:unitType = new String[]{"民户"};break;
//            default:unitType = new String[]{"供热源","一次网","换热站","二次线","民户"};
//        }
//        List<Map<String,Object>> series = new ArrayList<Map<String,Object>>();
//        for(String type:unitType){
//            Map<String, Object> serie = new HashMap<String,Object>();
//            serie.put("name",type);
//            serie.put("type","line");
//            serie.put("symbol","circle");
//            serie.put("smooth",false);
//            List<String> lineValue = new ArrayList<String>();
//            if(null!=hash&&hash.size()>0){
//                for(String d:yearList){
//                    boolean isHas = false;
//                    for(Map<String, Object> map:hash){
//                        if(type.equals(map.get("unittype")+"")&&d.equals(map.get("yeardate")+"")){
//                            lineValue.add(map.get("total")+"");
//                            isHas = true;
//                        }
//                    }
//                    if(!isHas){
//                        lineValue.add("0");
//                    }
//                }
//                serie.put("data",lineValue);
//            }else{
//                serie.put("data",defaultValue);
//            }
//            series.add(serie);
//        }
//        result.put("xDate", yearList);
//        result.put("series", series);
//        result.put("legends", unitType);
        return SubFlowLine;
    }

    @Override
    public List<Map<String, Object>> subEnergyAn(Map<String, Object> paramsMap) {
        return this.twoLevelMenuCostDao.subEnergyAn(paramsMap);
    }


    @Override
    public List<Map<String, Object>> EnergyFlowDetail(Map<String, Object> params) {
        return this.twoLevelMenuCostDao.EnergyFlowDetail(params);
    }
}
