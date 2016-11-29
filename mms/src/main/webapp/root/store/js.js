/**
 * Created by Zhang Siqi on 2016/10/21.
 */

/**
 * 静态变量存储当前页数
 * @type {number}
 */
var currentPage = 1;
var pageSize = 10;

/*主页地址http://localhost:8089/MemberManageSystem/user/store_list.html*/
/**
 * 添加用户，前端传递用户类JOSN，后台不需要返回值
 * url: "/user/add",
 * "{"id":"a","username":"a","password":"a","passwordConform":"a","sex":"male","birthday":"09-02-2012","telephone":"a"}"
 */
function addStore() {
    var store = serializeJson("#addUserForm");
    $('#id').attr("placeholder", "门店ID");
    $('#idDiv').removeClass('has-error');

    $('#name').attr("placeholder", "门店名称");
    $('#nameDiv').removeClass('has-error');

    $('#telephone').attr("placeholder", "电话");
    $('#telephoneDiv').removeClass('has-error');

    $('#city').attr("placeholder", "所在城市");
    $('#cityDiv').removeClass('has-error');

    $('#address').attr("placeholder", "地址");
    $('#addressDiv').removeClass('has-error');

    $('#managerId').attr("placeholder", "管理员ID");
    $('#managerIdDiv').removeClass('has-error');



    if (store.id == null || store.id.trim() == "") {
        $('#id').attr("placeholder", "门店ID不能为空");
        $('#idDiv').addClass('has-error');
        return;
    }
    if (store.name == null || store.name.trim() == "") {
        $('#name').attr("placeholder", "门店名称不能为空");
        $('#nameDiv').addClass('has-error');
        return;
    }

    if (store.telephone == null || store.telephone.trim() == "") {
        $('#telephone').attr("placeholder", "电话不能为空");
        $('#telephoneDiv').addClass('has-error');
        return;
    }
    if (store.city == null || store.city.trim() == "") {
        $('#city').attr("placeholder", "所在城市不能为空");
        $('#cityDiv').addClass('has-error');
        return;
    }
    if (store.address == null || store.address.trim() == "") {
        $('#address').attr("placeholder", "地址不能为空");
        $('#addressDiv').addClass('has-error');
        return;
    }
    if (store.managerId == null || store.managerId.trim() == "") {
        $('#managerId').attr("placeholder", "管理员ID不能为空");
        $('#managerIdDiv').addClass('has-error');
        return;
    }
    /**
     * 添加会员
     */
    $.ajax({
        type: "post",
        url: path + "/store/addstore",
        dataType: "json",
        data: JSON.stringify(store),
        contentType: "application/json; charset=UTF-8",
        success: function (data) {
            if (data.code == 1) {
                alert("添加成功");
            } else {
                $('#id').val(null);
                $('#id').attr("placeholder", "该门店已经存在");
                $('#idDiv').addClass('has-error');
            }
        }
    });
}

/**
 * 查询接口，后台返回List<Member>注意日期格式转换
 * 前台传递Member类
 * "{"id":"a","telephone":"a","storeId":"a"}"
 */
function searchStore(flag) {
    var store = serializeJson("#searchForm");
    var request = {};
    request.store = store;
    if (flag == "search") {
        request.beginPageIndex = 1;
    } else if (flag == "pre") {
        request.beginPageIndex = currentPage - 1;
    } else if (flag == "next") {
        request.beginPageIndex = currentPage + 1;
    } else {
        request.beginPageIndex = flag;
    }
    request.pageSize = pageSize;

    $.ajax({
        type: "post",
        url: path + "/store/searchstorespages",
        dataType: "json",
        data: JSON.stringify(request),
        contentType: "application/json; charset=UTF-8",
        success: function (data) {
            changeTable(data.stores, "#detailBody");
            currentPage = data.pageInfo.pageNum;

            var html = "";
            if (data.pageInfo.hasPreviousPage == false)
                html += "<li class='prev'><a href='#'>←上一页</a></li>";
            else
                html += "<li class='prev'><a href='#' onclick='searchUser(\"pre\")'>← 上一页</a></li>";
            html += "<li><a href='#' onclick='searchUser(\"search\")'>第一页</a></li>";
            html += "<li><a href='#' onclick='searchUser(" + data.pageInfo.pages + ")'>最后一页</a></li>";
            if (data.pageInfo.hasNextPage == false)
                html += "<li class='next' ><a href='#'>下一页 → </a></li>";
            else
                html += "<li class='next'><a href='#'  onclick='searchUser(\"next\")'>下一页 → </a></li>";

            $("#pageButton").html(html);
            html = "第 " + data.pageInfo.pageNum + " 页" + "， 共 " + data.pageInfo.pages + " 页";

            $("#pageInfo").html(html);
        }
    });
}


function serializeJson(id) {
    var serializeObj = {};
    $($(id).serializeArray()).each(function () {
        serializeObj[this.name] = this.value;
    });
    return serializeObj;
}

function changeTable(data, detailBody) {
    var html = "";
    for (var i = 0; i < data.length; i++) {
        html +=
            "<tr> " +
            "<td>" + (i + 1) + "</td> " +
            "<td>" + "<a href='#' data-toggle='modal' data-target='#myModal' onclick='getStore( \"" + data[i].id + "\" )'>" + data[i].id + "</a>" + "</td> " +
            "<td>" + data[i].name + "</td> " +
            "<td>" + data[i].city + "</td> " +
            "<td>" + data[i].managerId + "</td> " +
            "<td title='" + data[i].address + "'>" + (data[i].address).substring(0, 100)+ "</td> " +
            "<td>" + data[i].telephone + "</td> " +
            "<td>" + data[i].flag + "</td> " +
            "<td>" + data[i].createTime + "</td> " +
            "<td>" + data[i].updateTime + "</td> " +
            "</tr>";
    }

    $(detailBody).html(html);
}
/**
 * 删除Restful格式
 * @param id
 */
function deleteStore(id) {
    $('#dialogCancelButton').click();
    var request = {};
    request.storeId = id;
    $.ajax({
        type: "post",
        url: path + "/store/deletestore",
        dataType: "json",
        data: JSON.stringify(request),
        contentType: "application/json; charset=UTF-8",
        success: function (data) {
            alert(data.message);
        }
    });
}

/**
 * 查询接口
 * 前台传递Member类
 * "{"id":"a","username":"a","sex":"male","birthday":"08-02-2012","telephone":"a"}"
 */
function changeStore() {
    var store = serializeJson("#changeForm");
    store.id = $('#idDialog').val();

    $('#nameDialog').attr("placeholder", "门店名称");
    $('#nameDialogDiv').removeClass('has-error');

    $('#telephoneDialog').attr("placeholder", "电话");
    $('#telephoneDialogDiv').removeClass('has-error');
    
    $('#cityDialog').attr("placeholder", "所在城市");
    $('#cityDialogDiv').removeClass('has-error');

    $('#addressDialog').attr("placeholder", "地址");
    $('#addressDialogDiv').removeClass('has-error');

    if (store.name == null || store.name.trim() == "") {
        $('#nameDialog').attr("placeholder", "门店名称不能为空");
        $('#nameDialogDiv').addClass('has-error');
        return;
    }

    if (store.telephone == null || store.telephone.trim() == "") {
        $('#telephoneDialog').attr("placeholder", "电话不能为空");
        $('#telephoneDialogDiv').addClass('has-error');
        return;
    }

    if (store.city == null || store.city.trim() == "") {
        $('#cityDialog').attr("placeholder", "所在城市不能为空");
        $('#cityDialogDiv').addClass('has-error');
        return;
    }

    if (store.address == null || store.address.trim() == "") {
        $('#addressDialog').attr("placeholder", "地址不能为空");
        $('#addressDialogDiv').addClass('has-error');
        return;
    }
    /**
     * 更该用户信息
     */
    $('#dialogCancelButton').click();
    $.ajax({
        type: "post",
        url: path + "/store/changestore",
        dataType: "json",
        data: JSON.stringify(store),
        contentType: "application/json; charset=UTF-8",
        success: function (data) {
            alert(data.message);
        }
    });
}
/**
 * 按照ID查询用户restful格式
 * @param Id
 */
function getStore(id) {
    var request = {};
    request.storeId = id;
    $.ajax({
        type: "post",
        url: path + "/store/getstore",
        dataType: "json",
        data: JSON.stringify(request),
        contentType: "application/json; charset=UTF-8",
        success: function (data) {
            $('#idDialog').val(data.id);
            $('#nameDialog').val(data.name);
            $('#telephoneDialog').val(data.telephone);
            $('#cityDialog').val(data.city);
            $('#addressDialog').val(data.address);

            if (data.flag == "true") {
                $("#able").text("禁用");
                $("#able").attr('onclick', "disableStore(" + id + ")");
            } else {
                $("#able").text("激活");
                $("#able").attr('onclick', "enableStore(" + id + ")");
            }
        }
    });
}

function enableStore(id) {
    var request = {};
    request.storeId = id;
    $('#dialogCancelButton').click();
    $.ajax({
        type: "post",
        url: path + "/store/enablestore",
        dataType: "json",
        data: JSON.stringify(request),
        contentType: "application/json; charset=UTF-8",
        success: function (data) {
            alert(data.message);
        }
    });
}


function disableStore(id) {
    var request = {};
    request.storeId = id;
    $('#dialogCancelButton').click();
    $.ajax({
        type: "post",
        url: path + "/store/disablestore",
        dataType: "json",
        data: JSON.stringify(request),
        contentType: "application/json; charset=UTF-8",
        success: function (data) {
            alert(data.message);
        }
    });
}