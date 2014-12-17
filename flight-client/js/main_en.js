var context_path = '/pa165';
var server_url = 'http://localhost:8080' + context_path + '/rest/';

function loadEntities(type, success_func) {
    return $.ajax({
        url: server_url + type + '/',
        dataType: 'json',
        contentType: "application/json",
        success: success_func,
        error: handleAjaxError
    })
}

function removeEntity(type, id) {
    $.ajax({
        url: server_url + type + '/' + id,
        type: 'delete',
        contentType: "application/json",
        success: function () {
            $('.data-table').find('tr[data-id="' + id + '"]').remove();
        },
        error: handleAjaxError
    })
}

function handleAjaxError() {
    //todo display user friendly warning that server is down
}

jQuery.fn.cell = function (data) {
    return $(this[0]).append($('<td>').html(data));
};