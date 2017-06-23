$(document).ready(function(){

	$('.post-text').expander({
		slicePoint: 300, // cantidad de caracteres a limitar
		expandEffect: 'fadeIn',
		collapseEffect: 'fadeOut',
		expandText: 'Ver más',
		expandPrefix: '&nbsp;',
		collapseTimer: 0, // tiempo de espera para colapsar el contenido. 0 para no cerrar.
		userCollapseText: 'Ver menos'
	});

});