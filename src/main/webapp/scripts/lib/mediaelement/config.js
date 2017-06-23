$(document).ready(function() {
  // $('#audio-player').mediaelementplayer({
  //  alwaysShowControls: true,
  //  features: ['playpause','volume','progress'],
  //  audioVolume: 'horizontal',
  //  audioWidth: 364,
  //  audioHeight: 70
  // });

  $('.audio-player-big audio').mediaelementplayer({
   alwaysShowControls: true,
   features: ['playpause'],
   audioVolume: 'horizontal',
   audioWidth: 90,
   audioHeight: 90
  });

  $('.audio-player-min audio').mediaelementplayer({
   alwaysShowControls: true,
   features: ['playpause'],
   audioVolume: 'horizontal',
   audioWidth: '100%',
   audioHeight: 50
  });

 });