const token = $('input[type="hidden"]')[0].value;
function deleteClient(cin) {
    swal({
        title: "Are you sure?",
        text: "Once deleted, you will not be able to recover this client!",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
        .then((willDelete) => {
            if (willDelete) {

                $.ajax({
                    url: '/clients/delete-ajax', type: 'POST',  // http method
                    data: {cin: cin},  // data to submit
                    headers:
                        {
                            'X-CSRF-Token': token
                        },
                    success: function () {
                        swal("Poof! Your client has been deleted!", {
                            icon: "success",
                        });
                        $("#" + cin).remove();
                    }, error: (err) => {
                        console.log(err)
                    }
                });
            } else {
                swal("Your client is safe!");
            }
        });
}
	