package com.planet_ink.coffee_mud.Races;

import com.planet_ink.coffee_mud.interfaces.*;
import com.planet_ink.coffee_mud.common.*;
import com.planet_ink.coffee_mud.utils.*;
import java.util.*;

public class Unique extends StdRace
{
	public Unique()
	{
		super();
		myID=this.getClass().getName().substring(this.getClass().getName().lastIndexOf('.')+1);
		name=myID;
		// inches
		shortestMale=64;
		shortestFemale=60;
		heightVariance=12;
		// pounds
		lightestWeight=100;
		weightVariance=100;
		forbiddenWornBits=0;
	}
	public boolean playerSelectable(){return false;}

	public String healthText(MOB mob)
	{
		double pct=(Util.div(mob.curState().getHitPoints(),mob.maxState().getHitPoints()));

		if(pct<.10)
			return "^r" + mob.name() + "^r is pulsating in an unstable rage!^N";
		else
		if(pct<.20)
			return "^r" + mob.name() + "^r is massively and amazingly angry.^N";
		else
		if(pct<.30)
			return "^r" + mob.name() + "^r is very angry.^N";
		else
		if(pct<.40)
			return "^y" + mob.name() + "^y is somewhat angry.^N";
		else
		if(pct<.50)
			return "^y" + mob.name() + "^y is very irritated.^N";
		else
		if(pct<.60)
			return "^p" + mob.name() + "^p is starting to show irritation.^N";
		else
		if(pct<.70)
			return "^p" + mob.name() + "^p is definitely serious and concerned.^N";
		else
		if(pct<.80)
			return "^g" + mob.name() + "^g is growing serious and concerned.^N";
		else
		if(pct<.90)
			return "^g" + mob.name() + "^g is definitely unamused and is starting to notice.^N";
		else
		if(pct<.99)
			return "^g" + mob.name() + "^g is no longer amused, though still unconcerned.^N";
		else
			return "^c" + mob.name() + "^c is in perfect condition.^N";
	}
}
