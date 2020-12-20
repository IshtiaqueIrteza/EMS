$(document).ready(function () {

    var table = $('#emp-list-mst').DataTable();

    /*$("#emp-list-mst tbody").on('click', '#btn_edit', function(e){

        let curRow = $(this).closest('tr');
        let col1 = curRow.find('td:eq(0)').text();

        $.ajax({
            url:  "employees/edit",
            data: $.trim(col1),
            type: 'GET',
            dataType: 'json',
            success: function(response) {

                if(response == 1){
                    table.row(curRow).remove().draw(false);
                    showAlertByType("Deleted Successfully", 'S');
                }
                else{
                    showAlertByType("Something went wrong ! Please try again", 'F');
                }
            },
            error: function(xhr, status, error) {
                showAlertByType("Something went wrong !!", 'F');
            }
        });
    });
*/
    $("#emp-list-mst tbody").on('click', '#btn_dlt', function(e){

        let curRow = $(this).closest('tr');
        let col1 = curRow.find('td:eq(0)').text();
        // alert(col1);

        $.confirm({
            title: 'Confirm',
            content: 'Are you sure?',
            buttons: {
                ok: function () {
                    $.ajax({
                        url:  "/api/employees/" + $.trim(col1),
                        type: 'DELETE',
                        dataType: 'json',
                        success: function(response) {

                            if(response == 1){
                                table.row(curRow).remove().draw(false);
                                showAlertByType("Deleted Successfully", 'S');
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
    });

});