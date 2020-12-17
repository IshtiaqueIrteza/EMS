$(document).ready(function () {

    $("#c_bank_acc_info_div :input").prop('disabled', true);

    $(document).on('input', '#cur_bal', function(e){
        e.target.value = e.target.value.replace(/[^0-9]/g,'');
    });

    if(message == 1)
        showAlertByType("Account Credited Successfully", 'S');
    else if(message == 0)
        showAlertByType("Something went wrong ! Please try again", 'F');

});