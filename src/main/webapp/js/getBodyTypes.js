function getBodyTypes() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/job4j_cars/getBodyTypes.do',
        dataType: 'json'
    }).done(function (data) {
        let row = '';
        for (let bodyType of data) {
            row += '<option value=\'' + bodyType.id + '\'>' + bodyType.bodyType + '</option>';
        }
        $('#bodyType').html(row);
    }).fail(function (err) {
        console.log(err);
    });
}