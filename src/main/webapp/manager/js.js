/**
 * Created by Zhang Siqi on 2016/12/4.
 */
var username = "";
function changeManager() {
    var manager = serializeJson("#managerInfor");

    $('#name').attr("placeholder", "名字");
    $('#nameDiv').removeClass('has-error');

    $('#email').attr("placeholder", "电子邮件");
    $('#emailDiv').removeClass('has-error');

    $('#telephone').attr("placeholder", "电话");
    $('#telephoneDiv').removeClass('has-error');

    if (manager.name == null || manager.name.trim() == "") {
        $('#name').attr("placeholder", "名称不能为空");
        $('#nameDiv').addClass('has-error');
        return;
    }

    if (manager.email== null || manager.email.trim() == "") {
        $('#email').attr("placeholder", "电子邮件不能为空");
        $('#emailDiv').addClass('has-error');
        return;
    }
    if (manager.telephone == null || manager.telephone.trim() == "") {
        $('#telephone').attr("placeholder", "电话不能为空");
        $('#telephoneDiv').addClass('has-error');
        return;
    }
    manager.id = $('#id').val();


    /**
     * 添加门店管理员
     */
    $.ajax({
        type: "post",
        url: path + "/Manager/updateOldManager",
        data: JSON.stringify(manager),
        contentType: "application/json; charset=UTF-8",
        dataType:"json",
        success: function (data) {
            if (data.msg=="success") {
                alert("修改成功");
                window.location.reload();
            } else {
               alert("修改失败");
            }
        }
    });
}

function changePassword() {
    $('#password').attr("placeholder", "密码");
    $('#passwordDiv').removeClass('has-error');

    $('#passwordConfirm').attr("placeholder", "密码确认");
    $('#passwordConfirmDiv').removeClass('has-error');

    var manager = {};
    manager.password = $("#password").val();
    manager.username = $("#id").val();

    if (manager.password == null || manager.password.trim() == "") {
        $('#password').attr("placeholder", "密码不能为空");
        $('#passwordDiv').addClass('has-error');
        return;
    }

    if ($('#passwordConfirm').val() != manager.password) {
        $('#passwordConfirm').val(null);
        $('#passwordConfirm').attr("placeholder", "两次密码不一致");
        $('#passwordConfirmDiv').addClass('has-error');
        return;
    }

    $.ajax({
        type: "post",
        url: path + "/changepassword",
        data: JSON.stringify(manager),
        contentType: "application/json; charset=UTF-8",
        dataType:"json",
        success: function (data) {
            if (data.code==1) {
                alert("修改成功");
                window.location.reload();
            } else {
                alert("修改失败");
            }
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

function getManager(){
    $.ajax({
        type: "post",
        url: path + "/getUser",
        contentType: "application/json; charset=UTF-8",
        dataType:"json",
        success: function (data) {
            if (data.role == "error"){
                alert("发生错误")
            }else {
                changeForm(data);
            }
        }
    });
}

function changeForm(data) {
    $("#id").val(data.managerJson.id);
    $('#name').val(data.managerJson.name);
    $('#email').val(data.managerJson.email);
    $('#telephone').val(data.managerJson.telephone);
}