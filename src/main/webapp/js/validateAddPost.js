function validateAddPost() {
    let result = '';
    if ($('#bodyColor').val() === '') {
        result = result + 'Введите цвет кузова' + '\n';
    }
    if ($('#mileAge').val() === '') {
        result = result + 'Введите пробег автомобиля' + '\n';
    }
    if ($('#ageYears').val() === '') {
        result = result + 'Введите возраст автомобиля' + '\n';
    }
    if ($('#description').val() === '') {
        result = result + 'Введите описание автомобиля' + '\n';
    }
    if ($('#photo').val() === '') {
        result = result + 'Выберите фото автомобиля' + '\n';
    }
    if (result != '') {
        alert(result);
        return false
    }
    return true;
}