/**
 * Created by Geetanjali Oishe on 11/18/2018.
 */
$(document).ready(function () {

    callDTable();
    var oTT;
    //     TableTools.fnGetInstance('tbl'); // Get Table instance
    // var sRow = oTT.fnGetSelected();

    function callDTable() {
        var empName = $('#employee-name').val();
        var empId = $('#employee-gid').val();
        var empOfc = $('#office-dropdown').val();
        var empDept = $('#department-dropdown').val();
        var empDesig = $('#designation-dropdown').val();
        var employmentType = $('#employement-type-dropdown').val();
        var employeeType = $('#employee-type-dropdown').val();
        var table = $("#tableData").DataTable({
            "destroy": true,
            "processing": true, // for show progress bar
            "serverSide": true, // for process server side
            "ordering": false,
            "bFilter": false,
            "ajax": {
                "url": "/hrm_admin/getEmployee",
                "type": "POST",
                "datatype": "json",
                "data": {
                    "empName": empName,
                    "empId": empId,
                    "empOfc": empOfc,
                    "empDept": empDept,
                    "empDesig": empDesig,
                    "employmentType": employmentType,
                    "employeeType":employeeType,
                },
            },
            "language": {
                "search": "Filter:"
            },
            "aoColumns": [
                {
                    // "bVisible": false,
                    "mData": "rowId",
                    "className": "rowId"
                },
                {
                    "mData": "employeeName",
                    "className": "employeeName"
                },
                {
                    "mData": "employeeGid",
                    "className": "employeeGid"
                },
                {
                    "mData": "office",
                    "className": "office"
                },
                {
                    // "bVisible": false,
                    "mData": "officeId",
                    "className": "officeId"
                },
                {
                    "mData": "department",
                    "className": "department"
                },
                {
                    // "bVisible": false,
                    "mData": "departmentId",
                    "className": "departmentId"
                },
                {
                    "mData": "designation",
                    "className": "designation"
                },
                {
                    // "bVisible": false,
                    "mData": "designationId",
                    "className": "designationId"
                },
                {
                    "mData": "employementType",
                    "className": "employementType"
                },
                {
                    // "bVisible": false,
                    "mData": "employementTypeId",
                    "className": "employementTypeId"
                },
                {
                    "mData": "employeeType",
                    "className": "employeeType"
                },
                {
                    // "bVisible": false,
                    "mData": "employeeTypeId",
                    "className": "employeeTypeId"
                },
                {
                    "defaultContent": "<div class='col-xs-6 previous'> " +
                    "<button class='btn btn-success' value='Edit' id='editButton'> " +
                    "<i class='fa fa-pencil'></i></button> </div> " +
                    "<div class='col-xs-6 next'> " +
                    "<button class='btn btn-danger' value='Delete' id='deleteButton'> " +
                    "<i class='fa fa-trash'></i></button> </div>"
                }
            ],
            "fnCreatedRow": function( nRow, aData, iDataIndex ) {
                var values = Object.values( aData );
                $(nRow).attr('id', "row"+values[0]);
                // $('td:eq(0)', nRow).attr("id", "row-"+ values[0]);
                $('td:eq(8)', nRow).find( 'button' ).attr("data-id", values[0]);
                $('td:eq(3)',nRow).attr("data-id", values[4]);
                $('td:eq(4)',nRow).attr("data-id", values[6]);
                $('td:eq(5)',nRow).attr("data-id", values[8]);
                $('td:eq(6)',nRow).attr("data-id", values[10]);
                $('td:eq(7)',nRow).attr("data-id", values[12]);
            }
        });

        table.columns([4,6,8,10,12]).visible(false);
    }



    $('#filterBtn').click(function () {
        callDTable();
    });


    $(document).on("click", "button", function (event) {

        if (this.value == "Edit") {
            $("#updateButton, #refreshButton").css("visibility", "visible");
            // $("#filterBtn").hide();
            formEdit(this);
        }
        if (this.value == "Delete") {
            var cancelText;
            var confirmDelete = confirm("Confirm Delete?");
            if (confirmDelete == true) {
                formDelete(this);
            } else {
                cancelText = "You pressed Cancel!";
            }

        }

    });




    function formEdit(identifier){
        var rowId = "#row" + $(identifier).data('id');
        var selectedRow = $(rowId);
        // selectedRow.find()
       $("#employee-name").val(selectedRow.find('td:eq(1)').text());
       $("#employee-gid").val(selectedRow.find('td:eq(2)').text());
       $("#office-dropdown").val(selectedRow.find('td:eq(3)').attr("data-id"));
       $("#department-dropdown").val(selectedRow.find('td:eq(4)').attr("data-id"));
       $("#designation-dropdown").val(selectedRow.find('td:eq(5)').attr("data-id"));
       $("#employement-type-dropdown").val(selectedRow.find('td:eq(6)').attr("data-id"));
       $("#employee-type-dropdown").val(selectedRow.find('td:eq(7)').attr("data-id"));


    }

    function formDelete(identifier) {
        var rowId = "#row" + $(identifier).data('id');
        var selectedRow = $(rowId);
        var empId = selectedRow.find('td:eq(2)').text();

        $.post('/hrm-admin/deleteEmployee', {
                elementId: empId,
                ajax: true
            },
            function (data) {
                alert(data);
            });
    }




    $('#option-setup').on("submit",function (e){


    });

    $('#refreshButton').click(function() {
        $("#employee")[0].reset();
    });

});







