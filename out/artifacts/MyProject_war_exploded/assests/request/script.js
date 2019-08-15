var slideIndexCar = 1;
showSlidesCar(slideIndexCar);

function plusSlidesCar(n) {
    showSlidesCar(slideIndexCar += n);
}

function showSlidesCar(n) {
    var i;
    var slides = document.getElementsByClassName("mySlides-car");
    if (n > slides.length) {slideIndexCar = 1}
    if (n < 1) {slideIndexCar = slides.length}
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    slides[slideIndexCar-1].style.display = "block";
}


var slideIndexTrack = 1;
showSlidesTrack(slideIndexTrack);

function plusSlidesTrack(n) {
    showSlidesTrack(slideIndexTrack += n);
}

function showSlidesTrack(n) {
    var i;
    var slides = document.getElementsByClassName("mySlides-track");
    if (n > slides.length) {slideIndexTrack = 1}
    if (n < 1) {slideIndexTrack = slides.length}
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    slides[slideIndexTrack-1].style.display = "block";
}
