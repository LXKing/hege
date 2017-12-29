
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

    $.fn.zTree.init($("#grouplist"), {
        check: {
            enable: true
        },
        data: {
            simpleData: {
                enable: true
            }
        }
    }, nodes2);


}

var nodes = [
    { id: 1, pId: 0, name: "父节点1" },
    { id: 11, pId: 1, name: "子节点1" },
    { id: 12, pId: 1, name: "子节点2" }
];
var nodes2 = [
    { id: 1, pId: 0, name: "父节点11",open:true, checked: true  },
    { id: 11, pId: 1, name: "子节点1", checked: true },
    { id: 12, pId: 1, name: "子节点1", checked: true },
    { id: 13, pId: 1, name: "子节点1", checked: true },
    { id: 14, pId: 1, name: "子节点1", checked: true },
    { id: 15, pId: 1, name: "子节点1", checked: true },
    { id: 16, pId: 1, name: "子节点1", checked: true },
    { id: 17, pId: 1, name: "子节点1", checked: true },
    { id: 18, pId: 1, name: "子节点1", checked: true },
    { id: 19, pId: 1, name: "子节点1", checked: true },
    { id: 20, pId: 1, name: "子节点1", checked: true },
    { id: 21, pId: 1, name: "子节点1", checked: true },
    { id: 22, pId: 1, name: "子节点1", checked: true },
    { id: 23, pId: 1, name: "子节点1", checked: true },
    { id: 24, pId: 1, name: "子节点1", checked: true },
    { id: 25, pId: 1, name: "子节点1", checked: true },
    { id: 26, pId: 1, name: "子节点1", checked: true },
    { id: 27, pId: 1, name: "子节点1", checked: true },
    { id: 12, pId: 1, name: "子节点2", checked: true }
];

function showGroupList() {

}