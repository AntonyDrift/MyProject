$('#bg')
    .animate({opacity: 0}, 'slow', function() {
        $(this)
            .css({'background-image': 'url(1.jpg)'})
            .animate({opacity: 1});
    });
