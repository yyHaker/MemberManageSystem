/**
 * Created by Zhang Siqi on 2016/10/21.
 */

/**
 * 静态变量存储当前页数
 * @type {number}
 */
var currentPage = 1;
var pageSize = 10;

/*主页地址http://localhost:8089/mms/user/user_list.html*/
/**
 * 添加用户，前端传递用户类JOSN，后台不需要返回值
 * url: "/user/add",
 * "{"id":"a","username":"a","password":"a","passwordConform":"a","sex":"male","birthday":"09-02-2012","telephone":"a"}"
 */
function addUser() {
    var member = serializeJson("#addUserForm");
    member.password = $('#password').val();
    $('#id').attr("placeholder", "会员ID");
    $('#idDiv').removeClass('has-error');

    $('#password').attr("placeholder", "密码");
    $('#passwordDiv').removeClass('has-error');

    $('#passwordConfirm').attr("placeholder", "密码确认");
    $('#passwordConfirmDiv').removeClass('has-error');

    $('#username').attr("placeholder", "姓名");
    $('#usernameDiv').removeClass('has-error');

    $('#storeId').attr("placeholder", "门店ID");
    $('#storeIdDiv').removeClass('has-error');

    $('#telephone').attr("placeholder", "电话");
    $('#telephoneDiv').removeClass('has-error');

    if (member.id == null || member.id.trim() == "") {
        $('#id').attr("placeholder", "会员ID不能为空");
        $('#idDiv').addClass('has-error');
        return;
    }
    if (member.username == null || member.username.trim() == "") {
        $('#username').attr("placeholder", "姓名不能为空");
        $('#usernameDiv').addClass('has-error');
        return;
    }
    if (member.password == null || member.password.trim() == "") {
        $('#password').attr("placeholder", "密码不能为空");
        $('#passwordDiv').addClass('has-error');
        return;
    }
    if ($('#passwordConfirm').val() != member.password) {
        $('#passwordConfirm').val(null);
        $('#passwordConfirm').attr("placeholder", "两次密码不一致");
        $('#passwordConfirmDiv').addClass('has-error');
        return;
    }
    if (member.telephone == null || member.telephone.trim() == "") {
        $('#telephone').attr("placeholder", "电话不能为空");
        $('#telephoneDiv').addClass('has-error');
        return;
    }
    if (member.storeId == null || member.storeId.trim() == "") {
        $('#storeId').attr("placeholder", "门店ID不能为空");
        $('#storeIdDiv').addClass('has-error');
        return;
    }

    /**
     * 添加会员
     */
    $.ajax({
        type: "post",
        url: path + "/Member/addNewMember",
        dataType :"json",
        data: JSON.stringify(member),
        contentType: "application/json; charset=UTF-8",
        success: function (data) {
            if (data.msg=="success") {
                alert("添加用户成功");
                window.location.reload();
            } else {
                $('#id').val(null);
                $('#id').attr("placeholder", "该用户已经存在");
                $('#idDiv').addClass('has-error');
            }
        },
        error:function (data) {
             alert("添加用户失败");
        }
    });
}

/**
 * 查询接口，后台返回List<Member>注意日期格式转换
 * 前台传递Member类
 * "{"id":"a","telephone":"a","storeId":"a"}"
 */
function searchUser(flag) {
    var member = serializeJson("#searchForm");
    if (flag == "search") {
        member.beginPageIndex = 1;
    } else if (flag == "pre") {
        member.beginPageIndex = currentPage - 1;
    } else if (flag == "next") {
        member.beginPageIndex = currentPage + 1;
    }else if (flag == "current"){
        member.beginPageIndex = currentPage;
    } else {
        member.beginPageIndex = flag;
    }
    member.pageSize = pageSize;

    $.ajax({
        type: "post",
        url: path + "/Member/searchMembersListPage",
        dataType: "json",
        data: JSON.stringify(member),
        contentType: "application/json; charset=UTF-8",
        success: function (data) {
            changeTable(data.memberJsonList, "#userDetailBody");
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

            $("#userListInfo").html(html);
            html = "第 " + data.pageInfo.pageNum + " 页" + "， 共 " + data.pageInfo.pages + " 页";

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

function changeTable(data, userDetailBody) {
    var html = "";
    for (var i = 0; i < data.length; i++) {
        html +=
            "<tr> " +
            "<td>" + (i + 1) + "</td> " +
            "<td>" + "<a href='#' data-toggle='modal'  data-target='#myModal' onclick='getMember( \"" + data[i].id + "\" )'>" + data[i].id + "</a>" + "</td> " +
            "<td>" + data[i].username + "</td> " +
            "<td>" + data[i].sex + "</td> " +
            "<td>" + data[i].birthday + "</td> " +
            "<td>" + data[i].telephone + "</td> " +
            "<td>" + data[i].storeId + "</td> " +
            "<td>" + data[i].level + "</td> " +
            "<td>" + data[i].points + "</td> " +
            "<td>" + data[i].flightCount + "</td> " +
            "<td>" + data[i].lastVisitedTime + "</td> " +
            "<td>" + data[i].flightTime + "</td> " +
            "<td>" + data[i].consumptionSum + "</td> " +
            "<td>" + data[i].balance + "</td> " +
            "</tr>";
    }

    $(userDetailBody).html(html);
}
/**
 * 删除Restful格式
 * @param id
 */
function deleteUser() {
    $.ajax({
        type: "get",
        url: path + "/Member/deleteMember/" + $('#idDialog').val(),
        contentType: "application/json; charset=UTF-8",
        dataType: "json",
        success: function (data) {
              if(data.msg=="success"){
                  alert("删除用户成功");
              }else {
                  alert("删除用户失败");
              }
             searchUser("current");
        },
        error:function (data) {
              alert("删除用户失败");
            searchUser("current");
        }
    });
}

/**
 * 查询接口
 * 前台传递Member类
 * "{"id":"a","username":"a","sex":"male","birthday":"08-02-2012","telephone":"a"}"
 */
function changeUser() {
    var member = serializeJson("#changeUserForm");
    member.id = $('#idDialog').val();

    $('#usernameDialog').attr("placeholder", "姓名");
    $('#usernameDialogDiv').removeClass('has-error');

    $('#telephoneDialog').attr("placeholder", "电话");
    $('#telephoneDialogDiv').removeClass('has-error');

    if (member.username == null || member.username.trim() == "") {
        $('#usernameDialog').attr("placeholder", "姓名不能为空");
        $('#usernameDialogDiv').addClass('has-error');
        return;
    }

    if (member.telephone == null || member.telephone.trim() == "") {
        $('#telephoneDialog').attr("placeholder", "电话不能为空");
        $('#telephoneDialogDiv').addClass('has-error');
        return;
    }
    /**
     * 更该用户信息
     */

    $.ajax({
        type: "post",
        url: path + "/Member/updateMember",
        data: JSON.stringify(member),
        contentType: "application/json; charset=UTF-8",
        success: function (data) {
                alert("更改用户成功!");
                searchUser("current");
        },
        error : function(XMLHttpRequest, textStatus, errorThrown) {
          //这个error函数调试时非常有用，如果解析不正确，将会弹出错误框
          /*  alert(XMLHttpRequest.responseText);
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus); // parser error;*/
            alert("更改用户失败");
            searchUser("current");
        }

    });

}
/**
 * 按照ID查询用户restful格式
 * @param Id
 */
function getMember(id) {
    $.ajax({
        type: "post",
        url: path + "/Member/getMember/" + id,
        success: function (data) {
            $('#idDialog').val(data.id);
            $('#usernameDialog').val(data.username);
            $('#datepicker').val(data.birthday);
            $('#telephoneDialog').val(data.telephone);
            $('#timeDetail').html("创建时间:" + data.createTime + "<br/>更新时间:" + data.updateTime);

            if (data.sex == "男") {
                $("#sexFemaleInputLabel").html('<input type="radio" name="sex" id="sexFemaleDialog" value="女"> 女');
                $("#sexMaleInputLabel").html('<input type="radio" name="sex" id="sexFemaleDialog" value="男"  checked> 男');
            } else {
                $("#sexFemaleInputLabel").html('<input type="radio" name="sex" id="sexFemaleDialog" value="女" checked> 女');
                $("#sexMaleInputLabel").html('<input type="radio" name="sex" id="sexFemaleDialog" value="男"> 男');
            }
        }
    });
}