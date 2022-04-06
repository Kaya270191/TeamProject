const whiteQuotes = 'url("image/quotes/quotes-w.png")';
const grayQuotes = 'url("image/quotes/quotes-g.png")';

const whiteLogo = 'url("/image/vsc-white.png")';
const blackLogo = 'url("/image/vsc-black.png")';

$(document).ready(function(){
    $(window).scroll(function(){
      var scroll = $(window).scrollTop();
      if (scroll > screen.height*0.7) {
        $(".quotes").css("background-image" , grayQuotes);
        $(".top-nav-logo").css("background-image" , blackLogo);
        $(".nav-container").css("background" , "linear-gradient( 45deg, #eee, #eee )");
        $(".nav-container").css("opacity" , "1");
      }
      else{
        $(".quotes").css("background-image" , whiteQuotes);
        $(".top-nav-logo").css("background-image" , whiteLogo);
        $(".nav-container").css("background" , "linear-gradient( 45deg, #85364A, #505050 )");
        $(".nav-container").css("opacity" , "0.8");
      }
    })
  })

var str = "only way to prove that you’re a good sport is to lose\"";
var index = 0;

setInterval(function () {
  if (index < str.length)
      $('#typing').append(str[index]);
  index++;
}, 60);