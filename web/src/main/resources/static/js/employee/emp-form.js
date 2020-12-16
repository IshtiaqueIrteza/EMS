$(document).ready(function () {

    $(document).on('input', '#emp_mb', function(e){
        e.target.value = e.target.value.replace(/[^0-9]/g,'');
    });

    function validate(){

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

    $(document).on("click", "#save_emp_btn", function () {

        if(validate()){

            $("#err_lowest_grade_basic").text("");

            $.confirm({
                title: 'Confirm',
                content: 'Are you sure?',
                buttons: {
                    ok: function () {

                        $.ajax({
                            url:  "init-sal-calc",
                            type: 'POST',
                            data: {
                                basic: $.trim($("#lowest_grade_basic").val())
                            },
                            dataType: 'json',
                            success: function(response) {

                                if(response == "1"){
                                    showAlertByType("Salary calculation processed successfully", 'S');
                                    setTimeout(function () {
                                        window.location.href = "/employees/grade-salary-sheet";
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