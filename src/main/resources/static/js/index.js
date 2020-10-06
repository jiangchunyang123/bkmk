$(document).ready(function () {
    $('.bookList').on('click', function () {
        // console.log('hello world!');
        $('#ctn').load("/bookList.html");
    });
    $('.schList').on('click', function () {
        // console.log('hello world!');
        $('#ctn').load("/schList.html");
    });
    $('#ctn').load("/bookList.html");
});