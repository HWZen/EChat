// JavaScript Document
function Show(e1){
	document.getElementById(e1).style.display="block";
}
function Hide(e2){
	document.getElementById(e2).style.display="none";
}

function Active(e){
	$(e).addClass("active");
}
function UnActive(e){
	$(e).removeClass("active");
}
window.onscroll = function(){
	var t = document.documentElement.scrollTop || document.body.scrollTop;
	var top_div = document.getElementById( "nav-right" );

	if( t >= 50 ) {
		top_div.style.display = "block";
	} else {
		top_div.style.display = "none";
	}
	if(t>=50){
		Active("#dd");
		UnActive("#gn");
		UnActive("#jw");
		UnActive("#zj");
	}
	if(t>=getY(2)){
		Active("#gn");
		UnActive("#dd");
		UnActive("#jw");
		UnActive("#zj");
	}
	if(t>=getY(3)-100){
		Active("#jw");
		UnActive("#dd");
		UnActive("#gn");
		UnActive("#zj");
	}
	if(t>=getY(4)-200){
		Active("#zj");
		UnActive("#dd");
		UnActive("#gn");
		UnActive("#jw");
	}
}
function getY(e){
	var y1 = document.getElementById(e);
	y1 = y1.offsetTop;
	return y1;
}
