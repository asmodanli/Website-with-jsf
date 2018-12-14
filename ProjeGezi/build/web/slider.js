$(function(){
	var slider=$('.slider'),
	list=slider.find('ul.slider_liste'),
	length=list.find('li').length,
	width=slider.outerWidth(),
	totalWidth=width*length,
	index=0;
	
	next=$('a.sonraki',slider),
	prev=$('a.onceki',slider);
	
	list.find('li').width(width).end().width(totalWidth);
	
	$(window).resize(function(){
		width=slider.outerWidth();
		totalWidth=width*length;
		list.find('li').width(width).end().width(totalWidth).css('margin-left','-'+(index*width)+'px');
		
	});
	
	
	
	next.on('click', function(){
		if(index<length-1) index++;
		else index=0;
		list.animate(
		{
			marginLeft:'-' + (index*width)
		},500);
		return false;
	});
	prev.on('click', function(){
		if(index>0) --index;
		
		list.stop().animate(
		{
			marginLeft:'-' + (index*width)
		},500);
		return false;
	});
});