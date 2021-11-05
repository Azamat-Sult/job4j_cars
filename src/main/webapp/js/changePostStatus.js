function changePostStatus(id) {
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/job4j_cars/changePostStatus.do',
        dataType: 'html',
        data: {id: id},
    }).done(function (data) {
        $('#carBrands').trigger('change')
    }).fail(function (err) {
        console.log(err);
    });
}