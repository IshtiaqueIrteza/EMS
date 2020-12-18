$(document).ready(function () {

    function display_loading(){
        document.getElementById("processing_modal_btn").click();
    }

    function close_loading(){
        document.getElementsByClassName("processing-modal-close")[0].click();
    }

    function display_result(){
        document.getElementById("result_modal_btn").click();
    }

    $(document).on("click", "#disburse_sal", function () {

        $.confirm({
            title: 'Confirm',
            content: 'Are you sure?',
            buttons: {
                ok: function () {

                    display_loading();

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
                                //do stuff
                            }
                            else if(response.msg == "3"){
                                //do stuff
                                showAlertByType("Insufficient Balance !", 'W');
                            }
                        },
                        error: function(xhr, status, error) {
                            close_loading();
                            showAlertByType("Something went wrong !!", 'F');
                        }
                    });

                },
                cancel: function () {
                }
            }
        });

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
    let span = document.getElementsByClassName("result-modal-close")[0];

// When the user clicks the button, open the modal
    r_btn.onclick = function() {
        r_modal.style.display = "block";
    };

// When the user clicks on <span> (x), close the modal
    span.onclick = function() {
        r_modal.style.display = "none";
    }

/*// When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == r_modal) {
            r_modal.style.display = "none";
        }
    };*/

});