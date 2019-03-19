$(function () {
    $.ajax(
        '/api/genres/' + $('#editId').val(),
        {
            type: 'GET',
            success: function (genre) {
                $("#name").val(genre.name);
            }
        }
    );
});
$( document ).ready(function() {
    $('#editForm').submit(function (event) {
        event.preventDefault();
        $.ajax(
            '/api/genres',
            {
                type: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify({id: $('#editId').val(), name: $('#name').val()}),
                success: function (result) {
                    window.location.assign('/genres/getAll');
                }
            }
        );
    });
    $('#deleteForm').submit(function (event) {
        event.preventDefault();
        $.ajax(
            '/api/genres/' + $('#deleteId').val(),
            {
                type: 'DELETE',
                success: function (result) {
                    window.location.assign('/genres/getAll');
                }
            }
        );
    });
});