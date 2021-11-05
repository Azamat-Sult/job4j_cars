function getPosts(carBrandIdFilter) {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/job4j_cars/getPosts.do',
        dataType: 'json',
        data: {carBrandIdFilter: carBrandIdFilter, showTodayPosts: $('#showTodayPosts').is(':checked')}
    }).done(function (data) {
        let row = '';
        for (let post of data) {
            row += '<tr>';
            row += '<td>';
            row += '<img src="download?name=' + post.photo + '" width="100px" height="75px"/>';
            row += '</td>';
            row += '<td>' + post.carBrand + '</td>';
            row += '<td>' + post.carModel + '</td>';
            row += '<td>' + post.bodyType + '</td>';
            row += '<td>' + post.bodyColor + '</td>';
            row += '<td>' + post.mileAge + '</td>';
            row += '<td>' + post.ageYears + '</td>';
            row += '<td>' + post.description + '</td>';
            row += '<td>' + post.created + '</td>';
            row += '<td>' + post.phone + '</td>';
            if (post.showSoldButton) {
                row += '<td>' + '<button type="button" class="btn btn-danger btn-sm" onclick="changePostStatus(' + post.id + ')">Снять с продажи</button>' + '</td>';
            }
            row += '</tr>';
        }
        $('#posts').html(row);
    }).fail(function (err) {
        console.log(err);
    });
}