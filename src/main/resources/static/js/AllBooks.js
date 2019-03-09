$( document ).ready(function() {
    $(function () {
        $.get('/api/books').done(function (books) {
            books.forEach(function (book) {
                $('tbody').append(`
                    <tr>
                        <td>${book.id}</td>
                        <td>${book.author}</td>
                        <td>${book.name}</td>
                        <td>${book.genre.name}</td>
                        <td>
                            <a href="/books/edit?id=${book.id}">Редактировать</a>
                        </td>
                    </tr>
                `)
            });
        })
    });
});