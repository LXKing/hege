<%--
  Created by IntelliJ IDEA.
  User: lichao
  Date: 2017/8/31
  Time: 9:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script src="${web}/script/huak.web.system.history.js"></script>

<form id="formForExport">
    <div class="main-box" style="min-height: 1500px">
        <div class="selectbg clearfix">
            <div class="sele-row clearfix row">
                <!--<div class="clearfix row">-->

                <div class="select-box col-xs-12 col-sm-6 col-md-4">
                    <label for="">时间</label>
                    <input id="startTime" name="startTime" class="Wdate time-input" type="text" placeholder="开始时间"
                           onFocus="var endTime=$dp.$('endTime');WdatePicker({onpicked:function(){endTime.focus();},maxDate:'#F{$dp.$D(\'endTime\')}'})"
                            /> 至
                    <input id="endTime" name="endTime" class="Wdate time-input" type="text" placeholder="结束时间"
                           onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})"
                            />
                </div>
                <div class="col-xs-12 col-sm-6 col-md-2">
                    <a class="btns btnsfl btns-lookin" onclick="query(1)">查询</a>
                    <a class="btns btnsfl btns-reset" onclick =reset()>重置</a>
                </div>
            </div>
        </div>

        <div class="col-xs-12 btngroups   ">
            <a class="btns btnsfl exportchange btns-green"  export-url="${web}/history/export" >导出</a>
            <%--<a class="btns btnsfl btns-lookin" href="javascipt:;">修改日期</a>--%>
        </div>
        <div class="col-xs-12 main-table no-padding" style="overflow:scroll;width:100%;height: 100%">
            <table class="table table-striped table-bordered table-hover pgtable" style="width:1800px" align="center">

                <tr>
                    <td colspan="14"  style="text-align: center">电厂东西线能耗情况统计表</td>
                    <td colspan="6"  style="text-align: center">查表时间</td>
                    <%--<td colspan="6"  style="text-align: center">天气预报</td>--%>
                </tr>
                <tr>
                    <td rowspan="2" colspan="2">电厂</td>
                    <td colspan="2">上次表底</td>
                    <td colspan="2">本次表底</td>
                    <td colspan="4">能耗量</td>
                    <td rowspan="2" colspan="2">各换热站耗能统计(GJ)</td>
                    <td rowspan="2" colspan="2">热漏失率</td>
                    <td rowspan="2" colspan="3">上次查表时间</td>
                    <td rowspan="2" colspan="3">2017/11/15 9:00:00</td>

                </tr>
                <tr>
                    <td>用热量（GJ）</td>
                    <td>少回水量(t)</td>
                    <td>用热量(GJ)</td>
                    <td>少回水量(t)</td>
                    <td colspan="2">用热量(GJ)</td>
                    <td colspan="2">少回水量(t)</td>
                    <%--<td>2017/11/15</td>--%>
                    <%--<td></td>--%>
                    <%--<td></td>--%>
                    <%--<td></td>--%>
                    <%--<td colspan="2"></td>--%>
                </tr>
                <tr>
                    <td colspan="2">三院西线</td>
                    <td >108704</td>
                    <td >-312359</td>
                    <td></td>
                    <td></td>
                    <td colspan="2">108704</td>
                    <td colspan="2">-312359</td>
                    <td colspan="2">-157878</td>
                    <td colspan="2">-45.24%</td>
                    <td colspan="3">本次查表时间</td>
                    <td colspan="3">2017/11/17 9:00:00</td>

                </tr>
                <tr>
                    <td colspan="2">合计</td>
                    <td ></td>
                    <td ></td>
                    <td></td>
                    <td></td>
                    <td colspan="2">108704</td>
                    <td colspan="2">312359</td>
                    <td colspan="2">0</td>
                    <td colspan="2"></td>
                    <td colspan="3">两次查表时间差(天)</td>
                    <td colspan="3">2.00</td>

                </tr>
                <tr>
                    <td colspan="20" style="font-size:15px;text-align: center;background-color: #0000ff">天气预报</td>
                </tr>
                <tr>
                    <td colspan="3">日期</td>
                    <td colspan="3">最高温度</td>
                    <td colspan="3">最低温度</td>
                    <td colspan="3">平均温度</td>
                    <td colspan="8">天气情况</td>
                </tr>
                <tr>
                    <td colspan="3">2017/11/15</td>
                    <td colspan="3"></td>
                    <td colspan="3"></td>
                    <td colspan="3"></td>
                    <td colspan="8"></td>
                </tr>
                <tr>
                    <td colspan="3">2017/11/156</td>
                    <td colspan="3"></td>
                    <td colspan="3"></td>
                    <td colspan="3"></td>
                    <td colspan="8"></td>
                </tr>
                <tr>
                    <td colspan="3">2017/11/17</td>
                    <td colspan="3"></td>
                    <td colspan="3"></td>
                    <td colspan="3"></td>
                    <td colspan="8"></td>
                </tr>
                <tr>
                    <td colspan="20" style="font-size:15px;text-align: center;background-color: #0000ff">各换热站日报统计表(2017-11-15——2017-11-17)</td>
                </tr>
                <tr>
                    <td rowspan="2" colspan="2" style="text-align: center;background-color: #696969">换热站名称</td>
                    <td rowspan="2" style="text-align: center;background-color: #696969">供热面积</td>
                    <td colspan="3"style="text-align: center;background-color: #696969">上次表底</td>
                    <td colspan="3"style="text-align: center;background-color: #696969">本次表底</td>
                    <td style="text-align: center;background-color: #a9a9a9"></td>
                    <td colspan="3" style="text-align: center;background-color: #696969">能耗量</td>
                    <td colspan="3" style="text-align: center;background-color: #696969">单位能耗（kg KWh GJ /平方米.天）</td>
                    <td colspan="4" style="text-align: center;background-color: #696969">单耗（元/平方米.天）</td>
                </tr>
                <tr>
                    <td style="text-align: center;background-color: #696969">用热量（GJ）</td>
                    <td style="text-align: center;background-color: #696969">水(t）</td>
                    <td style="text-align: center;background-color: #696969">电（Kwh）</td>
                    <td style="text-align: center;background-color: #696969">用热量（GJ）</td>
                    <td style="text-align: center;background-color: #696969">水(t）</td>
                    <td style="text-align: center;background-color: #696969">电（Kwh）</td>
                    <td style="text-align: center;background-color: #a9a9a9">电表倍率</td>
                    <td style="text-align: center;background-color: #696969">水(t）</td>
                    <td style="text-align: center;background-color: #696969">电（Kwh）</td>
                    <td style="text-align: center;background-color: #696969">用热量（GJ）</td>
                    <td style="text-align: center;background-color: #696969">水单耗 </td>
                    <td style="text-align: center;background-color: #696969">电单耗 </td>
                    <td style="text-align: center;background-color: #696969">热单耗 </td>

                    <td style="text-align: center;background-color: #696969">水费单耗 </td>
                    <td style="text-align: center;background-color: #696969">电费单耗 </td>
                    <td style="text-align: center;background-color: #696969">热费单耗 </td>
                    <td style="text-align: center;background-color: #696969">综合耗费 </td>
                </tr>
                <tr>
                    <td rowspan="9">西线</td>
                    <td>发</td>
                    <td>116.8</td>
                    <td>329</td>
                    <td>5678</td>
                    <td>175</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td style="color: #C5C1AA">80</td>
                    <td>-5678</td>
                    <td>-14400</td>
                    <td>-32</td>
                    <td>-23.8</td>
                    <td>-6.005</td>
                    <td>-0.1390</td>
                    <td>-195.4</td>
                    <td>-8.1050</td>
                    <td>-12.09</td>
                    <td>-215.7 </td>
                </tr>
                <tr>
                    <td>发</td>
                    <td>116.8</td>
                    <td>32908</td>
                    <td>5678</td>
                    <td>17755</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td style="color: #C5C1AA">80</td>
                    <td>-5678</td>
                    <td>-1400</td>
                    <td>-3208</td>
                    <td>-23.908</td>
                    <td>-6.0015</td>
                    <td>-0.1390</td>
                    <td>-195.54</td>
                    <td>-8.1050</td>
                    <td>-12.068</td>
                    <td>-215.7273 </td>
                </tr>
                <tr>
                    <td>发</td>
                    <td>1136.8</td>
                    <td>3298</td>
                    <td>567</td>
                    <td>175</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td style="color: #C5C1AA">80</td>
                    <td>-5678</td>
                    <td>-100</td>
                    <td>-328</td>
                    <td>-23.99</td>
                    <td>-6.0015</td>
                    <td>-0.1390</td>
                    <td>-95.54</td>
                    <td>-8.100</td>
                    <td>-12.098</td>
                    <td>-25.773 </td>
                </tr>
                <tr>
                    <td>研</td>
                    <td>116.8</td>
                    <td>328</td>
                    <td>5678</td>
                    <td>17</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td style="color: #C5C1AA">80</td>
                    <td>-5678</td>
                    <td>-1400</td>
                    <td>-328</td>
                    <td>-23.8</td>
                    <td>-6.0015</td>
                    <td>-0.1390</td>
                    <td>-195.5</td>
                    <td>-8.1050</td>
                    <td>-12.68</td>
                    <td>-25.73 </td>
                </tr>
                <tr>
                    <td>研</td>
                    <td>116.8</td>
                    <td>32</td>
                    <td>5678</td>
                    <td>17755</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td style="color: #C5C1AA">80</td>
                    <td>-5678</td>
                    <td>-1400</td>
                    <td>-328</td>
                    <td>-23.98</td>
                    <td>-6.0015</td>
                    <td>-0.13</td>
                    <td>-95.4</td>
                    <td>-8.1050</td>
                    <td>-12.0</td>
                    <td>-25.73 </td>
                </tr>
                <tr>
                    <td>研</td>
                    <td>118336.8</td>
                    <td>32908</td>
                    <td>5678</td>
                    <td>17755</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td style="color: #C5C1AA">80</td>
                    <td>-5678</td>
                    <td>-1400</td>
                    <td>-308</td>
                    <td>-3.98</td>
                    <td>-6.05</td>
                    <td>-0.10</td>
                    <td>-15.54</td>
                    <td>-8.1050</td>
                    <td>-12.068</td>
                    <td>-2.73 </td>
                </tr>
                <tr>
                    <td>研</td>
                    <td>36.8</td>
                    <td>38</td>
                    <td>5678</td>
                    <td>55</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td style="color: #C5C1AA">80</td>
                    <td>-5678</td>
                    <td>-400</td>
                    <td>-308</td>
                    <td>-3.08</td>
                    <td>-6.0015</td>
                    <td>-0.10</td>
                    <td>-195.5</td>
                    <td>-8.1050</td>
                    <td>-12.0968</td>
                    <td>-25.72 </td>
                </tr>
                <tr>
                    <td>研</td>
                    <td>116.8</td>
                    <td>329</td>
                    <td>5678</td>
                    <td>177</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td style="color: #C5C1AA">80</td>
                    <td>-5678</td>
                    <td>-400</td>
                    <td>-32908</td>
                    <td>-3.98</td>
                    <td>-6.0015</td>
                    <td>-0.1390</td>
                    <td>-195.4</td>
                    <td>-8.1050</td>
                    <td>-12.0968</td>
                    <td>-15.3 </td>
                </tr>
                <tr>
                    <td>厂</td>
                    <td>116.8</td>
                    <td>328</td>
                    <td>5678</td>
                    <td>17755</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td style="color: #C5C1AA">80</td>
                    <td>-5678</td>
                    <td>-1400</td>
                    <td>-328</td>
                    <td>-23.99</td>
                    <td>-6.15</td>
                    <td>-0.13</td>
                    <td>-15.4</td>
                    <td>-8.1</td>
                    <td>-12.8</td>
                    <td>-215.3 </td>
                </tr>

            <tr>
                <td rowspan="2">小计</td>
                <td style="font-weight: bold ;color: #FFF">东线小计</td>
                <td style="font-weight: bold;color: #FFF">116.8</td>
                <td>——</td>
                <td>——</td>
                <td>——</td>
                <td>——</td>
                <td>——</td>
                <td>——</td>
                <td>——</td>
                <td>-5678</td>
                <td>-1400</td>
                <td>-328</td>
                <td>-23.99</td>
                <td>-6.15</td>
                <td>-0.13</td>
                <td>-15.4</td>
                <td>-8.1</td>
                <td>-12.8</td>
                <td>-21.73</td>
            </tr>
            <tr>
                <td style="font-weight: bold">西线小计</td>
                <td style="font-weight: bold">116.8</td>
                <td>——</td>
                <td>——</td>
                <td>——</td>
                <td>——</td>
                <td>——</td>
                <td>——</td>
                <td>——</td>
                <td>-5678</td>
                <td>-1400</td>
                <td>-328</td>
                <td>-23.99</td>
                <td>-6.15</td>
                <td>-0.13</td>
                <td>-15.4</td>
                <td>-8.1</td>
                <td>-12.8</td>
                <td>-21.73</td>
            </tr>
            <tr>
                <td colspan="2">东西线合计</td>
                <td style="font-weight: bold">116.8</td>
                <td>——</td>
                <td>——</td>
                <td>——</td>
                <td>——</td>
                <td>——</td>
                <td>——</td>
                <td>——</td>
                <td>-5678</td>
                <td>-1400</td>
                <td>-328</td>
                <td>-23.99</td>
                <td>-6.15</td>
                <td>-0.13</td>
                <td>-15.4</td>
                <td>-8.1</td>
                <td>-12.8</td>
                <td>-21.73</td>
            </tr>
            </table>
        </div>

    </div>
</form>
