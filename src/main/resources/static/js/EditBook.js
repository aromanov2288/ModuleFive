$(function () {
    $.ajax(
        '/api/books/' + $('#editId').val(),
        {
            type: 'GET',
            success: function (book) {
                $("#author").val(book.author);
                $("#name").val(book.name);
                $("#genreId").val(book.genre.id);
            }
        }
    );
});
$( document ).ready(function() {
    $('#editForm').submit(function (event) {
        event.preventDefault();
        $.ajax(
            '/api/books',
            {
                type: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify({
                    id: $('#editId').val(),
                    author: $('#author').val(),
                    name: $('#name').val(),
                    genreId: $('#genreId').val()
                }),
                success: function (result) {
                    window.location.assign('/books/getAll');
                }
            }
        );
    });
    $('#deleteForm').submit(function (event) {
        event.preventDefault();
        $.ajax(
            '/api/books/' + $('#deleteId').val(),
            {
                type: 'DELETE',
                success: function (result) {
                    window.location.assign('/books/getAll');
                }
            }
        );
    });
});