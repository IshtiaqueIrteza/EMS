$(document).ready(function () {

    $(document).on('input', '#lowest_grade_basic', function(e){
        e.target.value = e.target.value.replace(/[^0-9]/g,'');
    });

    function validate(){
        if($.trim($("#lowest_grade_basic").val()) == ""){
            $("#err_lowest_grade_basic").text("Required !");
            return false;
        }
        return true;
    }

    $(document).on("click", "#process_btn", function () {

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