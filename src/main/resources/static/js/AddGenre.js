$( document ).ready(function() {
    $('#addForm').submit(function (event) {
        event.preventDefault();
        $.ajax(
            '/api/genres',
            {
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({name: $('#name').val()}),
                success: function (result) {
                    window.location.assign('/genres/getAll');
                }
            }
        );
    });
});