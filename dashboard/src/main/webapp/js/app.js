// In case of static HTML, serve static info:
var SUITE_VERSION = 4.1;
var RELEASE_DATE = "June 19, 2014";

function onLoad() {
  var active_tab = $('#home');
  var previous_link;

  // set up link navigation
  function setUpTabs (tab_button) {
    $(tab_button).click(function(e) {
      e.preventDefault();
      active_tab.hide();

      switch (tab_button) {
        case '#gslink':
          active_tab = $('#gettingstarted');
          break;
        case '.homelink':
          active_tab = $('#home');
          break;
        case '.aboutlink':
          active_tab = $('#about');
          break;
        default:
          active_tab = $('#home');
          break;
      }
      active_tab.show();
    });

  }

  setUpTabs('#gslink');
  setUpTabs('.homelink');
  setUpTabs('.aboutlink');

  $('.nav a').click(function(e) {
    if ($(this).attr("id") !== '_documentationlink') {
      e.preventDefault();
      $(this).tab('show');
    }
  });


  // set up getting started stepchoose-detail-inner show/hide
  function setUpStepDetails () {
    $(".data-link").click (function() {
      var target = $(this).attr('data-target');
      var dataclass = $(this).attr('dataclass');
      // hide all with that dataclass in title attribute
      var l = $('.stepchoose-detail-inner[dtitle="' + dataclass + '"]').hide();
      $('.stepchoose-detail[dtitle="' + dataclass + '"]').show();

      // show selected
      $(target).show();
    });
  }

  setUpStepDetails();

  // toggle accordion title active state
  $('#accordion h3.acc-toggle').click(function (e){
    $('#accordion h3.acc-toggle').removeClass("active");
    $(this).toggleClass("active");
  });

  // scroll to top of open accordion if in responsive view
  $(".panel-collapse").on("shown.bs.collapse", function () {
    if ($(window).width() <= 768) {
      var selected = $(this);

      $('html, body').animate({
          scrollTop: selected.offset().top - 70
      }, 500);
    }
  });

  // Add version info to all version spans
  var proj_version = $('.version').html();
  proj_version = SUITE_VERSION;
  $('.version').html(proj_version);

  // Add version to all docs links
/*
  var docs_version = "docs/" + proj_version + "/";
  $('.docs').each(function( index ) {
      var component = $(this).attr("component");
      var path = this.href.split("#")[1];
      this.href = docs_version + path;
       this.target = '_blank';
    });
*/

  // Remove non-static info
  if ($('.commit').html() == " (${git.commit.id.abbrev})") {
    $('.commit').hide();
  }
  if ($('.date').html() == " built on ${build.prettydate}") {
    $('.date').hide();
  }
  $('.release_date').html(RELEASE_DATE);

  // Initialize popovers
  $("#data-tip").popover();

}
