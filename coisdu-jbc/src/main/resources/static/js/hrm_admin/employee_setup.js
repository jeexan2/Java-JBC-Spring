$(document).ready(function () {
    if($("#emp_photo_status_id").val() === "0") $("#emp_photo_prev_id").hide();
    if($("#emp_sig_status_id").val() === "0") $("#emp_sig_prev_id").hide();

    var changeOnBank = function (id) {
        var bankId = $("#emp_bank_name_id").val();
        $.post("/hrm_admin/get_branch_details", {
                bankId: bankId
            },
            function (data, status) {
                var branches = $('#emp_branch_name_id');
                branches.empty();
                $.each(data, function (index, branch) {
                    branches.append($('<option/>', {
                        value: branch.lookupId,
                        text: branch.name
                    }));
                });
                if (data.length != 0) {
                    if (typeof id !== "undefined") {
                        $('#emp_branch_name_id').val(id);
                    }
                }
            });
    }

    var confirmGeneralDialog = function (text) {
        $.confirm({
            title: 'Confirm!',
            content: text,
            buttons: {
                confirm: {
                    btnClass: 'btn-info',
                    keys: ['enter'],
                    action: function () {
                        $("#general_form").submit();
                    }
                },
                cancel: function () {

                }
            }
        });
    };

    var validation = function () {
        var isValid = true;
        $(".err_msg").text("");
        $(".mand").each(function(){
            if(this.value.length == 0) {
                $("#err_" + this.id ).text("Please enter the value");
                isValid = false;
            }
        });
        if($("#emp_photo_status_id").val() === 0) {
            $("err_emp_photo_id").text("Please enter your photo");
            isValid = false;
        }
        if($("#emp_sig_status_id").val() === 0) {
            $("err_emp_sig_id").text("Please enter your signature");
            isValid = false;
        }
        return isValid;
    };

    function readURL(input, target) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $(target).attr('src', e.target.result);
            }

            reader.readAsDataURL(input.files[0]);
        }
    }

    changeOnBank();

    $("#emp_photo_id").change(function () {
        readURL(this, "#emp_photo_prev_id");
        $("#emp_photo_prev_id").show();
        $("#emp_photo_status_id").val("1");
    });

    $("#emp_sig_id").change(function () {
        readURL(this, "#emp_sig_prev_id");
        $("#emp_sig_prev_id").show();
        $("#emp_sig_status_id").val("1");
    });

    $(document).on("change", "#emp_bank_name_id", function () {
        changeOnBank();
    });

    $("#general_form_submit").click(function (event) {
        event.preventDefault();
        var isVaild = validation();
        if(isVaild) {
            confirmGeneralDialog("Are you sure to save general information?");
        }
    });

    $(document).on("keyup", ".mand", function () {
        $("#err_" + this.id).text("");
    });

    $(document).on("change", "#emp_dob_id", function () {
        // lpr and retirement calc
    });

    // var setBranchOnEd = function () {
    //     var branchId = $("#edit_branch_id").val();
    //     changeOnBank(branchId);
    // }

});