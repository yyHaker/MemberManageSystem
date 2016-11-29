/**
 * Created by Zhang Siqi on 2016/10/21.
 */

/**
 * 静态变量存储当前页数
 * @type {number}
 */
var currentPage = 1;
var pageSize = 10;

/*主页地址http://localhost:8089/mms/cabin/cabin_list.html*/
/**
 * 添加用户，前端传递用户类JOSN，后台不需要返回值
 * url: "/user/add",
 * "{"id":"a","username":"a","password":"a","passwordConform":"a","sex":"male","birthday":"09-02-2012","telephone":"a"}"
 */
function addCabin() {
    var cabin = serializeJson("#addCabinForm");

    $('#id').attr("placeholder", "会员ID");
    $('#idDiv').removeClass('has-error');

    $('#number').attr("placeholder", "编号");
    $('#numberDiv').removeClass('has-error');

    $('#name').attr("placeholder", "名称");
    $('#nameDiv').removeClass('has-error');

    $('#storeId').attr("placeholder", "门店");
    $('#storeIdDiv').removeClass('has-error');

    $('#rent').attr("placeholder", "租金");
    $('#rentDiv').removeClass('has-error');

    if (cabin.id == null || cabin.id.trim() == "") {
        $('#id').attr("placeholder", "会员ID不能为空");
        $('#idDiv').addClass('has-error');
        return;
    }

    if (cabin.number== null || cabin.number.trim() == "") {
        $('#number').attr("placeholder", "编号不能为空");
        $('#numberDiv').removeClass('has-error');
        return;
    }

    if (cabin.name == null || cabin.name.trim() == "") {
        $('#name').attr("placeholder", "名称不能为空");
        $('#nameDiv').removeClass('has-error');
        return;
    }

    if (cabin.storeId == null || cabin.storeId.trim() == "") {
        $('#storeId').attr("placeholder", "门店不能为空");
        $('#storeIdDiv').removeClass('has-error');
        return;
    }
    if (cabin.rent == null || cabin.rent.trim() == "") {
        $('#rent').attr("placeholder", "租金不能为空");
        $('#rentDiv').removeClass('has-error');
        return;
    }

    /**
     * 添加会员
     */
    $.ajax({
        type: "post",
        url: path + "/Cabin/addNewCabin",
        data: JSON.stringify(cabin),
        contentType: "application/json; charset=UTF-8",
        success: function (data) {
            if (data == null || data == "") {
                alert("添加飞行舱成功!");
                window.location.reload();
            } else {
                $('#id').val(null);
                $('#id').attr("placeholder", "该飞行舱已经存在!");
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
function searchCabin(flag) {
    var cabin = serializeJson("#searchForm");
    if (flag == "search") {
        cabin.beginPageIndex = 1;
    } else if (flag == "pre") {
        cabin.beginPageIndex = currentPage - 1;
    } else if (flag == "next") {
        cabin.beginPageIndex = currentPage + 1;
    }else if (flag == "current"){
        cabin.beginPageIndex = currentPage;
    } else {
        cabin.beginPageIndex = flag;
    }
    cabin.pageSize = pageSize;

    $.ajax({
        type: "post",
        url: path + "/Cabin/searchCabinsListPage",
        dataType: "json",
        data: JSON.stringify(cabin),
        contentType: "application/json; charset=UTF-8",
        success: function (data) {
            changeTable(data.cabinJsonList, "#CabinDetailBody");
            currentPage = data.cabinPageInfo.pageNum;
            var html = "";
            if (data.cabinPageInfo.hasPreviousPage == false)
                html += "<li class='prev'><a href='#'>←上一页</a></li>";
            else
                html += "<li class='prev'><a href='#' onclick='searchCabin(\"pre\")'>← 上一页</a></li>";
            html += "<li><a href='#' onclick='searchCabin(\"search\")'>第一页</a></li>";
            html += "<li><a href='#' onclick='searchCabin(" + data.cabinPageInfo.pages + ")'>最后一页</a></li>";
            if (data.cabinPageInfo.hasNextPage == false)
                html += "<li class='next' ><a href='#'>下一页 → </a></li>";
            else
                html += "<li class='next'><a href='#'  onclick='searchCabin(\"next\")'>下一页 → </a></li>";

            $("#cabinListInfo").html(html);
            html = "第 " + data.cabinPageInfo.pageNum + " 页" + "， 共 " + data.cabinPageInfo.pages + " 页";

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

function changeTable(data, cabinDetailBody) {
    var html = "";
    for (var i = 0; i < data.length; i++) {
        html +=
            "<tr> " +
            "<td>" + (i + 1) + "</td> " +
            "<td>" + "<a href='#' data-toggle='modal'  data-target='#myModal' onclick='getCabin( \"" + data[i].id + "\" )'>" + data[i].id + "</a>" + "</td> " +
            "<td>" + data[i].number + "</td> " +
            "<td>" + data[i].name+ "</td> " +
            "<td>" + data[i].storeId + "</td> " +
            "<td>" + data[i].rent+ "</td> " +
            "<td>" + data[i].createTime + "</td> " +
            "<td>" + data[i].updateTime + "</td> " +
            "</tr>";
    }
    $(cabinDetailBody).html(html);
}
/**
 * 删除Restful格式
 * @param id
 */
function deleteCabin() {
    $.ajax({
        type: "get",
        url: path + "/Cabin/deleteCabin/" + $('#idDialog').val(),
        dataType: "json",
        contentType: "application/json; charset=UTF-8",
        success: function (data) {
            if(data.msg=="success"){
                alert("删除飞行舱成功");
            }else{
                alert("删除飞行舱失败");
            }
            searchCabin("current");
        },
        error : function(XMLHttpRequest, textStatus, errorThrown,data) {
            //这个error函数调试时非常有用，如果解析不正确，将会弹出错误框
            /*  alert(XMLHttpRequest.responseText);
             alert(XMLHttpRequest.status);
             alert(XMLHttpRequest.readyState);
             alert(textStatus); // parser error;*/
            alert("删除飞行舱失败");
            searchCabin("current");
        }
    });
}

/**
 * 更改接口
 * 前台传递Cabin类
 * "{"id":"a","username":"a","sex":"male","birthday":"08-02-2012","telephone":"a"}"
 */
function changeCabin() {
    var cabin = serializeJson("#changeCabinForm");
    cabin.id = $('#idDialog').val();

     if(cabin.number==null||cabin.number==""){
         $('#numberDialog').attr("placeholder", "编号不能为空");
         $('#numberDialogDiv').removeClass('has-error');
         return;
     }

    if (cabin.name == null || cabin.name.trim() == "") {
        $('#nameDialog').attr("placeholder", "名称不能为空");
        $('#nameDialogDiv').addClass('has-error');
        return;
    }

    if (cabin.storeId == null || cabin.storeId.trim() == "") {
        $('#storeIdDialog').attr("placeholder", "门店不能为空");
        $('#storeIdDialogDiv').addClass('has-error');
        return;
    }

    if (cabin.storeId == null || cabin.storeId.trim() == "") {
        $('#storeIdDialog').attr("placeholder", "门店不能为空");
        $('#storeIdDialogDiv').addClass('has-error');
        return;
    }

    if (cabin.rent == null || cabin.rent.trim() == "") {
        $('#rentDialog').attr("placeholder", "租金不能为空");
        $('#rentDialogDiv').addClass('has-error');
        return;
    }
    /**
     * 更该飞行舱信息
     */

    $.ajax({
        type: "post",
        url: path + "/Cabin/updateCabin",
        data: JSON.stringify(cabin),
        contentType: "application/json; charset=UTF-8",
        dataType: "json",
        success: function (data) {
             if(data.msg=="success"){
                 alert("更改飞行舱成功!");
             }else{
                 alert("更改飞行舱失败!");
             }
            searchCabin("current");
              //$('#cancelDialog').click();
        },
        error : function(XMLHttpRequest, textStatus, errorThrown,data) {
          //这个error函数调试时非常有用，如果解析不正确，将会弹出错误框
          /*  alert(XMLHttpRequest.responseText);
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus); // parser error;*/
            alert("更改飞行舱失败");
            searchCabin("current");
        }

    });

}
/**
 * 按照ID查询用户restful格式
 * @param Id
 */
function getCabin(id) {
    $.ajax({
        type: "post",
        url: path + "/Cabin/getCabin/" + id,
        success: function (data) {
            $('#idDialog').val(data.id);
            $('#numberDialog').val(data.number);
            $('#nameDialog').val(data.name);
            $('#storeIdDialog').val(data.storeId);
            $('#rentDialog').val(data.rent);
           // $('#timeDetailDialog').html("创建时间:" + data.createTime + "<br/>更新时间:" + data.updateTime);
        }
    });
}