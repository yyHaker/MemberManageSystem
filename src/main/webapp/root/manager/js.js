/**
 * Created by Zhang Siqi on 2016/10/21.
 */

/**
 * 静态变量存储当前页数
 * @type {number}
 */
var currentPage = 1;
var pageSize = 10;

/**
 * 添加用户，前端传递用户类JOSN，后台不需要返回值
 * url: "/user/add",
 * "{"id":"a","username":"a","password":"a","passwordConform":"a","sex":"male","birthday":"09-02-2012","telephone":"a"}"
 */
function addManager() {
    var manager = serializeJson("#addManagerForm");

    $('#id').attr("placeholder", "管理员ID");
    $('#idDiv').removeClass('has-error');

    $('#name').attr("placeholder", "名字");
    $('#nameDiv').removeClass('has-error');

    $('#password').attr("placeholder", "密码");
    $('#passwordDiv').removeClass('has-error');

    $('#passwordConfirm').attr("placeholder", "密码确认");
    $('#passwordConfirmDiv').removeClass('has-error');

    $('#email').attr("placeholder", "电子邮件");
    $('#emailDiv').removeClass('has-error');

    $('#telephone').attr("placeholder", "电话");
    $('#telephoneDiv').removeClass('has-error');

    if (manager.id == null || manager.id.trim() == "") {
        $('#id').attr("placeholder", "门店管理员ID不能为空");
        $('#idDiv').addClass('has-error');
        return;
    }

    if (manager.name == null || manager.name.trim() == "") {
        $('#name').attr("placeholder", "名称不能为空");
        $('#nameDiv').removeClass('has-error');
        return;
    }

    if (manager.password == null || manager.password.trim() == "") {
        $('#password').attr("placeholder", "密码不能为空");
        $('#passwordDiv').addClass('has-error');
        return;
    }
    manager.password=$('#password').val();
    if ($('#passwordConfirm').val() != manager.password) {
        $('#passwordConfirm').val(null);
        $('#passwordConfirm').attr("placeholder", "两次密码不一致");
        $('#passwordConfirmDiv').addClass('has-error');
        return;
    }


    if (manager.email== null || manager.email.trim() == "") {
        $('#email').attr("placeholder", "电子邮件不能为空");
        $('#emailDiv').removeClass('has-error');
        return;
    }
    if (manager.telephone == null || manager.telephone.trim() == "") {
        $('#telephone').attr("placeholder", "电话不能为空");
        $('#telephoneDiv').removeClass('has-error');
        return;
    }

    /**
     * 添加门店管理员
     */
    $.ajax({
        type: "post",
        url: path + "/Manager/addNewManager",
        data: JSON.stringify(manager),
        contentType: "application/json; charset=UTF-8",
        dataType:"json",
        success: function (data) {
            if (data.msg=="success") {
                alert("添加门店管理员成功!");
                window.location.reload();
            } else {
                $('#id').val(null);
                $('#id').attr("placeholder", "该门店管理员已经存在!");
                $('#idDiv').addClass('has-error');
            }
        }
    });
}

/**
 * 查询接口，后台返回List<Cabin>注意日期格式转换
 * 前台传递Cabin类
 * "{"id":"a","telephone":"a","storeId":"a"}"
 */
function searchManager(flag) {
    var manager = serializeJson("#searchForm");
    if (flag == "search") {
        manager.beginIndex = 1;
    } else if (flag == "pre") {
        manager.beginIndex = currentPage - 1;
    } else if (flag == "next") {
        manager.beginIndex = currentPage + 1;
    }else if (flag == "current"){
        manager.beginIndex = currentPage;
    } else {
        manager.beginIndex = flag;
    }
    manager.pageSize = pageSize;

    $.ajax({
        type: "post",
        url: path + "/Manager/searchManagerListPage",
        dataType: "json",
        data: JSON.stringify(manager),
        contentType: "application/json; charset=UTF-8",
        success: function (data) {
            changeTable(data.managerJsonList, "#ManagerDetailBody");
            currentPage = data.managerPageInfo.pageNum;
            var html = "";
            if (data.managerPageInfo.hasPreviousPage == false)
                html += "<li class='prev'><a href='#'>←上一页</a></li>";
            else
                html += "<li class='prev'><a href='#' onclick='searchManager(\"pre\")'>← 上一页</a></li>";
            html += "<li><a href='#' onclick='searchManager(\"search\")'>第一页</a></li>";
            html += "<li><a href='#' onclick='searchManager(" + data.managerPageInfo.pages + ")'>最后一页</a></li>";
            if (data.managerPageInfo.hasNextPage == false)
                html += "<li class='next' ><a href='#'>下一页 → </a></li>";
            else
                html += "<li class='next'><a href='#'  onclick='searchManager(\"next\")'>下一页 → </a></li>";

            $("#managerListInfo").html(html);
            html = "第 " + data.managerPageInfo.pageNum + " 页" + "， 共 " + data.managerPageInfo.pages + " 页";

            $("#pageButton").html(html);
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

function changeTable(data, managerDetailBody) {
    var html = "";
    for (var i = 0; i < data.length; i++) {
        html +=
            "<tr> " +
            "<td>" + (i + 1) + "</td> " +
            "<td>" + "<a href='#' data-toggle='modal'  data-target='#myModal' onclick='getManager( \"" + data[i].id + "\" )'>" + data[i].id + "</a>" + "</td> " +
            "<td>" + data[i].name + "</td> " +
            "<td>" + data[i].email+ "</td> " +
            "<td>" + data[i].telephone+ "</td> " +
            "<td>" + data[i].createTime + "</td> " +
            "<td>" + data[i].updateTime + "</td> " +
            "</tr>";
    }
    $(managerDetailBody).html(html);
}
/**
 * 删除Restful格式
 * @param id
 */
function deleteManager() {
    $.ajax({
        type: "get",
        url: path + "/Manager/deleteManager/" + $('#idDialog').val(),
        dataType: "json",
        contentType: "application/json; charset=UTF-8",
        success: function (data) {
            if(data.msg=="success"){
                alert("删除门店管理员成功");
            }else{
                alert("删除门店管理员失败");
            }
            searchManager("current");
        },
        error : function(XMLHttpRequest, textStatus, errorThrown,data) {
            //这个error函数调试时非常有用，如果解析不正确，将会弹出错误框
            /*  alert(XMLHttpRequest.responseText);
             alert(XMLHttpRequest.status);
             alert(XMLHttpRequest.readyState);
             alert(textStatus); // parser error;*/
            alert("删除门店管理员失败");
            searchManager("current");
        }
    });
}

/**
 * 更改接口
 * 前台传递Cabin类
 * "{"id":"a","username":"a","sex":"male","birthday":"08-02-2012","telephone":"a"}"
 */
function changeManager() {
    var manager = serializeJson("#changeManagerForm");
    manager.id = $('#idDialog').val();


    if (manager.name == null || manager.name.trim() == "") {
        $('#nameDialog').attr("placeholder", "名字不能为空");
        $('#nameDialogDiv').addClass('has-error');
        return;
    }

    if (manager.email == null || manager.email.trim() == "") {
        $('#emailDialog').attr("placeholder", "电子邮件不能为空");
        $('#emailDialogDiv').addClass('has-error');
        return;
    }

    if (manager.telephone== null || manager.telephone.trim() == "") {
        $('#telephoneDialog').attr("placeholder", "电话不能为空");
        $('#telephoneDialogDiv').addClass('has-error');
        return;
    }

    /**
     * 更该管理员信息
     */

    $.ajax({
        type: "post",
        url: path + "/Manager/updateOldManager",
        data: JSON.stringify(manager),
        contentType: "application/json; charset=UTF-8",
        dataType: "json",
        success: function (data) {
             if(data.msg=="success"){
                 alert("更改门店管理员成功!");
             }else{
                 alert("更改门店管理员失败!");
             }
            searchManager("current");
              //$('#cancelDialog').click();
        },
        error : function(XMLHttpRequest, textStatus, errorThrown,data) {
          //这个error函数调试时非常有用，如果解析不正确，将会弹出错误框
          /*  alert(XMLHttpRequest.responseText);
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus); // parser error;*/
            alert("更改门店管理员失败");
            searchManager("current");
        }

    });

}
/**
 * 按照ID查询用户restful格式
 * @param Id
 */
function getManager(id) {
    $.ajax({
        type: "post",
        url: path + "/Manager/getManager/" + id,
        success: function (data) {
            $('#idDialog').val(data.id);
            $('#nameDialog').val(data.name);
            $('#emailDialog').val(data.email);
            $('#telephoneDialog').val(data.telephone);
           // $('#timeDetailDialog').html("创建时间:" + data.createTime + "<br/>更新时间:" + data.updateTime);
        }
    });
}