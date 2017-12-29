
if (typeof jQuery === 'undefined') { throw new Error('Common\'s JavaScript requires jQuery') }


var option = null;
var feedOption = null;
var oncenetOption = null;
var secondnetOption = null;
var stationOption = null;
var gameOption = null;
var banOption = null;
var Org = function (option) {
    this.option = option ;
    this.init();
};
Org.prototype.init = function () {
    top.documentWindows = this.option.windows;
    top.documentText = this.option.dom;
};

Org.prototype.show = function(){
    var wind =this.option.windows;
    var $this = this.option.dom;
    var $span = $this.parent();
    var $textInput=$span.siblings('input:text');
    wind.html("");
    if($this.attr("typetext")){
        $.get(ctx + '/common/list/',{typeId:$this.attr("typetext")}, function (result) {
            wind.html(result);
        });
    }else{
        $.get(ctx + '/common/list/', function (result) {
            wind.html(result);
        });
    }

    top.layerIndex = layer.open({
        area: ["800px", "600px"],
        type: 1,
        title: "组织机构",
        skin: 'layer-ext-moon', //样式类名
        closeBtn: 1, //不显示关闭按钮
        shift: 2,
        offset:"auto",
        move:true,
        fix:false,
        scrollbar: false,
        shadeClose: true, //开启遮罩关闭
        content:wind,
        cancel : function(index){
            layer.close(index);
        },
        end :function(){
            wind.remove();

            $textInput.change();
        }
    });
}

var Feed = function (option) {
    this.feedOption = option ;
    this.init();
};
Feed.prototype.init = function () {
    top.documentWindows = this.feedOption.windows;
    top.documentText = this.feedOption.dom;
};

Feed.prototype.show = function(){
    var wind =this.feedOption.windows;
    var $this = this.feedOption.dom;
    var $span = $this.parent();
    var $textInput=$span.siblings('input:text');
    wind.html("");
    $.get(ctx + '/common/feedlist/', function (result) {
        wind.html(result);
    });
    top.layerIndex = layer.open({
        area: ["800px","600px"],
        type: 1,
        title: "热源信息",
        skin: 'layer-ext-moon', //样式类名
        closeBtn: 1, //不显示关闭按钮
        shift: 2,
        shadeClose: true, //开启遮罩关闭
        content: wind,
        cancel : function(index){
            layer.close(index);
        },
        end :function(){
            wind.remove();

            $textInput.change();
        }
    });
}

var Oncenet = function (option) {
    this.oncenetOption = option ;
    this.init();
};
Oncenet.prototype.init = function () {
    top.documentWindows = this.oncenetOption.windows;
    top.documentText = this.oncenetOption.dom;
};

Oncenet.prototype.show = function(){
    var wind =this.oncenetOption.windows;
    var $this = this.oncenetOption.dom;
    var $span = $this.parent();
    var $textInput=$span.siblings('input:text');
    wind.html("");
    $.get(ctx + '/common/oncenetlist/', function (result) {
        wind.html(result);
    });
    top.layerIndex = layer.open({
        area: ["800px","600px"],
        type: 1,
        title: "一次管网信息",
        skin: 'layer-ext-moon', //样式类名
        closeBtn: 1, //不显示关闭按钮
        shift: 2,
        shadeClose: true, //开启遮罩关闭
        content: wind,
        cancel : function(index){
            layer.close(index);
        },
        end :function(){
            wind.remove();

            $textInput.change();
        }
    });
}


var Station = function (option) {
    this.stationOption = option ;
    this.init();
};
Station.prototype.init = function () {
    top.documentWindows = this.stationOption.windows;
    top.documentText = this.stationOption.dom;
};

Station.prototype.show = function(){
    var wind =this.stationOption.windows;
    var $this = this.stationOption.dom;
    var $span = $this.parent();
    var $textInput=$span.siblings('input:text');
    wind.html("");
    $.get(ctx + '/common/satationlist/', function (result) {
        wind.html(result);
    });
    top.layerIndex = layer.open({
        area: ["800px","600px"],
        type: 1,
        title: "热力站信息",
        skin: 'layer-ext-moon', //样式类名
        closeBtn: 1, //不显示关闭按钮
        shift: 2,
        shadeClose: true, //开启遮罩关闭
        content: wind,
        cancel : function(index){
            layer.close(index);
        },
        end :function(){
            wind.remove();

            $textInput.change();
        }
    });
}

var Secondnet = function (option) {
    this.secondnetOption = option ;
    this.init();
};
Secondnet.prototype.init = function () {
    top.documentWindows = this.secondnetOption.windows;
    top.documentText = this.secondnetOption.dom;
};

Secondnet.prototype.show = function(){
    var wind =this.secondnetOption.windows;
    var $this = this.secondnetOption.dom;
    var $span = $this.parent();
    var $textInput=$span.siblings('input:text');
    wind.html("");
    $.get(ctx + '/common/secondnetlist/', function (result) {
        wind.html(result);
    });
    top.layerIndex = layer.open({
        area: ["800px","600px"],
        type: 1,
        title: "二次管网信息",
        skin: 'layer-ext-moon', //样式类名
        closeBtn: 1, //不显示关闭按钮
        shift: 2,
        shadeClose: true, //开启遮罩关闭
        content: wind,
        cancel : function(index){
            layer.close(index);
        },
        end :function(){
            wind.remove();
            $textInput.change();
        }
    });
}

var Ban = function (option) {
    this.banOption = option ;
    this.init();
};
Ban.prototype.init = function () {
    top.documentWindows = this.banOption.windows;
    top.documentText = this.banOption.dom;
};

Ban.prototype.show = function(){
    var wind =this.banOption.windows;
    var $this = this.banOption.dom;
    var $span = $this.parent();
    var $textInput=$span.siblings('input:text');
    wind.html("");
    $.get(ctx + '/common/banlist/', function (result) {
        wind.html(result);
    });
    top.layerIndex = layer.open({
        area: ["900px","600px"],
        type: 1,
        title: "楼栋信息",
        skin: 'layer-ext-moon', //样式类名
        closeBtn: 1, //不显示关闭按钮
        shift: 2,
        shadeClose: true, //开启遮罩关闭
        content: wind,
        cancel : function(index){
            layer.close(index);
        },
        end :function(){
            wind.remove();
            $textInput.change();
        }
    });
}

var Unit = function (option) {
    this.unitOption = option ;
    this.init();
};
Unit.prototype.init = function () {
    top.documentWindows = this.unitOption.windows;
    top.documentText = this.unitOption.dom;
};

Unit.prototype.show = function(){
    var wind =this.unitOption.windows;
    var $this = this.unitOption.dom;
    var $span = $this.parent();
    var $textInput=$span.siblings('input:text');
    wind.html("");
    $.get(ctx + '/common/unitlist/', function (result) {
        wind.html(result);
    });
    top.layerIndex = layer.open({
        area: ["900px","600px"],
        type: 1,
        title: "单元信息",
        skin: 'layer-ext-moon', //样式类名
        closeBtn: 1, //不显示关闭按钮
        shift: 2,
        shadeClose: true, //开启遮罩关闭
        content: wind,
        cancel : function(index){
            layer.close(index);
        },
        end :function(){
            wind.remove();
            $textInput.change();
        }
    });
}
var Game = function (option) {
    this.gameOption = option ;
    this.init();
};
Game.prototype.init = function () {
    top.documentWindows = this.gameOption.windows;
    top.documentText = this.gameOption.dom;
};

Game.prototype.show = function(){
    var wind =this.gameOption.windows;
    var $this = this.gameOption.dom;
    var $span = $this.parent();
    $.get(ctx + '/common/game/', function (result) {
        wind.html(result);
    });
    top.layerIndex = layer.open({
        type: 1,
        maxmin: true,
        content: wind,
        cancel : function(index){
            layer.close(index);
        },
        end :function(){
            wind.remove();
        }
    });
    layer.full(top.layerIndex);
}