$( document ).ready(function() {
    $('#addForm').submit(function (event) {
        event.preventDefault();
        $.ajax(
            '/api/books',
            {
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({
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
});