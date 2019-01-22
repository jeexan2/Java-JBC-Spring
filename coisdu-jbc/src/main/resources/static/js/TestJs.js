/**
 * Created by Geetanjali Oishe on 11/19/2018.
 */
$(document).ready(function () {
    // var table = $('#employeesTable').DataTable({
    //     "sAjaxSource": "/employees",
    //     "sAjaxDataProp": "",
    //     "order": [[ 0, "asc" ]],
    //     "aoColumns": [
    //         { "mData": "id"},
    //         { "mData": "name" },
    //         { "mData": "lastName" },
    //         { "mData": "email" },
    //         { "mData": "phone" },
    //         { "mData": "active" }
    //     ]
    // })
    var table = $("#employeesTable").DataTable({
        "processing": true, // for show progress bar
        "serverSide": true, // for process server side

        "ajax": {
            "url": "/test/getEmployee",
            "type": "POST",
            "datatype": "json"
        },
        "language": {
            "search": "Filter:"
        },
        "aoColumns": [
            {"mData": "id"},
            {"mData": "name"},
            {"mData": "salary"},
        ]
    });
});