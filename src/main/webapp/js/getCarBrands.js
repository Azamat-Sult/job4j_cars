function getCarBrands() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/job4j_cars/getCarBrands.do',
        dataType: 'json'
    }).done(function (data) {
        let row = '';
        for (let carBrand of data) {
            row += '<option value=\'' + carBrand.id + '\'>' + carBrand.carBrand + '</option>';
        }
        $('#carBrands').append(row).trigger('change');
    }).fail(function (err) {
        console.log(err);
    });
}