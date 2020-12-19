$(document).ready(function () {

    $(document).on('input', '#cr_amt', function(e){
        e.target.value = e.target.value.replace(/[^0-9]/g,'');
    });

    function display_loading(){
        document.getElementById("processing_modal_btn").click();
    }

    function close_loading(){
        document.getElementsByClassName("processing-modal-close")[0].click();
    }

    function display_result(){
        document.getElementById("result_modal_btn").click();
    }

    function disburse_process(){
        $.ajax({
            url:  "salary-disburse-process",
            type: 'POST',
            dataType: 'json',
            success: function(response) {

                close_loading();

                if(response.msg == "0"){
                    showAlertByType("Something went wrong ! Please try again", 'F');
                }
                else if(response.msg == "1"){
                    showAlertByType("Salary processed successfully", 'S');
                    setTimeout(function () {
                        window.location.reload();
                    }, 1500);
                }
                else if(response.msg == "2"){
                    showAlertByType("Insufficient Balance !", 'W');

                    $("#paid_employee_list tbody tr").remove();
                    $("#unpaid_employee_list tbody tr").remove();

                    $("#paid_employee_list tbody").append(response.tableHTMLPaid);
                    $("#total_paid_sal").text(response.empAggregateSalary);
                    $("#total_paid_emp").text(response.totalEmployee);
                    $("#unpaid_employee_list tbody").append(response.tableHTMLUnpaid);
                    $("#total_unpaid_sal").text(response.unpaidEmpInfo.empAggregateSalary);
                    $("#total_unpaid_emp").text(response.unpaidEmpInfo.totalEmployee);
                    $("#cp_cur_bal").text(response.companyCurBal);
                    $("#cr_amt").val(response.unpaidEmpInfo.empAggregateSalary-response.companyCurBal);
                    display_result();
                }
            },
            error: function(xhr, status, error) {
                close_loading();
                showAlertByType("Something went wrong !!", 'F');
            }
        });
    }

    $(document).on("click", "#disburse_sal", function () {

        $.confirm({
            title: 'Confirm',
            content: 'Are you sure?',
            buttons: {
                ok: function () {
                    display_loading();
                    disburse_process();
                },
                cancel: function () {
                }
            }
        });

    });

    $(document).on("click", "#credit_cp_acc", function () {
        $.ajax({
            url:  "update-company-acc-bal-req",
            type: 'POST',
            data: {
                balanceToCredit: $.trim($("#cr_amt").val())
            },
            dataType: 'json',
            success: function(response) {

                if(response == "0"){
                    showAlertByType("Something went wrong ! Please try again", 'F');
                }
                else{
                    $("#cp_cur_bal").text(response);
                    showAlertByType("Account credited successfully", 'S');
                }

            },
            error: function(xhr, status, error) {
                close_loading();
                showAlertByType("Something went wrong !!", 'F');
            }
        });
    });

    $(document).on("click", "#process_again", function () {
        //close modal first
        r_span.click();
        display_loading();
        disburse_process();
    });

    // Get the processing modal
    let p_modal = document.getElementById("processing_modal");

// Get the button that opens the modal
    let p_modal_btn = document.getElementById("processing_modal_btn");

// Get the <span> element that closes the modal
    let p_modal_span = document.getElementsByClassName("processing-modal-close")[0];

// When the user clicks on the button, open the modal
    p_modal_btn.onclick = function() {
        p_modal.style.display = "block";
    };

// When the user clicks on <span> (x), close the modal
    p_modal_span.onclick = function() {
        p_modal.style.display = "none";
    };

// When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == p_modal) {
            p_modal.style.display = "none";
        }
    };

    // Get the result modal
    let r_modal = document.getElementById("result_modal");

// Get the button that opens the modal
    let r_btn = document.getElementById("result_modal_btn");

// Get the <span> element that closes the modal
    let r_span = document.getElementsByClassName("result-modal-close")[0];

// When the user clicks the button, open the modal
    r_btn.onclick = function() {
        r_modal.style.display = "block";
    };

// When the user clicks on <span> (x), close the modal
    r_span.onclick = function() {
        r_modal.style.display = "none";
    }

// When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == r_modal) {
            r_modal.style.display = "none";
        }
    };

});