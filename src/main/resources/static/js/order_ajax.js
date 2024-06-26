//文档就绪事件开始
$(function(){
    // 设置收货人
    var name = $("#selectedname").text();
    var address = $("#selectedaddress").text();
    var phone = $("#selectedphone").text();
    var custID = $("#selectedcustID").text();

    $("input[name=custID]").val(custID);
    $("#send-to").text("配送至："+address);
    $("#send-for").text("收货人："+name+" "+phone);

    //点击提交
    $(".submit").click(function(event){
        var custID=$("input[name='custID']").val();
        var total = $(".total-price").text();
        var date=$("input[name=date]").val();
        var time=$("input[name=time]").val();
        $.post("/order/addOrder",{
            "custId":custID,
            "shifu":total,
            "peisongday":date,
            "peisongtime":time
        },function(data){
            alert(data);
            if(data=="success"){
                window.location.href="/order/showorder";
            }
        });
    });

    // 选择配送时段和支付方式时
    $(".time").click(function(){
        $(this).parent().children(".selected").removeClass("selected").addClass("select");
        $(this).removeClass("select").addClass("selected");
        $(this).parent().children("input:hidden").val($(this).text());
    });

    // 切换送花对象时
    $(".words").hide();
    $(".words:first").show();
    $(".for-who").click(function(){
        var index=$(".send-word>div").index($(this));
        $(".words").hide();
        $(".words").eq(index).show();
        $(".for-who").removeClass("who-selected");
        $(this).addClass("who-selected");
    });
    // 当快速选择赠言时
    $(".word-select").click(function(){
        var len=$(this).text().length;
        var str=$(this).text().substr(2,len-2);

        $("textarea[name=message]").val(str);
        $(".type-word").text(202-len);
    });
    //当赠言超过200字时
    $("textarea[name=message]").keyup(function(e){
        var len=$(this).val().length;
        var left=200-len;
        // console.log(len);
        $(".type-word").text(left);
        if(left<=0){
            $(this).val($(this).val().substr(0,199));
        }
    });
    //关闭添加收件人面板
    $(".close-panel").click(function(){
        $("#add-panel").hide();
        $("#hgb").fadeOut("fast");
    });

    // switch (operation) {
    //     case 'add':
    //         $("#hgb").fadeOut("fast");
    //         $("#add-panel").hide();
    //         $.post("/customer/addCustomer",{addName: addName,addPhone: addPhone, address: address},function (data) {
    //             window.location.reload();
    //         });
    //         break;
    //     case 'edit':
    //         //ajax更新服务器数据
    //         $("#hgb").fadeOut("fast");
    //         $("#add-panel").hide();
    //         var editId="#scn"+member;
    //         $.post("/customer/editCustomer", {custId:member,sname: addName,mobile: addPhone, address: address}, function(data) {
    //             // console. Log(data);
    //             if (data == "success") {
    //                 $(editId).find(".select-name").text(addName);
    //                 $(editId).find(".name").text(addName);
    //                 $(editId).find(".address").text(address);
    //                 $(editId).find(".phone").text(addPhone);
    //             }
    //         });
    //
    // }

});

//文档就绪事件结束

// 选择收货人
function Select(obj){
    var name=$(obj).text();
    var address=$(obj).parent().find(".address").text();
    var phone=$(obj).parent().find(".phone").text();
    var custID=$(obj).parent().find(".custID").text();
    $(".select-name").removeClass("selected").addClass("select");
    $(obj).removeClass("select").addClass("selected");

    $("input[name=custID]").val(custID);

    $("#send-to").text("配送至："+address);
    $("#send-for").text("收货人："+name+" "+phone);
}
//添加收货人
function ConsigneeOper(member,operation){
    if("member" == member && operation == "add"){
        $("#edit-btn").hide();
        $("#add-panel").show().css({"left":($(window).width()/2-200)});
        $("#add-btn").show();
        $("#hgb").fadeIn("fast").css({"height":$(window).height(),"top":0});
        $(".edit-panel>input").val("");
    }


}
//编辑收货人信息
function editCustomer(member){
    $("#add-btn").hide();
    $("#add-panel").show().css({"left":($(window).width()/2-200)});
    $("#hgb").fadeIn("fast").css({"height":$(window).height(),"top":0});
    $("#edit-btn").show();
    $("#edit-btn").attr('onclick','javascript:SaveConsignee("'+member+'","edit")');
    var editId="#scn"+member;
    var editName=$(editId).find(".select-name").text();
    var address=$(editId).find(".address").text().split(" ");

    var editProvince=address[0];
    var editCity=address[1];
    var editAddr=address[2];
    if(address.length==1){
        editProvince="";
        editAddr=address[0];
    }
    var editPhone=$(editId).find(".phone").text();
    $("#addName").val(editName);
    $("#addPhone").val(editPhone);
    $("#addProvince").val(editProvince);
    $("#addCity").val(editCity);
    $("#addAddr").val(editAddr);
}
//删除收货人
function deleteCustomer(member){
    // ajax删除服务器数据
    var deleteId="#scn"+member;
    $.post("/customer/deleteCustomer",{"custId":member},function(result){
        if(result=='success'){
            $(deleteId).hide();
        }
    });
}
//将收货人设为默认
function setDefault(member){
    var original=$(".default-address").parent().parent();
    var originalID=$(original).attr('id');
    var idLen=originalID.length;
    var defaultId="#scn"+member;
    originalID=originalID.substr(3,idLen);
    // ajax设置默认
    $.post("/customer/setDefault",{custId:member,originalId:originalID},function(data){
        if(data=="success"){
            window.location.reload();
        }
    });
}
// 保存收货人信息SaveConsignee
function SaveConsignee(member,operation){
    var addName=$("#addName").val();
    var addPhone=$("#addPhone").val();
    var addProvince=$("#addProvince").val();
    var addCity=$("#addCity").val();
    var addAddr=$("#addAddr").val();
    var address=addProvince+" "+addCity+" "+addAddr;
    switch(operation){
        case 'add':
            // ajax添加数据
            $("#hgb").fadeOut("fast");
            $("#add-panel").hide();
            $.post("/customer/addCustomer",{addName:addName,addPhone:addPhone,address:address},function(data){
                window.location.reload();
            });
            break;
        case 'edit':
            //ajax更新服务器数据
            $("#hgb").fadeOut("fast");
            $("#add-panel").hide();
            var editId="#scn"+member;
            $.post("/customer/editCustomer",{custId:member,sname:addName,mobile:addPhone,address:address},function(data){
                // console.log(data);
                if(data=="success"){
                    $(editId).find(".select-name").text(addName);
                    $(editId).find(".name").text(addName);
                    $(editId).find(".address").text(address);
                    $(editId).find(".phone").text(addPhone);
                }
            });
            break;
    }
}
//保存订购人
function AddBuyer(){
    var name=$("#buy-name").val();
    var phone=$("#buy-phone").val();
    var email=$("#buy-email").val();
    // ajax更新服务器数据
    $("#add-buyer").parent().text("").append('<span class="buy-name">'+name
        +'</span><span class="buy-phone">'+phone+'</span><span class="buy-email">'+email+'</span>'
        +'<a href="javascript:EditBuyer()" class="edit-buy">编辑</a>');
    $.post("{:url('member/editMember')}",{name:name,phone:phone});

}
// 修改订购人
function EditBuyer(){
    var name=$(".buy-name").text();
    var phone=$(".buy-phone").text();
    var email=$(".buy-email").text();
    // ajax更新服务器数据
    $(".edit-buy").parent().text("").append('<input id="buy-name" type="text" placeholder="您的姓名">'
        +'<input id="buy-phone" type="text" placeholder="手机号码或电话">'
        +'<input id="buy-email" class="disabled" type="text" value="hhccrr@foxmail.com" disabled="disabled">'
        +'<button onclick="AddBuyer()" id="add-buyer">确定</button>');
    $("#buy-name").val(name);
    $("#buy-phone").val(phone);
    $("#buy-email").val(email);
    $.post("{:url('member/editMember')}",{name:name,phone:phone});
}