var sh;
marqueesWidth=610;
preLeft=0; currentLeft=0; stopscroll=false; getlimit=0;preTop=0; currentTop=0;

function scrollLeft()
{
	if(stopscroll==true) return;
	preLeft=marquees.scrollLeft;
	marquees.scrollLeft+=2;
	if(preLeft==marquees.scrollLeft)
	{
		//marquees.scrollLeft=templayer.offsetWidth-marqueesWidth+1;
	}
}

function scrollRight()
{
	if(stopscroll==true) return;

	preLeft=marquees.scrollLeft;
	marquees.scrollLeft-=2;
	if(preLeft==marquees.scrollLeft)
	{
		if(!getlimit)
		{
			marquees.scrollLeft=templayer.offsetWidth*2;
			getlimit=marquees.scrollLeft;
		}
		marquees.scrollLeft-=1;
	}
}

function Left()
{
	stopscroll = false;
	sh = setInterval("scrollLeft()",20);
}

function Right()
{
	stopscroll = false;
	sh = setInterval("scrollRight()",20);
}

function StopScroll()
{
	stopscroll = true;
	clearInterval( sh );
}



function SelectType(value)
{
	document.all.sendForm.page.value = 1;
	document.all.sendForm.type.value = value;

	document.all.sendForm.submit();
}

function init()
{
	with(marquees)
	{
		style.height=0;
		style.width=marqueesWidth;
		style.overflowX="hidden";
		style.overflowY="visible";
		style.align = "center";
		noWrap=true;
	}
}


//-->
