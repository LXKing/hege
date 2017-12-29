
$(function () {
    loadDataFun();
});

function loadDataFun() {

    // http://www.treejs.cn/v3/main.php#_zTreeInfo

    var setting = {
        data: {
            simpleData: {
                enable: true
            }
        }
    };
    zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, nodes);

}

var nodes = [
    { id: 1, pId: 0, name: "父节点1" },
    { id: 11, pId: 1, name: "子节点1" },
    { id: 12, pId: 1, name: "子节点2" }
];