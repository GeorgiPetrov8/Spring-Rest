$('#loadBooks').click(() => {
    $('.book-container').empty();

    fetch("http://localhost:8000/books")
        .then((response) => response.json())
        .then((json) => json.forEach((book, idx) => {
            console.log(book.title);

            let tableRow = '<tr>' +
                '<td>' + book.id + '</td>' +
                '<td>' + book.title + '</td>' +
                '<td>' + book.author + '</td>' +
                '<td><button type="submit" class="book-edit-btn" data-book-id="' + book.id + '">Edit</button></td>' +
                '<td><button type="submit" class="book-delete-btn" data-book-id="' + book.id + '">Delete</button></td>'
                '</tr>'

            $('.book-container').append(tableRow);
        }))
});

$('.book-delete-btn').click(() => {

    fetch("http://localhost:8000/books/" + book.id, {
        method: 'DELETE',
    })
        .then((response) => response.text())
        .then((response) => console.log(response));
});