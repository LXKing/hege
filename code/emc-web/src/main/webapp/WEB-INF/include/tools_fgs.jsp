<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="clearfix row no-margin index_header">
    <form id="searchTools">
        <input type="hidden" id="toolOrgId" name="toolOrgId" value="">
        <input type="hidden" id="toolFeedType" name="toolFeedType" value="2">
        <input type="hidden" id="toolStartDate" name="toolStartDate" value="">
        <input type="hidden" id="toolEndDate" name="toolEndDate" value="">
        <input type="hidden" id="toolOrgType" name="toolOrgType" value="">
    </form>
    <!--面包屑导航-->
    <div class="bread-crumb pull-left">
       <%-- 当前位置：
        <a href="index.html">[<var class="xmhpg">首页 </var>]</a> &gt; [<var class="xmhpg" style="color: #666;">能源流概况</var>]--%>
    </div>

    <div class="mianTop pull-right" style="min-width: 600px;">
        <div class="selectbg clearfix col-xs-12">

            <%--<div class="select-box clearfix">--%>
                <%--<div class="clearfix h-selectbox">--%>
                    <%--<div class="x-selectfree fl">--%>
                        <%--<div class="x-sfbgbox">--%>
                            <%--<div class="x-sfleft1 x-sfw1">--%>
                                <%--&lt;%&ndash;<input type="text" value="" readonly="readonly">&ndash;%&gt;--%>
                            <%--</div>--%>
                            <%--<div class="x-sfright1"></div>--%>
                        <%--</div>--%>
                        <%--<div class="x-sfoption x-sfoption1">--%>
                            <%--&lt;%&ndash;<p value=" "> </p>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;&lt;%&ndash;<p value="2222">上海公司</p>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<p value="3333">南京集团</p>&ndash;%&gt;&ndash;%&gt;--%>
                        <%--</div>--%>
                        <%--&lt;%&ndash;<input type="hidden" value="1111" />&ndash;%&gt;--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <div class="select-box select-boxbtnAlarm clearfix">
                <a href="javascript:;" class="btnAlarm btnAlarm-on">全部</a>
                <a href="javascript:;" class="btnAlarm ">集中供暖</a>
                <a href="javascript:;" class="btnAlarm ">区域供暖</a>
            </div>

            <div class="select-box select-boxbtnAlarm clearfix" style="position:relative">
                <a href="javascript:;" class="btnAlarm ">本年度</a>
                <a href="javascript:;" class="btnAlarm btnAlarm-on">本采暖季</a>
                <a href="javascript:;" class="btnAlarm " id="datepicker-config">自定义</a>

                <div id="selectdate" class="daterangepicker dropdown-menu ltr show-calendar opensright" style="position:absolute;z-index: 9999;     top: 30px;
    left: -100px; width: 533px;  ">
                    <div class="datepictitle"><span style="font-size:14px;color:#aaa">您也可能想看</span><span class="datepictime" id="nearlyaweek">近一周</span><span class="datepictime" id="nearlyamonth">近一月</span><span class="datepictime" id="nearlytwomonth">近两月</span></div>
                    <div class="calendar left" style="width: 50%;padding:0px 10px">

                    </div>
                    <div class="calendar right">

                    </div>
                    <div style="clear:both"></div>
                    <div class="ranges">
                        <div class="range_inputs"><button class="applyBtn btn-sm btn-confirm" type="button">确定</button> <button class="cancelBtn btn-sm btn-cancel" type="button">取消</button></div>
                    </div>

                </div>
            </div>
            <div class="select-box select-boxWdate">
                <input id="begin" class="Wdate time-input time-input-disable" disabled="disabled" readonly="readonly" value="2017-05-01" type="text" />
                <span>至</span>
                <input id="end" class="Wdate time-input time-input-disable" disabled="disabled" readonly="readonly" value="2017-05-08" type="text" />
            </div>

        </div>
    </div>
</div>
<script>
    $(function () {
        //console.info("重置面包屑");
        var mbhtml = "";
        var mbxdh =  ${navigations};
        var ymurl = document.location.href;
        var prefix = "";
        if(ymurl.indexOf('?')>0){
            prefix = ymurl.substr(0, ymurl.indexOf('?'));
        }else{
            prefix = ymurl;
        }

        $.each(mbxdh, function (idx, item) {
            /*console.info(item);
             console.info(document.location.href==_web + item.url)*/

            var hturl = _web + item.url;
            if(hturl.indexOf('*')>=0){
                hturl = hturl.replace('*','');
                if (prefix.indexOf(hturl)>=0 ) {
                    mbhtml = getMbHtml(item, mbhtml,prefix);
                }
            }else{
                if (prefix == hturl) {
                    mbhtml = getMbHtml(item, mbhtml,prefix);
                }
            }

        });
        $('.bread-crumb.pull-left').html("当前位置：" + mbhtml);
    });
    function getMbHtml(navigation, html,prefix) {
        if (navigation.navigation == "undefined" || navigation.navigation == null || navigation.navigation == "") {
            html += '<a href="' + _web + navigation.url + '">[<var class="xmhpg">' + navigation.title + '</var>]</a>';
            return html;
        } else {
            if(navigation.url.indexOf('*')>=1){
                html = getMbHtml(navigation.navigation, html,navigation.navigation.url)  + '&gt;<a href="' + _web + prefix + '">[<var class="xmhpg">' + navigation.title + '</var>]</a>';
            }else{
                html = getMbHtml(navigation.navigation, html,navigation.navigation.url) + '&gt;<a href="' + _web + navigation.url + '">[<var class="xmhpg">' + navigation.title + '</var>]</a>';
            }
            return html;
        }
    }
</script>