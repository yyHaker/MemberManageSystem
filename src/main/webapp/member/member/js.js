/**
 * Created by Zhang Siqi on 2016/10/21.
 */

/**
 * 静态变量存储当前页数
 * @type {number}
 */
var currentPage = 1;
var pageSize = 10;

/*主页地址http://localhost:8089/mms/user/infor.html*/
/**
 * 添加用户，前端传递用户类JOSN，后台不需要返回值
 * url: "/user/add",
 * "{"id":"a","username":"a","password":"a","passwordConform":"a","sex":"male","birthday":"09-02-2012","telephone":"a"}"
 */

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
    member.id=userId;

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
 * 更改接口
 * 前台传递Member类
 * "{"id":"a","username":"a","sex":"male","birthday":"08-02-2012","telephone":"a"}"
 */
function changeUser() {
    var member = serializeJson("#userInformationForm");
    member.id = $('#id').val();

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
     * 更该用户信息，只能提交id、姓名、性别、电话、生日
     */
       member.level="";
       member.flightCount="";
       member.lastVisitedTime="";
       member.flightTime="";
      member.consumptionSum="";
      member.balance="";

    $.ajax({
        type: "post",
        url: path + "/Member/updateMember",
        data: JSON.stringify(member),
        contentType: "application/json; charset=UTF-8",
        success: function (data) {
               if(data.msg=="success"){
                   alert("更改信息成功!");
               }else{
                   alert("更改信息失败")
               }
                searchUser("current");
        },
        error : function(XMLHttpRequest, textStatus, errorThrown) {
          //这个error函数调试时非常有用，如果解析不正确，将会弹出错误框
          /*  alert(XMLHttpRequest.responseText);
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus); // parser error;*/
            alert("更改信息失败");
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
            $('#id').val(data.id);
            $('#username').val(data.username);
            $('#email').val(data.email);
            $('#birthday').val(data.birthday);
            $('#telephone').val(data.telephone);
            $('#storeId').val(data.storeId);
            $('#level').val(data.level);
            $('#flightCount').val(data.flightCount);
            $('#lastVisitedTime').val(data.lastVisitedTime);
            $('#flightTime').val(data.flightTime);
            $('#consumptionSum').val(data.consumptionSum);
            $('#balance').val(data.balance);

           // $('#timeDetail').html("创建时间:" + data.createTime + "<br/>更新时间:" + data.updateTime);

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

function  changePassword(){



    $('#passwordConfirmDiv').removeClass('has-error');
    var member = serializeJson("#passwordForm");
    member.password = $('#password').val();
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

    var user={};
   // alert($('#id').val());
    user.username=$('#id').val();
    user.password=member.password;
    $.ajax({
        type: "post",
        url: path + "/changepassword",
        dataType: "json",
        data: JSON.stringify(user),
        contentType: "application/json; charset=UTF-8",
        success: function (data) {
            if(data.code==1){
                alert("修改密码成功");
            }else {
                alert("修改密码失败");
            }
        },
        error:function (data) {
            alert("修改密码失败");
        }
    });
}

