$(document).ready(function () {

    $(document).on('input', '#emp_mb', function(e){
        e.target.value = e.target.value.replace(/[^0-9]/g,'');
    });

    $(document).on('input', '#acc_num', function(e){
        e.target.value = e.target.value.replace(/[^0-9]/g,'');
    });

    function validateEmpMaster(){

        $(".err_msg").text("");

        if($.trim($("#emp_nm").val()) == ""){
            $("#err_emp_nm").text("Required !!");
            return false;
        }

        if($("#emp_grade").val() == "-1"){
            $("#err_emp_grade").text("Required !!");
            return false;
        }

        if($.trim($("#emp_add").val()) == ""){
            $("#err_emp_add").text("Required !!");
            return false;
        }

        if($.trim($("#emp_mb").val()) == ""){
            $("#err_emp_mb").text("Required !!");
            return false;
        }

        mobile_num = $.trim($("#emp_mb").val());
        if(mobile_num.length != 11 || (mobile_num[0] != '0' || mobile_num[1] != '1')){
            $("#err_emp_mb").text("Invalid mobile number !!");
            return false;
        }

        return true;
    }

    function validateEmpDtl(){

        $(".err_msg").text("");

        if($.trim($("#bank_nm").val()) == ""){
            $("#err_bank_nm").text("Required !!");
            return false;
        }

        if($("#br_nm").val() == ""){
            $("#err_br_nm").text("Required !!");
            return false;
        }

        if($.trim($("#acc_name").val()) == ""){
            $("#err_acc_name").text("Required !!");
            return false;
        }

        if($.trim($("#acc_num").val()) == ""){
            $("#err_acc_num").text("Required !!");
            return false;
        }

        if($("#acc_tp").val() == "-1"){
            $("#err_acc_tp").text("Required !!");
            return false;
        }

        return true;
    }

    $(document).on("click", "#save_emp_btn", function () {

        if(validateEmpMaster()){

            $.confirm({
                title: 'Confirm',
                content: 'Are you sure?',
                buttons: {
                    ok: function () {

                        let empBasicInfo = {};
                        empBasicInfo.empName = $.trim($("#emp_nm").val());
                        empBasicInfo.grade = $.trim($("#emp_grade").val());
                        empBasicInfo.address = $.trim($("#emp_add").val());
                        empBasicInfo.mobile = $.trim($("#emp_mb").val());

                        $.ajax({
                            contentType: 'application/json',
                            url:  "/api/employees",
                            type: 'POST',
                            data: JSON.stringify(empBasicInfo),
                            dataType: 'json',
                            success: function(response) {

                                if(response != 0){
                                    $("#emp_id").val(response);
                                    $("#basic_info_div :input").prop('disabled',true);
                                    $("#display_emp_id").text(response);
                                    $("#acc_info_div").show();
                                    showAlertByType("Employee added successfully", 'S');
                                }
                                else{
                                    showAlertByType("Something went wrong ! Please try again", 'F');
                                }
                            },
                            error: function(xhr, status, error) {
                                showAlertByType("Something went wrong !!", 'F');
                            }
                        });

                    },
                    cancel: function () {
                    }
                }
            });
        }
    });

    $(document).on("click", "#save_dtl_btn", function () {

        if(validateEmpDtl()){

            $.confirm({
                title: 'Confirm',
                content: 'Are you sure?',
                buttons: {
                    ok: function () {

                        let empAccInfo = {};
                        empAccInfo.accName = $.trim($("#acc_name").val());
                        empAccInfo.accNum = $.trim($("#acc_num").val());
                        empAccInfo.accType = $.trim($("#acc_tp").val());
                        empAccInfo.bankName = $.trim($("#bank_nm").val());
                        empAccInfo.branchName = $.trim($("#br_nm").val());

                        $.ajax({
                            contentType: 'application/json',
                            url:  "/api/employees/" + $.trim($("#emp_id").val()) + "/details",
                            type: 'POST',
                            data: JSON.stringify(empAccInfo),
                            dataType: 'json',
                            success: function(response) {

                                if(response != '0'){
                                    showAlertByType("Employee bank info added successfully", 'S');
                                    setTimeout(function () {
                                        window.location.reload();
                                    }, 1500);
                                }
                                else{
                                    showAlertByType("Something went wrong ! Please try again", 'F');
                                }
                            },
                            error: function(xhr, status, error) {
                                showAlertByType("Something went wrong !!", 'F');
                            }
                        });

                    },
                    cancel: function () {
                    }
                }
            });
        }
    });

});