package com.planet_ink.coffee_mud.Races;

import com.planet_ink.coffee_mud.interfaces.*;
import com.planet_ink.coffee_mud.common.*;
import com.planet_ink.coffee_mud.utils.*;
import java.util.*;

public class Shambler extends StdRace
{
	public String ID(){	return "Shambler"; }
	public String name(){ return "Shambler"; }
	public int shortestMale(){return 34;}
	public int shortestFemale(){return 30;}
	public int heightVariance(){return 12;}
	public int lightestWeight(){return 140;}
	public int weightVariance(){return 30;}
	public long forbiddenWornBits(){return Integer.MAX_VALUE-Item.HELD;}
	public String racialCategory(){return "Vegetation";}
	public boolean uncharmable(){return true;}

	//                                an ey ea he ne ar ha to le fo no gi mo wa ta wi
	private static final int[] parts={0 ,1 ,0 ,1 ,0 ,2 ,2 ,1 ,2 ,2 ,0 ,0 ,1 ,0 ,1 ,0 };
	public int[] bodyMask(){return parts;}

	protected static Vector resources=new Vector();
	public boolean playerSelectable(){return false;}

	public void affectEnvStats(Environmental affected, EnvStats affectableStats)
	{
		affectableStats.setDisposition(affectableStats.disposition()|EnvStats.IS_GOLEM);
		affectableStats.setAttackAdjustment(affectableStats.attackAdjustment()+(affected.envStats().level()));
		affectableStats.setDamage(affectableStats.damage()+(affected.envStats().level()/4));
	}
	public void affectCharState(MOB affectedMOB, CharState affectableState)
	{
		affectableState.setHunger(999999);
		affectedMOB.curState().setHunger(affectableState.getHunger());
	}
	public void affectCharStats(MOB affectedMOB, CharStats affectableStats)
	{
		affectableStats.setStat(CharStats.GENDER,(int)'N');
		affectableStats.setStat(CharStats.SAVE_POISON,affectableStats.getStat(CharStats.SAVE_POISON)+100);
		affectableStats.setStat(CharStats.SAVE_MIND,affectableStats.getStat(CharStats.SAVE_MIND)+100);
		affectableStats.setStat(CharStats.SAVE_GAS,affectableStats.getStat(CharStats.SAVE_GAS)+100);
		affectableStats.setStat(CharStats.SAVE_PARALYSIS,affectableStats.getStat(CharStats.SAVE_PARALYSIS)+100);
		affectableStats.setStat(CharStats.SAVE_UNDEAD,affectableStats.getStat(CharStats.SAVE_UNDEAD)+100);
		affectableStats.setStat(CharStats.SAVE_DISEASE,affectableStats.getStat(CharStats.SAVE_DISEASE)+100);
	}
	public String arriveStr()
	{
		return "shambles in";
	}
	public String leaveStr()
	{
		return "shambles";
	}
	public Weapon myNaturalWeapon()
	{
		if(naturalWeapon==null)
		{
			naturalWeapon=CMClass.getWeapon("StdWeapon");
			naturalWeapon.setName("a horrible limb");
			naturalWeapon.setRanges(0,1);
			naturalWeapon.setWeaponType(Weapon.TYPE_BASHING);
		}
		return naturalWeapon;
	}

	public String healthText(MOB mob)
	{
		double pct=(Util.div(mob.curState().getHitPoints(),mob.maxState().getHitPoints()));

		if(pct<.10)
			return "^r" + mob.name() + "^r is near destruction!^N";
		else
		if(pct<.20)
			return "^r" + mob.name() + "^r is massively shredded and damaged.^N";
		else
		if(pct<.30)
			return "^r" + mob.name() + "^r is extremeley shredded and damaged.^N";
		else
		if(pct<.40)
			return "^y" + mob.name() + "^y is very shredded and damaged.^N";
		else
		if(pct<.50)
			return "^y" + mob.name() + "^y is shredded and damaged.^N";
		else
		if(pct<.60)
			return "^p" + mob.name() + "^p is shredded and slightly damaged.^N";
		else
		if(pct<.70)
			return "^p" + mob.name() + "^p has lost numerous strands.^N";
		else
		if(pct<.80)
			return "^g" + mob.name() + "^g has lost some strands.^N";
		else
		if(pct<.90)
			return "^g" + mob.name() + "^g has lost a few strands.^N";
		else
		if(pct<.99)
			return "^g" + mob.name() + "^g is no longer in perfect condition.^N";
		else
			return "^c" + mob.name() + "^c is in perfect condition.^N";
	}
	public Vector myResources()
	{
		synchronized(resources)
		{
			if(resources.size()==0)
			{
				for(int i=0;i<3;i++)
				resources.addElement(makeResource
					("a pile of vegetation",EnvResource.RESOURCE_VINE));
			}
		}
		return resources;
	}
}
