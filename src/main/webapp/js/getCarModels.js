function getCarModels(selectObject) {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/job4j_cars/getCarModels.do',
        dataType: 'json',
        data: {id: selectObject.value}
    }).done(function (data) {
        let row = '';
        for (let carModel of data) {
            row += '<option value=\'' + carModel.id + '\'>' + carModel.carModel + '</option>';
        }
        $('#carModels').html(row);
    }).fail(function (err) {
        console.log(err);
    });
}