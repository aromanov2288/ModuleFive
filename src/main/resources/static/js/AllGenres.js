$( document ).ready(function() {
    $(function () {
        $.get('/api/genres').done(function (genres) {
            genres.forEach(function (genre) {
                $('tbody').append(`
                    <tr>
                        <td>${genre.id}</td>
                        <td>${genre.name}</td>
                        <td>
                            <a href="/genres/edit?id=${genre.id}">Редактировать</a>
                        </td>
                    </tr>
                `)
            });
        })
    });
});