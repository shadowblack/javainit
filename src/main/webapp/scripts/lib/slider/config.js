$(document).ready(function(){
 
  $(".related-carousel").slick({
    arrows:false,
    dots: false,
    infinite: false,
    slidesToShow: 5,
    slidesToScroll: 1,
    touchThreshold: 10,
    swipeToSlide: true,
    mobileFirst: true,
    adaptiveHeight: true,
    lazyLoad: 'ondemand',
    responsive: [
      {
        breakpoint: 567,//min-width
        settings: {
          slidesToShow: 8
        }
      },
      {
        breakpoint: 667,//min-width
        settings: {
          slidesToShow: 10
        }
      }
    ]
  });

  $('#tab-nav-1').slick({
    slidesToShow: 3,
    slidesToScroll: 1,
    focusOnSelect: true,
    arrows: false,
    centerMode:true,
    infinite: true,
    initialSlide: 1,
    useTransform:true,
    draggable:false,
    centerPadding:0,
    asNavFor: '#tab-content-1'
  });
  $('#tab-content-1').slick({
    slidesToShow: 1,
    slidesToScroll: 1,
    infinite: true,
    arrows: false,
    adaptiveHeight:true,
    initialSlide: 1,
    useTransform:true,
    asNavFor: '#tab-nav-1'
  });
  $('#tab-content-1').on('afterChange', function(event, slick, currentSlide, nextSlide){
     $("html, body").animate({scrollTop: 0}, 300);
  });

  $('#tab-nav-2').slick({
    slidesToShow: 3,
    slidesToScroll: 1,
    focusOnSelect: true,
    arrows: false,
    centerMode:false,
    infinite: false,
    initialSlide: 0,
    useTransform:true,
    draggable:false,
    centerPadding:0,
    asNavFor: '#tab-content-2'
  });
  $('#tab-content-2').slick({
    slidesToShow: 1,
    slidesToScroll: 1,
    infinite: false,
    arrows: false,
    adaptiveHeight:true,
    initialSlide: 0,
    useTransform:true,
    asNavFor: '#tab-nav-2'
  });
  $('#tab-content-2').on('afterChange', function(event, slick, currentSlide, nextSlide){
     $("html, body").animate({scrollTop: 0}, 300);
  });

  $('.slider').slick({
    dots: true,
    arrows:false,
    speed: 300,
    autoplay: true,
    autoplaySpeed: 3000
  });

  $('.wallpapers-carousel').slick({
    infinite: true,
    dots: false,
    touchThreshold: 50,
    swipeToSlide: true,
    slidesToShow: 3,
    slidesToScroll: 1,
    lazyLoad: 'ondemand'
  });

  $('.video-slider').slick({
    slidesToShow: 1,
    slidesToScroll: 1,
    adaptiveHeight:true,
    // fade: true,
    asNavFor: '.video-slider-thumbnails'
  });
  $('.video-slider-thumbnails').slick({
    slidesToShow: 3,
    slidesToScroll: 1,
    asNavFor: '.video-slider',
    centerMode: false,
    focusOnSelect: true
  });

  $('.ads-carousel').slick({
    infinite: true,
    arrows:true,
    dots: false,
    touchThreshold: 50,
    swipeToSlide: true,
    slidesToShow: 3,
    slidesToScroll: 1,
    autoplay: true,
    autoplaySpeed: 2000
  });

  $(".game-carousel").slick({
    arrows:false,
    dots: false,
    infinite: true,
    slidesToShow: 3,
    touchThreshold: 10,
    slidesToScroll: 1,
    swipeToSlide: true,
    mobileFirst: true,
    adaptiveHeight: true,
    lazyLoad: 'ondemand',
    responsive: [
      {
        breakpoint: 382,//min-width
        settings: {
          slidesToShow: 5
        }
      },
      {
        breakpoint: 667,//min-width
        settings: {
          slidesToShow: 6
        }
      },
      {
        breakpoint: 760,//min-width
        settings: {
          slidesToShow: 6
        }
      },
      {
          breakpoint: 890,//min-width
          settings: {
              slidesToShow: 8
          }
      },
      {
          breakpoint: 1000,//min-width
          settings: {
              slidesToShow: 10
          }
      }
    ]
  });

  $(".video-carousel").slick({
    arrows:true,
    dots: false,
    infinite: true,
    slidesToShow: 3,
    touchThreshold: 10,
    slidesToScroll: 1,
    swipeToSlide: true,
    mobileFirst: true,
    adaptiveHeight: true,
    lazyLoad: 'ondemand'
  });

  $(".album-carousel").slick({
    arrows:true,
    dots: false,
    infinite: true,
    slidesToShow: 3,
    touchThreshold: 10,
    slidesToScroll: 1,
    swipeToSlide: true,
    mobileFirst: true,
    adaptiveHeight: true,
    lazyLoad: 'ondemand'
  });

  $('.game-slider').slick({
    arrows: false,
    slidesToShow: 1,
    adaptiveHeight:true,
    draggable:false,
    fade: true,
    asNavFor: '.game-slider-thumbnails'
  });
  $('.game-slider-thumbnails').slick({
    arrows:false,
    slidesToShow: 4,
    slidesToScroll: 1,
    touchThreshold: 10,
    swipeToSlide: true,
    centerMode: false,
    centerPadding:0,
    asNavFor: '.game-slider',
    focusOnSelect: true
  });

});